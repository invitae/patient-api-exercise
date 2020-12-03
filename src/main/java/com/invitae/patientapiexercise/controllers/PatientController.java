package com.invitae.patientapiexercise.controllers;

import com.invitae.patientapiexercise.models.Patient;
import com.invitae.patientapiexercise.repositories.PatientRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@Slf4j
@RestController
@RequestMapping(value = "/v0/patients")
@Validated
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Get a page of patients.
     *
     * @param pageNumber the desired page number, starting with 0
     * @param pageSize   the desired page size
     * @return the page of patients
     */
    @ApiOperation(value = "Get a page of patients.")
    @GetMapping
    public Page<Patient> getPatients(
            @ApiParam(value = "Page number, starting with 0") @RequestParam(defaultValue = "0") @Min(0) int pageNumber,
            @ApiParam(value = "Page size, maximum of 100") @RequestParam(defaultValue = "10")
            @Range(min = 0, max = 100) int pageSize) {

        log.info("Received GET /patients request with pageNumber={}, size={}", pageNumber, pageSize);

        return patientRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
}
