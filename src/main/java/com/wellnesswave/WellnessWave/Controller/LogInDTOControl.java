package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Service.UserServiceDTO;
import com.wellnesswave.WellnessWave.Utils.LogInDTO;
import com.wellnesswave.WellnessWave.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInDTOControl {
    @Autowired
    private UserServiceDTO userServiceDTO;

    @PostMapping("/logIN")
    public ResponseEntity<Result> logIN(@RequestBody LogInDTO logInDTO){
        Result result = userServiceDTO.logIn(logInDTO.getUsername(), logInDTO.getPassword());
        System.out.println(">> Print of result in controller -> " + result.toString()); //TEST
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
