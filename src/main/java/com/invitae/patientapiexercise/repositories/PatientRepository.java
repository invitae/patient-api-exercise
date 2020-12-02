package com.invitae.patientapiexercise.repositories;

import com.invitae.patientapiexercise.models.BiologicalSex;
import com.invitae.patientapiexercise.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    /**
     * Gets a page of patients with the given biological sex.
     * No need to implement - Spring Data JPA does that for us based on the method name.
     *
     * @param biologicalSex the biological sex of the patients
     * @param pageable the page request
     * @return a page of patients
     */
    Page<Patient> findAllByBiologicalSex(BiologicalSex biologicalSex, Pageable pageable);
}
