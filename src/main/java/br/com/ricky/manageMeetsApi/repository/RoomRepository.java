package br.com.ricky.manageMeetsApi.repository;


import br.com.ricky.manageMeetsApi.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
