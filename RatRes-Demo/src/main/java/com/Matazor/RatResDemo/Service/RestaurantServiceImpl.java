package com.Matazor.RatResDemo.Service;

import com.Matazor.RatResDemo.Dto.RestaurantRequest;
import com.Matazor.RatResDemo.Entity.Restaurant;
import com.Matazor.RatResDemo.Entity.RestaurantRates;
import com.Matazor.RatResDemo.Entity.Role;
import com.Matazor.RatResDemo.Repository.RateRepository;
import com.Matazor.RatResDemo.Repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final EmailValidator emailValidator;
   private final RestaurantRepository restaurantRepository;
   private final RateRepository rateRepository;

    @Override
    public Restaurant create(RestaurantRequest restaurantRequest) {
        boolean isValid = emailValidator.test(restaurantRequest.getEmail());
        if (!isValid) {
            throw new IllegalStateException("Email is not valid");
        }

        Restaurant existingRestaurant= restaurantRepository.findByEmail(restaurantRequest.getEmail());
        if (existingRestaurant != null) {
            throw new IllegalStateException("Account already exists");
        }

       Restaurant newRestaurant=Restaurant.builder()
               .name(restaurantRequest.getName())
               .email(restaurantRequest.getEmail())
               .role(Role.Owner)
               .MenuLink(restaurantRequest.getMenuLink())
               .overallRate(0)
               .SocialAccounts(restaurantRequest.getSocialAccounts())
               .phoneNumber(restaurantRequest.getPhoneNumber())
               .phoneNumber2(restaurantRequest.getPhoneNumber2())
               .Location(restaurantRequest.getLocation())
               .build();
        restaurantRepository.save(newRestaurant);
        return newRestaurant;

    }

    @Override
    public List<RestaurantRates> getRatings(Long restaurantId) {
        double overallAverageRate = calculateOverallAverageRate();
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Overall Average Rate: " + df.format(overallAverageRate));

        return  rateRepository.findAll();
    }
    private double calculateOverallAverageRate() {
        Iterable<RestaurantRates> allRates = rateRepository.findAll();
        double sum = 0;
        int count = 0;
        for ( RestaurantRates rate : allRates) {
            sum += rate.getOverall();
            count++;
        }
        return count > 0 ? sum / count : 0;
    }
}

