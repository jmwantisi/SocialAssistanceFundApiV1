package org.api.socialassistancefundapiv1.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
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
    
    public Applicant create(ApplicantDTO payload) {
    	System.out.println(payload);
        final Applicant applicant = new Applicant();
        applicant.setFirstName(payload.getFirstName());
        applicant.setMiddleName(payload.getMiddleName());
        applicant.setLastName(payload.getLastName());
        applicant.setSex(payload.getSex());
        applicant.setDob(payload.getDob());
        applicant.setMaritalStatus(payload.getMaritalStatus());
        applicant.setIdNumber(payload.getIdNumber());
        applicant.setApplicationDate(payload.getApplicationDate());
        
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

        PostalAddress postalAddress = new PostalAddress();
        postalAddress.setAddressLine1(payload.getPostalAddress().getAddressLine1());
        postalAddress.setAddressLine2(payload.getPostalAddress().getAddressLine2());
        postalAddress.setPostalCode(payload.getPostalAddress().getPostalCode());
        postalAddress = postalAddressRepository.save(postalAddress);

        applicant.setPostalAddress(postalAddress);

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

        applicantProgramRepository.saveAll(applicantPrograms);
        
        return applicantRepository.save(savedApplicant);
    }


    public ApplicantDTO read(Integer id) {
        return applicantRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public void delete(Integer id) {
    	Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found ID: " + id));
    	if(applicant != null) applicant.setVoid(1);
    	applicantRepository.save(applicant);
    }
    
    
    public Applicant update(Integer id, ApplicantDTO payload) {
        Applicant applicant = applicantRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Applicant not found ID: " + id));
        
        if (payload.getFirstName() != null) applicant.setFirstName(payload.getFirstName());
        if (payload.getMiddleName() != null) applicant.setMiddleName(payload.getMiddleName());
        if (payload.getLastName() != null) applicant.setLastName(payload.getLastName());
        if (payload.getSex() != null) applicant.setSex(payload.getSex());
        if (payload.getDob() != null) applicant.setDob(payload.getDob());
        if (payload.getMaritalStatus() != null) applicant.setMaritalStatus(payload.getMaritalStatus());
        if (payload.getIdNumber() != null) applicant.setIdNumber(payload.getIdNumber());
        if (payload.getApplicationDate() != null) applicant.setApplicationDate(payload.getApplicationDate());

        if (payload.getCountyId() != null) {
            County county = countyRepository.findById(payload.getCountyId())
                .orElseThrow(() -> new RuntimeException("County not found ID: " + payload.getCountyId()));
            applicant.setCounty(county);
        }
        
        if (payload.getSubCountyId() != null) {
            SubCounty subCounty = subCountyRepository.findById(payload.getSubCountyId())
                .orElseThrow(() -> new RuntimeException("SubCounty not found ID: " + payload.getSubCountyId()));
            applicant.setSubCounty(subCounty);
        }

        if (payload.getLocationId() != null) {
            Location location = locationRepository.findById(payload.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found ID: " + payload.getLocationId()));
            applicant.setLocation(location);
        }
        
        if (payload.getSubLocationId() != null) {
            SubLocation subLocation = subLocationRepository.findById(payload.getSubLocationId())
                .orElseThrow(() -> new RuntimeException("SubLocation not found ID: " + payload.getSubLocationId()));
            applicant.setSubLocation(subLocation);
        }

        if (payload.getVillageId() != null) {
            Village village = villageRepository.findById(payload.getVillageId())
                .orElseThrow(() -> new RuntimeException("Village not found ID: " + payload.getVillageId()));
            applicant.setVillage(village);
        }

        if (payload.getPostalAddress() != null) {
            PostalAddress postalAddress = applicant.getPostalAddress();
            if (postalAddress == null) {
                postalAddress = new PostalAddress();
            }
            postalAddress.setAddressLine1(payload.getPostalAddress().getAddressLine1());
            postalAddress.setAddressLine2(payload.getPostalAddress().getAddressLine2());
            postalAddress.setPostalCode(payload.getPostalAddress().getPostalCode());
            postalAddress = postalAddressRepository.save(postalAddress);
            applicant.setPostalAddress(postalAddress);
        }

        if (payload.getPhysicalAddress() != null) {
            PhysicalAddress physicalAddress = applicant.getPhysicalAddress();
            if (physicalAddress == null) {
                physicalAddress = new PhysicalAddress();
            }
            physicalAddress.setStreet(payload.getPhysicalAddress().getStreet());
            physicalAddress.setCity(payload.getPhysicalAddress().getCity());
            physicalAddress.setHouseNumber(payload.getPhysicalAddress().getHouseNumber());
            physicalAddress = physicalAddressRepository.save(physicalAddress);
            applicant.setPhysicalAddress(physicalAddress);
        }

        if (payload.getTelephones() != null) {
            List<Telephone> existingTelephones = telephoneRepository.findByApplicantId(id);
            existingTelephones.forEach(telephoneRepository::delete);

            List<Telephone> telephones = payload.getTelephones().stream().map(phoneDto -> {
                Telephone telephone = new Telephone();
                telephone.setPhoneNumber(phoneDto.getPhoneNumber());
                telephone.setType(phoneDto.getType());
                telephone.setApplicant(applicant);
                return telephone;
            }).collect(Collectors.toList());

            telephoneRepository.saveAll(telephones);
        }

        if (payload.getApplicantProgrammes() != null) {
            List<ApplicantProgram> existingPrograms = applicantProgramRepository.findByApplicantId(id);
            existingPrograms.forEach(applicantProgramRepository::delete);

            List<ApplicantProgram> applicantPrograms = payload.getApplicantProgrammes().stream().map(programId -> {
                Program program = programRepository.findById(programId)
                    .orElseThrow(() -> new RuntimeException("Program not found ID: " + programId));
                
                ApplicantProgram applicantProgram = new ApplicantProgram();
                applicantProgram.setApplicant(applicant);
                applicantProgram.setProgram(program);
                applicantProgram.setApplicationDate(applicant.getApplicationDate());
                return applicantProgram;
            }).collect(Collectors.toList());

            applicantProgramRepository.saveAll(applicantPrograms);
        }

        return applicantRepository.save(applicant);
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

    public CountyDTO convertToDTO(County county) {
        if (county == null) {
            return null;
        }
        CountyDTO dto = new CountyDTO();
        dto.setId(county.getId());
        dto.setName(county.getName());
        return dto;
    }

    public SubCountyDTO convertToDTO(SubCounty subCounty) {
        if (subCounty == null) {
            return null;
        }
        SubCountyDTO dto = new SubCountyDTO();
        dto.setId(subCounty.getId());
        dto.setName(subCounty.getName());
        return dto;
    }

    public LocationDTO convertToDTO(Location location) {
        if (location == null) {
            return null;
        }
        LocationDTO dto = new LocationDTO();
        dto.setId(location.getId());
        dto.setName(location.getName());
        return dto;
    }

    public SubLocationDTO convertToDTO(SubLocation subLocation) {
        if (subLocation == null) {
            return null;
        }
        SubLocationDTO dto = new SubLocationDTO();
        dto.setId(subLocation.getId());
        dto.setName(subLocation.getName());
        return dto;
    }

    public VillageDTO convertToDTO(Village village) {
        if (village == null) {
            return null;
        }
        VillageDTO dto = new VillageDTO();
        dto.setId(village.getId());
        dto.setName(village.getName());
        return dto;
    }
}


