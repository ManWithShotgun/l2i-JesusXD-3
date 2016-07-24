package ru.jesus.login.loginserver.mmocore;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
/**
 * @version 2.0
 * @author JesusXD
 * */
public abstract class UDPHeaderHandler<T extends MMOConnection<T>> extends HeaderHandler<T, UDPHeaderHandler<T>> {
	public UDPHeaderHandler(UDPHeaderHandler<T> subHeaderHandler)
	{
		super(subHeaderHandler);
	}

	protected abstract HeaderInfo<T> handleHeader(ByteBuffer buf);

	protected abstract void onUDPConnection(SelectorThread<T> selector, DatagramChannel dc, SocketAddress key, ByteBuffer buf);
}