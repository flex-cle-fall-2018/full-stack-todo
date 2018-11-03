package com.wecancodeit.todo;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TaskPopulator implements CommandLineRunner {

	@Resource
	TaskRepository taskRepo;

	@Resource
	TagRepository tagRepo;

	@Override
	public void run(String... args) throws Exception {
		
		TaskItem mowTheLawn = taskRepo.save(new TaskItem("Mow the Lawn"));
		TaskItem feedTheCat = taskRepo.save(new TaskItem("Feed the Cat"));
		
		Tag houseChores = tagRepo.save(new Tag("House Chores", mowTheLawn));
	}

}
