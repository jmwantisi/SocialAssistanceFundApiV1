package org.api.socialassistancefundapiv1.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.api.socialassistancefundapiv1.models.*;
import org.api.socialassistancefundapiv1.repositories.*;
import org.api.socialassistancefundapiv1.dtos.*;

@Service
public class ApplicantService {
	
    @Autowired
    private ApplicantRepository applicantRepository;
    
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
    private PostalAddressRepository postalAddressRepository;
    
    @Autowired
    private PhysicalAddressRepository physicalAddressRepository;

    @Autowired
    private TelephoneRepository telephoneRepository;
    
    @Autowired
    private ProgramsRepository programRepository;
    
    @Autowired
    private ApplicantProgramRepository applicantProgramRepository;
    
    

    public Page<ApplicantDTO> list(Pageable pageable) {
        return applicantRepository.findAll(pageable).map(this::convertToDTO);
    }

//    public ApplicantDTO create(ApplicantDTO applicantDTO) {
//        Applicant applicant = convertToEntity(applicantDTO);
//        List<Telephone> telephone = convertToEntity(applicantDTO.getTelephones());
//        applicant.setTelephones(telephone);
//
//        return convertToDTO(applicantRepository.save(applicant));
//    }
    
    //
    
    public Applicant create(ApplicantDTO payload) {
        // Create Applicant entity
        final Applicant applicant = new Applicant();
        
        // Map basic fields
        applicant.setFirstName(payload.getFirstName());
        applicant.setMiddleName(payload.getMiddleName());
        applicant.setLastName(payload.getLastName());
        applicant.setSex(payload.getSex());
        applicant.setAge(payload.getAge());
        applicant.setMaritalStatus(payload.getMaritalStatus());
        applicant.setIdNumber(payload.getIdNumber());
        applicant.setApplicationDate(payload.getApplicationDate());
        
        // Map relationships (County, SubCounty, Location, SubLocation, Village)
        County county = countyRepository.findById(payload.getCountyId())
            .orElseThrow(() -> new RuntimeException("County not found ID " + payload.getCountyId()));
        SubCounty subCounty = subCountyRepository.findById(payload.getSubCountyId())
            .orElseThrow(() -> new RuntimeException("SubCounty not found"));
        Location location = locationRepository.findById(payload.getLocationId())
            .orElseThrow(() -> new RuntimeException("Location not found"));
        SubLocation subLocation = subLocationRepository.findById(payload.getSubLocationId())
            .orElseThrow(() -> new RuntimeException("SubLocation not found"));
        Village village = villageRepository.findById(payload.getVillageId())
            .orElseThrow(() -> new RuntimeException("Village not found"));

        applicant.setCounty(county);
        applicant.setSubCounty(subCounty);
        applicant.setLocation(location);
        applicant.setSubLocation(subLocation);
        applicant.setVillage(village);

        // Map Postal Address
        PostalAddress postalAddress = new PostalAddress();
        postalAddress.setAddressLine1(payload.getPostalAddress().getAddressLine1());
        postalAddress.setAddressLine2(payload.getPostalAddress().getAddressLine2());
        postalAddress.setPostalCode(payload.getPostalAddress().getPostalCode());
        postalAddress = postalAddressRepository.save(postalAddress);

        applicant.setPostalAddress(postalAddress);

        // Map Physical Address
        PhysicalAddress physicalAddress = new PhysicalAddress();
        physicalAddress.setStreet(payload.getPhysicalAddress().getStreet());
        physicalAddress.setCity(payload.getPhysicalAddress().getCity());
        physicalAddress.setHouseNumber(payload.getPhysicalAddress().getHouseNumber());
        physicalAddress = physicalAddressRepository.save(physicalAddress);

        applicant.setPhysicalAddress(physicalAddress);

        Applicant savedApplicant = applicantRepository.save(applicant);
        List<Telephone> telephones = payload.getTelephones().stream()
            .map(phoneDto -> {
                Telephone telephone = new Telephone();
                telephone.setPhoneNumber(phoneDto.getPhoneNumber());
                telephone.setType(phoneDto.getType());
                telephone.setApplicant(savedApplicant);
                return telephone;
            })
            .collect(Collectors.toList());

        telephoneRepository.saveAll(telephones);
        
        List<Program> programs = payload.getApplicantProgrammes().stream()
            .map(program -> programRepository.findById(program)
                .orElseThrow(() -> new RuntimeException("Program not found ID: " + program)))
            .collect(Collectors.toList());
        
        List<ApplicantProgram> applicantPrograms = programs.stream().map(program -> {
            ApplicantProgram applicantProgram = new ApplicantProgram();
            applicantProgram.setApplicant(applicant);
            applicantProgram.setProgram(program);
            applicantProgram.setApplicationDate(applicant.getApplicationDate());
            System.out.println("Application Date::: " + applicant.getApplicationDate());
            return applicantProgram;
        }).collect(Collectors.toList());

        // 4. Save the ApplicantProgram entities
        applicantProgramRepository.saveAll(applicantPrograms);

        // 5. Set the applicant's programs (for consistency if needed in the response or further logic)
        //applicant.setApplicantProgrammes(applicantPrograms);
        
        

        //applicant.setApplicantProgrammes(programs);

        // Save and return the updated Applicant
        return applicantRepository.save(savedApplicant);
    }


