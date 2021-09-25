package br.com.ricky.manageMeetsApi.service;

import br.com.ricky.manageMeetsApi.builder.RoomDTOBuilder;
import br.com.ricky.manageMeetsApi.dto.RoomDTO;
import br.com.ricky.manageMeetsApi.mapper.RoomMapper;
import br.com.ricky.manageMeetsApi.model.Room;
import br.com.ricky.manageMeetsApi.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    private static final long INVALID_ROOM_ID = 1L;

    @Mock
    private RoomRepository roomRepository;

    private RoomMapper roomMapper = RoomMapper.INSTANCE;

    @InjectMocks
    private RoomService roomService;

    @Test
    void whenRoomInformedThenItShouldBeCreated() {
        // Given
        RoomDTO roomDTO = RoomDTOBuilder.builder().build().toRoomDTO();
        Room expectedSavedRoom = roomMapper.toModel(roomDTO);

        // When
        Mockito.when(roomRepository.save(expectedSavedRoom)).thenReturn(expectedSavedRoom);

        // Then
        RoomDTO createdRoomDTO = roomService.create(roomDTO);

        assertEquals(roomDTO.getId(), createdRoomDTO.getId());
        assertEquals(roomDTO.getName(), createdRoomDTO.getName());
    }

}
