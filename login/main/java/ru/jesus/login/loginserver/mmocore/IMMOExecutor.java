package ru.jesus.login.loginserver.mmocore;

public interface IMMOExecutor<T extends MMOConnection<T>>
{
	public void execute(ReceivablePacket<T> packet);
}