package moda.plugin.module.chatmodule;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import moda.plugin.moda.modules.IMessage;
import moda.plugin.moda.modules.Module;
import moda.plugin.moda.utils.placeholders.ModaPlaceholderAPI;
import moda.plugin.moda.utils.storage.DatabaseStorageHandler;
import moda.plugin.moda.utils.storage.FileStorageHandler;
import moda.plugin.moda.utils.vault.VaultHandler;
import moda.plugin.moda.utils.vault.VaultNotAvailableException;
import moda.plugin.module.chatmodule.storage.ChatDatabaseStorageHandler;
import moda.plugin.module.chatmodule.storage.ChatFileStorageHandler;
import moda.plugin.module.chatmodule.storage.ChatStorageHandler;
import xyz.derkades.derkutils.bukkit.Chat;
import xyz.derkades.derkutils.bukkit.Colors;
import xyz.derkades.derkutils.bukkit.PlaceholderUtil.Placeholder;

public class ChatModule extends Module<ChatStorageHandler> {

	private VaultHandler vault;

	@Override
	public DatabaseStorageHandler getDatabaseStorageHandler() {
		return new ChatDatabaseStorageHandler(this);
	}

	@Override
	public FileStorageHandler getFileStorageHandler() {
		return new ChatFileStorageHandler(this);
	}

	@Override
	public IMessage[] getMessages() {
		return ChatMessage.values();
	}

	@Override
	public String getName() {
		return "Chat";
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onChat(final AsyncPlayerChatEvent event) {
		final Player player = event.getPlayer();

		if (player.hasPermission("moda.chat.use_colors")) {
			event.setMessage(Colors.parseColors(event.getMessage()));
		}

		final Placeholder message = new Placeholder("{MESSAGE}", event.getMessage());

		final Placeholder vaultPrefix = new Placeholder("{PREFIX}", this.vault == null ? "&c[NoVault]" : this.vault.getChat().getPlayerPrefix(player));
		final Placeholder vaultSuffix = new Placeholder("{SUFFIX}", this.vault == null ? "&c[NoVault] " : this.vault.getChat().getPlayerSuffix(player));
		final Placeholder vaultGroup = new Placeholder("{GROUP}", this.vault == null ? "&c[NoVault]" : this.vault.getChat().getPrimaryGroup(player));

		for (final Player recipient : event.getRecipients()) {
			recipient.spigot().sendMessage(
					ModaPlaceholderAPI.parsePlaceholders(player,
							Chat.toComponentWithPapiPlaceholders(this.config, "format", player,
									message, vaultPrefix, vaultSuffix, vaultGroup)));
		}
		event.getRecipients().clear();
	}

	@Override
	public void onEnable() {
		try {
			this.vault = new VaultHandler(this);
			this.vault.getChat();
		} catch (final VaultNotAvailableException e) {
			this.logger.info("Vault not installed, vault Placeholders will not work.");
			this.vault = null;
		}
	}

}
