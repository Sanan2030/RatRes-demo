package com.Matazor.RatResDemo.Repository;

import com.Matazor.RatResDemo.Entity.RestaurantRates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<RestaurantRates,Long> {

}
