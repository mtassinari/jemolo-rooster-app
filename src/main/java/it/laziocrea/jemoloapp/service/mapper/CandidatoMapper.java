package it.laziocrea.jemoloapp.service.mapper;


import it.laziocrea.jemoloapp.domain.*;
import it.laziocrea.jemoloapp.service.dto.CandidatoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Candidato} and its DTO {@link CandidatoDTO}.
 */
@Mapper(componentModel = "spring", uses = {StatoRegistrazioneMapper.class})
public interface CandidatoMapper extends EntityMapper<CandidatoDTO, Candidato> {

    @Mapping(source = "statoRegistrazione.id", target = "statoRegistrazioneId")
    CandidatoDTO toDto(Candidato candidato);

    @Mapping(target = "anagraficaCandidato", ignore = true)
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
