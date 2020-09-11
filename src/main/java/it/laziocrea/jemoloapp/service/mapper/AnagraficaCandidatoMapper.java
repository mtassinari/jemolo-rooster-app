package it.laziocrea.jemoloapp.service.mapper;


import it.laziocrea.jemoloapp.domain.*;
import it.laziocrea.jemoloapp.service.dto.AnagraficaCandidatoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnagraficaCandidato} and its DTO {@link AnagraficaCandidatoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AnagraficaCandidatoMapper extends EntityMapper<AnagraficaCandidatoDTO, AnagraficaCandidato> {


    @Mapping(target = "competenzeLngs", ignore = true)
    @Mapping(target = "removeCompetenzeLng", ignore = true)
    @Mapping(target = "titoloStudios", ignore = true)
    @Mapping(target = "removeTitoloStudio", ignore = true)
    @Mapping(target = "curricula", ignore = true)
    @Mapping(target = "removeCurriculum", ignore = true)
    @Mapping(target = "competenzas", ignore = true)
    @Mapping(target = "removeCompetenza", ignore = true)
    @Mapping(target = "dichiarazionis", ignore = true)
    @Mapping(target = "removeDichiarazioni", ignore = true)
    @Mapping(target = "candidato", ignore = true)
    AnagraficaCandidato toEntity(AnagraficaCandidatoDTO anagraficaCandidatoDTO);

    default AnagraficaCandidato fromId(Long id) {
        if (id == null) {
            return null;
        }
        AnagraficaCandidato anagraficaCandidato = new AnagraficaCandidato();
        anagraficaCandidato.setId(id);
        return anagraficaCandidato;
    }
}
