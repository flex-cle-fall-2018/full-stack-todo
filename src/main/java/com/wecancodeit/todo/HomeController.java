package com.wecancodeit.todo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
