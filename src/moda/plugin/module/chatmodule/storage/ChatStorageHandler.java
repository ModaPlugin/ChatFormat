package moda.plugin.module.chatmodule.storage;

import moda.plugin.moda.utils.storage.ModuleStorageHandler;
import xyz.derkades.derkutils.NoParameter;
import xyz.derkades.derkutils.bukkit.BukkitFuture;

public interface ChatStorageHandler extends ModuleStorageHandler {

	public BukkitFuture<Boolean> getMuted();

	public BukkitFuture<NoParameter> muteChat();

}
