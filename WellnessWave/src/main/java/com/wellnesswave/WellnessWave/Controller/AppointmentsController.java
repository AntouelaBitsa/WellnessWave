package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Entities.Appointments;
import com.wellnesswave.WellnessWave.Service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getAppointById")
    public Appointments getAppointById(@PathVariable Long id){
        return appointService.getAppointById(id);
    }

    @PostMapping("/updateAppointment")
    public Appointments updateAppointment(@PathVariable Appointments appoint){
        return appointService.updateAppointment(appoint);
    }

    @PostMapping("/deleteAppointment")
    public void deleteAppointment(@PathVariable Long id){
        appointService.deleteAppointment(id);
    }

    //check for other endpoints that we want to implement
}
