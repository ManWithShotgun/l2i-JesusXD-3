package ru.catssoftware.gameserver.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;


import org.apache.log4j.Logger;


import ru.catssoftware.Config;
import ru.catssoftware.L2DatabaseFactory;
import ru.catssoftware.Message;
import ru.catssoftware.gameserver.datatables.CharNameTable;
import ru.catssoftware.gameserver.datatables.NpcTable;
import ru.catssoftware.gameserver.datatables.SpawnTable;
import ru.catssoftware.gameserver.model.L2ItemInstance;
import ru.catssoftware.gameserver.model.L2Spawn;
import ru.catssoftware.gameserver.model.L2World;
import ru.catssoftware.gameserver.model.actor.instance.L2PcInstance;
import ru.catssoftware.gameserver.network.SystemMessageId;
import ru.catssoftware.gameserver.network.serverpackets.InventoryUpdate;
import ru.catssoftware.gameserver.network.serverpackets.PlaySound;
import ru.catssoftware.gameserver.network.serverpackets.SystemMessage;
import ru.catssoftware.gameserver.network.serverpackets.UserInfo;
import ru.catssoftware.gameserver.templates.chars.L2NpcTemplate;
import ru.catssoftware.gameserver.templates.item.L2Item;

/**
 * @author m095
 * @version 2.0
 */

public class PcAction
{
	private static Logger				_log				= Logger.getLogger(PcAction.class.getName());

	public static void addPremiumServices(L2PcInstance character, int days, String AccName)
	{
		addPremiumServices(character,AccName,days);
	}

	public static void addPremiumServices(L2PcInstance player, String AccName, int days)
	{
		if (player == null)
			return;

		Calendar finishtime = Calendar.getInstance();
		finishtime.setTimeInMillis(System.currentTimeMillis());
		finishtime.set(Calendar.SECOND, 0);
		finishtime.add(Calendar.DAY_OF_MONTH, days);
		if(!player.getAccountName().equalsIgnoreCase(AccName)) {
			player = null;
			for(L2PcInstance pc : L2World.getInstance().getAllPlayers())
				if(pc.getAccountName().equalsIgnoreCase(AccName)) {
					player = pc;
					break;
				}
			if(player==null || player.getClient()==null) {
				Connection con  = null;
				try {
					con = L2DatabaseFactory.getInstance().getConnection();
					PreparedStatement stm = con.prepareStatement("REPLACE account_data VALUES (?,'premium',?)");
					stm.setString(1, AccName.toLowerCase());
					stm.setString(2, String.valueOf(finishtime.getTimeInMillis()));
					stm.execute();
					stm.close();
					con.close();
				} catch(SQLException e) {} 
				return;
			}
		}
		player.setPremiumService(finishtime.getTimeInMillis());
		player.sendMessage(Message.getMessage(player, Message.MessageId.MSG_PREMIUM_ON));
		player.getClient().storeData();
		
	}

