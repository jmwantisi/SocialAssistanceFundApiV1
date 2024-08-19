package org.api.socialassistancefundapiv1.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.api.socialassistancefundapiv1.models.*;
import org.api.socialassistancefundapiv1.repositories.*;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private SubCountyRepository subCountyRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SubLocationRepository subLocationRepository;

    @Autowired
    private VillageRepository villageRepository;

    @Autowired
    private ProgramsRepository programRepository;

    @Autowired
    private ApplicantProgramRepository applicantProgramRepository;

    @Override
    public void run(String... args) throws Exception {
        seedCounties();
        seedSubCounties();
        seedLocations();
        seedSubLocations();
        seedVillages();
        seedPrograms();
        //seedApplicantPrograms();
    }

    private void seedCounties() {
        if (countyRepository.count() == 0) {
            County county1 = new County();
            county1.setName("County A");
            countyRepository.save(county1);

            County county2 = new County();
            county2.setName("County B");
            countyRepository.save(county2);

            System.out.println("Counties Seeded!");
        }
    }

    private void seedSubCounties() {
        if (subCountyRepository.count() == 0) {
            County countyA = countyRepository.findByName("County A").orElseThrow(() -> new RuntimeException("County A not found"));
            County countyB = countyRepository.findByName("County B").orElseThrow(() -> new RuntimeException("County B not found"));

            SubCounty subCounty1 = new SubCounty();
            subCounty1.setName("SubCounty A1");
            subCounty1.setCounty(countyA);
            subCountyRepository.save(subCounty1);

            SubCounty subCounty2 = new SubCounty();
            subCounty2.setName("SubCounty B1");
            subCounty2.setCounty(countyB);
            subCountyRepository.save(subCounty2);

            System.out.println("SubCounties Seeded!");
        }
    }

    private void seedLocations() {
        if (locationRepository.count() == 0) {
            SubCounty subCountyA1 = subCountyRepository.findByName("SubCounty A1").orElseThrow(() -> new RuntimeException("SubCounty A1 not found"));
            SubCounty subCountyB1 = subCountyRepository.findByName("SubCounty B1").orElseThrow(() -> new RuntimeException("SubCounty B1 not found"));

            Location location1 = new Location();
            location1.setName("Location A1");
            location1.setSubCounty(subCountyA1);
            locationRepository.save(location1);

            Location location2 = new Location();
            location2.setName("Location B1");
            location2.setSubCounty(subCountyB1);
            locationRepository.save(location2);

            System.out.println("Locations Seeded!");
        }
    }

    private void seedSubLocations() {
        if (subLocationRepository.count() == 0) {
            Location locationA1 = locationRepository.findByName("Location A1").orElseThrow(() -> new RuntimeException("Location A1 not found"));
            Location locationB1 = locationRepository.findByName("Location B1").orElseThrow(() -> new RuntimeException("Location B1 not found"));

            SubLocation subLocation1 = new SubLocation();
            subLocation1.setName("SubLocation A1");
            subLocation1.setLocation(locationA1);
            subLocationRepository.save(subLocation1);

            SubLocation subLocation2 = new SubLocation();
            subLocation2.setName("SubLocation B1");
            subLocation2.setLocation(locationB1);
            subLocationRepository.save(subLocation2);

            System.out.println("SubLocations Seeded!");
        }
    }

    private void seedVillages() {
        if (villageRepository.count() == 0) {
            SubLocation subLocationA1 = subLocationRepository.findByName("SubLocation A1").orElseThrow(() -> new RuntimeException("SubLocation A1 not found"));
            SubLocation subLocationB1 = subLocationRepository.findByName("SubLocation B1").orElseThrow(() -> new RuntimeException("SubLocation B1 not found"));

            Village village1 = new Village();
            village1.setName("Village A1");
            village1.setSubLocation(subLocationA1);
            villageRepository.save(village1);

            Village village2 = new Village();
            village2.setName("Village B1");
            village2.setSubLocation(subLocationB1);
            villageRepository.save(village2);

            System.out.println("Villages Seeded!");
        }
    }

    private void seedPrograms() {
        if (programRepository.count() == 0) {
            Program program1 = new Program();
            program1.setName("Orphans and vulnerable children");
            programRepository.save(program1);

            Program program2 = new Program();
            program2.setName("Poor elderly persons");
            programRepository.save(program2);

            Program program3 = new Program();
            program3.setName("Persons with disability");
            programRepository.save(program3);

            Program program4 = new Program();
            program3.setName("Persons in extreme poverty");
            programRepository.save(program4);

            Program program5 = new Program();
            program3.setName("Any other");
            programRepository.save(program5);

            System.out.println("Programs Seeded!");
        }
    }

    private void seedApplicantPrograms() {
        if (applicantProgramRepository.count() == 0) {
            Program programA = programRepository.findByName("Program A").orElseThrow(() -> new RuntimeException("Program A not found"));
            Program programB = programRepository.findByName("Program B").orElseThrow(() -> new RuntimeException("Program B not found"));

            ApplicantProgram applicantProgram1 = new ApplicantProgram();
            applicantProgram1.setProgram(programA);
            applicantProgramRepository.save(applicantProgram1);

            ApplicantProgram applicantProgram2 = new ApplicantProgram();
            applicantProgram2.setProgram(programB);
            applicantProgramRepository.save(applicantProgram2);

            System.out.println("Applicant Programs Seeded!");
        }
    }
}

