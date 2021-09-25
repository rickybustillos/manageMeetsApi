package br.com.ricky.manageMeetsApi.controller;

import br.com.ricky.manageMeetsApi.dto.RoomDTO;
import br.com.ricky.manageMeetsApi.exception.ResourceNotFoundException;
import br.com.ricky.manageMeetsApi.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/rooms")
    public List<RoomDTO> getAll() {
        return roomService.getAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<RoomDTO> getById(@PathVariable Long id) throws ResourceNotFoundException {
        var roomDTO = roomService.getById(id);
        return ResponseEntity.ok().body(roomDTO);
    }

    @PostMapping("/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDTO create(@Valid @RequestBody RoomDTO roomDTO) {
        return roomService.create(roomDTO);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<RoomDTO> update(@PathVariable Long id, @RequestBody @Valid RoomDTO roomDTO) throws ResourceNotFoundException {
        var updatedRoom = roomService.update(id, roomDTO);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> delete(@PathVariable Long id) throws ResourceNotFoundException {
        var response = roomService.delete(id);
        return response;
    }
}
