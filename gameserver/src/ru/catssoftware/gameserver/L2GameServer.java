package ru.catssoftware.gameserver;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;


import com.lameguard.LameGuard;

import ru.catssoftware.Config;
import ru.catssoftware.Message;
import ru.catssoftware.L2DatabaseFactory;
import ru.catssoftware.extension.GameExtensionManager;
import ru.catssoftware.gameserver.Shutdown.ShutdownModeType;
import ru.catssoftware.gameserver.cache.*;
import ru.catssoftware.gameserver.communitybbs.CommunityBoard;
import ru.catssoftware.gameserver.datatables.AdditionalSkillTable;
import ru.catssoftware.gameserver.datatables.ArmorSetsTable;
import ru.catssoftware.gameserver.datatables.CharNameTable;
import ru.catssoftware.gameserver.datatables.CharTemplateTable;
import ru.catssoftware.gameserver.datatables.ClanTable;
import ru.catssoftware.gameserver.datatables.EventDroplist;
import ru.catssoftware.gameserver.datatables.FishTable;
import ru.catssoftware.gameserver.datatables.GmListTable;
import ru.catssoftware.gameserver.datatables.HelperBuffTable;
import ru.catssoftware.gameserver.datatables.HennaTable;
import ru.catssoftware.gameserver.datatables.HennaTreeTable;
import ru.catssoftware.gameserver.datatables.HeroSkillTable;
import ru.catssoftware.gameserver.datatables.ItemTable;
import ru.catssoftware.gameserver.datatables.JailSpawnTable;
import ru.catssoftware.gameserver.datatables.LevelUpData;
import ru.catssoftware.gameserver.datatables.NobleSkillTable;
import ru.catssoftware.gameserver.datatables.NpcTable;
import ru.catssoftware.gameserver.datatables.PetDataTable;
import ru.catssoftware.gameserver.datatables.PetSkillsTable;
import ru.catssoftware.gameserver.datatables.ResidentialSkillTable;
import ru.catssoftware.gameserver.datatables.ServerData;
import ru.catssoftware.gameserver.datatables.SkillSpellbookTable;
import ru.catssoftware.gameserver.datatables.SkillTable;
import ru.catssoftware.gameserver.datatables.SkillTreeTable;
import ru.catssoftware.gameserver.datatables.SpawnTable;
import ru.catssoftware.gameserver.datatables.TeleportLocationTable;
import ru.catssoftware.gameserver.datatables.TradeListTable;
import ru.catssoftware.gameserver.datatables.xml.AugmentationData;
import ru.catssoftware.gameserver.datatables.xml.DoorTable;
import ru.catssoftware.gameserver.datatables.xml.EnchantHPBonusData;
import ru.catssoftware.gameserver.datatables.xml.ExtractableItemsData;
import ru.catssoftware.gameserver.datatables.xml.NpcLikePcTemplates;
import ru.catssoftware.gameserver.datatables.xml.StaticObjects;
import ru.catssoftware.gameserver.datatables.xml.SummonItemsData;
import ru.catssoftware.gameserver.geodata.GeoData;
import ru.catssoftware.gameserver.geodata.pathfinding.PathFinding;
import ru.catssoftware.gameserver.gmaccess.gmCache;
import ru.catssoftware.gameserver.gmaccess.gmController;
import ru.catssoftware.gameserver.handler.ChatHandler;
import ru.catssoftware.gameserver.handler.ItemHandler;
import ru.catssoftware.gameserver.handler.SkillHandler;
import ru.catssoftware.gameserver.handler.UserCommandHandler;
import ru.catssoftware.gameserver.handler.VoicedCommandHandler;
import ru.catssoftware.gameserver.idfactory.IdFactory;
import ru.catssoftware.gameserver.instancemanager.AuctionManager;
import ru.catssoftware.gameserver.instancemanager.AutoSpawnManager;
import ru.catssoftware.gameserver.instancemanager.AwayManager;
import ru.catssoftware.gameserver.instancemanager.BlockListManager;
import ru.catssoftware.gameserver.instancemanager.BoatManager;
import ru.catssoftware.gameserver.instancemanager.CastleManager;
import ru.catssoftware.gameserver.instancemanager.CastleManorManager;
import ru.catssoftware.gameserver.instancemanager.ClanHallManager;
import ru.catssoftware.gameserver.instancemanager.CoupleManager;
import ru.catssoftware.gameserver.instancemanager.CrownManager;
import ru.catssoftware.gameserver.instancemanager.CursedWeaponsManager;
import ru.catssoftware.gameserver.instancemanager.DayNightSpawnManager;
import ru.catssoftware.gameserver.instancemanager.DimensionalRiftManager;
import ru.catssoftware.gameserver.instancemanager.EventsDropManager;
import ru.catssoftware.gameserver.instancemanager.FactionManager;
import ru.catssoftware.gameserver.instancemanager.FactionQuestManager;
import ru.catssoftware.gameserver.instancemanager.FortManager;
import ru.catssoftware.gameserver.instancemanager.FortSiegeManager;
import ru.catssoftware.gameserver.instancemanager.FourSepulchersManager;
import ru.catssoftware.gameserver.instancemanager.InstanceManager;
import ru.catssoftware.gameserver.instancemanager.ItemsOnGroundManager;
import ru.catssoftware.gameserver.instancemanager.MapRegionManager;
import ru.catssoftware.gameserver.instancemanager.MercTicketManager;
import ru.catssoftware.gameserver.instancemanager.PartyRoomManager;
import ru.catssoftware.gameserver.instancemanager.PetitionManager;
import ru.catssoftware.gameserver.instancemanager.QuestManager;
import ru.catssoftware.gameserver.instancemanager.RaidBossSpawnManager;
import ru.catssoftware.gameserver.instancemanager.RaidPointsManager;
import ru.catssoftware.gameserver.instancemanager.SiegeManager;
import ru.catssoftware.gameserver.instancemanager.TimedItemControl;
import ru.catssoftware.gameserver.instancemanager.TownManager;
import ru.catssoftware.gameserver.instancemanager.ZoneManager;
import ru.catssoftware.gameserver.instancemanager.clanhallsiege.*;
import ru.catssoftware.gameserver.instancemanager.games.fishingChampionship;
import ru.catssoftware.gameserver.instancemanager.grandbosses.AntharasManager;
import ru.catssoftware.gameserver.instancemanager.grandbosses.BaiumManager;
import ru.catssoftware.gameserver.instancemanager.grandbosses.CoreManager;
import ru.catssoftware.gameserver.instancemanager.grandbosses.FrintezzaManager;
import ru.catssoftware.gameserver.instancemanager.grandbosses.OrfenManager;
import ru.catssoftware.gameserver.instancemanager.grandbosses.QueenAntManager;
import ru.catssoftware.gameserver.instancemanager.grandbosses.SailrenManager;
import ru.catssoftware.gameserver.instancemanager.grandbosses.ValakasManager;
import ru.catssoftware.gameserver.instancemanager.grandbosses.VanHalterManager;
import ru.catssoftware.gameserver.instancemanager.grandbosses.ZakenManager;
import ru.catssoftware.gameserver.instancemanager.lastimperialtomb.LastImperialTombManager;
import ru.catssoftware.gameserver.instancemanager.leaderboards.ArenaManager;
import ru.catssoftware.gameserver.instancemanager.leaderboards.FishermanManager;
import ru.catssoftware.gameserver.mmocore.SelectorConfig;
import ru.catssoftware.gameserver.mmocore.SelectorThread;
import ru.catssoftware.gameserver.model.AutoChatHandler;
import ru.catssoftware.gameserver.model.L2Manor;
import ru.catssoftware.gameserver.model.L2PcOffline;
import ru.catssoftware.gameserver.model.L2SiegeStatus;
import ru.catssoftware.gameserver.model.L2World;
import ru.catssoftware.gameserver.model.entity.Castle;
import ru.catssoftware.gameserver.model.entity.Hero;
import ru.catssoftware.gameserver.model.entity.events.BigSquash;
import ru.catssoftware.gameserver.model.entity.events.Cristmas;
import ru.catssoftware.gameserver.model.entity.events.EventMedals;
import ru.catssoftware.gameserver.model.entity.events.GameEventManager;
import ru.catssoftware.gameserver.model.entity.events.L2day;
import ru.catssoftware.gameserver.model.entity.events.StarlightFestival;
import ru.catssoftware.gameserver.model.olympiad.Olympiad;
import ru.catssoftware.gameserver.model.quest.QuestMessage;
import ru.catssoftware.gameserver.model.restriction.ObjectRestrictions;
import ru.catssoftware.gameserver.network.IOFloodManager;
import ru.catssoftware.gameserver.network.L2GameClient;
import ru.catssoftware.gameserver.network.L2GamePacketHandler;
import ru.catssoftware.gameserver.network.daemons.*;
import ru.catssoftware.gameserver.script.CoreScriptsLoader;
import ru.catssoftware.gameserver.scripting.L2ScriptEngineManager;
import ru.catssoftware.gameserver.taskmanager.AttackStanceTaskManager;
import ru.catssoftware.gameserver.taskmanager.DecayTaskManager;
import ru.catssoftware.gameserver.taskmanager.KnownListUpdateTaskManager;
import ru.catssoftware.gameserver.taskmanager.LeakTaskManager;
import ru.catssoftware.gameserver.taskmanager.OfflineManager;
import ru.catssoftware.gameserver.taskmanager.SQLQueue;
import ru.catssoftware.gameserver.taskmanager.TaskManager;
import ru.catssoftware.gameserver.taskmanager.tasks.TaskPcCaffe;
import ru.catssoftware.gameserver.threadmanager.DeadlockDetector;
import ru.catssoftware.gameserver.util.PcAction;
import ru.catssoftware.gameserver.util.Util;
import ru.catssoftware.info.Version;
import ru.catssoftware.protection.CatsGuard;
import ru.catssoftware.util.Console;
import ru.catssoftware.util.concurrent.RunnableStatsManager;


