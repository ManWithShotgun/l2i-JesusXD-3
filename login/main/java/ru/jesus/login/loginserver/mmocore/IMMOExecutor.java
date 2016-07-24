package ru.jesus.login.loginserver.mmocore;

/**
 * @version 2.0
 * @author JesusXD
 * */
public interface IMMOExecutor<T extends MMOConnection<T>>
{
	public void execute(ReceivablePacket<T> packet);
}