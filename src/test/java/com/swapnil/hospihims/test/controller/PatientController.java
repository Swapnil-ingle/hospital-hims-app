package com.swapnil.hospihims.test.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.swapnil.hospihims.app.Application;

@RunWith(SpringRunner.class)
@WebMvcTest()
@ContextConfiguration(classes = Application.class)
public class PatientController {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldReturnListOfPatients() throws Exception {
		this.mockMvc.perform(get("/patient/get"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Chandler")));
	}
}
