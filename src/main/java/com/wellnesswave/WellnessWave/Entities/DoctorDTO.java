package com.wellnesswave.WellnessWave.Entities;

import com.wellnesswave.WellnessWave.Entities.Doctor;

public class DoctorDTO {
    private Integer docId;
    private String firstName;
    private String lastName;

    public DoctorDTO(Integer docId, String firstName, String lastName) {
        this.docId = docId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
