package com.Matazor.RatResDemo.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String phoneNumber;
    private String email;
    private @Nullable String phoneNumber2;
    private @Nullable String SocialAccounts;
    private @Nullable String MenuLink;
    private float overallRate;
    private String Location;
    Role role;





}
