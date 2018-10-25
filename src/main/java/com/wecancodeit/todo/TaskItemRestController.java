package com.wecancodeit.todo;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskItemRestController {

	@Resource
	TaskRepository taskRepo;

	@GetMapping("/api/tasks")
	public Iterable<TaskItem> getAllTasks() {
		return taskRepo.findAll();
	}

	@PostMapping("/api/tasks")
	public TaskItem createTask(@RequestBody TaskItem newTaskItem) {
		return taskRepo.save(newTaskItem);
	}

	@GetMapping("/api/tasks/{id}")
	public TaskItem viewTask(@PathVariable Long id) {
		return taskRepo.findById(id).get();
	}

	@DeleteMapping("/api/tasks/{id}")
	public boolean deleteTask(@PathVariable Long id) {
		if (taskRepo.findById(id).isPresent()) {
			taskRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@PutMapping("/api/tasks")
	public TaskItem updateTask(@RequestBody TaskItem taskItemUpdate) {
		return taskRepo.save(taskItemUpdate);
	}
}
