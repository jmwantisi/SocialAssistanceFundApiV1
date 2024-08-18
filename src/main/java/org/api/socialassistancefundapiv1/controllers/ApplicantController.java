package org.api.socialassistancefundapiv1.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This controller can be renamed name to ApplicantController in future when plan to scale up
@RestController
@RequestMapping("/api/v1/applicants")
public class MainController {
	// CRUD Operations for applicants
	// Create	
	@PostMapping
    public ResponseEntity<Object> create(@RequestBody Object applicant) {
        return null;
    }
	
	//Index	
	@GetMapping
	public List<Object> list () {
		return null;
	}
	
	// Read
	@GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable Integer id) {
        return null;
    }
	
	// Update
	@PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Object applicant) {
        return null;
    }
	
	// Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return null;
    }
	
}
