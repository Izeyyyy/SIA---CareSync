package edu.cit.gaane.caresync.features.patientRegistration.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.cit.gaane.caresync.features.patientRegistration.entity.PatientEntity;
import edu.cit.gaane.caresync.features.patientRegistration.service.PatientService;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:5173")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientEntity> registerPatient(
            @RequestBody PatientEntity patient) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(patientService.registerPatient(patient));
    }

    @GetMapping
    public List<PatientEntity> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {

        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientEntity> updatePatient(
            @PathVariable Long id,
            @RequestBody PatientEntity patient) {

        return ResponseEntity.ok(
                patientService.updatePatient(id, patient)
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(
            @PathVariable Long id) {

        patientService.deletePatient(id);

        return ResponseEntity.noContent().build();

    }

}

