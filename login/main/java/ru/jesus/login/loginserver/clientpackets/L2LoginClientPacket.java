package ru.jesus.login.loginserver.clientpackets;


import org.apache.log4j.Logger;


import ru.jesus.login.loginserver.L2LoginClient;
import ru.jesus.login.loginserver.mmocore.ReceivablePacket;


public abstract class L2LoginClientPacket extends ReceivablePacket<L2LoginClient>
{
	private static Logger _log	= Logger.getLogger(L2LoginClientPacket.class.getName());

	@Override
	protected final boolean read()
	{
		try
		{
			return this.readImpl();
		}
		catch (Exception e)
		{
			_log.fatal("ERROR READING: " + this.getClass().getSimpleName(), e);
			return false;
		}
	}

	protected abstract boolean readImpl();
}
