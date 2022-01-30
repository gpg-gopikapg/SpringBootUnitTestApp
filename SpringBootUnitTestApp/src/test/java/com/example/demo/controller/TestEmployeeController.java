package com.example.demo.controller;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class TestEmployeeController {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Disabled
	public void testSaveEmployee() throws Exception {
		// Create Mock Request
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/employee/save")
				.contentType(MediaType.APPLICATION_JSON).content("{\"empName\":\"Gokul\",\"empSalary\":6000}");
		// Execute Request and get Result
		MvcResult result = mockMvc.perform(request).andReturn();
		// Read Response
		MockHttpServletResponse response = result.getResponse();
		// Test using Assert Method
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		if (!response.getContentAsString().contains("empId")) {
			fail("Save Employee Not Working!!!");
		}
	}

	@Test
	@Disabled
	public void testGetAllEmployees() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employee/all");
		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
		if (response.getContentAsString().length() <= 0) {
			fail("Empty Records");
		}
	}

	@Test
	@Disabled
	public void testGetEmployeeExists() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employee/get/2");
		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
		if (response.getContentAsString().isEmpty()) {
			fail("Employee Not Exists!!!");
		}

	}

	@Test
	@Disabled
	public void testGetEmployeeNotExists() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employee/get/40");
		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		if (!response.getContentAsString().equals("Employee Not Found with Id:40")) {
			fail("Employee Exists with the id");
		}
	}

	@Test
	@Disabled
	public void testDeleteEmployee() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/employee/delete/4");
		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
	}
	@Test
	@Disabled
	public void testDeleteEmployeeFail() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/employee/delete/40");
		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		if (!response.getContentAsString().equals("Employee Not Found with Id:40")) {
			fail("Employee Exists with the id");
		}

	}
	@Test
	public void testUpdateEmployee() throws Exception {
		// Create Mock Request
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/employee/update/1")
				.contentType(MediaType.APPLICATION_JSON).content("{\"empName\":\"Gopika\",\"empSalary\":6000}");
		// Execute Request and get Result
		MvcResult result = mockMvc.perform(request).andReturn();
		// Read Response
		MockHttpServletResponse response = result.getResponse();
		// Test using Assert Method
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if (!response.getContentAsString().contains("empId")) {
			fail("Save Employee Not Working!!!");
		}
	}
}

