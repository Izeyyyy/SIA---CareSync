package edu.cit.gaane.caresync.features.consultation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.cit.gaane.caresync.audit.service.AuditLogService;
import edu.cit.gaane.caresync.config.SecurityUtils;
import edu.cit.gaane.caresync.features.authentication.entity.UserEntity;
import edu.cit.gaane.caresync.features.consultation.dto.ConsultationRequest;
import edu.cit.gaane.caresync.features.consultation.dto.ConsultationResponse;
import edu.cit.gaane.caresync.features.consultation.entity.ConsultationEntity;
import edu.cit.gaane.caresync.features.consultation.repository.ConsultationRepository;
import edu.cit.gaane.caresync.features.patientRegistration.entity.PatientEntity;
import edu.cit.gaane.caresync.features.patientRegistration.repository.PatientRepository;

@Service
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;
    private final AuditLogService auditLogService;

    public ConsultationService(
            ConsultationRepository consultationRepository,
            PatientRepository patientRepository,
            AuditLogService auditLogService
    ) {
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
        this.auditLogService = auditLogService;
    }

    /*
     * CREATE
     */

    public ConsultationResponse createConsultation(
            ConsultationRequest request
    ) {

        PatientEntity patient = patientRepository
                .findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found."));

        UserEntity doctor = SecurityUtils.getCurrentUser();

        if (doctor == null) {
            throw new RuntimeException("Doctor not authenticated.");
        }

        ConsultationEntity consultation = new ConsultationEntity();

        consultation.setPatient(patient);
        consultation.setDoctor(doctor);
        consultation.setChiefComplaint(request.getChiefComplaint());
        consultation.setDiagnosis(request.getDiagnosis());
        consultation.setTreatmentPlan(request.getTreatmentPlan());
        consultation.setPrescription(request.getPrescription());
        consultation.setNotes(request.getNotes());

        ConsultationEntity saved =
                consultationRepository.save(consultation);

        auditLogService.createLog(
                "CREATE",
                "CONSULTATION",
                "Created consultation",
                saved.getId()
        );

        return mapToResponse(saved);
    }

    /*
     * GET ALL
     */

    public List<ConsultationResponse> getAllConsultations() {

        return consultationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }

    /*
     * GET ONE
     */

    public ConsultationResponse getConsultationById(Long id) {

        ConsultationEntity consultation =
                consultationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Consultation not found."));

        return mapToResponse(consultation);

    }

    /*
     * GET BY PATIENT
     */

    public List<ConsultationResponse> getPatientConsultations(Long patientId){

        return consultationRepository
                .findByPatientId(patientId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }

    /*
     * UPDATE
     */

    public ConsultationResponse updateConsultation(
            Long id,
            ConsultationRequest request
    ){

        ConsultationEntity consultation =
                consultationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Consultation not found."));
        
        UserEntity currentDoctor = SecurityUtils.getCurrentUser();

        if (!consultation.getDoctor().getId().equals(currentDoctor.getId())) {
        throw new RuntimeException(
                "You are not allowed to edit another doctor's consultation."
        );
        }

        consultation.setChiefComplaint(request.getChiefComplaint());
        consultation.setDiagnosis(request.getDiagnosis());
        consultation.setTreatmentPlan(request.getTreatmentPlan());
        consultation.setPrescription(request.getPrescription());
        consultation.setNotes(request.getNotes());

        consultationRepository.save(consultation);

        auditLogService.createLog(
                "UPDATE",
                "CONSULTATION",
                "Updated consultation",
                consultation.getId()
        );

        return mapToResponse(consultation);

    }

    /*
     * DELETE
     */

    public void deleteConsultation(Long id){

        ConsultationEntity consultation =
                consultationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Consultation not found."));

        UserEntity currentDoctor = SecurityUtils.getCurrentUser();

        if (!consultation.getDoctor().getId().equals(currentDoctor.getId())) {
        throw new RuntimeException(
                "You are not allowed to delete another doctor's consultation."
        );
        }

        auditLogService.createLog(
                "DELETE",
                "CONSULTATION",
                "Deleted consultation "
                + consultation.getPatient().getFirstName()
                + " "
                + consultation.getPatient().getLastName(),
                consultation.getId()
        );

        consultationRepository.delete(consultation);

    }

    /*
     * ENTITY -> RESPONSE
     */

    private ConsultationResponse mapToResponse(
            ConsultationEntity consultation
    ){

        return new ConsultationResponse(

                consultation.getId(),

                consultation.getPatient().getId(),
                consultation.getPatient().getFirstName() + " " +
                consultation.getPatient().getLastName(),
                consultation.getDoctor().getId(),
                consultation.getDoctor().getFirstName() + " " +
                consultation.getDoctor().getLastName(),
                consultation.getChiefComplaint(),
                consultation.getDiagnosis(),
                consultation.getTreatmentPlan(),
                consultation.getPrescription(),
                consultation.getNotes(),
                consultation.getConsultationDate()

        );

    }

}