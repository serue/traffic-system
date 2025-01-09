package com.beymo.traffic.offence.controller;

import com.beymo.traffic.offence.dto.FineRequest;
import com.beymo.traffic.offence.dto.OffenceRequest;
import com.beymo.traffic.offence.dto.OffenceResponse;
import com.beymo.traffic.offence.exception.OffenceNotFoundException;
import com.beymo.traffic.offence.model.Offence;
import com.beymo.traffic.offence.service.OffenceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OffenceController.class)
@AutoConfigureMockMvc
class OffenceControllerTest {

    @MockitoBean
    private OffenceService offenceService;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private List<OffenceResponse> offences = new ArrayList<>();


    @BeforeEach
    void setUp() {
        OffenceResponse offence1 =   new OffenceResponse(
                1L,
                "51 2(a(iii))",
                "Proceeding against red robot",
                30.34
        );


        OffenceResponse offence2 = new OffenceResponse(
                2L,
                "32 1(a)",
                "Driving without a driver's license",
                60.24
        );
        offences.add(offence1);
        offences.add(offence2);

    }

    @Test
    void shouldReturnOffences() throws Exception {
        when(offenceService.findAll()).thenReturn(offences);
        mockMvc.perform(get("/offences")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(offences.size()))
                .andExpect(jsonPath("$[0].title").value("Proceeding against red robot"))
                .andExpect(jsonPath("$[1].title").value("Driving without a driver's license"));
    }

    @Test
    void shouldFindOffenceWhenGivenValidId() throws Exception {
        var offenceId = 1L;
        when(offenceService.findById(offenceId)).thenReturn(offences.get(0));

        var offence = offences.get(0);
        var json = """
            {
                "id": %d,
                "section": "%s",
                "title": "%s",
                "fine": %.2f
            }
            """.formatted(
                offence.id(),
                offence.section(),
                offence.title(),
                offence.fine()
        );

        mockMvc.perform(get("/offences/{offence-id}", offenceId))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }


    @Test
    void shouldNotFindOffenceWhenGivenInvalidID() throws Exception {
        var offenceId = 999L;
        when(offenceService.findById(offenceId)).thenThrow(OffenceNotFoundException.class);

        mockMvc.perform(get("/{offence-id}", offenceId))
                .andExpect(status().isNotFound());
    }
    @Test
    void shouldCreateOffence() throws Exception {
        // Arrange: Prepare request and expected response
        OffenceRequest offenceRequest = new OffenceRequest(
                null,
                "51 2(a(iii))",
                "Proceeding against red robot",
                30.34
        );

        OffenceResponse offenceResponse = new OffenceResponse(
                1L,
                "51 2(a(iii))",
                "Proceeding against red robot",
                30.34
        );

        when(offenceService.createOffence(any(OffenceRequest.class))).thenReturn(offenceResponse);

        // Act & Assert: Send POST request and verify response
        mockMvc.perform(post("/offences")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(offenceRequest)))
                .andExpect(status().isCreated()) // Assuming your controller returns 201 CREATED
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.section").value("51 2(a(iii))"))
                .andExpect(jsonPath("$.title").value("Proceeding against red robot"))
                .andExpect(jsonPath("$.fine").value(30.34));

        // Verify that the service method was called once
        verify(offenceService, Mockito.times(1)).createOffence(any(OffenceRequest.class));
    }
    @Test
    void shouldNotCreateNewOffenceWhenOffenceIsInvalid() throws Exception {
        // Create an invalid OffenceRequest (missing required fields or invalid values)
        var offenceRequest = new OffenceRequest(
                null, // Assuming the id should be auto-generated
                "",   // Invalid: empty section
                "",   // Invalid: empty title
                -5.00 // Invalid: negative fine
        );

        // Convert OffenceRequest to JSON
        var json = """
    {
        "id": %s,
        "section": "%s",
        "title": "%s",
        "fine": %f
    }
    """.formatted(
                offenceRequest.id(),
                offenceRequest.section(),
                offenceRequest.title(),
                offenceRequest.fine()
        );

        // Perform the POST request to create an Offence
        mockMvc.perform(post("/offences")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest()); // Expect BadRequest (400) for invalid data
    }

    // update

    @Test
    void updateOffenceCompletely() throws Exception {
        // Arrange
        Long offenceId = 1L;
        OffenceRequest updatedOffenceRequest = new OffenceRequest(
                offenceId,
                "32 1(a)",  // Updated section
                "Driving without a driver's license" ,
                60.24
        );

        Offence originalOffence = new Offence(offenceId, "51 2(a(iii))", "Proceeding against red robot", 30.34);
        Offence updatedOffence = new Offence(offenceId, "32 1(a)", "Driving without a driver's license", 60.24);

        // Mock repository and service behavior
        when(offenceService.updateOffence(eq(offenceId), any(OffenceRequest.class))).thenReturn(
                new OffenceResponse(offenceId, "32 1(a)", "Driving without a driver's license", 60.24)
        );

        // Convert OffenceRequest to JSON for the HTTP request
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(updatedOffenceRequest);

        // Act and Assert: Perform the PUT request and verify the results
        mockMvc.perform(put("/offences/{offence-id}", offenceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk()) // Expect 200 OK status for successful update
                .andExpect(jsonPath("$.id").value(offenceId)) // Verify the returned offence ID
                .andExpect(jsonPath("$.section").value("32 1(a)")) // Verify the updated section
                .andExpect(jsonPath("$.title").value("Driving without a driver's license")) // Verify the updated title
                .andExpect(jsonPath("$.fine").value(60.24)); // Verify the updated fine

        // Verify interactions with the service
        verify(offenceService).updateOffence(eq(offenceId), any(OffenceRequest.class));
    }


    @Test
    void shouldUpdateOffenceFineOnly() throws Exception {
        // Arrange
        Long offenceId = 1L;
        Double updatedFine = 50.00;
        FineRequest fineRequest = new FineRequest(offenceId, updatedFine);
        OffenceResponse updatedResponse = new OffenceResponse(
                offenceId, "51 2(a(iii))", "Proceeding against red robot", updatedFine
        );

        // Mock service behavior
        when(offenceService.updateFine(offenceId, fineRequest)).thenReturn(updatedResponse);

        // Convert request object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(fineRequest);

        // Act & Assert
        mockMvc.perform(patch( "/offences/{offence-id}/fine", offenceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk()) // Verify HTTP 200 OK status
                .andExpect(jsonPath("$.id").value(offenceId)) // Verify ID
                .andExpect(jsonPath("$.section").value("51 2(a(iii))")) // Verify section
                .andExpect(jsonPath("$.title").value("Proceeding against red robot")) // Verify title
                .andExpect(jsonPath("$.fine").value(updatedFine)); // Verify updated fine

        // Verify interaction with the service
        verify(offenceService).updateFine(offenceId, fineRequest);
    }


}