package com.wecancodeit.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPAMappingsTest {
	
	@Resource
	TaskRepository taskRepo;
	
	@Resource
	EntityManager entityManager;
	
	@Test
	public void shouldBeAbleToSaveAndLoadTask() {
		TaskItem task = taskRepo.save(new TaskItem("Mow the Lawn"));
		long taskId = task.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<TaskItem> taskOptional = taskRepo.findById(taskId);
		TaskItem taskResult = taskOptional.get();
		assertThat(taskResult.getDescription(), is("Mow the Lawn"));
	}

}
