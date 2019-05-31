package com.swapnil.hospihims.test.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.swapnil.hospihims.app.Application;
import com.swapnil.hospihims.controller.PatientController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class SmokeTest {
	@Autowired
	private PatientController controller;

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
