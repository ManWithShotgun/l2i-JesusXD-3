package ru.catssoftware.gameserver.model.entity.events.LastHero;

import ru.catssoftware.gameserver.model.entity.events.GameEvent;
import ru.catssoftware.gameserver.taskmanager.Task;
import ru.catssoftware.gameserver.taskmanager.TaskManager.ExecutedTask;

public class TaskStartLH extends Task
{
	
	@Override
	public String getName()
	{
		return LastHero.getInstance().getName();
	}

	@Override
	public void onTimeElapsed(ExecutedTask task)
	{
		if(LastHero.getInstance()!=null && LastHero.getInstance().getState()==GameEvent.STATE_INACTIVE)
		{
			LastHero.getInstance().start();
		}
	}
}