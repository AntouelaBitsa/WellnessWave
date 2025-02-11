package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Entities.BookedSlots;
import com.wellnesswave.WellnessWave.Service.BookedSlotsService;
import com.wellnesswave.WellnessWave.Utils.BookedSlotsDTO;
import com.wellnesswave.WellnessWave.Utils.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@Tag(name = "BookedSlots")
public class BookedSlotsController {
    @Autowired
    private BookedSlotsService bookedSlotsService;

    @GetMapping("/getSlotById/{id}")
    public BookedSlots getSlotById(@PathVariable Integer slotId){
        BookedSlots bookedSlot = bookedSlotsService.getSlotById(slotId);
        System.out.println("[BookedSlotController 00] getSlotById()" + bookedSlot);
        return bookedSlot;
    }

    //TODO: endpoint to find slots by date and docId
    @GetMapping("/getBookedSlotsByDate/{date}/{docId}")
    public ResponseEntity<Map<String, Set<String>>> getBookedSlotsByDate(@PathVariable String date, @PathVariable Integer docId){
        Map<String, Set<String>> bookedSlotsMap = (Map<String, Set<String>>) bookedSlotsService.bookedSlotsByDate(date, docId);
        System.out.println("[BookedSlotController 01] date: " + date);
        System.out.println("[BookedSlotController 02] getBookedSlotsByDate(): call to service class");
        System.out.println("[BookedSlotController 03] (response) bookedSlotsList: " + bookedSlotsMap);
        return new ResponseEntity<>(bookedSlotsMap, HttpStatus.OK);
    }

    //TODO: endpoint to save slot being booked
    @PostMapping("/createSlot")
    public ResponseEntity<Result> createSlot(@RequestBody BookedSlots slot){
        Result slotResult = bookedSlotsService.createSlot(slot);
        System.out.println("[BookedSlotController 01 bookSlot()] slot: " + slot);
        System.out.println("[BookedSlotController 01 bookSlot()] slotResult: " + slotResult);
        if (slotResult.getStatus() == 1){
            return new ResponseEntity<>(slotResult, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(slotResult, HttpStatus.CREATED);
    }

    @PutMapping("/updateSlot/{slotDate}/{docId}")
    public ResponseEntity<Result> updateSlot(@PathVariable String slotDate, @PathVariable Integer docId, @RequestParam String newSlotTime){
        Result updatedSlot = bookedSlotsService.updateExistingSlot(slotDate, docId, newSlotTime);
        System.out.println("[BookedSlotController 00] updateSlot(): " + updatedSlot);
        return new ResponseEntity<>(updatedSlot, HttpStatus.OK);
    }
}
