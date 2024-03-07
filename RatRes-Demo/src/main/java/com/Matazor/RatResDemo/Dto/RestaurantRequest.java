package com.Matazor.RatResDemo.Dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequest {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private @Nullable String phoneNumber2;
    private @Nullable String SocialAccounts;
    private @Nullable String MenuLink;
    private String Location;


}