    public ApplicantDTO read(Integer id) {
        return applicantRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public void delete(Integer id) {
    	applicantRepository.deleteById(id);
    }

    public ApplicantDTO update(Integer id, ApplicantDTO updatedApplicantDTO) {
        Optional<Applicant> existingApplicantOptional = applicantRepository.findById(id);

        if (existingApplicantOptional.isPresent()) {
            Applicant existingApplicant = existingApplicantOptional.get();
            existingApplicant.setFirstName(updatedApplicantDTO.getFirstName());
            existingApplicant.setMiddleName(updatedApplicantDTO.getMiddleName());
            existingApplicant.setLastName(updatedApplicantDTO.getLastName());
            existingApplicant.setSex(updatedApplicantDTO.getSex());
            existingApplicant.setAge(updatedApplicantDTO.getAge());
            existingApplicant.setMaritalStatus(updatedApplicantDTO.getMaritalStatus());
            existingApplicant.setIdNumber(updatedApplicantDTO.getIdNumber());
            
            existingApplicant.setPostalAddress(convertToEntity(updatedApplicantDTO.getPostalAddress()));
            existingApplicant.setPhysicalAddress(convertToEntity(updatedApplicantDTO.getPhysicalAddress()));
            existingApplicant.setTelephones(convertToEntity(updatedApplicantDTO.getTelephones()));
            existingApplicant.setApplicationDate(updatedApplicantDTO.getApplicationDate());

            return convertToDTO(applicantRepository.save(existingApplicant));
        } else {
            return null; // Handle case where the applicant doesn't exist
        }
    }


    private Applicant convertToEntity(ApplicantDTO dto) {
        Applicant applicant = new Applicant();
        applicant.setId(dto.getId());
        applicant.setFirstName(dto.getFirstName());
        applicant.setMiddleName(dto.getMiddleName());
        applicant.setLastName(dto.getLastName());
        applicant.setSex(dto.getSex());
        applicant.setAge(dto.getAge());
        applicant.setMaritalStatus(dto.getMaritalStatus());
        applicant.setIdNumber(dto.getIdNumber());
        applicant.setPostalAddress(convertToEntity(dto.getPostalAddress()));
        applicant.setPhysicalAddress(convertToEntity(dto.getPhysicalAddress()));
        applicant.setTelephones(convertToEntity(dto.getTelephones()));
        applicant.setApplicationDate(dto.getApplicationDate());
        
        //
        if (dto.getCountyId() != null) {
        	applicant.setCounty(countyRepository.findById(dto.getCountyId()).orElse(null));
        }
        
        if (dto.getSubCountyId() != null) {
            applicant.setSubCounty(subCountyRepository.findById(dto.getSubCountyId()).orElse(null));
        }
        
        if (dto.getLocationId() != null) {
            applicant.setLocation(locationRepository.findById(dto.getLocationId()).orElse(null));
        }
        
        if (dto.getSubLocationId() != null) {
            applicant.setSubLocation(subLocationRepository.findById(dto.getSubLocationId()).orElse(null));
        }
        if (dto.getVillageId() != null) {
            applicant.setVillage(villageRepository.findById(dto.getVillageId()).orElse(null));
        }
        return applicant;
    }
    
    public PostalAddress convertToEntity(PostalAddressDTO postalAddressDTO) {
        PostalAddress postalAddress = new PostalAddress();
        postalAddress.setAddressLine1(postalAddressDTO.getAddressLine1());
        postalAddress.setAddressLine2(postalAddressDTO.getAddressLine2());
        postalAddress.setPostalCode(postalAddressDTO.getPostalCode());
        return postalAddress;
    }
    
    public PhysicalAddress convertToEntity(PhysicalAddressDTO physicalAddressDTO) {
        if (physicalAddressDTO == null) {
            return null;
        }

        PhysicalAddress physicalAddress = new PhysicalAddress();
        physicalAddress.setStreet(physicalAddressDTO.getStreet());
        physicalAddress.setCity(physicalAddressDTO.getCity());
        physicalAddress.setHouseNumber(physicalAddressDTO.getHouseNumber());
        return physicalAddress;
    }
    
    public Telephone convertToEntity(TelephoneDTO telephoneDTO) {
        if (telephoneDTO == null) {
            return null;
        }

        Telephone telephone = new Telephone();
        telephone.setPhoneNumber(telephoneDTO.getPhoneNumber());
        telephone.setType(telephoneDTO.getType());

        return telephone;
    }
    
    public List<Telephone> convertToEntity(List<TelephoneDTO> telephoneDTOs) {
        if (telephoneDTOs == null) {
            return null;
        }

        return telephoneDTOs.stream()
            .map(this::convertToEntity)
            .collect(Collectors.toList());
    }
    
    // DTO convensions
    
    public ApplicantDTO convertToDTO(Applicant applicant) {
        if (applicant == null) {
            return null;
        }

        ApplicantDTO dto = new ApplicantDTO();
        dto.setId(applicant.getId());
        dto.setApplicationDate(applicant.getApplicationDate());
        dto.setFirstName(applicant.getFirstName());
        dto.setMiddleName(applicant.getMiddleName());
        dto.setLastName(applicant.getLastName());
        dto.setSex(applicant.getSex());
        dto.setAge(applicant.getAge());
        dto.setMaritalStatus(applicant.getMaritalStatus());
        dto.setIdNumber(applicant.getIdNumber());

        dto.setPostalAddress(convertToDTO(applicant.getPostalAddress()));

        dto.setTelephones(convertToDTO(applicant.getTelephones()));

        if (applicant.getCounty() != null) dto.setId(applicant.getCounty().getId());
        if (applicant.getSubCounty() != null) dto.setId(applicant.getSubCounty().getId());
        if (applicant.getLocation() != null) dto.setId(applicant.getLocation().getId());
        if (applicant.getSubLocation() != null) dto.setId(applicant.getSubLocation().getId());
        if (applicant.getVillage() != null) dto.setId(applicant.getVillage().getId());

        return dto;
    }

    public PostalAddressDTO convertToDTO(PostalAddress postalAddress) {
        if (postalAddress == null) {
            return null;
        }
        PostalAddressDTO dto = new PostalAddressDTO();
        dto.setAddressLine1(postalAddress.getAddressLine1());
        dto.setAddressLine2(postalAddress.getAddressLine2());
        dto.setPostalCode(postalAddress.getPostalCode());
        return dto;
    }

    public List<TelephoneDTO> convertToDTO(List<Telephone> telephones) {
        if (telephones == null) {
            return null;
        }

        return telephones.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public TelephoneDTO convertToDTO(Telephone telephone) {
        if (telephone == null) {
            return null;
        }

        TelephoneDTO dto = new TelephoneDTO();
        dto.setPhoneNumber(telephone.getPhoneNumber());
        dto.setType(telephone.getType());

        return dto;
    }
}


