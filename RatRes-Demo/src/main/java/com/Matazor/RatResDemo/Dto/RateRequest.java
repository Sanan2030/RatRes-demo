package com.Matazor.RatResDemo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateRequest {

    private Long user_id;
    private int food_q;
    private int service_q;
    private int atmosphere;
    private int money_performance;
    private String note;
}
