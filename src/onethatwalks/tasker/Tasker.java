package onethatwalks.tasker;

public interface Tasker {
	/**
	 * Registers a task that will run at a certain time.
	 * 
	 * @param name
	 *            Name of the task
	 * @param time
	 *            Time to run the task each Minecraft day
	 * @param file
	 *            file the macro is located at
	 */
	public void registerTask(String name, long time, String file);
}