	public static void admGiveHero(L2PcInstance player, boolean delete)
	{
		if (player == null)
			return;

		Connection con = null;
		try
		{
			Calendar finishtime = Calendar.getInstance();
			finishtime.setTimeInMillis(System.currentTimeMillis());
			finishtime.set(Calendar.SECOND, 0);
			finishtime.add(Calendar.MONTH, 4);
			
			con = L2DatabaseFactory.getInstance().getConnection(con);
			PreparedStatement statement = con.prepareStatement("UPDATE character_herolist SET enddate=? WHERE charId=?");
			statement.setLong(1, delete ? 0 : finishtime.getTimeInMillis());
			statement.setInt(2, player.getObjectId());
			statement.execute();
			statement.close();
		}
		catch (Exception e)
		{
			_log.warn(e.getMessage(), e);
		}
		finally
		{
			try
			{
				if (con != null)
					con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void addHeroStatus(L2PcInstance player, int days)
	{
		if (player == null)
			return;

		Connection con = null;
		try
		{
			
			Calendar finishtime = Calendar.getInstance();
			finishtime.setTimeInMillis(System.currentTimeMillis());
			finishtime.set(Calendar.SECOND, 0);
			finishtime.add(Calendar.DAY_OF_MONTH, days);
			
			con = L2DatabaseFactory.getInstance().getConnection(con);
			PreparedStatement statement = con.prepareStatement("UPDATE character_herolist SET enddate=? WHERE charId=?");
			statement.setLong(1, finishtime.getTimeInMillis());
			statement.setInt(2, player.getObjectId());
			statement.execute();
			statement.close();
			player.setHero(true);
			player.broadcastUserInfo();
			player.sendMessage(Message.getMessage(player, Message.MessageId.MSG_YOU_ARE_HERO_NOW));
		}
		catch (SQLException e)
		{
			_log.warn("PremiumService:  Could not increase data");
		}
		finally
		{
			try
			{
				if (con != null)
					con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void storeCharSex(L2PcInstance player, int mode)
	{
		L2PcInstance character = player;

		if (character == null)
			return;

		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			PreparedStatement statement = con.prepareStatement("UPDATE characters SET sex=? WHERE charId=?");
			statement.setInt(1, character.getAppearance().getSex() ? 1 : 0);
			statement.setInt(2, character.getObjectId());
			statement.execute();
			statement.close();
			if (mode == 0)
				character.sendMessage(Message.getMessage(character, Message.MessageId.MSG_SEX_CHANGE));
		}
		catch (SQLException e)
		{
			_log.warn("StoreSex:  Could not save data");
		}
		finally
		{
			try
			{
				if (con != null)
					con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void giveItems(L2PcInstance player, int itemId, int count)
	{
		L2PcInstance character = player;
		if (character == null)
			return;

		giveItems(character, itemId, count, 0);
	}

	public static void giveItems(L2PcInstance player, int itemId, int count, int enchantlevel)
	{
		L2PcInstance character = player;

		if (character == null)
			return;

		if (count <= 0)
			return;

		L2ItemInstance item = character.getInventory().addItem("PcAction", itemId, count, character, character.getTarget());

		if (item == null)
			return;
		if (enchantlevel > 0)
			item.setEnchantLevel(enchantlevel);
		if (itemId == 57)
		{
			SystemMessage msg = new SystemMessage(SystemMessageId.EARNED_S1_ADENA);
			msg.addNumber(count);
			character.sendPacket(msg);
		}
		else
		{
			if (count > 1)
			{
				SystemMessage msg = new SystemMessage(SystemMessageId.EARNED_S2_S1_S);
				msg.addItemName(item);
				msg.addNumber(count);
				character.sendPacket(msg);
			}
			else
			{
				SystemMessage msg = new SystemMessage(SystemMessageId.EARNED_S1);
				msg.addItemName(item);
				character.sendPacket(msg);
			}
		}
		character.getInventory().updateInventory(item);
	}

	public static void playSound(L2PcInstance player, String sound)
	{
		L2PcInstance character = player;

		if (character == null)
			return;

		character.sendPacket(new PlaySound(0, sound));
	}

	public static void botMessage(L2PcInstance activeChar, L2PcInstance player)
	{
		L2PcInstance character = activeChar;

		if (character == null)
			return;
		if (player == null)
			return;

		Connection con = null;
		PreparedStatement statement = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			statement = con.prepareStatement("INSERT INTO character_reports(char_name, bot_name, date) VALUES(?,?,now())");
			statement.setString(1, character.getName());
			statement.setString(2, player.getName());
			statement.executeUpdate();
		}
		catch (Exception e)
		{
			_log.fatal("Could not write botReport:", e);
		}
		finally
		{
			try
			{
				if (con != null)
					con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void changeTitle(L2PcInstance activeChar, String _title, String oldTitle)
	{
		L2PcInstance character = activeChar;

		if (character == null)
			return;

		if (!Config.TITLE_PATTERN.matcher(_title).matches())
		{
			character.sendMessage(Message.getMessage(character, Message.MessageId.MSG_WRONG_TITLE));
			return;
		}
		character.setTitle(_title);
		character.sendPacket(SystemMessageId.TITLE_CHANGED);
		character.broadcastTitleInfo();
	}

	public static boolean changeName(L2PcInstance activeChar, String _name, String oldName)
	{
		L2PcInstance character = activeChar;

		if (character == null)
			return false;

		if (CharNameTable.getInstance().doesCharNameExist(_name.toLowerCase()))
		{
			character.sendMessage(Message.getMessage(character, Message.MessageId.MSG_NICK_EXIST));
			return false;
		}
		else if (!Config.CNAME_PATTERN.matcher(_name).matches())
		{
			character.sendMessage(Message.getMessage(character, Message.MessageId.MSG_WRONG_NICK));
			return false;
		}
		L2World.getInstance().removeFromAllPlayers(character);
		character.changeName(_name);
		character.store();
		L2World.getInstance().addToAllPlayers(character);
		character.broadcastUserInfo();
		if (character.isInParty())
			character.getParty().refreshPartyView();
		if (character.getClan() != null)
			character.getClan().broadcastClanStatus();

		character.sendMessage(Message.getMessage(character, Message.MessageId.MSG_NICK_CHANGED));
		return true;
	}

	
	public static void spawnManager()
	{
		L2NpcTemplate tmpl = NpcTable.getInstance().getTemplate(50014);
		try
		{
			L2Spawn _npcSpawn;
			_npcSpawn = new L2Spawn(tmpl);
			_npcSpawn.setLocx(147462);
			_npcSpawn.setLocy(22384);
			_npcSpawn.setLocz(-1941);
			_npcSpawn.setAmount(1);
			_npcSpawn.setHeading(0);
			_npcSpawn.setRespawnDelay(1);
			SpawnTable.getInstance().addNewSpawn(_npcSpawn, false);
			_npcSpawn.init();
			_npcSpawn.getLastSpawn().getStatus().setCurrentHp(999999999);
			_npcSpawn.getLastSpawn().setTitle("Wedding Manager");
			_npcSpawn.getLastSpawn().isAggressive();
			_npcSpawn.getLastSpawn().decayMe();
			_npcSpawn.getLastSpawn().spawnMe(_npcSpawn.getLastSpawn().getX(), _npcSpawn.getLastSpawn().getY(), _npcSpawn.getLastSpawn().getZ());
		}
		catch (Exception e)
		{
			_log.info("Wedding Instance [Can't spawn NPC]: exception: " + e.getMessage());
		}
	}

	public static synchronized void deleteHeroItems(L2PcInstance player)
	{
		L2ItemInstance[] items;
		InventoryUpdate iu;

		if (player == null)
			return;

		try
		{
			items = player.getInventory().unEquipItemInBodySlotAndRecord(L2Item.SLOT_LR_HAND);
			iu = new InventoryUpdate();
				for (L2ItemInstance item : items)
					iu.addModifiedItem(item);
			player.sendPacket(iu);

			items = player.getInventory().unEquipItemInBodySlotAndRecord(L2Item.SLOT_R_HAND);
			iu = new InventoryUpdate();
				for (L2ItemInstance item : items)
					iu.addModifiedItem(item);
			player.sendPacket(iu);

			items = player.getInventory().unEquipItemInBodySlotAndRecord(L2Item.SLOT_HAIR);
			iu = new InventoryUpdate();
				for (L2ItemInstance item : items)
					iu.addModifiedItem(item);
			player.sendPacket(iu);

			items = player.getInventory().unEquipItemInBodySlotAndRecord(L2Item.SLOT_FACE);
			iu = new InventoryUpdate();
				for (L2ItemInstance item : items)
					iu.addModifiedItem(item);
			player.sendPacket(iu);

			items = player.getInventory().unEquipItemInBodySlotAndRecord(L2Item.SLOT_HAIRALL);
			iu = new InventoryUpdate();
				for (L2ItemInstance item : items)
					iu.addModifiedItem(item);
			player.sendPacket(iu);

			for (L2ItemInstance item : player.getInventory().getAvailableItems(false))
			{
				if (item == null)
					continue;

				if (!item.isHeroItem())
					continue;

				player.destroyItem("HeroService", item, null, true);
				iu = new InventoryUpdate();
				iu.addRemovedItem(item);
				player.sendPacket(iu);
			}
			player.sendPacket(new UserInfo(player));
			player.broadcastUserInfo();
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
	}

	public static void clearRestartTask()
	{
		Connection con = null;
		PreparedStatement statement = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			// Clear game server restart task
			statement = con.prepareStatement("DELETE FROM global_tasks WHERE task=?");
			statement.setString(1, "restart");
			statement.executeUpdate();
			statement.close();
			// Clear login server restart task
			statement = con.prepareStatement("DELETE FROM global_tasks WHERE task=?");
			statement.setString(1, "restartLogin");
			statement.executeUpdate();
			statement.close();
		}
		catch (Exception e)
		{
			_log.fatal("Could not delete restart task:", e);
		}
		finally
		{
			try
			{
				if (con != null)
					con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
