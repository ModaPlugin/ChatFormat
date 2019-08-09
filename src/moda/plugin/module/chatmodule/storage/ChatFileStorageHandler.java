package moda.plugin.module.chatmodule.storage;

import moda.plugin.moda.modules.Module;
import moda.plugin.moda.utils.storage.FileStorageHandler;
import moda.plugin.moda.utils.storage.ModuleStorageHandler;
import xyz.derkades.derkutils.NoParameter;
import xyz.derkades.derkutils.bukkit.BukkitFuture;

public class ChatFileStorageHandler extends FileStorageHandler implements ChatStorageHandler {

	public ChatFileStorageHandler(final Module<? extends ModuleStorageHandler> module) {
		super(module);
	}

	@Override
	public BukkitFuture<NoParameter> muteChat() {
		return null;
	}

	@Override
	public BukkitFuture<Boolean> getMuted() {
		return null;
	}

}
