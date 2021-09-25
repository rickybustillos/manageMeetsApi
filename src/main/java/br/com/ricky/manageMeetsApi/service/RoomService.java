package br.com.ricky.manageMeetsApi.service;

import br.com.ricky.manageMeetsApi.dto.RoomDTO;
import br.com.ricky.manageMeetsApi.exception.ResourceNotFoundException;
import br.com.ricky.manageMeetsApi.mapper.RoomMapper;
import br.com.ricky.manageMeetsApi.model.Room;
import br.com.ricky.manageMeetsApi.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper = RoomMapper.INSTANCE;

    public List<RoomDTO> getAll() {
        return roomRepository.findAll()
                .stream()
                .map(roomMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RoomDTO getById(Long id) throws ResourceNotFoundException {
        Room room = verifyIfExists(id);
        return roomMapper.toDTO(room);
    }

    public RoomDTO create(RoomDTO roomDTO) {
        Room room = roomMapper.toModel(roomDTO);
        Room savedRoom = roomRepository.save(room);
        return roomMapper.toDTO(savedRoom);
    }

    public RoomDTO update(Long id, RoomDTO roomDTO) throws ResourceNotFoundException {
        verifyIfExists(id);

        Room room = roomMapper.toModel(roomDTO);
        Room updatedRoom = roomRepository.save(room);

        return roomMapper.toDTO(updatedRoom);
    }

    public Map<String, Boolean> delete(Long id) throws ResourceNotFoundException {
        verifyIfExists(id);

        // If all it's ok, will delete the room
        roomRepository.deleteById(id);

        // If was successfully deleted, creating a custom return alerting that deleted = true
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    private Room verifyIfExists(Long id) throws ResourceNotFoundException {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID "+ id));
    }
}
