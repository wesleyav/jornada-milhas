package com.github.wesleyav.jornadamilhas.controllers;

import static com.github.wesleyav.jornadamilhas.common.DepoimentoConstants.DEPOIMENTO;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wesleyav.jornadamilhas.services.DepoimentoService;

@WebMvcTest(DepoimentoController.class)
public class DepoimentoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private DepoimentoService depoimentoService;

	@Test
	public void createDepoimento_WithValidData_ReturnsCreated() throws Exception {
		when(depoimentoService.save(DEPOIMENTO)).thenReturn(DEPOIMENTO);
		
		mockMvc
			.perform(
				post("/api/v1/depoimentos").content(objectMapper.writeValueAsString(DEPOIMENTO)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$").value(DEPOIMENTO));
	}

	@Test
	public void listDepoimentos_ReturnsDepoimentos() throws Exception {

	}

	@Test
	public void getDepoimento_ByExistingId_ReturnsDepoimento() throws Exception {
		Long id = 1L;

		when(depoimentoService.findById(id)).thenReturn(DEPOIMENTO);

		mockMvc.perform(get("/api/v1/depoimentos/{id}", id)).andExpect(status().isOk());
	}

	@Test
	public void updateDepoimento_ByExistingId_ReturnsDepoimento() throws Exception {
		Long id = 1L;

		when(depoimentoService.update(id, DEPOIMENTO)).thenReturn(DEPOIMENTO);

		mockMvc.perform(put("/api/v1/depoimentos/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(DEPOIMENTO)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteDepoimento_ByExistingId_ReturnsDepoimento() throws Exception {
		Long id = 1L;

		doNothing().when(depoimentoService).deleteById(id);

		mockMvc.perform(delete("/api/v1/depoimentos/{id}", id)).andExpect(status().isNoContent());
	}

}
