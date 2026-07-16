package edu.cit.gaane.caresync.features.consultation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.cit.gaane.caresync.features.consultation.entity.ConsultationEntity;

public interface ConsultationRepository
        extends JpaRepository<ConsultationEntity,Long>{

    List<ConsultationEntity> findByPatientId(Long patientId);

}