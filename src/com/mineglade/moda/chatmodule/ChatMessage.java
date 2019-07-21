package com.mineglade.moda.chatmodule;

import com.mineglade.moda.modules.IMessage;

public enum ChatMessage implements IMessage {

	// Mute Command
	ERRORS_UNKNOWN("errors.unknown", "&cthere was an error sending your chat message."),
	ERRORS_CHAT_MUTED("errors.chat.muted", "&cCouldn't send your message, the chat is muted."),

	;

	private String path;
	private String defaultMessage;

	ChatMessage(final String path, final String defaultMessage) {
		this.path = path;
		this.defaultMessage = defaultMessage;
	}

	@Override
	public String getPath() {
		return this.path;
	}

	@Override
	public String getDefault() {
		return this.defaultMessage;
	}
}
