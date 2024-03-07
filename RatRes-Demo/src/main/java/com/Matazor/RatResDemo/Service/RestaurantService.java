package com.Matazor.RatResDemo.Service;

import com.Matazor.RatResDemo.Dto.RestaurantRequest;
import com.Matazor.RatResDemo.Entity.Restaurant;
import com.Matazor.RatResDemo.Entity.RestaurantRates;

import java.util.List;

public interface RestaurantService {

    Restaurant create(RestaurantRequest restaurantRequest);

    List<RestaurantRates> getRatings(Long restaurantId);
}
