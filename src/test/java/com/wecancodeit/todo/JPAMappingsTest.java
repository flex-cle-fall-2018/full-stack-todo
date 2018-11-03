package com.wecancodeit.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
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
	TagRepository tagRepo;
	
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
	

	@Test
	public void shouldBeAbleToSaveAndLoadTags() {
		Tag tag = tagRepo.save(new Tag("House Chores"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Tag> tagOptional = tagRepo.findById(tagId);
		Tag tagResult = tagOptional.get();
		assertThat(tagResult.getName(), is("House Chores"));
	}
	
	@Test
	public void shouldEstablishTaskTagRelationship() {
		TaskItem task = taskRepo.save(new TaskItem("Mow the Lawn"));
		Long taskId = task.getId();
		Tag tag = tagRepo.save(new Tag("House Chores", task));

		entityManager.flush();
		entityManager.clear();
		
		Optional<TaskItem> taskOptional = taskRepo.findById(taskId);
		TaskItem taskResult = taskOptional.get();
		assertThat(taskResult.getTags(), contains(tag));
	}

}
