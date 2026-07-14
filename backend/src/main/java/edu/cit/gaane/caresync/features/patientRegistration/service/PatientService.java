package edu.cit.gaane.caresync.features.patientRegistration.service;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.cit.gaane.caresync.features.patientRegistration.dto.PatientRequest;
import edu.cit.gaane.caresync.features.patientRegistration.dto.PatientResponse;
import edu.cit.gaane.caresync.features.patientRegistration.entity.PatientEntity;
import edu.cit.gaane.caresync.features.patientRegistration.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /*
     * REGISTER PATIENT
     */

    public PatientResponse registerPatient(PatientRequest request) {

        PatientEntity patient = new PatientEntity();

        patient.setFirstName(request.getFirstName());
        patient.setMiddleInitial(request.getMiddleInitial());
        patient.setLastName(request.getLastName());
        patient.setBirthDate(request.getBirthDate());
        patient.setGender(request.getGender());
        patient.setContactNumber(request.getContactNumber());
        patient.setAddress(request.getAddress());


        String patientNumber = generatePatientNumber();

            patient.setPatientNumber(patientNumber);

            PatientEntity savedPatient = patientRepository.save(patient);

            return mapToResponse(savedPatient);
    }

    /*
     * GET ALL PATIENTS
     */

    public List<PatientResponse> getAllPatients() {

        return patientRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }

    /*
     * GET ONE PATIENT
     */

    public PatientResponse getPatientById(Long id) {

        PatientEntity patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found."));

        return mapToResponse(patient);

    }

    /*
     * UPDATE PATIENT
     */

    public PatientResponse updatePatient(Long id, PatientRequest request) {

        PatientEntity patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found."));

        patient.setFirstName(request.getFirstName());
        patient.setMiddleInitial(request.getMiddleInitial());
        patient.setLastName(request.getLastName());
        patient.setBirthDate(request.getBirthDate());
        patient.setGender(request.getGender());
        patient.setContactNumber(request.getContactNumber());
        patient.setAddress(request.getAddress());

        patientRepository.save(patient);

        return mapToResponse(patient);

    }

    /*
     * DELETE PATIENT
     */

    public void deletePatient(Long id) {

        patientRepository.deleteById(id);

    }

    /*
     * ENTITY -> RESPONSE DTO
     */

    private PatientResponse mapToResponse(PatientEntity patient) {

        return new PatientResponse(
                patient.getId(),
                patient.getPatientNumber(),
                patient.getFirstName(),
                patient.getMiddleInitial(),
                patient.getLastName(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getContactNumber(),
                patient.getAddress(),
                patient.getDateRegistered()
        );

    }

    private String generatePatientNumber() {

    int year = Year.now().getValue();

    var latestPatient = patientRepository.findTopByOrderByIdDesc();

    int nextNumber = 1;

    if (latestPatient.isPresent()) {

        String latest = latestPatient.get().getPatientNumber();

        if (latest != null && !latest.isBlank()) {

            String[] parts = latest.split("-");

            nextNumber = Integer.parseInt(parts[2]) + 1;

        }

    }

    return String.format(
            "CS-%d-%06d",
            year,
            nextNumber
    );

}

}