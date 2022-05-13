package com.cts.company.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@ToString
public class AbstractResponse {

    private HttpStatus httpstatus;
    private String status;
    private String message;

}

