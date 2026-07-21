package edu.cit.gaane.caresync.audit.service;

import java.util.List;
import org.springframework.stereotype.Service;

import edu.cit.gaane.caresync.audit.entity.AuditLog;
import edu.cit.gaane.caresync.audit.repository.AuditLogRepository;
import edu.cit.gaane.caresync.config.SecurityUtils;
import edu.cit.gaane.caresync.features.authentication.entity.UserEntity;
import edu.cit.gaane.caresync.features.consultation.repository.ConsultationRepository;
import edu.cit.gaane.caresync.features.patientRegistration.repository.PatientRepository;
import edu.cit.gaane.caresync.audit.dto.MyActivityResponse;


@Service
public class AuditLogService {

    private final AuditLogRepository repository;
    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;

    public AuditLogService(
            AuditLogRepository repository,
            ConsultationRepository consultationRepository,
            PatientRepository patientRepository
    ){
        this.repository = repository;
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
    }


    public void createLog(
            String action,
            String module,
            String description,
            Long targetId
    ){


        UserEntity user =
                SecurityUtils.getCurrentUser();

        AuditLog log = new AuditLog();



        if(user != null){
            log.setUserId(user.getId());
            log.setUsername(user.getEmail());
            log.setRole(user.getRole());

        }

        else {
            log.setUsername("UNKNOWN");
            log.setRole("UNKNOWN");

        }

        log.setAction(action);
        log.setModule(module);
        log.setDescription(description);
        log.setTargetId(targetId);

        repository.save(log);

    }

    public void createLog(
        UserEntity user,
        String action,
        String module,
        String description,
        Long targetId

    ){
        AuditLog log = new AuditLog();
        log.setUserId(user.getId());
        log.setUsername(user.getEmail());
        log.setRole(user.getRole());
        log.setAction(action);
        log.setModule(module);
        log.setDescription(description);
        log.setTargetId(targetId);
        repository.save(log);

    }

    public List<MyActivityResponse> getMyActivity() {

    UserEntity user = SecurityUtils.getCurrentUser();

    if (user == null) {
        throw new RuntimeException("User not authenticated.");
    }

    return repository
            .findByUserIdAndActionNotOrderByCreatedAtDesc(
                    user.getId(),
                    "LOGIN"
            )
            .stream()
            .map(log -> {

                String description = log.getDescription();

                if ("CONSULTATION".equals(log.getModule())) {

                    var consultation =
                            consultationRepository.findById(log.getTargetId());

                    if (consultation.isPresent()) {

                        var patient =
                                consultation.get().getPatient();

                        description =
                                switch (log.getAction()) {

                                    case "CREATE" ->
                                            "Created consultation for "
                                                    + patient.getFirstName()
                                                    + " "
                                                    + patient.getLastName();

                                    case "UPDATE" ->
                                            "Updated consultation for "
                                                    + patient.getFirstName()
                                                    + " "
                                                    + patient.getLastName();

                                    case "DELETE" ->
                                            description;

                                    default -> description;

                                };

                    }

                }

                if ("PATIENT".equals(log.getModule())) {

                    var patient =
                            patientRepository.findById(log.getTargetId());

                    if (patient.isPresent()) {

                        description =
                                switch (log.getAction()) {

                                    case "CREATE" ->
                                            "Registered patient "
                                                    + patient.get().getFirstName()
                                                    + " "
                                                    + patient.get().getLastName();

                                    case "UPDATE" ->
                                            "Updated patient "
                                                    + patient.get().getFirstName()
                                                    + " "
                                                    + patient.get().getLastName();

                                    case "DELETE" ->
                                            "Deleted patient "
                                                    + patient.get().getFirstName()
                                                    + " "
                                                    + patient.get().getLastName();

                                    default -> description;

                                };

                    }

                }

                return new MyActivityResponse(

                        log.getId(),

                        log.getAction(),

                        log.getModule(),

                        description,

                        log.getCreatedAt()

                );

            })
            .toList();

}

}