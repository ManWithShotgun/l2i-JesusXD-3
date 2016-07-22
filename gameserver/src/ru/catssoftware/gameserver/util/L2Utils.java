package ru.catssoftware.gameserver.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ru.catssoftware.Config;
import ru.catssoftware.L2DatabaseFactory;
import ru.catssoftware.Message;
import ru.catssoftware.gameserver.model.L2World;
import ru.catssoftware.gameserver.model.actor.instance.L2PcInstance;
import ru.catssoftware.gameserver.model.zone.L2Zone;
import ru.catssoftware.gameserver.taskmanager.SQLQueue;
import ru.catssoftware.sql.SQLQuery;


 /**
  * Автор: Azagthtot
  * Данный класс содержит методы для дополнительных сервисов:
  * L2TopDaemon
  **/

public class L2Utils
{
	public static void addItem(String charName, int itemId, int count) {
		L2PcInstance result = L2World.getInstance().getPlayer(charName);
		if(result!=null)
			result.addItem("PcUtils", itemId, count, null, true);
		else {
		 SQLQueue.getInstance().add(new AddToOffline(charName,itemId,count));	
		}
	}
	private static class AddToOffline implements SQLQuery {

		private String _charName;
		private int [] _item;
		public AddToOffline(String charName, int itemid, int count) {
			_charName = charName;
			_item = new int[] { itemid,count };
		}
		@Override
		public void execute(Connection con) {
			try {
				PreparedStatement stm = con.prepareStatement("insert into character_items select charId,?,?,0 from characters where char_name=?");
				stm.setInt(1, _item[0]);
				stm.setInt(2, _item[1]);
				stm.setString(3, _charName);
				stm.execute();
				stm.close();
			} catch(SQLException e) {
				
			}
		}
		
	}
	
	public static String getHwId(String charName) {
		L2PcInstance pc = L2World.getInstance().getPlayer(charName);
		if(pc!=null)
			return pc.getHWid();
		try {
			Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement stm = con.prepareStatement("select hwid from characters where char_name like ?");
			stm.setString(1, charName);
			ResultSet r = stm.executeQuery();
			String hwid = null;
			if(r.next())
				hwid = r.getString(1);
			r.close();
			stm.close();
			con.close();
			return hwid;
		}
		catch(SQLException e)
		{
			return null;
		}
	}
	public static boolean charExists(String charName) {
		boolean result =  L2World.getInstance().getPlayer(charName) !=null;
		if (!result) try {
			Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement stm = con.prepareStatement("select charId from characters where char_name like ?");
			stm.setString(1, charName);
			ResultSet r = stm.executeQuery();
			if(r.next())
				result = true;
			r.close();
			stm.close();
			con.close();
		}
		catch(SQLException e)
		{
			result = false;
		}
		return result;
		
	}
	public static L2PcInstance loadPlayer(String charName)
	{
		L2PcInstance result = L2World.getInstance().getPlayer(charName);

		if (result == null)
		{
			try
			{
				Connection con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement stm = con.prepareStatement("select charId from characters where char_name like ?");
				stm.setString(1, charName);
				ResultSet r = stm.executeQuery();
				if(r.next())
					result = L2PcInstance.load(r.getInt(1));
				r.close();
				stm.close();
				con.close();
			}
			catch(SQLException e)
			{
				result = null;
			}
		}
		return result;
	} 
	public static boolean checkMagicCondition(L2PcInstance player)
	{
		boolean ok=true;
		if(player.getPrivateStoreType()!=L2PcInstance.STORE_PRIVATE_NONE && Config.BBS_RESTRICTIONS.contains("TRADE"))
			ok = false;
		if ((player._event!=null && player._event.isRunning()) && Config.BBS_RESTRICTIONS.contains("EVENT"))
			ok=false;
		if (player.isInJail() && Config.BBS_RESTRICTIONS.contains("JAIL"))
			ok=false;
		if (player.getOlympiadGameId() >= 0 && Config.BBS_RESTRICTIONS.contains("OLY"))
			ok=false;
		if (player.isInCombat() && Config.BBS_RESTRICTIONS.contains("COMBAT"))
			ok=false;
		if(player.getKarma()>0 && Config.BBS_RESTRICTIONS.contains("KARMA"))
			ok = false;
		if(player.getPvpFlag()>0 && Config.BBS_RESTRICTIONS.contains("PVP"))
			ok = false;
		if(player.isInsideZone(L2Zone.FLAG_SIEGE) && Config.BBS_RESTRICTIONS.contains("SIEGE"))
			ok = false;
		if(player.isInsideZone(L2Zone.FLAG_NOSUMMON) && Config.BBS_RESTRICTIONS.contains("RB"))
			ok = false;
		if(player.isInsideZone(L2Zone.FLAG_PVP)&& Config.BBS_RESTRICTIONS.contains("ARENA"))
			ok = false;
		if (!ok)
			player.sendMessage(Message.getMessage(player, Message.MessageId.MSG_NOT_ALLOWED_AT_THE_MOMENT));
		return ok;		
	}
	
}

