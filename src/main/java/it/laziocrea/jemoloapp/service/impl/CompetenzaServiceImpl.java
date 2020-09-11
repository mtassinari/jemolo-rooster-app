package it.laziocrea.jemoloapp.service.impl;

import it.laziocrea.jemoloapp.service.CompetenzaService;
import it.laziocrea.jemoloapp.domain.Competenza;
import it.laziocrea.jemoloapp.repository.CompetenzaRepository;
import it.laziocrea.jemoloapp.service.dto.CompetenzaDTO;
import it.laziocrea.jemoloapp.service.mapper.CompetenzaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Competenza}.
 */
@Service
@Transactional
public class CompetenzaServiceImpl implements CompetenzaService {

    private final Logger log = LoggerFactory.getLogger(CompetenzaServiceImpl.class);

    private final CompetenzaRepository competenzaRepository;

    private final CompetenzaMapper competenzaMapper;

    public CompetenzaServiceImpl(CompetenzaRepository competenzaRepository, CompetenzaMapper competenzaMapper) {
        this.competenzaRepository = competenzaRepository;
        this.competenzaMapper = competenzaMapper;
    }

    @Override
    public CompetenzaDTO save(CompetenzaDTO competenzaDTO) {
        log.debug("Request to save Competenza : {}", competenzaDTO);
        Competenza competenza = competenzaMapper.toEntity(competenzaDTO);
        competenza = competenzaRepository.save(competenza);
        return competenzaMapper.toDto(competenza);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CompetenzaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Competenzas");
        return competenzaRepository.findAll(pageable)
            .map(competenzaMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CompetenzaDTO> findOne(Long id) {
        log.debug("Request to get Competenza : {}", id);
        return competenzaRepository.findById(id)
            .map(competenzaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Competenza : {}", id);
        competenzaRepository.deleteById(id);
    }
}
