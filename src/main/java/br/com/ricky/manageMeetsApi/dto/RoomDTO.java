package br.com.ricky.manageMeetsApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    private Long id;

    @NotNull
    @Size(min = 2, max = 255)
    private String name;

    @NotNull
    @Size(min = 10, max = 10)
    private String date;

    @NotNull
    @Size(min = 2, max = 5)
    private String startHour;

    @NotNull
    @Size(min = 2, max = 5)
    private String endHour;
}
