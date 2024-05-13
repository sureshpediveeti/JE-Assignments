package com.javaexpress.loans.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoansDto{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long loanId;

    private String mobileNumber;
    private Long loanNumber;
    private String loanType;
    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;
}
