package onethatwalks.tasker;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Tasks implements Tasker {
	private Map<String, Task> tasks = new LinkedHashMap<String, Task>();

	protected void addTask(Task task) {
		tasks.put(task.name, task);
	}

	public void registerTask(String name, long time, String file) {
		if (getTask(name) != null) {
			new Task(name, time, file);
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
}
