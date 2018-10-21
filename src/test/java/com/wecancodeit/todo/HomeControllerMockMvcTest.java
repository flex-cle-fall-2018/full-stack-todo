package com.wecancodeit.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerMockMvcTest {

	@Resource private MockMvc mvc;
	
	@MockBean TaskRepository taskRepo;
	@Mock TaskItem task1;
	@Mock TaskItem task2;
	
	// Test viewing task list on index page
	@Test
	public void shouldRouteToIndex() throws Exception {
		mvc.perform(get("/")).andExpect(view().name(is("index")));
	}
	
	@Test
	public void shouldBeOkForIndex() throws Exception {
		mvc.perform(get("/")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutTasksIntoModelForIndex() throws Exception {
		when(taskRepo.findAll()).thenReturn(Arrays.asList(task1, task2));
		mvc.perform(get("/")).andExpect(model().attribute("tasks", contains(task1, task2)));
	}

	// Test form submission to create new task
	@Test
	public void shouldAcceptValidNewTaskForm() throws Exception {
		mvc.perform(post("/addTask").param("taskDescription", "New Task"))
			.andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void shouldNotAcceptInvalidNewTaskForm() throws Exception {
		mvc.perform(post("/addTask")).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void taskFormSubmissionShouldCreateNewTask() throws Exception {
		mvc.perform(post("/addTask").param("taskDescription", "New Task"));
		verify(taskRepo, times(1)).save(any());
	}
	
	// Test deleting a task
	@Test
	public void shouldAcceptValidTaskDeletionRequest() throws Exception {
		mvc.perform(get("/deleteTask").param("taskId", "1"))
			.andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void shouldNotAcceptInvalidTaskDeletionRequest() throws Exception {
		mvc.perform(get("/deleteTask"))
			.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void shouldDeleteTask() throws Exception {
		mvc.perform(get("/deleteTask").param("taskId", "1"));
		verify(taskRepo, times(1)).deleteById(any());
	}
	
	// Test viewing an individual task
	@Test
	public void shouldRouteToViewTaskPage() throws Exception {
		mvc.perform(get("/viewTask/1/")).andExpect(view().name(is("task")));
	}
	
	@Test
	public void shouldBeOkForViewTask() throws Exception {
		mvc.perform(get("/viewTask/1/")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutTaskIntoModelForViewTask() throws Exception {
		when(taskRepo.findById(1L)).thenReturn(Optional.of(task1));
		mvc.perform(get("/viewTask/1/"))
			.andExpect(model().attribute("task", is(task1)));
	}
	
	// Test updating a task
	@Test
	public void shouldAcceptValidTaskUpdateForm() throws Exception {
		when(taskRepo.findById(1L)).thenReturn(Optional.of(task1));
		mvc.perform(post("/updateTask/1/").param("taskDescription", "New Description"))
			.andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void shouldNotAcceptInvalidTaskUpdateForm() throws Exception {
		mvc.perform(post("/updateTask/1/")).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void shouldUpdateTask() throws Exception {
		when(taskRepo.findById(1L)).thenReturn(Optional.of(task1));
		mvc.perform(post("/updateTask/1/").param("taskDescription", "New Description"));
		verify(task1).setDescription("New Description");
		verify(taskRepo).save(task1);
	}
}
