package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Entities.Appointments;
import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Service.AppointmentsService;
import com.wellnesswave.WellnessWave.Utils.Result;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AppointmentsController {
    //CRUD Operations Implemenation
    @Autowired
    private AppointmentsService appointService;

    @GetMapping("/getAllAppointments")
    public List<Appointments> getAllAppointments(){
        return appointService.getAllAppointments();
    }

    @GetMapping("/getAppointById/{id}")
    public Appointments getAppointById(@PathVariable Integer id){
        return appointService.getAppointById(id);
    }

    @PostMapping("/createAppointments")
    public ResponseEntity<Result> createAppointments(@RequestBody Appointments appoint){
        System.out.println(">>> Test Print : CONTROLLER ");
        System.out.println(appoint.toString());
        return new ResponseEntity<Result>(appointService.createAppointment(appoint), HttpStatus.CREATED);
    }

    @PostMapping("/getSpecialisedDoc")
    public ResponseEntity<Result> getSpecialisedDoc(@RequestParam("profession") String profession){
        //TODO: here we have an issue-> what type do i want to be returned from service Result or Doctor???
        //TODO: is this a get or a post request ???

        Result docResult = appointService.getSpecialisedDoc(profession);
        System.out.println("Result print: " + docResult.toString());
        return new ResponseEntity<>(docResult, HttpStatus.OK);
    }
//    @PostMapping("/updateAppointment")
//    public Appointments updateAppointment(@PathVariable Appointments appoint){
//        return appointService.updateAppointment(appoint);
//    }
//
//    @PostMapping("/deleteAppointment")
//    public void deleteAppointment(@PathVariable Integer id){
//        appointService.deleteAppointment(id);
//    }

    //check for other endpoints that we want to implement
}
