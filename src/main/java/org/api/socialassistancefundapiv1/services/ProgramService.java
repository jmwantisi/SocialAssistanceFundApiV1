package org.api.socialassistancefundapiv1.services;
import org.api.socialassistancefundapiv1.models.Program;
import org.api.socialassistancefundapiv1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProgramService {

    private final ProgramsRepository programsRepository;

    @Autowired
    public ProgramService(ProgramsRepository programsRepository) {
        this.programsRepository = programsRepository;
    }
    public List<Program> list() {
        return programsRepository.findAll();
    }
}
