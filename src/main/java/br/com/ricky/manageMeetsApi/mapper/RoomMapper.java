package br.com.ricky.manageMeetsApi.mapper;

import br.com.ricky.manageMeetsApi.dto.RoomDTO;
import br.com.ricky.manageMeetsApi.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    Room toModel(RoomDTO roomDTO);

    RoomDTO toDTO(Room room);
}
