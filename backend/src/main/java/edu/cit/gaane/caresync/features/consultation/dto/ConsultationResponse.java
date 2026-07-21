package edu.cit.gaane.caresync.features.consultation.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ConsultationResponse {

    private Long id;

    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private String chiefComplaint;
    private String diagnosis;
    private String treatmentPlan;
    private String prescription;
    private String notes;

    private LocalDateTime consultationDate;

    public ConsultationResponse(
            Long id,
            Long patientId,
            String patientName,
            Long doctorId,
            String doctorName,
            String chiefComplaint,
            String diagnosis,
            String treatmentPlan,
            String prescription,
            String notes,
            LocalDateTime consultationDate
    ) {

        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.chiefComplaint = chiefComplaint;
        this.diagnosis = diagnosis;
        this.treatmentPlan = treatmentPlan;
        this.prescription = prescription;
        this.notes = notes;
        this.consultationDate = consultationDate;
    }

}