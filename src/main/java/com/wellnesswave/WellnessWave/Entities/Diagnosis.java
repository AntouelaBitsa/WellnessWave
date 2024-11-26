package com.wellnesswave.WellnessWave.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.atomic.LongAccumulator;

@Entity
@Table(name="diagnosis")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer diagnosisId;
    @Column(nullable = false)
    private String diagnType;
    private String treatment;
    private String treatmDose;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @Column(nullable = false)
    private String diagnInfo;
    //Foreign key
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id")
    private Doctor doctor;

    //Foreign Key
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Diagnosis() {
    }

    //POSTMAN endpoint test
//    public Diagnosis(Integer diagnosisId, String diagnInfo, String diagnType, String treatment, String treatmDose, LocalDate startDate, LocalDate endDate, Doctor doctor, Patient patient) {
//        this.diagnosisId = diagnosisId;
//        this.diagnInfo = diagnInfo;
//        this.diagnType = diagnType;
//        this.treatment = treatment;
//        this.treatmDose = treatmDose;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.doctor = doctor;
//        this.patient = patient;
//    }
//

    public Diagnosis(String diagnType, String treatment, String treatmDose, LocalDate startDate, LocalDate endDate, String diagnInfo, Doctor doctor, Patient patient) {
        this.diagnType = diagnType;
        this.treatment = treatment;
        this.treatmDose = treatmDose;
        this.startDate = startDate;
        this.endDate = endDate;
        this.diagnInfo = diagnInfo;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Integer getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Integer diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getDiagnInfo() {
        return diagnInfo;
    }

    public void setDiagnInfo(String diagnInfo) {
        this.diagnInfo = diagnInfo;
    }

    public String getDiagnType() {
        return diagnType;
    }

    public void setDiagnType(String diagnType) {
        this.diagnType = diagnType;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getTreatmDose() {
        return treatmDose;
    }

    public void setTreatmDose(String treatmDose) {
        this.treatmDose = treatmDose;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "diagnosisId=" + diagnosisId +
                ", diagnInfo='" + diagnInfo + '\'' +
                ", diagnType='" + diagnType + '\'' +
                ", treatment='" + treatment + '\'' +
                ", treatmDose='" + treatmDose + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }
}
