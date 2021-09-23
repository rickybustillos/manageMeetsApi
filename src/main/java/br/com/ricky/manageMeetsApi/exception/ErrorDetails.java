package br.com.ricky.manageMeetsApi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails extends Exception {

    private Date timestamp;
    private String message;
    private String details;

}
