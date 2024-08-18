package org.api.socialassistancefundapiv1.controllers;
import java.net.URI;
import org.api.socialassistancefundapiv1.dtos.ApplicantDTO;
import org.api.socialassistancefundapiv1.models.Applicant;
import org.api.socialassistancefundapiv1.services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;
    
    // Fix this back to a DTO    
    @PostMapping("/applicants")
    public ResponseEntity<Applicant> createApplicant(@RequestBody ApplicantDTO applicant) {
    	Applicant newApplicant = applicantService.create(applicant);
    	URI location = ServletUriComponentsBuilder
    	        .fromCurrentRequest()
    	        .path("/{id}")
    	        .buildAndExpand(newApplicant.getId())
    	        .toUri();

    	    return ResponseEntity.created(location).body(newApplicant);
    }

    @GetMapping("/applicants/{id}")
    public ResponseEntity<ApplicantDTO> getApplicant(@PathVariable Integer id) {
    	ApplicantDTO applicant = applicantService.read(id);
        if (applicant != null) {
            return ResponseEntity.ok(applicant);
        }
        return ResponseEntity.notFound().build();
    }
    
    
    // FIX HERE
    @PutMapping("/applicants/{id}")
    public ResponseEntity<Applicant> updateApplicant(@PathVariable Integer id, @RequestBody ApplicantDTO applicant) {
    	Applicant updatedApplicant = applicantService.update(id, applicant);
        if (updatedApplicant != null) {
            return ResponseEntity.ok(updatedApplicant);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/applicants/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Integer id) {
    	applicantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/applicants")
    public ResponseEntity<Page<ApplicantDTO>> listApplicants(Pageable pageable) {
        Page<ApplicantDTO> applicants = applicantService.list(pageable);
        return ResponseEntity.ok(applicants);
    }

}
