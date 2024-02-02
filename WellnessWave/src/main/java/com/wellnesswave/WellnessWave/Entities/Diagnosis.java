package com.wellnesswave.WellnessWave.Entities;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="diagnosis")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diagnosisId;
    private int docId;
    private int patId;
    private String diagnInfo;
    private String diagnType;
    private String treatment;
    private String treatmName;
    private String treatmDose;
    private Date startDate;
    private Date endDate;

    public Diagnosis(int diagnosisId, int docId, int patId, String diagnInfo, String diagnType, String treatment, String treatmName, String treatmDose, Date startDate, Date endDate) {
        this.diagnosisId = diagnosisId;
        this.docId = docId;
        this.patId = patId;
        this.diagnInfo = diagnInfo;
        this.diagnType = diagnType;
        this.treatment = treatment;
        this.treatmName = treatmName;
        this.treatmDose = treatmDose;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Diagnosis(int docId, int patId, String diagnInfo, String diagnType, String treatment, String treatmName, String treatmDose, Date startDate, Date endDate) {
        this.docId = docId;
        this.patId = patId;
        this.diagnInfo = diagnInfo;
        this.diagnType = diagnType;
        this.treatment = treatment;
        this.treatmName = treatmName;
        this.treatmDose = treatmDose;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getPatId() {
        return patId;
    }

    public void setPatId(int patId) {
        this.patId = patId;
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

    public String getTreatmName() {
        return treatmName;
    }

    public void setTreatmName(String treatmName) {
        this.treatmName = treatmName;
    }

    public String getTreatmDose() {
        return treatmDose;
    }

    public void setTreatmDose(String treatmDose) {
        this.treatmDose = treatmDose;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "diagnosisId=" + diagnosisId +
                ", docId=" + docId +
                ", patId=" + patId +
                ", diagnInfo='" + diagnInfo + '\'' +
                ", diagnType='" + diagnType + '\'' +
                ", treatment='" + treatment + '\'' +
                ", treatmName='" + treatmName + '\'' +
                ", treatmDose='" + treatmDose + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