public class L2GameServer
{
	private static final Logger						_log				= Logger.getLogger(L2GameServer.class);
	private static final Calendar					_serverStarted		= Calendar.getInstance();
	private static SelectorThread<L2GameClient>		_selectorThread;
	public static long								_upTime				= 0;
	public static double							_intialTime			= 0;
	
	public L2GameServer() throws Throwable
	{

		/* Предстартовая подготовка */
		showLogo();
		prepare();
		
		/* Загрузка чат фильтра */
		Console.printSection("Chat Filter");
		Config.loadFilter();
		Message.load();

		/* Инициализация базы данных */
		Console.printSection("Database Engine");
		L2DatabaseFactory.getInstance();
		
		PcAction.clearRestartTask();

		/* Вывод системной инфы */
		Console.printSection("System Info");
		Util.printGeneralSystemInfo();
		Console.printSection("Scripting Engines");
		L2ScriptEngineManager.getInstance();
		/* Инициализация пулов */
		Console.printSection("ThreadPool Manager");
		ThreadPool();
		
		/* Установка основго мира */
		Console.printSection("Lineage 2 World");
		ServerData.getInstance();
		L2World.getInstance();
		
		/* Запуск дедлок детектора */
		Console.printSection("DeadLock Detector");
		if (Config.DEADLOCKCHECK_INTERVAL > 0)
			DeadlockDetector.getInstance();
		else
			_log.info("DeadlockDetector: Manager is disabled");

			
		/* Создание карт мира */
		Console.printSection("MapRegion Manager");
		MapRegionManager.getInstance();

		/* Загрузка анонсов */
		Console.printSection("Announce Manager");
		Announcements.getInstance();

		/* ID Factory менеджер */
		Console.printSection("ID Factory Manager");
		if (!IdFactory.getInstance().isInitialized())
			_log.fatal("IdFactory: Could not read object IDs from DB");
		_log.info("IdFactory: Free ObjectID's remaining: " + IdFactory.getInstance().size());

		/* Дополнительные треды */
		RunnableStatsManager.getInstance();
		Console.printSection("Extensions");
		GameExtensionManager.getInstance().load();
	
		/* Запуск движка геодаты */
		Console.printSection("Geodata Engine");
		GeoData.getInstance();
		PathFinding.getInstance();
		/* Загрузка статических объектов */
		Console.printSection("Static Objects");
		StaticObjects.getInstance();

		/* Сервер менеджер, основные классы */
		Console.printSection("Server Manager");
		if (Config.ALT_ALLOW_AWAY_STATUS)
			AwayManager.getInstance();
		GameTimeController.getInstance();
		BoatManager.getInstance();
		InstanceManager.getInstance();
		
		
		
		/* Старт серверных задач */
		Console.printSection("TaskManagers");
		AttackStanceTaskManager.getInstance();
		DecayTaskManager.getInstance();
		KnownListUpdateTaskManager.getInstance();
		LeakTaskManager.getInstance();
		SQLQueue.getInstance();

		/* Таблица телепортов */
		Console.printSection("Teleport Table");
		TeleportLocationTable.getInstance();

		/* Загрузка скилов */
		Console.printSection("Skills");
		SkillTreeTable.getInstance();
		SkillTable.getInstance();
		AdditionalSkillTable.getInstance();
		ResidentialSkillTable.getInstance();
		PetSkillsTable.getInstance();
		NobleSkillTable.getInstance();
		HeroSkillTable.getInstance();

		/* Загрущка итемов */
		Console.printSection("Items");
		ItemTable.getInstance();
		ArmorSetsTable.getInstance();
		AugmentationData.getInstance();
		if (Config.SP_BOOK_NEEDED)
			SkillSpellbookTable.getInstance();
		SummonItemsData.getInstance();
		ExtractableItemsData.getInstance();
		if (Config.ALLOW_FISHING)
			FishTable.getInstance();
		ItemsOnGroundManager.getInstance();
		if (Config.AUTODESTROY_ITEM_AFTER > 0 || Config.HERB_AUTO_DESTROY_TIME > 0)
			ItemsAutoDestroy.getInstance();
		EnchantHPBonusData.getInstance();

		/* Кэш  HTML */
		HtmCache.getInstance();

		/* Данные персонажей */
		Console.printSection("Characters");
		CharNameTable.getInstance();
		CharTemplateTable.getInstance();
		LevelUpData.getInstance();
		HelperBuffTable.getInstance();
		HennaTable.getInstance();
		HennaTreeTable.getInstance();
		if (Config.ALLOW_WEDDING)
			CoupleManager.getInstance();
		ClanTable.getInstance();
		CrestCache.getInstance();
		Hero.getInstance();
		BlockListManager.getInstance();
		
		/* Загрузка всех НПЦ */
		Console.printSection("NPC Stats");
		NpcTable.getInstance();
		NpcLikePcTemplates.getInstance();
		PetDataTable.getInstance().loadPetsData();
		
		/* Автоспавн и авто чат */
		Console.printSection("Auto Handlers");
		AutoChatHandler.getInstance();
		AutoSpawnManager.getInstance();
		
		/* Семь печатей */
		Console.printSection("Seven Signs");
		SevenSigns.getInstance();
		SevenSignsFestival.getInstance();
		
		/* Замки, форты, зоны */
		Console.printSection("Entities and zones");
		CrownManager.getInstance();
		TownManager.getInstance();
		ClanHallManager.getInstance();
		DoorTable.getInstance();
		CastleManager.getInstance();
		SiegeManager.getInstance().load();
		FortManager.getInstance();
		FortSiegeManager.getInstance().load();
		ZoneManager.getInstance();
		MercTicketManager.getInstance();
		DoorTable.getInstance().registerToClanHalls();
		DoorTable.getInstance().setCommanderDoors();

		/* Осады элитных КХ */
		Console.printSection("Clan Hall Siege");
		FortResistSiegeManager.load();
		BanditStrongholdSiege.load();
		DevastatedCastleSiege.load();
		FortressOfDeadSiege.load();
		WildBeastFarmSiege.load();
		RainbowSpringSiege.load();
		// make sure that all the scheduled siege dates are in the Seal Validation period
		for (Castle castle : CastleManager.getInstance().getCastles().values())
			castle.getSiege().correctSiegeDateTime();
		
		/* Квесты */
		
		Console.printSection("Events/Script/CoreScript/Engine");
		QuestManager.getInstance();
		CoreScriptsLoader.Register();
		try
		{
			L2ScriptEngineManager.getInstance().loadScripts();
			
		}
		catch (IOException ioe)
		{
			_log.fatal("Failed loading scripts, no script going to be loaded");
		}
		QuestManager.getInstance().report();
		EventsDropManager.getInstance();
		EventDroplist.getInstance();
		if (Config.ARENA_ENABLED)
			ArenaManager.getInstance().engineInit();
		if (Config.FISHERMAN_ENABLED)
			FishermanManager.getInstance().engineInit();
		if (Config.SHOW_NOT_REG_QUEST)
			QuestMessage.showNotRegQuest();
		Console.printSection("HTML");
		_log.info(HtmCache.getInstance());

		/* Спавн, запуск спавна */
		Console.printSection("Spawns");
		SpawnTable.getInstance();
		if (Config.JAIL_SPAWN_SYSTEM)
			JailSpawnTable.getInstance().loadJailSpawns();
		if (Config.ALLOW_WEDDING)
			PcAction.spawnManager();
		DayNightSpawnManager.getInstance().notifyChangeMode();
		RaidBossSpawnManager.getInstance();
		RaidPointsManager.init();
		AutoChatHandler.getInstance();
		AutoSpawnManager.getInstance();
		
		/* Экономика */
		Console.printSection("Economy");
		CursedWeaponsManager.getInstance();
		TradeListTable.getInstance();
		CastleManorManager.getInstance();
		L2Manor.getInstance();
		AuctionManager.getInstance();
		TimedItemControl.getInstance();
		PartyRoomManager.getInstance();
		
		/* Олимпиада */
		Console.printSection("Olympiad");
		Olympiad.getInstance();
		
		Console.printSection("DimensionalRift");
		DimensionalRiftManager.getInstance();
		
		Console.printSection("FourSepulchers");
		FourSepulchersManager.getInstance().init();
		
		Console.printSection("Bosses");
		QueenAntManager.getInstance().init();
		ZakenManager.getInstance().init();
		CoreManager.getInstance().init();
		OrfenManager.getInstance().init();
		SailrenManager.getInstance().init();
		VanHalterManager.getInstance().init();
		
		Console.printSection("GrandBosses");
		AntharasManager.getInstance().init();
		BaiumManager.getInstance().init();
		ValakasManager.getInstance().init();
		LastImperialTombManager.getInstance().init();
		FrintezzaManager.getInstance().init();
		
		Console.printSection("Factions Manager");
		if (Config.FACTION_ENABLED)
		{
			FactionManager.getInstance();
			FactionQuestManager.getInstance();
		}
		else
			_log.info("Faction Manager: disabled.");
		
		Console.printSection("Handlers");
		ItemHandler.getInstance();
		SkillHandler.getInstance();
		UserCommandHandler.getInstance();
		VoicedCommandHandler.getInstance();
		ChatHandler.getInstance();
		
		Console.printSection("Misc");
		ObjectRestrictions.getInstance();
		L2SiegeStatus.getInstance();
		TaskManager.getInstance();
		GmListTable.getInstance();
		PetitionManager.getInstance();
		if (Config.ONLINE_PLAYERS_ANNOUNCE_INTERVAL > 0)
			OnlinePlayers.getInstance();
		CommunityBoard.getInstance();
		TimedItemControl.getInstance();
		fishingChampionship.getInstance();

		Console.printSection("Offline Service");
		if (Config.ALLOW_OFFLINE_TRADE)
			OfflineManager.getInstance();
		if (Config.RESTORE_OFFLINE_TRADERS)
			L2PcOffline.loadOffliners();
		else
			L2PcOffline.clearOffliner();

		Runtime.getRuntime().addShutdownHook(Shutdown.getInstance());
		System.gc();

		Console.printSection("ServerThreads");
		LoginServerThread.getInstance().start();
		L2GamePacketHandler gph = new L2GamePacketHandler();

		final SelectorConfig<L2GameClient> sc = new SelectorConfig<L2GameClient>(gph);

		_selectorThread = new SelectorThread<L2GameClient>(sc, gph, gph, gph, IOFloodManager.getInstance());
		_selectorThread.openServerSocket(InetAddress.getByName(Config.GAMESERVER_HOSTNAME), Config.PORT_GAME);
		_selectorThread.start();

		Console.printSection("Daemons");
		SuperDeamon.getInstance();
		if(Config.PC_CAFFE_ENABLED)
			new TaskPcCaffe().schedule(Config.PC_CAFFE_INTERVAL*60000);
		

		Console.printSection("Events");
		GameEventManager.getInstance();
		Console.printSection("Mods");
		Cristmas.startEvent();
        EventMedals.startEvent();
        StarlightFestival.startEvent();
		L2day.startEvent();
		BigSquash.startEvent();


		Console.printSection("Gm System");
		gmController.getInstance();
		gmCache.getInstance();
		
		CatsGuard.getInstance();
		if(!CatsGuard.getInstance().isEnabled()) try {
			Class<?> clazz = Class.forName("com.lameguard.LameGuard");
			if(clazz!=null) {
				File f = new File("./lameguard/lameguard.properties");
				if(f.exists()) {
					Console.printSection("LameGuard");
					LameGuard.main(new String []{"ru.catssoftware.protection.LameStub"});
				}
			}
		} catch(Exception e) {
			
		}
		Console.printSection("Tasks Manager");
		TaskManager.getInstance().startAllTasks();
		onStartup();
		System.gc();
		printInfo();
		
	}

