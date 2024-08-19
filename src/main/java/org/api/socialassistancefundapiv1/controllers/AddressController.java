package org.api.socialassistancefundapiv1.controllers;

import org.api.socialassistancefundapiv1.models.*;
import org.api.socialassistancefundapiv1.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/counties")
    public ResponseEntity<List<County>> listAllCounties() {
        List<County> counties = addressService.listAllCounties();
        return ResponseEntity.ok(counties);
    }

    @GetMapping("/counties/{id}")
    public ResponseEntity<County> readCountyById(@PathVariable int id) {
        Optional<County> county = addressService.readCountyById(id);
        return county.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/subcounties")
    public ResponseEntity<List<SubCounty>> listAllSubCounties() {
        List<SubCounty> subCounties = addressService.listAllSubCounties();
        return ResponseEntity.ok(subCounties);
    }

    @GetMapping("/subcounties/{id}")
    public ResponseEntity<SubCounty> readSubCountyById(@PathVariable int id) {
        Optional<SubCounty> subCounty = addressService.readSubCountyById(id);
        return subCounty.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> listAllLocations() {
        List<Location> locations = addressService.listAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> readLocationById(@PathVariable int id) {
        Optional<Location> location = addressService.readLocationById(id);
        return location.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/sublocations")
    public ResponseEntity<List<SubLocation>> listAllSubLocations() {
        List<SubLocation> subLocations = addressService.listAllSubLocations();
        return ResponseEntity.ok(subLocations);
    }

    @GetMapping("/sublocations/{id}")
    public ResponseEntity<SubLocation> readSubLocationById(@PathVariable int id) {
        Optional<SubLocation> subLocation = addressService.readSubLocationById(id);
        return subLocation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/villages")
    public ResponseEntity<List<Village>> listAllVillages() {
        List<Village> villages = addressService.listAllVillages();
        return ResponseEntity.ok(villages);
    }

    @GetMapping("/villages/{id}")
    public ResponseEntity<Village> readVillageById(@PathVariable int id) {
        Optional<Village> village = addressService.readVillageById(id);
        return village.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/counties/{id}/subcounties")
    public ResponseEntity<List<SubCounty>> listSubCountiesByCountyId(@PathVariable int id) {
        List<SubCounty> subCounties = addressService.listSubCountiesByCountyId(id);
        return ResponseEntity.ok(subCounties);
    }

    @GetMapping("/locations/{id}/sublocations")
    public ResponseEntity<List<SubLocation>> listSubLocationsByLocationId(@PathVariable int id) {
        List<SubLocation> subLocations = addressService.listSubLocationsByLocationId(id);
        return ResponseEntity.ok(subLocations);
    }

    @GetMapping("/sublocations/{id}/villages")
    public ResponseEntity<List<Village>> listVillagesBySubLocationId(@PathVariable int id) {
        List<Village> villages = addressService.listVillagesBySubLocationId(id);
        return ResponseEntity.ok(villages);
    }

    @GetMapping("/subcounties/{id}/locations")
    public ResponseEntity<List<Location>> findLocationsBySubCountyId(@PathVariable int id) {
        List<Location> locations = addressService.findBySubCountyId(id);
        return ResponseEntity.ok(locations);
    }
}
