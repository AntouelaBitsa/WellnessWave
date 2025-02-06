package com.wellnesswave.WellnessWave.Service;

import com.wellnesswave.WellnessWave.Entities.BookedSlots;
import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Repository.BookedSlotsRepository;
import com.wellnesswave.WellnessWave.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookedSlotsService {
    @Autowired
    private BookedSlotsRepository bookedSlotsRepository;
    @Autowired
    private DoctorService doctorService;

    public BookedSlots getSlotById(Integer slotId){
        return bookedSlotsRepository.findById(slotId).orElseThrow(()-> new RuntimeException("Slot with id: " + slotId + " not found"));
    }

    //TODO: find slots by date
    /**
     * return type was: Map<LocalDate, Set<LocalTime>>*/
    public Map<String, Set<String>>  bookedSlotsByDate(String incomingDate, Integer docId){
        System.out.println("[BookedSlotService 01] incomingDate: " + incomingDate);
        LocalDate formattedDate = LocalDate.parse(incomingDate);
        System.out.println("[BookedSlotService 02] formattedDate: " + formattedDate);
        Map<String, Set<String>> bookedSlotsMap = new HashMap<>();

        try {
            System.out.println("[BookedSlotService 03] try{call RepositoryInterface}: findByDate()");
//            bookedSlotsMap = appointmentsService.findBySlotDate(formattedDate);

            List<BookedSlots> docSlotsList = bookedSlotsRepository.findAllBySlotDateAndDoctor_DocId(formattedDate, docId);
            System.out.println("[BookedSlotService 04] try{list returned from RepositoryInterface}: findByDate() | bookedSlotsList: " + docSlotsList);

//            bookedSlotsMap = docSlotsList.stream()
//                    .collect(Collectors.groupingBy(
//                            BookedSlots::getSlotDate,
//                            Collectors.mapping(BookedSlots::getSlotTime, Collectors.toSet())
//                    ));

            bookedSlotsMap = docSlotsList.stream()
                    .collect(Collectors.groupingBy(
                            slotDate -> slotDate.getSlotDate().toString(),
                            Collectors.mapping(
                                    slotTime -> slotTime.getSlotTime().toString(),
                                    Collectors.toSet())
                    ));
        }catch (Exception e){
            System.out.println("[BookedSlotService 05] catch(){exception occurred} exception: " + e.getMessage());
        }

        return bookedSlotsMap;
    }

    //TODO: save slot being booked
    public Result createSlot(BookedSlots bookedSlot, Integer docId){
        LocalDate newDate = bookedSlot.getSlotDate();
        LocalTime newSlotTime = bookedSlot.getSlotTime();

        List<BookedSlots> docSlots = bookedSlotsRepository.findAllBySlotDateAndDoctor_DocId(newDate, docId);
        boolean isSlotBooked = docSlots.stream()
                .anyMatch(slot -> slot.getSlotTime().equals(newSlotTime));

        if (isSlotBooked){
            return new Result(1, "The Selected Time slot is already booked.");
        }

        Doctor doc = doctorService.getDoctorById(docId);
        System.out.println("Doctor returned: " + doc);

        bookedSlot.setDoctor(doc);
        BookedSlots newSlot = bookedSlotsRepository.save(bookedSlot);
        return new Result(0, "Slot successfully created for date " + newDate + " and time " + newSlotTime);
    }

    /**Not in USE maybe will be needed*/
    public BookedSlots createSlot2(BookedSlots bookedSlot, Integer docId){
        /**same if I want to return only slot id*/
        LocalDate newDate = bookedSlot.getSlotDate();
        LocalTime newSlotTime = bookedSlot.getSlotTime();

        List<BookedSlots> docSlots = bookedSlotsRepository.findAllBySlotDateAndDoctor_DocId(newDate, docId);
        boolean isSlotBooked = docSlots.stream()
                .anyMatch(slot -> slot.getSlotTime().equals(newSlotTime));

        if (isSlotBooked){
            return new BookedSlots();
        }

        Doctor doc = doctorService.getDoctorById(docId);
        System.out.println("Doctor returned: " + doc);

        bookedSlot.setDoctor(doc);
        BookedSlots newSlot = bookedSlotsRepository.save(bookedSlot);
        return newSlot;
    }

    public Result updateExistingSlot(String selectedDate, Integer docId, String newTime){
        LocalDate slotDate = LocalDate.parse(selectedDate);
        LocalTime newSlotTime = LocalTime.parse(newTime);
        Doctor doc = doctorService.getDoctorById(docId);

        Optional<BookedSlots> bookedSlotsOptional = bookedSlotsRepository.findBySlotDateAndDoctor_DocId(slotDate, docId);
        if (bookedSlotsOptional.isPresent()){
            BookedSlots bookedSlot = bookedSlotsOptional.get();
            bookedSlot.setSlotTime(newSlotTime);
            bookedSlotsRepository.save(bookedSlot);
            return new Result(0, "Slot updated successfully for date " + slotDate + " and time " + newSlotTime);
        }else {
            return new Result(1, "Slot not found for the specified date and doctor.");
        }
    }


//    public Result createSlot(BookedSlots slot){
//        System.out.println("[BookedSlotService 01 createSlot()] slot: " + slot);
//        if (slot.equals(null) || slot == null){
//            System.out.println("[BookedSlotService 02 createSlot()] slot is null");
//            return new Result(1, "Slot is empty or null");
//        }
//
//        if (appointmentsService.getAppointById(slot.getAppointments().getAppointmentId()) == null){
//            System.out.println("[BookedSlotService 03 createSlot()] slot's appointment is null");
//            return new Result(1, "Slot has no Appointment Assigned");
//        }
//        slot.setAppointments(slot.getAppointments());
//        System.out.println("[BookedSlotService 03 createSlot()] slot's appointment: " + slot.getAppointments());
//
//        try {
//            BookedSlots bookingSlot = bookedSlotsRepository.save(slot);
//            System.out.println("[BookedSlotService 04 createSlot()] try{(response)} bookingSlot: " + bookingSlot);
//            return new Result(0, "Slot is booked successfully");
//        }catch (Exception e){
//            System.out.println("[BookedSlotService 05 createSlot()] catch{exception occurred} exception: " + e.getMessage() +
//                    " cause: " + e.getCause());
//            return new Result(1, "An exception occurred during slot booking");
//        }
//    }
}
