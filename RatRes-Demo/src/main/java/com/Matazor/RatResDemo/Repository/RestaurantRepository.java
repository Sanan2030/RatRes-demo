package com.Matazor.RatResDemo.Repository;

import com.Matazor.RatResDemo.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Restaurant findByEmail(String email);


}
