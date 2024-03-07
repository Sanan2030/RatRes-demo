package com.Matazor.RatResDemo.Repository;

import com.Matazor.RatResDemo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);



}
