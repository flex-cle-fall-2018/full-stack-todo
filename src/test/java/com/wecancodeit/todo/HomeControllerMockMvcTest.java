package com.wecancodeit.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerMockMvcTest {

	@Resource
	private MockMvc mvc;

	@Test
	public void shouldRouteToIndex() throws Exception {
		mvc.perform(get("/")).andExpect(view().name(is("index")));
	}
	
	@Test
	public void shouldBeOkForIndex() throws Exception {
		mvc.perform(get("/")).andExpect(status().isOk());
	}

}
