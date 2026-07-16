package edu.cit.gaane.caresync.features.consultation.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultationRequest {

    private Long patientId;

    private String chiefComplaint;

    private String diagnosis;

    private String treatmentPlan;

    private String prescription;

    private String notes;

    public ConsultationRequest() {}

    public ConsultationRequest(
            Long patientId,
            Long doctorId,
            String chiefComplaint,
            String diagnosis,
            String treatmentPlan,
            String prescription,
            String notes
    ) {
        this.patientId = patientId;
        this.chiefComplaint = chiefComplaint;
        this.diagnosis = diagnosis;
        this.treatmentPlan = treatmentPlan;
        this.prescription = prescription;
        this.notes = notes;
    }

}
