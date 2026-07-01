package edu.cit.gaane.caresync.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cit.gaane.caresync.entities.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    Optional<ProfileEntity> findByEmail(String email);
}