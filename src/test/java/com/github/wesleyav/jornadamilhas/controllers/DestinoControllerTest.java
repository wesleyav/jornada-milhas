package com.github.wesleyav.jornadamilhas.controllers;

import static com.github.wesleyav.jornadamilhas.common.DestinoConstants.DESTINO;
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
import com.github.wesleyav.jornadamilhas.services.DestinoService;

@WebMvcTest(DestinoController.class)
public class DestinoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private DestinoService destinoService;

	@Test
	public void createDestino_WithValidData_ReturnsCreated() throws Exception {
		when(destinoService.save(DESTINO)).thenReturn(DESTINO);
		
		mockMvc
			.perform(
				post("/api/v1/destinos").content(objectMapper.writeValueAsString(DESTINO)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$").value(DESTINO));
	}

	@Test
	public void listDestinos_ReturnsDestinos() throws Exception {

	}

	@Test
	public void getDestino_ByExistingId_ReturnsDestino() throws Exception {
		Long id = 1L;

		when(destinoService.findById(id)).thenReturn(DESTINO);

		mockMvc.perform(get("/api/v1/destinos/{id}", id)).andExpect(status().isOk());
	}

	@Test
	public void updateDestino_ByExistingId_ReturnsDestino() throws Exception {
		Long id = 1L;

		when(destinoService.update(id, DESTINO)).thenReturn(DESTINO);

		mockMvc.perform(put("/api/v1/destinos/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(DESTINO)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteDestino_ByExistingId_ReturnsDestino() throws Exception {
		Long id = 1L;

		doNothing().when(destinoService).deleteById(id);

		mockMvc.perform(delete("/api/v1/destinos/{id}", id)).andExpect(status().isNoContent());
	}

}
