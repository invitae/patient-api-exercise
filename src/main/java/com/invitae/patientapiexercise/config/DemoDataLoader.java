package com.invitae.patientapiexercise.config;

import com.invitae.patientapiexercise.models.BiologicalSex;
import com.invitae.patientapiexercise.models.Patient;
import com.invitae.patientapiexercise.models.Variant;
import com.invitae.patientapiexercise.models.Zygosity;
import com.invitae.patientapiexercise.repositories.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Loads some random data into the database.
 */
@Configuration      // Indicates that a class declares one or more @Bean methods
@Slf4j              // Auto-create log instance
public class DemoDataLoader {

    private final List<String> genes =
            List.of("ALK", "APC", "ATM", "AXIN2", "BAP1", "BARD1", "BLM", "BMPR1A", "BRCA1", "BRCA2", "BRIP1", "CASR",
                    "CDC73", "CDH1", "CDK4", "CDKN1B", "CDKN1C", "CDKN2A", "CEBPA", "CHEK2", "CTNNA1", "DICER1");

    /**
     * Spring Boot runs all CommandLineRunner beans once the application context is loaded.
     */
    @Bean
    CommandLineRunner loadData(PatientRepository patientRepository) {
        // Callback used to run the bean
        return args -> {
            // Generate list of 1000 patients
            List<Patient> patients = IntStream.range(0, 1000)
                    .mapToObj(
                            i -> new Patient("first" + i, "last" + i, getRandomDateOfBirth(), getRandomBiologicalSex(),
                                    getRandomVariantSet(), null))
                    .collect(Collectors.toList());

            patientRepository.saveAll(patients);
        };
    }

    private BiologicalSex getRandomBiologicalSex() {
        int index = ThreadLocalRandom.current().nextInt(0, BiologicalSex.values().length);
        return BiologicalSex.values()[index];
    }

    private LocalDate getRandomDateOfBirth() {
        // Pick a random year in the last 80 years
        int currentYear = Year.now().getValue();
        int year = ThreadLocalRandom.current().nextInt(currentYear - 80, currentYear);
        // Pick a random day of the year
        int day = ThreadLocalRandom.current().nextInt(1, 366);
        return LocalDate.ofYearDay(year, day);
    }

    private String getRandomGene() {
        int index = ThreadLocalRandom.current().nextInt(0, genes.size());
        return genes.get(index);
    }

    private String getRandomVariantId() {
        int id = ThreadLocalRandom.current().nextInt(0, 999);
        return "VI" + id;
    }

    private Zygosity getRandomZygosity() {
        int index = ThreadLocalRandom.current().nextInt(0, Zygosity.values().length);
        return Zygosity.values()[index];
    }

    private Set<Variant> getRandomVariantSet() {
        // Get 0-9 variants
        int numVariants = ThreadLocalRandom.current().nextInt(0, 10);
        return IntStream.range(0, numVariants)
                .mapToObj(i -> new Variant(getRandomVariantId(), getRandomGene(), getRandomZygosity()))
                .collect(Collectors.toSet());
    }
}
