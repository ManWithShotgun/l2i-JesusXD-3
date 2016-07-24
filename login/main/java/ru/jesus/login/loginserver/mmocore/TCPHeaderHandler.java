package ru.jesus.login.loginserver.mmocore;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

/**
 * @version 2.0
 * @author JesusXD
 * */
public abstract class TCPHeaderHandler<T extends MMOConnection<T>> extends HeaderHandler<T, TCPHeaderHandler<T>> {
	public TCPHeaderHandler(TCPHeaderHandler<T> subHeaderHandler)
	{
		super(subHeaderHandler);
	}

	protected abstract HeaderInfo<T> handleHeader(SelectionKey key, ByteBuffer buf);
}