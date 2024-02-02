package com.wellnesswave.WellnessWave.Service;

import com.wellnesswave.WellnessWave.Entities.Appointments;
import com.wellnesswave.WellnessWave.Repository.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentsService {
    @Autowired
    private AppointmentsRepository appointmentRep;

    //CRUD Operations Declaration
    public List<Appointments> getAllAppointments(){
        return appointmentRep.findAll();
    }

    public Appointments getAppointById(Long id){
        return appointmentRep.findById(id).orElse(null);
    }

    public Appointments updateAppointment(Appointments appoint){
        return appointmentRep.save(appoint);
    }

    public void deleteAppointment(Long id){
        appointmentRep.deleteById(id);
    }
}
