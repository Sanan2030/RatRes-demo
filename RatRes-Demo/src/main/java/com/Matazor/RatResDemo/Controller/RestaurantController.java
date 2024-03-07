package com.Matazor.RatResDemo.Controller;

import com.Matazor.RatResDemo.Dto.RestaurantRequest;
import com.Matazor.RatResDemo.Entity.Restaurant;
import com.Matazor.RatResDemo.Entity.RestaurantRates;
import com.Matazor.RatResDemo.Service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class RestaurantController {

   private final RestaurantService restaurantService;
    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        Restaurant createdRes = restaurantService.create(restaurantRequest);

        return ResponseEntity.ok(createdRes);
    }
    @GetMapping("/Rating/{restaurant_id}")
    public List<RestaurantRates> showRates(@PathVariable Long restaurant_id){

         return restaurantService.getRatings(restaurant_id);

    }

    }