package edu.cit.gaane.caresync.features.patientRegistration.service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.cit.gaane.caresync.features.patientRegistration.entity.PatientEntity;
import edu.cit.gaane.caresync.features.patientRegistration.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientEntity registerPatient(PatientEntity patient) {

        // Save first to generate the database ID
        PatientEntity savedPatient = patientRepository.save(patient);

        String patientNumber = String.format(
                "CS-%d-%06d",
                Year.now().getValue(),
                savedPatient.getId()
        );

        savedPatient.setPatientNumber(patientNumber);

        return patientRepository.save(savedPatient);
    }

    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<PatientEntity> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Optional<PatientEntity> getPatientByPatientNumber(String patientNumber) {
        return patientRepository.findByPatientNumber(patientNumber);
    }

    public PatientEntity updatePatient(Long id, PatientEntity updatedPatient) {

        PatientEntity patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found."));

        patient.setFirstName(updatedPatient.getFirstName());
        patient.setMiddleInitial(updatedPatient.getMiddleInitial());
        patient.setLastName(updatedPatient.getLastName());
        patient.setBirthDate(updatedPatient.getBirthDate());
        patient.setGender(updatedPatient.getGender());
        patient.setContactNumber(updatedPatient.getContactNumber());
        patient.setAddress(updatedPatient.getAddress());

        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

}