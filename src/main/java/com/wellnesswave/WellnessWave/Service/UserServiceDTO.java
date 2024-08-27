package com.wellnesswave.WellnessWave.Service;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Entities.Patient;
import com.wellnesswave.WellnessWave.Repository.DoctorRepository;
import com.wellnesswave.WellnessWave.Repository.PatientRepository;
import com.wellnesswave.WellnessWave.Utils.Result;
import com.wellnesswave.WellnessWave.Utils.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDTO {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public Result logIn(String username, String password){
        Result result = new Result();
        Doctor doc = doctorRepository.findByDocUsernameAndPassword(username, password);
        Patient pat = patientRepository.findByPatUsernameAndPatPassword(username, password);
        if (doc != null ){
            UserSession userSession = new UserSession(doc);
            boolean userExists = userSession.tryLogIn();
            if (doc.getDocUsername().equals(username) && doc.getPassword().equals(password)){
                System.out.println("[D] LogIn: UserServiceDTO -> The User Is : " + userExists + " " + userSession.getDocSessionJSON());
                System.out.println("[D] LogIn: UserServiceDTO -> Return statement");
                result = new Result(0, userSession.getDocSessionJSON());
            }else if (doc == null){
                System.out.println("[D] LogIn: UserServiceDTO -> userExists == False");
                result = new Result(1, "User doesn't exist! Check your credentials");
            }
        } else if (pat != null) {
            if (pat.getPatUsername().equals(username) && pat.getPatPassword().equals(password)){
                UserSession userSession = new UserSession(pat);
                boolean userExists = userSession.tryLogIn();
                System.out.println("[P] LogIn: UserServiceDTO -> The User Is : " + userExists + " " + userSession.getPatSessionJSON());
                System.out.println("[P] LogIn: UserServiceDTO -> Return statement");
                return new Result(0, userSession.getPatSessionJSON());
            }else if (pat == null){
                System.out.println("[P] LogIn: UserServiceDTO -> userExists == FALSE");
                return new Result(1, "User doesn't exist. Check your username and password");
            }
        }
        return result;
    }
}
