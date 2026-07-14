package edu.cit.gaane.caresync.features.patientRegistration.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cit.gaane.caresync.features.patientRegistration.entity.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByPatientNumber(String patientNumber);
    Optional<PatientEntity> findTopByOrderByIdDesc();

    List<PatientEntity> findByLastNameContainingIgnoreCase(String lastName);

    List<PatientEntity> findByFirstNameContainingIgnoreCase(String firstName);

    long countByGenderIgnoreCase(String gender);

    long countByDateRegisteredAfter(LocalDateTime date);

}