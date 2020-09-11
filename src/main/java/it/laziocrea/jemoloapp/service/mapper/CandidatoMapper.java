package it.laziocrea.jemoloapp.service.mapper;


import it.laziocrea.jemoloapp.domain.*;
import it.laziocrea.jemoloapp.service.dto.CandidatoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Candidato} and its DTO {@link CandidatoDTO}.
 */
@Mapper(componentModel = "spring", uses = {AnagraficaCandidatoMapper.class, StatoRegistrazioneMapper.class})
public interface CandidatoMapper extends EntityMapper<CandidatoDTO, Candidato> {

    @Mapping(source = "anagraficaCandidato.id", target = "anagraficaCandidatoId")
    @Mapping(source = "statoRegistrazione.id", target = "statoRegistrazioneId")
    CandidatoDTO toDto(Candidato candidato);

    @Mapping(source = "anagraficaCandidatoId", target = "anagraficaCandidato")
    @Mapping(source = "statoRegistrazioneId", target = "statoRegistrazione")
    Candidato toEntity(CandidatoDTO candidatoDTO);

    default Candidato fromId(Long id) {
        if (id == null) {
            return null;
        }
        Candidato candidato = new Candidato();
        candidato.setId(id);
        return candidato;
    }
}
