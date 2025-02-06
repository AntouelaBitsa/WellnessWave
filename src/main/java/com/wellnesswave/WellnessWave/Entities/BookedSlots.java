package com.wellnesswave.WellnessWave.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "booked_slots", uniqueConstraints = @UniqueConstraint(columnNames = {"date", "time", "doc_id"}))
public class BookedSlots {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer slotId;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate slotDate;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime slotTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doc_id", nullable = false)
    private Doctor doctor;

    //TODO: add foreign key of appointment id
//    @OneToOne(optional = true)
//    @JoinColumn(name = "appointment_id", nullable = false, unique = true)
//    private Appointments appointments;


    public BookedSlots() {
    }

    public BookedSlots(LocalDate slotDate, LocalTime slotTime) {
        this.slotDate = slotDate;
        this.slotTime = slotTime;
    }

    public BookedSlots(LocalDate slotDate, LocalTime slotTime, Doctor doctor) {
        this.slotDate = slotDate;
        this.slotTime = slotTime;
        this.doctor = doctor;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public LocalDate getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(LocalDate slotDate) {
        this.slotDate = slotDate;
    }

    public LocalTime getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(LocalTime slotTime) {
        this.slotTime = slotTime;
    }

//    public Appointments getAppointments() {
//        return appointments;
//    }
//    public void setAppointments(Appointments appointments) {
//        this.appointments = appointments;
//    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "BookedSlots{" +
                "slotId=" + slotId +
                ", slotDate=" + slotDate +
                ", slotTime=" + slotTime +
                ", doctor=" + doctor +
                '}';
    }
}
