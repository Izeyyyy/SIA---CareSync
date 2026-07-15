package edu.cit.gaane.caresync.audit.service;


import org.springframework.stereotype.Service;

import edu.cit.gaane.caresync.audit.entity.AuditLog;
import edu.cit.gaane.caresync.audit.repository.AuditLogRepository;
import edu.cit.gaane.caresync.config.SecurityUtils;
import edu.cit.gaane.caresync.features.authentication.entity.UserEntity;


@Service
public class AuditLogService {


    private final AuditLogRepository repository;


    public AuditLogService(
            AuditLogRepository repository
    ){

        this.repository = repository;

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

}