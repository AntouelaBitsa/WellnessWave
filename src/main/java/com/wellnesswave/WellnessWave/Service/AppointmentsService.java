package com.wellnesswave.WellnessWave.Service;

import com.google.gson.GsonBuilder;
import com.wellnesswave.WellnessWave.Entities.Appointments;
import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Utils.DoctorDTO;
import com.wellnesswave.WellnessWave.Entities.Patient;
import com.wellnesswave.WellnessWave.Repository.AppointmentsRepository;
import com.wellnesswave.WellnessWave.Repository.DoctorRepository;
import com.wellnesswave.WellnessWave.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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
//    private List<Appointments> docAppList;

    //CRUD Operations
    public List<Appointments> getAllAppointments(){
        return appointmentRep.findAll();
    }

    public Appointments getAppointById(Integer id){
        Appointments appoint = new Appointments();
        appoint = appointmentRep.findById(id).orElseThrow(()-> new RuntimeException("Appointment Not Found."));
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
            return new Result(1, "Oops an Exception occurred during appointment booking");
        }
        return new Result(0, "Successful appointment booking");
    }

    //DONE: get doctor by specialization
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
            docDTOList.add(new DoctorDTO(d.getDocId(),d.getFirstName(), d.getLastName(), d.getPhoneNum(), d.getAddress()));  //by using class DoctorDTO
        }
        System.out.println(">>> Doctor List -> " + docSpecList.isEmpty());
        System.out.println(">>> Doctor DTO List -> " + docDTOList.isEmpty());
        Gson gsonConvert = new GsonBuilder().setPrettyPrinting().create();
        String message = gsonConvert.toJson(docDTOList);

        return new Result(0, message);
    }

    public List<Appointments> patientAppointments(Patient patientID){
        //test
        System.out.println("[-***0-AppointmentService] patientAppointments: id of patient= " + appointmentRep.findByPatient_PatientId(patientID.getPatientId()));
        //check if id passed as null
        if (patientID == null){
            System.out.println("[-1-AppointmentService] patientAppointments: patientID == " + patientID);
            return Collections.emptyList();
        }

        //Initialize Appointments List
        List<Appointments> appList = appointmentRep.findByPatient_PatientId(patientID.getPatientId());
        try {
            System.out.println("SUCCESSFUL GET Request");
        }catch (Exception e){
            System.out.println("[-2-AppointmentService] patientAppointments: Exception Occurred= " + e);
        }
        return appList;
    }

    public List<Appointments> doctorAppointments(Doctor doctorID) {
        //test print
        System.out.println("[-***0-AppointmentService] doctorAppointments: id of doctor= " + appointmentRep.findByDoctor_DocId(doctorID.getDocId()));
        //check if id passed as null
        if (doctorID == null){
            System.out.println("[-1-AppointmentService] doctorAppointments: doctorID == " + doctorID);
            return Collections.emptyList();
        }

        //Initialize Appointments List: get today's and tomorrow's appointments
        /*List<Appointments> tempDocAppoint = appointmentRep.findByDoctor_DocId(doctorID.getDocId());
        List<Appointments> docAppList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        for (Appointments appoint: tempDocAppoint){
            LocalDate appointDate = appoint.getDate();
            if (appointDate.isAfter(currentDate)){
                docAppList.add(appoint);
            }
        }
        System.out.println("SOS - UPCOMING APPOINT: " + docAppList);*/

        List<Appointments> docAppList = appointmentRep.findByDoctor_DocId(doctorID.getDocId());

        try {
            System.out.println("SUCCESSFUL GET Request");
        }catch (Exception e){
            System.out.println("[-2-AppointmentService] doctorAppointments: Exception Occurred= " + e);
        }
        return docAppList;
    }

    /*private Appointments nextAppFromList(Doctor docID){
        //List of appointments in carousel
        List<Appointments> tempDocAppoint = appointmentRep.findByDoctor_DocId(docID.getDocId());
        //Filtered list with today's and tomorrow's appointments
        List<Appointments> docAppList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
//        LocalDate nextDate = LocalDate.
        for (Appointments appoint: tempDocAppoint){
            LocalDate appointDate = appoint.getDate();
            if (appointDate.isAfter(currentDate) || appointDate.equals(currentDate)){
                docAppList.add(appoint);
            }
        }
        System.out.println("SOS - UPCOMING APPOINT: " + docAppList);
        //Sorted Appointments list from database
        Collections.sort(docAppList, new Comparator<Appointments>() {
            @Override
            public int compare(Appointments appoint1, Appointments appoint2) {
                return appoint1.getTime().compareTo(appoint2.getTime());
            }
        });

        Appointments nextAppoint = new Appointments();

        return nextAppoint;
    }

    public Appointments getNextAppoint(Appointments appID){
        //test print
        System.out.println("[-***0-AppointmentService] doctorAppointments: id of doctor= " + appointmentRep.findByPatient_PatientId(appID.getAppointmentId()));
        //check if id passed as null
        if (appID == null){
            System.out.println("[-1-AppointmentService] doctorAppointments: doctorID == " + appID);
            return new Appointments();
        }
//        Integer nextAppoint = appID.getAppointmentId();
        Appointments docAppList = appointmentRep.findByAppointmentId(appID.getAppointmentId());

        try {
            System.out.println("SUCCESSFUL GET Request");
        }catch (Exception e){
            System.out.println("[-2-AppointmentService] doctorAppointments: Exception Occurred= " + e);
        }
        return docAppList;
    }*/

