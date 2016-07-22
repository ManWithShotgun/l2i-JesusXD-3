package ru.jesus.login.loginserver.network.gameserverpackets;

import ru.jesus.login.loginserver.clientpackets.ClientBasePacket;

public class ChangeIpAddres extends ClientBasePacket
{

	private String	_iphost;
	private String	_account;

	public ChangeIpAddres(byte[] decrypt)
	{
		super(decrypt);
		_iphost = readS();
		_account = readS();
	}

	public String getAccount()
	{
		return _account;
	}

	public String getIpHost()
	{
		return _iphost;
	}
}