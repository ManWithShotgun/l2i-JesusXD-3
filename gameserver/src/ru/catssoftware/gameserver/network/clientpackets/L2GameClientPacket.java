package ru.catssoftware.gameserver.network.clientpackets;

import java.nio.BufferUnderflowException;


import static ru.catssoftware.gameserver.network.serverpackets.ActionFailed.STATIC_PACKET;

import org.apache.log4j.Logger;



import ru.catssoftware.Config;
import ru.catssoftware.gameserver.mmocore.ReceivablePacket;
import ru.catssoftware.gameserver.model.actor.instance.L2PcInstance;
import ru.catssoftware.gameserver.network.Disconnection;
import ru.catssoftware.gameserver.network.IOFloodManager;
import ru.catssoftware.gameserver.network.InvalidPacketException;
import ru.catssoftware.gameserver.network.L2GameClient;
import ru.catssoftware.gameserver.network.SystemMessageId;
import ru.catssoftware.gameserver.network.IOFloodManager.ErrorMode;
import ru.catssoftware.gameserver.network.serverpackets.L2GameServerPacket;

public abstract class L2GameClientPacket extends ReceivablePacket<L2GameClient>
{
	protected static final Logger	_log	= Logger.getLogger(L2GameClientPacket.class);

	@Override
	protected final boolean read()
	{
		
		try
		{
			readImpl();
			getClient().can_runImpl = true;
			getClient().clearBufferErrors();
			return true;
		}
		catch (BufferUnderflowException e)
		{
			getClient().incBufferErrors();
			if(getClient().getBufferErrors()>3)
				new Disconnection(getClient()).close(false);
		}
		catch (RuntimeException e)
		{
			if(Config.DEBUG)
				IOFloodManager.getInstance().report(ErrorMode.FAILED_READING, getClient(), this, e);
		}
		return false;
	}

	protected abstract void readImpl();

	/**
	 * Защита от пакетчиков. Пакет, который требуется для обработки<br>
	 * @return as String - имя серверного пакета, который должен быть отослан
	 */
	public String []requiredPacket() {
		return null;
	}
	
	@Override
	public final void run()
	{
		try
		{
				runImpl();
		}
		catch (InvalidPacketException e)
		{
			IOFloodManager.getInstance().report(ErrorMode.FAILED_RUNNING, getClient(), this, e);
		}
		catch(NullPointerException npe) {
			
		}
		catch (Exception e)
		{
			_log.error("Receive "+getClass().getSimpleName()+" to "+getClient()+" failed");
			e.printStackTrace();
		}
	}

	protected abstract void runImpl() throws InvalidPacketException;

	protected final void sendPacket(L2GameServerPacket gsp)
	{
		getClient().sendPacket(gsp);
	}

	protected final void sendPacket(SystemMessageId sm)
	{
		getClient().sendPacket(sm.getSystemMessage());
	}

	protected final L2PcInstance getActiveChar()
	{
		return getClient().getActiveChar();
	}

	public String getType()
	{
		return getClass().getSimpleName();
	}

	protected int getMinimumLength()
	{
		return 0;
	}


	public static long limit(long min, long value, long max)
	{
		return Math.max(min, Math.min(value, max));
	}

	protected final void ActionFailed()
	{
		sendPacket(STATIC_PACKET);
	}
	
}
