package com.invitae.patientapiexercise.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


/**
 * A variant detected in the patient's DNA. Also known as a mutation.
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // No-args constructor required by JPA
public class Variant {

    private String variantId;

    /**
     * The name of the gene that this variant was found in.
     */
    private String gene;

    @Enumerated(EnumType.STRING)
    private Zygosity zygosity;
}
