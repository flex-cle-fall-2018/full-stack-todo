package com.wecancodeit.todo;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@Resource
	TaskRepository taskRepo;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("tasks", taskRepo.findAll());
		return "index";
	}
	
	// @RequestMapping(value = "/addTask", method = RequestMethod.POST)
	@PostMapping("/addTask")
	public String addTask(@RequestParam(required = true) String taskDescription) {
		taskRepo.save(new TaskItem(taskDescription));
		return "redirect:/";
	}
	
	@GetMapping("/deleteTask")
	public String removeTask(@RequestParam(required = true) Long taskId) {
		taskRepo.deleteById(taskId);
		return "redirect:/";
	}
	
	@GetMapping("/viewTask/{id}/")
	public String viewTask(@PathVariable Long id, Model model) throws TaskItemNotFoundException {
		Optional<TaskItem> taskOptional = taskRepo.findById(id);
		
		if (taskOptional.isPresent()) {
			TaskItem task = taskOptional.get();
			model.addAttribute("task", task);
			return "task";
		}
		
		throw new TaskItemNotFoundException();
	}
	
	@PostMapping("/updateTask/{id}/")
	public String updateTask(
		@PathVariable Long id,
		@RequestParam(required = true) String taskDescription
	) throws TaskItemNotFoundException {
		Optional<TaskItem> taskOptional = taskRepo.findById(id);
		
		if (taskOptional.isPresent()) {
			TaskItem task = taskOptional.get();
			task.setDescription(taskDescription);
			taskRepo.save(task);
			return "redirect:/viewTask/{id}/";
		}
		
		throw new TaskItemNotFoundException();		
	}
}
