package com.example.HelloAppEngine;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloAppEngineApplicationTests {

	@Autowired
	private MockMvc mvc;

	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	@Test
	public void contextLoads() {
		System.out.println(">>>>>>Testing<<<<<<: " + getMethodName());
	}

	@Test
	public void getHello() throws Exception {
		System.out.println(">>>>>Testing<<<<<<: " + getMethodName());
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(equalTo("Hello Spring Boot!")));
	}
    
    @Test
    public void getParam() throws Exception {
		System.out.println(">>>>>Testing<<<<<<: " + getMethodName());
        mvc.perform(MockMvcRequestBuilders.get("/get?name=HelloWorld").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(equalTo("HelloWorld")));
    }

}
