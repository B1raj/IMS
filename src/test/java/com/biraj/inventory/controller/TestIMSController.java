package com.biraj.inventory.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.biraj.inventory.constant.IMSConstant;
import com.biraj.inventory.filter.IMSFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles({ "in-memory" })
public class TestIMSController {

	private MockMvc mockMvc;

	private static final Logger LOG = LoggerFactory.getLogger(TestIMSController.class);

	@Autowired
	private IMSController tokenController;

	@Autowired
	private IMSFilter imsFilter;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(tokenController).addFilters(imsFilter).build();
	}


	@Test
	public void testSSOLoginParty() {
		try {
			mockMvc.perform(post("/ims/login").contentType(MediaType.APPLICATION_JSON)
					.header(IMSConstant.UUID, "7890").header(IMSConstant.AUTHORIZATION, "Basic YmlyYWo6bWlzaHJh")).andExpect(status().isCreated()).andReturn();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}





}
