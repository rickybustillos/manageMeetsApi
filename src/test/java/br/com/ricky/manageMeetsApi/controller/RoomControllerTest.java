package br.com.ricky.manageMeetsApi.controller;

import br.com.ricky.manageMeetsApi.builder.RoomDTOBuilder;
import br.com.ricky.manageMeetsApi.dto.RoomDTO;
import br.com.ricky.manageMeetsApi.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static br.com.ricky.manageMeetsApi.utils.JsonConversionUtils.asJsonString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RoomControllerTest {

    private static final String ROOM_API_URL_PATH = "/api/v1/rooms";
    private static final long VALID_ROOM_ID = 1L;
    private static final long INVALID_ROOM_ID = 2L;

    private MockMvc mockMvc;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(roomController)
                .setViewResolvers(((s, locale) -> new MappingJackson2JsonView()))
                .build();
    }

    @Test
    void whenPOSTIsCalledThenARoomIsCreated() throws Exception {
        // Given
        RoomDTO roomDTO = RoomDTOBuilder.builder().build().toRoomDTO();

        // When
        Mockito.when(roomService.create(roomDTO)).thenReturn(roomDTO);

        // Then
        mockMvc.perform(post(ROOM_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(roomDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(roomDTO.getName())))
                .andExpect(jsonPath("$.date", is(roomDTO.getDate())))
                .andExpect(jsonPath("$.startHour", is(roomDTO.getStartHour())))
                .andExpect(jsonPath("$.endHour", is(roomDTO.getEndHour().toString())));
    }

}
