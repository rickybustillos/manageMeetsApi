package br.com.ricky.manageMeetsApi.builder;

import br.com.ricky.manageMeetsApi.dto.RoomDTO;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
public class RoomDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Angular meet";

    @Builder.Default
    private String date = "25/09/2021";

    @Builder.Default
    private String startHour = "19h";

    @Builder.Default
    private String endHour = "20h30";

    public RoomDTO toRoomDTO() {
        return new RoomDTO(
                id,
                name,
                date,
                startHour,
                endHour
        );
    }
}
