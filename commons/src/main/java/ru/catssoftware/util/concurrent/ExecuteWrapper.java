package ru.catssoftware.util.concurrent;

public class ExecuteWrapper implements Runnable
{
	private final Runnable _runnable;

	public ExecuteWrapper(Runnable runnable)
	{
		_runnable = runnable;
	}

	public final void run()
	{
		ExecuteWrapper.execute(_runnable, getMaximumRuntimeInMillisecWithoutWarning());
	}

	protected long getMaximumRuntimeInMillisecWithoutWarning()
	{
		return Long.MAX_VALUE;
	}

	public static void execute(Runnable runnable)
	{
		execute(runnable, Long.MAX_VALUE);
	}

	public static void execute(Runnable runnable, long maximumRuntimeInMillisecWithoutWarning)
	{
		long begin = System.nanoTime();

		try
		{
			runnable.run();
		}
		catch (Exception e)
		{
		}
		finally
		{
			long runtimeInNanosec = System.nanoTime() - begin;
			Class<? extends Runnable> clazz = runnable.getClass();

			RunnableStatsManager.getInstance().handleStats(clazz, runtimeInNanosec);
		}
	}
}