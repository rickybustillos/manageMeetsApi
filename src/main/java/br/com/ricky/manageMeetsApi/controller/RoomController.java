package br.com.ricky.manageMeetsApi.controller;

import br.com.ricky.manageMeetsApi.exception.ResourceNotFoundException;
import br.com.ricky.manageMeetsApi.model.Room;
import br.com.ricky.manageMeetsApi.repository.RoomRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getById(@PathVariable Long roomId) throws ResourceNotFoundException {
        // Searching room by ID, if it returns an error it will return an Exception
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID " + roomId));
        return ResponseEntity.ok(room);
    }

    @PostMapping("/rooms")
    public Room create(@Valid @RequestBody Room room) {
        return roomRepository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> update(@PathVariable Long roomId, @Valid @RequestBody Room roomDetails) throws ResourceNotFoundException {
        // Searching room by ID, if it returns an error it will return an Exception
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID " + roomId));

        // If all it's ok, will update the room found with the new values
        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStartHour(roomDetails.getStartHour());
        room.setEndHour(roomDetails.getEndHour());

        // Persisting the updated room
        final Room updatedRoom = roomRepository.save(room);

        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> delete(@PathVariable Long roomId) throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID "+ roomId));

        // If all it's ok, will delete the room
        roomRepository.delete(room);

        // If was successfully deleted, creating a custom return alerting that deleted = true
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
