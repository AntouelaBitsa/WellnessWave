package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Entities.Appointments;
import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Entities.Patient;
import com.wellnesswave.WellnessWave.Service.AppointmentsService;
import com.wellnesswave.WellnessWave.Utils.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Appointments")
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

    @GetMapping("/getAppointmentsByPatient/{patientID}")
    public ResponseEntity<List<Appointments>> getAppointmentsByPatient(@PathVariable Patient patientID){
        List<Appointments> appointList = appointService.patientAppointments(patientID);
        System.out.println("[-1-AppointmentController] getAppointmentsByPatient: appointList= " + appointList);
        System.out.println("[-2-AppointmentController] getAppointmentsByPatient: Call of service");
        return new ResponseEntity<>(appointList, HttpStatus.OK);
    }

    @GetMapping("/getAppointmentsByDoctor/{doctorID}")
    public  ResponseEntity<List<Appointments>> getAppointmentsByDoctor(@PathVariable Doctor doctorID){
        List<Appointments> docAppointList = appointService.doctorAppointments(doctorID);
        System.out.println("[-1-AppointmentController] getAppointmentsByDoctor: appointList= " + docAppointList);
        System.out.println("[-2-AppointmentController] getAppointmentsByDoctor: Call of service");
        return new ResponseEntity<>(docAppointList, HttpStatus.OK);
    }

    @PostMapping("/updateAppointOnReschedule")
    public ResponseEntity<Result> updateAppointOnReschedule(@RequestParam("id") Integer id, @RequestParam("status") String status){
        Result responseResult = appointService.updateStatusOnReschedule(id, status);
        return new ResponseEntity<>(responseResult, HttpStatus.OK);
    }

//    @PostMapping("/updateAppointment")
//    public Appointments updateAppointment(@PathVariable Appointments appoint){
//        return appointService.updateAppointment(appoint);
//    }
//
    @PostMapping("/deleteAppointment")
    public ResponseEntity<Result> deleteAppointment(@RequestParam("id") Integer id){
        System.out.println("[-1-AppointmentController] deleteAppointment: appoint id= " + id);
        System.out.println("[-2-AppointmentController] deleteAppointment: Call of service");
        return new ResponseEntity<>(appointService.softDeleteAppointment(id), HttpStatus.OK);
    }

    @GetMapping("/getAppointmentsOnDateSelected/{date}/{userId}/{userType}")
    public  ResponseEntity<List<Appointments>> getAppointmentsOnDateSelected(@PathVariable String date, @PathVariable Integer userId, @PathVariable Integer userType){
        List<Appointments> appointListOnDate = appointService.getAppointOnDateSelect(date, userId, userType);
        System.out.println("[-1-AppointmentController] getAppoinmnetsOnDateSelected: appointListOnDate= " + appointListOnDate);
        System.out.println("[-2-AppointmentController] getAppoinmnetsOnDateSelected: Call of service");
        return new ResponseEntity<>(appointListOnDate, HttpStatus.OK);
    }
}