	private static Set<StartupHook> _startupHooks = new HashSet<StartupHook>();
	
	public synchronized static void addStartupHook(StartupHook hook)
	{
		if (_startupHooks != null)
			_startupHooks.add(hook);
		else
			hook.onStartup();
	}

	private static void printInfo()
	{
		Console.printSection("Server Info");
		long freeMem = (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1048576;
		long totalMem = Runtime.getRuntime().maxMemory() / 1048576;
		double finalTime = System.currentTimeMillis();
		
		_log.info("Build version: "+Version.Version);
		_log.info("Free memory: " + freeMem + " Mb of " + totalMem + " Mb");
		_log.info("Ready on IP: " + Config.EXTERNAL_HOSTNAME + ":" + Config.PORT_GAME + ".");
		_log.info("Max players: " + Config.MAXIMUM_ONLINE_USERS);
		_log.info("Load time: " + (int) ((finalTime - _intialTime) / 1000) + " Seconds.");
		Console.printSection("");
		// set uptime
		_upTime = System.currentTimeMillis();
	}
	
	private synchronized static void onStartup()
	{
		final Set<StartupHook> startupHooks = _startupHooks;
		
		_startupHooks = null;
		
		for (StartupHook hook : startupHooks)
			hook.onStartup();
	}
	
	public interface StartupHook
	{
		public void onStartup();
	}

	public static void printMemUsage()
	{
		Console.printSection("Memory");
		for (String line : Util.getMemUsage())
			_log.info(line);
	}

	public static SelectorThread<L2GameClient> getSelectorThread()
	{
		return _selectorThread;
	}

	public static Calendar getStartedTime()
	{
		return _serverStarted;
	}

	private void prepare()
	{
		
		System.setProperty("line.separator", "\r\n");
		System.setProperty("file.encoding", "UTF-8");
		System.setProperty("python.home", ".");

		Console.printSection("Preparations");
		_log.info("Checking folders: preparations");
		new File("log").mkdirs();
		new File("log/java").mkdirs();
		new File("log/error").mkdirs();
		new File("log/audit").mkdirs();
		new File("log/chat").mkdirs();
		new File("log/item").mkdirs();
		new File("data/crests").mkdirs();
		new File("data/serial").mkdirs();
		_log.info("Checking folders: all folders checked.");

		if (Config.CheckSystemParam && System.getProperty("user.name").equals("root") && System.getProperty("user.home").equals("/root"))
		{
			System.out.print("Servers can't run under root-account ... exited.");
			System.exit(-1);
		}

		_intialTime = System.currentTimeMillis();
	
	}
	
	
	private void ThreadPool()
	{
		_log.info("ThreadPoolManager: Initializing.");
		ThreadPoolManager.getInstance();
		_log.info("General threads: ..... " + Config.GENERAL_THREAD_POOL_SIZE + ".");
		_log.info("Effect threads: ...... " + Config.EFFECT_THREAD_POOL_SIZE + ".");
		_log.info("AI threads: .......... " + Config.AI_THREAD_POOL_SIZE + ".");
		_log.info("Packet threads: ...... " + Config.PACKET_THREAD_POOL_SIZE + ".");
		_log.info("Total threads: ....... " + Config.THREAD_POOL_SIZE + ".");
	}
	private void showLogo() {
		System.out.println("..................................................................");
		System.out.println("...........Lineage.2.Interlude.Premium.Development.Team...........");
		System.out.println("..................................................................");
		System.out.println("............................L2jService............................");
		System.out.println("..................................................................");


		
	}

}
