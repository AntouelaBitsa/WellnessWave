package com.wellnesswave.WellnessWave.Service;

import com.google.gson.GsonBuilder;
import com.wellnesswave.WellnessWave.Entities.Appointments;
import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Entities.DoctorDTO;
import com.wellnesswave.WellnessWave.Entities.Patient;
import com.wellnesswave.WellnessWave.Repository.AppointmentsRepository;
import com.wellnesswave.WellnessWave.Repository.DoctorRepository;
import com.wellnesswave.WellnessWave.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentsService {
    @Autowired
    private AppointmentsRepository appointmentRep;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    //CRUD Operations
    public List<Appointments> getAllAppointments(){
        return appointmentRep.findAll();
    }

    public Appointments getAppointById(Integer id){
        Appointments appoint = new Appointments();
        appoint = appointmentRep.findById(id).orElse(null);
        System.out.println(">>> appoint : " + appoint.toString());
//        return appointmentRep.findById(id).orElse(null);
        return appoint;
    }

    public Result createAppointment(Appointments appointment){
        Doctor doc = doctorService.getDoctorById(appointment.getDoctor().getDocId());
        System.out.println(">>> docService : " + doc.toString());
        appointment.setDoctor(doc);
        Patient pat = patientService.getPatientById(appointment.getPatient().getPatientId());
        System.out.println(">>> patService : " + pat.toString());
        appointment.setPatient(pat);
        System.out.println(">>> Appointments class " + appointment.toString());
        if (appointment.equals(null)){
            return new Result(1, "One or more fields are empty");
        }

        try{
            appointmentRep.save(appointment);
        }catch (Exception e){
            System.out.println(">>> Exception : " + e.getMessage());
            System.out.println("Doctor " + appointment.getDoctor().toString());
            System.out.println("Patient " + appointment.getPatient().toString());
            return new Result(1, "Oops an Exception occured during appointment booking");
        }
        return new Result(0, "Successful appointment booking");
    }

    //TODO: get doctor by spacialisation
    public Result getSpecialisedDoc(String profession){
        // I need to get back the doctor id, first, last name, email
        List<Doctor> docSpecList = doctorRepository.findByProfession(profession);

        if (docSpecList == null || docSpecList.isEmpty()){
            System.out.println("Inside if Null List");
            return new Result(1, "There is no doctor of this specialisation!");
        }

        System.out.println("Doctor : " + docSpecList.toString());
        List<DoctorDTO> docDTOList = new ArrayList<>();
        for (Doctor d: docSpecList){
            System.out.println("Service Data of Doc : " + "i = " + d);
            docDTOList.add(new DoctorDTO(d.getDocId(),d.getFirstName(), d.getLastName() /*, d.getEmail()*/));  //by using class DoctorDTO
        }
        System.out.println(">>> Doctor List -> " + docSpecList.isEmpty());
        System.out.println(">>> Doctor DTO List -> " + docDTOList.isEmpty());
//        String message = Doctor.createJSONList(docDTOList);
        Gson gsonConvert = new GsonBuilder().setPrettyPrinting().create();
        String message = gsonConvert.toJson(docDTOList);

        return new Result(0, message);
    }

//    public Appointments updateAppointment(Appointments appoint){
//        return appointmentRep.save(appoint);
//    }
//
//    public void deleteAppointment(Integer id){
//        appointmentRep.deleteById(id);
//    }
}
