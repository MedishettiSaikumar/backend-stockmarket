package com.cts.company.response;

import com.cts.company.model.Company;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class CompanySaveResponse extends AbstractResponse{
    private Company company;

}
