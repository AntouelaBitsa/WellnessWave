package com.wellnesswave.WellnessWave.Utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class BookedSlotsDTO {
    private Integer doctorId;
    private LocalDate slotDate;
    private List<LocalTime> slotTimes;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(LocalDate slotDate) {
        this.slotDate = slotDate;
    }

    public List<LocalTime> getSlotTimes() {
        return slotTimes;
    }

    public void setSlotTimes(List<LocalTime> slotTimes) {
        this.slotTimes = slotTimes;
    }
}
