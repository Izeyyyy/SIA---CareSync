package edu.cit.gaane.caresync.features.consultation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.cit.gaane.caresync.features.consultation.dto.ConsultationRequest;
import edu.cit.gaane.caresync.features.consultation.dto.ConsultationResponse;
import edu.cit.gaane.caresync.features.consultation.service.ConsultationService;

@RestController
@RequestMapping("/api/consultations")
@CrossOrigin(origins = "http://localhost:5173")
public class ConsultationController {

    private final ConsultationService consultationService;

    public ConsultationController(
            ConsultationService consultationService
    ) {
        this.consultationService = consultationService;
    }

    /*
     * CREATE
     */

    @PostMapping
    public ResponseEntity<ConsultationResponse> createConsultation(
            @RequestBody ConsultationRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        consultationService.createConsultation(request)
                );

    }

    /*
     * GET ALL
     */

    @GetMapping
    public List<ConsultationResponse> getAllConsultations(){

        return consultationService.getAllConsultations();

    }

    /*
     * GET ONE
     */

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationResponse> getConsultationById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                consultationService.getConsultationById(id)
        );

    }

    /*
     * GET PATIENT CONSULTATIONS
     */

    @GetMapping("/patient/{patientId}")
    public List<ConsultationResponse> getPatientConsultations(
            @PathVariable Long patientId
    ){

        return consultationService
                .getPatientConsultations(patientId);

    }

    /*
     * UPDATE
     */

    @PutMapping("/{id}")
    public ResponseEntity<ConsultationResponse> updateConsultation(

            @PathVariable Long id,

            @RequestBody ConsultationRequest request

    ){

        return ResponseEntity.ok(

                consultationService.updateConsultation(
                        id,
                        request
                )

        );

    }

    /*
     * DELETE
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(
            @PathVariable Long id
    ){

        consultationService.deleteConsultation(id);

        return ResponseEntity.noContent().build();

    }

}