package com.wecancodeit.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TaskItemTest {

	@Test
	public void shouldBeAbleToCreateTask() {
		TaskItem task = new TaskItem("Fix sink");
		assertThat(task.getDescription(), is("Fix sink"));
	}
}
