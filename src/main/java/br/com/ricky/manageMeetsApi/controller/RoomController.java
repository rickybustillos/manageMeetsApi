package br.com.ricky.manageMeetsApi.controller;

import br.com.ricky.manageMeetsApi.exception.ResourceNotFoundException;
import br.com.ricky.manageMeetsApi.model.Room;
import br.com.ricky.manageMeetsApi.repository.RoomRepository;
import br.com.ricky.manageMeetsApi.service.RoomService;
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

    private RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getAll() {
        return roomService.getAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getById(@PathVariable Long id) throws ResourceNotFoundException {
        var room = roomService.getById(id);
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room create(@Valid @RequestBody Room room) {
        return roomService.create(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> update(@PathVariable Long id, @RequestBody @Valid Room roomDetails) throws ResourceNotFoundException {
        var updatedRoom = roomService.update(id, roomDetails);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> delete(@PathVariable Long id) throws ResourceNotFoundException {
        var response = roomService.delete(id);
        return response;
    }
}
