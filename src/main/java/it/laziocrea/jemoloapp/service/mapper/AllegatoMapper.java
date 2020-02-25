package it.laziocrea.jemoloapp.service.mapper;


import it.laziocrea.jemoloapp.domain.*;
import it.laziocrea.jemoloapp.service.dto.AllegatoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Allegato} and its DTO {@link AllegatoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AllegatoMapper extends EntityMapper<AllegatoDTO, Allegato> {

    @Mapping(target = "curriculum", ignore = true)
    Allegato toEntity(AllegatoDTO allegatoDTO);
    
    default Allegato fromId(Long id) {
        if (id == null) {
            return null;
        }
        Allegato allegato = new Allegato();
        allegato.setId(id);
        return allegato;
    }
}
