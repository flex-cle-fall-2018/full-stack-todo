package com.wecancodeit.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TaskRestControllerTest {
	
	@Resource
	TestRestTemplate restTemplate;
	
	/*
	 * /api/tasks      [GET = find all]
	 * /api/tasks      [POST = create]
	 * /api/tasks/{id} [GET = find this one]
	 * /api/tasks/{id} [PUT = update this one]
	 * /api/tasks/{id} [DELETE = delete this one]
	 */

	@Test
	public void shouldBeOkForGetTasks() {
		String url = "/api/tasks";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(HttpStatus.OK));
	}
	
	@Test
	public void shouldCreateTaskOnPost() {
		String url = "/api/tasks";
		
		HttpEntity<TaskItem> request =
				new HttpEntity<TaskItem>(new TaskItem("Mow the Lawn"));
		
		TaskItem taskItem = restTemplate.postForObject(url, request, TaskItem.class);
		
		assertThat(taskItem, notNullValue());
		assertThat(taskItem.getDescription(), is("Mow the Lawn"));
		assertThat(taskItem.getId(), is(greaterThan(0L)));
	}
}
