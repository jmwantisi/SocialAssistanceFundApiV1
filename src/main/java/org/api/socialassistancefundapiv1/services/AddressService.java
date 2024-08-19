package org.api.socialassistancefundapiv1.services;

import org.api.socialassistancefundapiv1.models.*;
import org.api.socialassistancefundapiv1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private final CountyRepository countyRepository;

    @Autowired
    private final SubCountyRepository subCountyRepository;

    @Autowired
    private final LocationRepository locationRepository;

    @Autowired
    private final SubLocationRepository subLocationRepository;

    @Autowired
    private final VillageRepository villageRepository;

    @Autowired
    public AddressService(CountyRepository countyRepository, SubCountyRepository subCountyRepository,
                          LocationRepository locationRepository, SubLocationRepository subLocationRepository,
                          VillageRepository villageRepository) {
        this.countyRepository = countyRepository;
        this.subCountyRepository = subCountyRepository;
        this.locationRepository = locationRepository;
        this.subLocationRepository = subLocationRepository;
        this.villageRepository = villageRepository;
    }

    public List<Location> findBySubCountyId(int subCountyId) {
        return locationRepository.findBySubCountyId(subCountyId);
    }

    public List<Village> listAllVillages() {
        return villageRepository.findAll();
    }

    public Optional<Village> readVillageById(int id) {
        return villageRepository.findById(id);
    }

    public Optional<Location> readLocationById(int id) {
        return locationRepository.findById(id);
    }

    public List<SubLocation> listAllSubLocations() {
        return subLocationRepository.findAll();
    }

    public List<Location> listAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<SubLocation> readSubLocationById(int id) {
        return subLocationRepository.findById(id);
    }

    public List<SubCounty> listAllSubCounties() {
        return subCountyRepository.findAll();
    }

    public Optional<SubCounty> readSubCountyById(int id) {
        return subCountyRepository.findById(id);
    }

    public List<County> listAllCounties() {
        return countyRepository.findAll();
    }

    public Optional<County> readCountyById(int id) {
        return countyRepository.findById(id);
    }

    public List<SubCounty> listSubCountiesByCountyId(int id) {
        return subCountyRepository.findByCountyId(id);
    }

    public List<SubLocation> listSubLocationsByLocationId(int id) {
        return subLocationRepository.findByLocationId(id);
    }

    public List<Village> listVillagesBySubLocationId(int id) {
        return villageRepository.findBySubLocationId(id);
    }
}
