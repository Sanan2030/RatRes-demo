package com.Matazor.RatResDemo.Service;

import com.Matazor.RatResDemo.Dto.RateRequest;
import com.Matazor.RatResDemo.Dto.UserRequest;
import com.Matazor.RatResDemo.Entity.RestaurantRates;
import com.Matazor.RatResDemo.Entity.User;


public interface UserService {

    User create(UserRequest userRequest);
    void saveUserVerificationToken(User theUser, String token);

    String validateToken(String token);

    RestaurantRates rate(RateRequest rateRequest, Long id);
}
