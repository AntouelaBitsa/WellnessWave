package com.wellnesswave.WellnessWave.Service;

import com.wellnesswave.WellnessWave.Entities.BookedSlots;
import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Repository.BookedSlotsRepository;
import com.wellnesswave.WellnessWave.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    public Result createSlot(BookedSlots bookedSlot){
        Doctor doc = doctorService.getDoctorById(bookedSlot.getDoctor().getDocId());
        System.out.println("Doctor returned: " + doc);

        LocalDate newDate = bookedSlot.getSlotDate();
        LocalTime newSlotTime = bookedSlot.getSlotTime();

        List<BookedSlots> docSlots = bookedSlotsRepository.findAllBySlotDateAndDoctor_DocId(newDate, bookedSlot.getDoctor().getDocId());
        boolean isSlotBooked = docSlots.stream()
                .anyMatch(slot -> slot.getSlotTime().equals(newSlotTime));

        if (isSlotBooked){
            return new Result(1, "The Selected Time slot is already booked.");
        }



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
}