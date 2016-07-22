package ru.jesus.login.loginserver.network.serverpackets;

import ru.jesus.login.loginserver.L2LoginClient;
import ru.jesus.login.loginserver.mmocore.SendablePacket;


/**
 *
 * @author  KenM
 */
public abstract class L2LoginServerPacket extends SendablePacket<L2LoginClient>
{
	/**
	* @see SendablePacket#getHeaderSize()
	*/
	@Override
	protected int getHeaderSize()
	{
		return 2;
	}

	/**
	* @see SendablePacket#writeHeader(int)
	*/
	@Override
	protected void writeHeader(int dataSize)
	{
		writeH(dataSize + this.getHeaderSize());
	}
}
