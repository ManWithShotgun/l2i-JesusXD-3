package ru.jesus.login.loginserver.mmocore;

import java.nio.channels.SocketChannel;

/**
 * @version 2.0
 * @author JesusXD
 * */
public interface IAcceptFilter
{
	public boolean accept(SocketChannel sc);
}