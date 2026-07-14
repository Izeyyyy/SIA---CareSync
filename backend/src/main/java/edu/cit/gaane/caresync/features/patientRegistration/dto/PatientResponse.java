package edu.cit.gaane.caresync.features.patientRegistration.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientResponse {

    private Long id;
    private String patientNumber;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String contactNumber;
    private String address;
    private LocalDateTime dateRegistered;

    public PatientResponse() {
    }

    public PatientResponse(
            Long id,
            String patientNumber,
            String firstName,
            char middleInitial,
            String lastName,
            LocalDate birthDate,
            String gender,
            String contactNumber,
            String address,
            LocalDateTime dateRegistered
    ) {
        this.id = id;
        this.patientNumber = patientNumber;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
        this.dateRegistered = dateRegistered;
    }

    public Long getId() {
        return id;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getDateRegistered() {
        return dateRegistered;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateRegistered(LocalDateTime dateRegistered) {
        this.dateRegistered = dateRegistered;
    }
}