package edu.cit.gaane.caresync.features.consultation.entity;

import java.time.LocalDateTime;

import edu.cit.gaane.caresync.features.patientRegistration.entity.PatientEntity;
import edu.cit.gaane.caresync.features.authentication.entity.UserEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="consultations")
public class ConsultationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PatientEntity patient;

    @ManyToOne
    private UserEntity doctor;

    private String chiefComplaint;

    @Column(length = 3000)
    private String diagnosis;

    @Column(length = 3000)
    private String treatmentPlan;

    @Column(length = 3000)
    private String prescription;

    @Column(length = 3000)
    private String notes;

    private LocalDateTime consultationDate;

    @PrePersist
    public void onCreate(){
        consultationDate = LocalDateTime.now();
    }

}