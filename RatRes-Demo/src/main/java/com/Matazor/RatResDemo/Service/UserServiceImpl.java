package com.Matazor.RatResDemo.Service;

import com.Matazor.RatResDemo.Dto.RateRequest;
import com.Matazor.RatResDemo.Dto.UserRequest;
import com.Matazor.RatResDemo.Entity.RestaurantRates;
import com.Matazor.RatResDemo.Entity.Role;
import com.Matazor.RatResDemo.Entity.User;
import com.Matazor.RatResDemo.Repository.RateRepository;
import com.Matazor.RatResDemo.Repository.UserRepository;

import com.Matazor.RatResDemo.token.VerificationToken;
import com.Matazor.RatResDemo.token.VerificationTokenRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Calendar;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

   private final UserRepository userRepository;
   private final EmailValidator emailValidator;
   private final VerificationTokenRepository tokenRepository;
   private final RateRepository rateRepository;

    @Override
    public User create(UserRequest userRequest) {
        boolean isValid = emailValidator.test(userRequest.getEmail());
        if (!isValid) {
            throw new IllegalStateException("Email is not valid");
        }

        User existingUser = userRepository.findByEmail(userRequest.getEmail());
        if (existingUser != null) {
            throw new IllegalStateException("Account already exists");
        }

        User newUser = User.builder()
                .username(userRequest.getUsername())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .gender(userRequest.getGender())
                .role(Role.User)
                .build();
        userRepository.save(newUser);
        return newUser;

    }
    @Override
    public void saveUserVerificationToken(User theUser, String token) {
        var verificationToken = new VerificationToken(token, theUser);
        tokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String theToken) {
        VerificationToken token = tokenRepository.findByToken(theToken);
        if(token == null){
            return "Invalid verification token";
        }
        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
            tokenRepository.delete(token);
            return "Token already expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public RestaurantRates rate(RateRequest rateRequest, Long id) {
        User user = userRepository.findById(rateRequest.getUser_id()).orElseThrow(() -> new RuntimeException("User not found"));
        RestaurantRates rate= RestaurantRates.builder()
                .username(user.getUsername())
                .restaurant_id(id)
                .food_q(rateRequest.getFood_q())
                .overall(calculateOverall(rateRequest))
                .atmosphere(rateRequest.getAtmosphere())
                .service_q(rateRequest.getService_q())
                .money_performance(rateRequest.getMoney_performance())
                .note(rateRequest.getNote())
                .build();
        rateRepository.save(rate);
        return rate;
    }
    public float calculateOverall(RateRequest rateRequest) {
        return (rateRequest.getService_q() + rateRequest.getFood_q() + rateRequest.getMoney_performance() + rateRequest.getAtmosphere()) /4.0f;
    }
}
