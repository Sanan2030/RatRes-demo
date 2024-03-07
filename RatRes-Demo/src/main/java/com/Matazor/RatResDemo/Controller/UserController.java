package com.Matazor.RatResDemo.Controller;
import com.Matazor.RatResDemo.Dto.RateRequest;
import com.Matazor.RatResDemo.Dto.UserRequest;
import com.Matazor.RatResDemo.Entity.RestaurantRates;
import com.Matazor.RatResDemo.Entity.User;
import com.Matazor.RatResDemo.Event.RegistrationCompleteEvent;
import com.Matazor.RatResDemo.Service.UserService;
import com.Matazor.RatResDemo.token.VerificationToken;
import com.Matazor.RatResDemo.token.VerificationTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class UserController {

   private final UserService userService;
   private final ApplicationEventPublisher publisher;
   private final VerificationTokenRepository tokenRepository;



    @PostMapping()
    public ResponseEntity<User> createuser(@RequestBody UserRequest userRequest, final HttpServletRequest request) {
        User createdUser = userService.create(userRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(createdUser, applicationUrl(request)));
        return ResponseEntity.ok(createdUser);
    }
    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken = tokenRepository.findByToken(token);
        if (theToken.getUser().isEnabled()){
            return "This account has already been verified, please, login.";
        }
        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully. Now you can login to your account";
        }
        return "Invalid verification token";
    }
    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
    @PostMapping("/rate/{id}")
    public ResponseEntity<RestaurantRates> rate(@PathVariable Long id, @RequestBody RateRequest rateRequest) {
        RestaurantRates rates=userService.rate(rateRequest,id);


        return  ResponseEntity.ok(rates);
    }


}
