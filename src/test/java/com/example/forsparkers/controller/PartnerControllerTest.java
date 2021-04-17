package com.example.forsparkers.controller;

import com.example.forsparkers.model.dto.PartnerDTO;
import com.example.forsparkers.util.DateFormatter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PartnerControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @LocalServerPort
    private int port;

    private final PartnerController partnerController;
    private final MockMvc mockMvc;

    @Autowired
    public PartnerControllerTest(PartnerController partnerController, MockMvc mockMvc) {
        this.partnerController = partnerController;
        this.mockMvc = mockMvc;
    }

    @Test
    public void contextLoads() {
        assertThat(partnerController).isNotNull();
    }

    @Test
    public void testGetPartnersWithoutQuery() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(getBaseUrl()))
                .andExpect(status().isOk())
                .andReturn();

        List<PartnerDTO> response = OBJECT_MAPPER.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );
        assertThat(response.size() == 10).isTrue();
    }

    @Test
    public void testGetPartnersPage() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(getBaseUrl() + "?from=0&size=5"))
                .andExpect(status().isOk())
                .andReturn();

        List<PartnerDTO> response = OBJECT_MAPPER.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        assertThat(response.size() == 5).isTrue();
    }

    @Test
    public void testGetPartnersQueryString() throws Exception {
        mockMvc.perform(get(getBaseUrl() + "?from=asd&size=asd"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetPartnersInvalidQuery() throws Exception {
        mockMvc.perform(get(getBaseUrl() + "?from=-1&size=0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetPartnerById() throws Exception {
        mockMvc.perform(get(getBaseUrl() + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPartnerByIdNoFound() throws Exception {
        mockMvc.perform(get(getBaseUrl() + "/123"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPostPartner() throws Exception {
        PartnerDTO partnerDTO = new PartnerDTO(
                null,
                "some name",
                "some reference",
                "en_ES",
                "2017-10-03T12:18:46+01:00"
        );
        MvcResult mvcResult = mockMvc.perform(post(getBaseUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(partnerDTO))
        ).andExpect(status().isCreated())
                .andReturn();
        PartnerDTO response = OBJECT_MAPPER.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        assertThat(response.getId() != null).isTrue();
        assertThat(response.getName().equals(partnerDTO.getName())).isTrue();
        assertThat(response.getReference().equals(partnerDTO.getReference())).isTrue();
        assertThat(response.getLocale().equals(partnerDTO.getLocale())).isTrue();

        Date dateFromDto = DateFormatter.stringToDate(partnerDTO.getExpirationTime());
        Date responseDate = DateFormatter.stringToDate(response.getExpirationTime());
        assertThat(dateFromDto.equals(responseDate)).isTrue();
    }

    @Test
    public void testPostPartnerInvalidName() throws Exception {
        PartnerDTO partnerDTO = new PartnerDTO(
                null,
                "",
                "some reference",
                "en_ES",
                "2017-10-03T12:18:46+01:00"
        );
        mockMvc.perform(post(getBaseUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(partnerDTO))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testPostPartnerInvalidReference() throws Exception {
        PartnerDTO partnerDTO = new PartnerDTO(
                null,
                "some name",
                "s",
                "en_ES",
                "2017-10-03T12:18:46+01:00"
        );
        mockMvc.perform(post(getBaseUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(partnerDTO))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testPostPartnerInvalidLocale() throws Exception {
        PartnerDTO partnerDTO = new PartnerDTO(
                null,
                "some name",
                "some reference",
                "en",
                "2017-10-03T12:18:46+01:00"
        );
        mockMvc.perform(post(getBaseUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(partnerDTO))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testPostPartnerInvalidDate() throws Exception {
        PartnerDTO partnerDTO = new PartnerDTO(
                null,
                "some name",
                "some reference",
                "en_ES",
                "jkasdhjkashdkj"
        );
        mockMvc.perform(post(getBaseUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(partnerDTO))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testPutPartner() throws Exception {
        PartnerDTO partnerDTO = new PartnerDTO(
                null,
                "some name",
                "some reference",
                "en_ES",
                "2017-10-03T12:18:46+00:00"
        );
        long id = 1;
        MvcResult mvcResult = mockMvc.perform(put(getBaseUrl() + '/' + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(partnerDTO))
        )
                .andExpect(status().isOk())
                .andReturn();

        PartnerDTO response = OBJECT_MAPPER.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        assertThat(response.getId() == id).isTrue();
        assertThat(response.getName().equals(partnerDTO.getName())).isTrue();
        assertThat(response.getReference().equals(partnerDTO.getReference())).isTrue();
        assertThat(response.getLocale().equals(partnerDTO.getLocale())).isTrue();

        Date dateFromDto = DateFormatter.stringToDate(partnerDTO.getExpirationTime());
        Date responseDate = DateFormatter.stringToDate(response.getExpirationTime());
        assertThat(dateFromDto.equals(responseDate)).isTrue();
    }

    @Test
    public void testPutPartnerInvalidName() throws Exception {
        PartnerDTO partnerDTO = new PartnerDTO(
                null,
                "",
                "some reference",
                "en_ES",
                "2017-10-03T12:18:46+01:00"
        );
        mockMvc.perform(put(getBaseUrl() + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(partnerDTO))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testPutPartnerInvalidReference() throws Exception {
        PartnerDTO partnerDTO = new PartnerDTO(
                null,
                "some name",
                "s",
                "en_ES",
                "2017-10-03T12:18:46+01:00"
        );
        mockMvc.perform(put(getBaseUrl() + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(partnerDTO))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testPutPartnerInvalidLocale() throws Exception {
        PartnerDTO partnerDTO = new PartnerDTO(
                null,
                "some name",
                "some reference",
                "en",
                "2017-10-03T12:18:46+01:00"
        );
        mockMvc.perform(put(getBaseUrl() + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(partnerDTO))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testPutPartnerInvalidDate() throws Exception {
        PartnerDTO partnerDTO = new PartnerDTO(
                null,
                "some name",
                "some reference",
                "en_ES",
                "jkasdhjkashdkj"
        );
        mockMvc.perform(put(getBaseUrl() + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(partnerDTO))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testPutPartnerNotFound() throws Exception {
        PartnerDTO partnerDTO = new PartnerDTO(
                null,
                "some name",
                "some reference",
                "en_ES",
                "2017-10-03T12:18:46+01:00"
        );
        mockMvc.perform(put(getBaseUrl() + "/1123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(partnerDTO))
        ).andExpect(status().isNotFound());
    }

    @Test
    public void testDeletePartner() throws Exception {
        mockMvc.perform(delete(getBaseUrl() + "/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePartnerNotFound() throws Exception {
        mockMvc.perform(delete(getBaseUrl() + "/234"))
                .andExpect(status().isNotFound());
    }

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/partner";
    }
}
