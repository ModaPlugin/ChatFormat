package com.mineglade.moda.chatmodule.storage;

import java.sql.SQLException;

import com.mineglade.moda.modules.Module;
import com.mineglade.moda.utils.storage.DatabaseStorageHandler;

import xyz.derkades.derkutils.NoParameter;
import xyz.derkades.derkutils.bukkit.BukkitFuture;

public class ChatDatabaseStorageHandler extends DatabaseStorageHandler implements ChatStorageHandler {

	public ChatDatabaseStorageHandler(final Module<?> module) {
		super(module);
	}

	@Override
	public BukkitFuture<Boolean> getMuted() {
		return null;
	}

	@Override
	public BukkitFuture<NoParameter> muteChat() {
		return null;
	}

	@Override
	public void setup() throws SQLException {
		this.db.createTableIfNonexistent("chatMuted",
				"CREATE TABLE `" + this.db.getDatabase() + "`.`chatMuted` (`muted` BOOLEAN) ENGINE = InnoDB");
	}
}
