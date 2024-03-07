package com.Matazor.RatResDemo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="RestaurantRates")
public class RestaurantRates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    private Long restaurant_id;
    private String username;
    private int food_q;
    private int service_q;
    private int atmosphere;
    private int money_performance;
    private float overall;
    private String note;

}
