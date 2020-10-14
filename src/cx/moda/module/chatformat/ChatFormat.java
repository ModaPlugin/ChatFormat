package cx.moda.module.chatformat;

import cx.moda.moda.module.Module;
import cx.moda.moda.module.storage.NoStorageHandler;
import cx.moda.moda.placeholder.ModaPlaceholderAPI;
import cx.moda.moda.util.vault.VaultHandler;
import cx.moda.moda.util.vault.VaultUnavailableException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.derkades.derkutils.bukkit.Chat;
import xyz.derkades.derkutils.bukkit.PlaceholderUtil.Placeholder;

public class ChatFormat extends Module<NoStorageHandler> implements Listener {

	@Override
	public String getName() { return "ChatFormat"; }

	private VaultHandler vault;

	@EventHandler(priority = EventPriority.HIGH)
	public void onChat(final AsyncPlayerChatEvent event) {
		final Player player = event.getPlayer();

		final Placeholder vaultPrefix = new Placeholder("{PREFIX}", this.vault == null ? "&c[NoVault]" : this.vault.getChat().getPlayerPrefix(player));
		final Placeholder vaultSuffix = new Placeholder("{SUFFIX}", this.vault == null ? "&c[NoVault] " : this.vault.getChat().getPlayerSuffix(player));
		final Placeholder vaultGroup = new Placeholder("{GROUP}", this.vault == null ? "&c[NoVault]" : this.vault.getChat().getPrimaryGroup(player));

		final Placeholder message = new Placeholder("{MESSAGE}", event.getMessage());

		//TODO
		for (final Player recipient : event.getRecipients()) {
			recipient.spigot().sendMessage(
					ModaPlaceholderAPI.parsePlaceholders(player,
							Chat.toComponentWithPapiPlaceholders(getConfig(), "format", player,
									vaultPrefix, vaultSuffix, vaultGroup, message)));
		}
		event.getRecipients().clear();
	}

	@Override
	public void onEnable() {
		try {
			this.vault = new VaultHandler(this);
			this.vault.getChat();
		} catch (final VaultUnavailableException e) {
			getLogger().info("Vault not installed, vault Placeholders will not work.");
			this.vault = null;
		}
		registerListener(this);
		getLogger().info("ChatFormat module enabled.");
	}

	@Override
	public void onDisable() {
		getLogger().info("ChatFormat module disabled.");
	}
}
