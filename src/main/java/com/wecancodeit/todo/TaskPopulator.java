package com.wecancodeit.todo;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TaskPopulator implements CommandLineRunner {
	
	@Resource
	TaskRepository taskRepo;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}

}
