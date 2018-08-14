package com.whl.myapp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.whl.myapp.Application;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class ControllerTests {
	private MockMvc mvc;
	@Before
	public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new MainController()).build();
    }
	
	@Test
	public void testLogin() throws Exception{
		 mvc.perform(MockMvcRequestBuilders.get("/north").param("username", "admin").param("password", "123456"))
         .andExpect(status().isOk())
         .andExpect(view().name("layout/north"))
         ;
	}
}
