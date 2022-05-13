package com.cts.company.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;
    private String companyCode;
    @NonNull
    private String companyName;
    private String companyCEO;
    private long companyTurnover;
    private String companyWebsite;
    private String stockExchange;
}
