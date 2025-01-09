package com.beymo.traffic.offence.service;

import com.beymo.traffic.offence.dto.FineRequest;
import com.beymo.traffic.offence.dto.OffenceRequest;
import com.beymo.traffic.offence.dto.OffenceResponse;
import com.beymo.traffic.offence.exception.OffenceNotFoundException;
import com.beymo.traffic.offence.mapper.OffenceMapper;
import com.beymo.traffic.offence.model.Offence;
import com.beymo.traffic.offence.repository.OffenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class OffenceServiceImplTest {

    @Mock
    private OffenceRepository offenceRepository;
    @Mock
    private OffenceMapper mapper;

    @InjectMocks
    private OffenceServiceImpl offenceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Manually initialize the mocks
    }

    @Test
    void shouldGetAllOffences() {
        // Arrange: Create test data
        Offence offence1 =   new Offence(
                1L,
                "51 2(a(iii))",
                "Proceeding against red robot",
                30.34
        );


        Offence offence2 = new Offence(
                2L,
                "32 1(a)",
                "Driving without a driver's license",
                60.24
        );



        List<Offence> offences = Arrays.asList(offence1, offence2);

        // Mock the repository call
        when(offenceRepository.findAll()).thenReturn(offences);
        when(mapper.toOffenceResponse(offence1))
                .thenReturn(new OffenceResponse(1L, "51 2(a(iii))", "Proceeding against red robot", 30.34));
        when(mapper.toOffenceResponse(offence2))
                .thenReturn(new OffenceResponse(2L, "32 1(a)", "Driving without a driver's license", 60.24));

        // Act: Call the service method
        List<OffenceResponse> result = offenceService.findAll();

        // Assert: Verify the expected result
        assertNotNull(result);
        assertEquals(2, result.size());

        // Assert the first offence data
        assertEquals(1L, result.get(0).id());
        assertEquals("51 2(a(iii))", result.get(0).section());
        assertEquals("Proceeding against red robot", result.get(0).title());

        // Assert the second offence data
        assertEquals(2L, result.get(1).id());
        assertEquals("32 1(a)", result.get(1).section());
        assertEquals("Driving without a driver's license", result.get(1).title());
    }

    @Test
    void shouldFindOffenceById() {
        // Arrange: Create test data
        Long offenceId = 1L;
        Offence offence = new Offence(
                offenceId,
                "51 2(a(iii))",
                "Proceeding against red robot",
                30.34
        );

        // Mock the repository call
        when(offenceRepository.findById(offenceId)).thenReturn(Optional.of(offence));
        when(mapper.toOffenceResponse(offence))
                .thenReturn(new OffenceResponse(offenceId, "51 2(a(iii))", "Proceeding against red robot", 30.34));

        // Act: Call the service method
        OffenceResponse result = offenceService.findById(offenceId);

        // Assert: Verify the expected result
        assertNotNull(result);
        assertEquals(offenceId, result.id());
        assertEquals("51 2(a(iii))", result.section());
        assertEquals("Proceeding against red robot", result.title());
    }

    @Test
    void shouldThrowOffenceNotFoundExceptionWhenOffenceDoesNotExist() {
        // Arrange: Mock the repository to return an empty Optional
        Long nonExistentId = 999L;
        when(offenceRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert: Verify that the custom exception is thrown
        OffenceNotFoundException exception = assertThrows(
                OffenceNotFoundException.class,
                () -> offenceService.findById(nonExistentId)
        );

        // Assert: Verify the exception message
        assertEquals("No offence found with the provided id::"+ nonExistentId, exception.getMessage());
    }

    @Test
    void shouldDeleteOffenceById() {
        // Arrange: Create test data
        Long offenceId = 1L;
        Offence offence = new Offence(
                offenceId,
                "51 2(a(iii))",
                "Proceeding against red robot",
                30.34
        );

        // Act: Call the service method to delete the offence by ID
        offenceService.deleteOffenceById(offenceId);

        // Assert: Verify the repository's deleteById method was called with the correct ID
        verify(offenceRepository, times(1)).deleteById(offenceId); // Verify that deleteById was called exactly once

        // You can also add an assertion to check the side-effects in the service (if any)
        // For example, you could check if any specific internal state is updated, though not necessary for this basic test.
    }

    @Test
    void shouldCreateOffence() {
        // Arrange
        OffenceRequest offenceRequest = new OffenceRequest(
                null,
                "51 2(a(iii))",
                "Proceeding against red robot",
                30.34
        );

        Offence offence = new Offence(
                null,
                "51 2(a(iii))",
                "Proceeding against red robot",
                30.34
        );

        when(mapper.toOffence(any(OffenceRequest.class))).thenReturn(offence);
        when(offenceRepository.save(any(Offence.class))).thenReturn(new Offence(1L, "51 2(a(iii))", "Proceeding against red robot", 30.34));

        OffenceResponse offenceResponse = new OffenceResponse(1L, "51 2(a(iii))", "Proceeding against red robot", 30.34);
        when(mapper.toOffenceResponse(any(Offence.class))).thenReturn(offenceResponse);

        // Act
        OffenceResponse result = offenceService.createOffence(offenceRequest);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(1L, result.id(), "The generated ID should be 1L");
        assertEquals("51 2(a(iii))", result.section(), "The section should match");
        assertEquals("Proceeding against red robot", result.title(), "The title should match");

        verify(mapper, times(1)).toOffence(any(OffenceRequest.class));
        verify(offenceRepository, times(1)).save(any(Offence.class));
        verify(mapper, times(1)).toOffenceResponse(any(Offence.class));
    }


    @Test
    void updateOffenceCompletely() {
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

        // Mock repository and mapper calls
        when(offenceRepository.findById(offenceId)).thenReturn(Optional.of(originalOffence));
        when(mapper.updateOffence(originalOffence, updatedOffenceRequest)).thenReturn(updatedOffence);
        when(offenceRepository.save(updatedOffence)).thenReturn(updatedOffence);
        when(mapper.toOffenceResponse(updatedOffence)).thenReturn(new OffenceResponse(offenceId, "32 1(a)", "Driving without a driver's license", 60.24));

        // Act
        OffenceResponse result = offenceService.updateOffence(offenceId, updatedOffenceRequest);

        // Assert
        assertNotNull(result);
        assertEquals(offenceId, result.id());
        assertEquals("32 1(a)", result.section());
        assertEquals("Driving without a driver's license", result.title());

        verify(offenceRepository).findById(offenceId);
        verify(mapper).updateOffence(originalOffence, updatedOffenceRequest);
        verify(offenceRepository).save(updatedOffence);
        verify(mapper).toOffenceResponse(updatedOffence);
    }


    @Test
    void shouldThrowExceptionWhenOffenceNotFoundForPut() {
        // Arrange: Prepare the test data
        Long offenceId = 999L; // ID that does not exist
        OffenceRequest offenceRequest = new OffenceRequest(
                offenceId,
                "51 2(a(iii))",
                "Proceeding against red robot",
                30.34
        );

        // Mock the repository to throw an exception when the offence ID is not found
        when(offenceRepository.findById(offenceId)).thenReturn(Optional.empty());

        // Act & Assert: Verify that the correct exception is thrown with the expected message
        Exception exception = assertThrows(
                OffenceNotFoundException.class,
                () -> offenceService.updateOffence(offenceId, offenceRequest)
        );

        // Assert: Verify the exception message
        assertEquals("update fail, no offence found with the provided id::"+offenceId, exception.getMessage());
    }


    @Test
    void updateOffenceFineOnly() {
        // Arrange
        Long offenceId = 1L; // Existing offence ID
        Double updatedFine = 50.00; // New fine value
        FineRequest fineRequest = new FineRequest(offenceId, updatedFine);
        Offence originalOffence = new Offence(offenceId, "51 2(a(iii))", "Proceeding against red robot", 30.34);
        Offence updatedOffence = new Offence(offenceId, "51 2(a(iii))", "Proceeding against red robot", updatedFine);

        // Mock repository and mapper calls
        when(offenceRepository.findById(offenceId)).thenReturn(Optional.of(originalOffence));
        when(offenceRepository.save(originalOffence)).thenReturn(updatedOffence);
        when(mapper.toOffenceResponse(updatedOffence)).thenReturn(
                new OffenceResponse(offenceId, "51 2(a(iii))", "Proceeding against red robot", updatedFine)
        );

        // Act
        OffenceResponse result = offenceService.updateFine(offenceId, fineRequest);

        // Assert
        assertNotNull(result);
        assertEquals(offenceId, result.id());
        assertEquals("51 2(a(iii))", result.section());
        assertEquals("Proceeding against red robot", result.title());
        assertEquals(updatedFine, result.fine());

        verify(offenceRepository).findById(offenceId);
        verify(offenceRepository).save(originalOffence);
        verify(mapper).toOffenceResponse(updatedOffence);
    }

    @Test
    void shouldThrowExceptionWhenOffenceNotFoundForPatch() {
        // Arrange
        Long offenceId = 999L; // Non-existent offence ID
        Double updatedFine = 50.00; // Fine to be updated
        FineRequest fineRequest = new FineRequest(offenceId, updatedFine);

        // Mock the repository to return an empty Optional (offence not found)
        when(offenceRepository.findById(offenceId)).thenReturn(Optional.empty());

        // Act & Assert: Verify that an exception is thrown when the offence does not exist
        Exception exception = assertThrows(
                OffenceNotFoundException.class,
                () -> offenceService.updateFine(offenceId, fineRequest)
        );

        // Assert: Verify the exception message
        assertEquals("No offence found with ID: " + offenceId, exception.getMessage());
    }

}
