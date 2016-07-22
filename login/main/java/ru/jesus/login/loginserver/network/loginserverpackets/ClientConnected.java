package ru.jesus.login.loginserver.network.loginserverpackets;

import java.io.IOException;

import ru.jesus.login.loginserver.network.serverpackets.ServerBasePacket;
import ru.jesus.login.loginserver.L2LoginClient;


public class ClientConnected extends ServerBasePacket {

	public ClientConnected(L2LoginClient cl) {
		writeC(0x05);
		writeS(cl.getIp());
	}
	@Override
	public byte[] getContent() throws IOException {
		return getBytes();
	}

}