//    public Appointments updateAppointment(Appointments appoint){
//        return appointmentRep.save(appoint);
//    }

    public List<Appointments> getAppointOnDateSelect(String date, Integer userId, Integer userType){
        //Maybe it will be needed a parse to LocalDate of the Selected Date
        System.out.println("[Service] Incoming DocId = " + userId);
        System.out.println("[Service] Incoming SelectedDate = " + date);
        System.out.println("[Service] Incoming UserType = " + userType);
        LocalDate dateFormatted = LocalDate.parse(date);
        List<Appointments> dateSelectedAppoint = new ArrayList<>();

        try {
            if (userType == 1){
                Doctor d = doctorService.getDoctorById(userId);
                if (d == null){
                    System.out.println("Error finding Appointments for DocID: " + d.getDocId());
                    throw new NoSuchElementException("[Appointment Service] Exception occurred! Doctor not found");
                }
            } else if (userType == 2) {
                Patient p = patientService.getPatientById(userId);
                if (p == null){
                    System.out.println("Error finding Appointments for PatID: " + p.getPatientId());
                    throw new NoSuchElementException("[Appointment Service] Exception occurred! Patient not found");
                }
            }
        }catch (Exception e){
            System.out.println("Handled Exception : " + e.getMessage() + " cause : " + e.getCause());
        }

        try{
            System.out.println("Before GET Request");
            if (userType == 1){
                dateSelectedAppoint = appointmentRep.findByDateAndDoctor_DocId(dateFormatted, userId);
            } else if (userType == 2) {
                dateSelectedAppoint = appointmentRep.findByDateAndPatient_PatientId(dateFormatted, userId); //TODO: create for patient
            }
            System.out.println("SUCCESSFUL GET Request");
        }catch (Exception e){
            System.out.println("[On Catch Block] Exception Occurred --> " + e);
        }
        return dateSelectedAppoint;
    }

    public Result softDeleteAppointment(Integer id){
        if(id.equals(null)) {
            return new Result(0, "Appointment id is null! Retry again");
        }

        Appointments appointment = this.getAppointById(id);
        appointment.setStatus("Inactive");

        try{
            appointmentRep.save(appointment);
        }catch (Exception e){
            System.out.println(">>> Exception on soft delete : " + e.getMessage());
            return new Result(1, "Oops an Exception occurred during appointment booking");
        }

        return new Result(0, "Appointment has been Deleted");
    }
}
