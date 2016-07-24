package ru.jesus.login.loginserver.mmocore;

import java.nio.channels.SelectionKey;

/**
 * @version 2.0
 * @author JesusXD
 * */
public interface IClientFactory<T extends MMOConnection<T>>
{
	public T create(SelectorThread<T> selectorThread, ISocket socket, SelectionKey key);
}