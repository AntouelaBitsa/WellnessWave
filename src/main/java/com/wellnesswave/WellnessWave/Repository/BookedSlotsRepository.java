package com.wellnesswave.WellnessWave.Repository;

import com.wellnesswave.WellnessWave.Entities.BookedSlots;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookedSlotsRepository extends JpaRepository<BookedSlots, Integer> {
    List<BookedSlots> findAllBySlotDateAndDoctor_DocId(LocalDate formattedDate, Integer docId);

    Optional<BookedSlots> findBySlotDateAndDoctor_DocId(LocalDate slotDate, Integer docId);
    //DONE: create custom method to find booked slots by date
//    List<BookedSlots> findBySlotDate(LocalDate formattedDate);

}
