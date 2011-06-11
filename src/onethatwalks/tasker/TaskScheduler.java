package onethatwalks.tasker;

import java.util.Collection;
import java.util.TreeMap;

import onethatwalks.satools.SATools;

/**
 * Scheduling class for SATools - Heavy Credit from the people at Bukkit.
 * 
 * @author OneThatWalks
 * 
 */
public class TaskScheduler implements Tasker, Runnable {
	private TreeMap<String, Task> tasks = new TreeMap<String, Task>();

	public TaskScheduler() {
		Thread t = new Thread(this);
		t.start();
	}

	protected void addTask(Task task) {
		tasks.put(task.name, task);
	}

	public void registerTask(String name, long time, String file) {
		if (getTask(name) != null) {
			Task newTask = new Task(name, time, file);
			addTask(newTask);
		}
	}

	private Task getTask(String name) {
		if (tasks.containsKey(name)) {
			return tasks.get(name);
		}
		return null;
	}

	public Collection<Task> getTasks() {
		return tasks.values();
	}

	public void killTask(Task task) {
		tasks.remove(task.name);
	}

	public void killAllTasks() {
		tasks.clear();
	}

	@Override
	public void run() {
		while (true) {
			boolean stop = false;
			long firstTime = -1;
			long currentTime = -1;
			Task first = null;
			int taskQueue = 0;
			do {
				synchronized (tasks) {
					first = null;
					if (!tasks.isEmpty()) {
						first = tasks.get(getTasks().toArray()[taskQueue]);
						if (first != null) {
							currentTime = SATools.time;

							firstTime = first.getExecutionTime();

							if (currentTime >= firstTime) {
								processTask(first);
							} else {
								stop = true;
							}
						} else {
							stop = true;
						}
					} else {
						stop = true;
					}
				}
			} while (!stop);

			long sleepTime = 0;
			if (first == null) {
				sleepTime = 6000;
			} else {
				currentTime = SATools.time;
				sleepTime = (firstTime - currentTime);
			}

			if (sleepTime < 50) {
				sleepTime = 50;
			} else if (sleepTime > 60000) {
				sleepTime = 60000;
			}

			synchronized (tasks) {
				try {
					tasks.wait(sleepTime);
				} catch (InterruptedException ie) {
				}
			}
		}
	}

	private void processTask(Task task) {
		task.run();
	}
}
