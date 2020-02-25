package it.laziocrea.jemoloapp.service.mapper;


import it.laziocrea.jemoloapp.domain.*;
import it.laziocrea.jemoloapp.service.dto.CurriculumDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Curriculum} and its DTO {@link CurriculumDTO}.
 */
@Mapper(componentModel = "spring", uses = {AllegatoMapper.class, AnagraficaCandidatoMapper.class})
public interface CurriculumMapper extends EntityMapper<CurriculumDTO, Curriculum> {

    @Mapping(source = "attach.id", target = "attachId")
    @Mapping(source = "anagrafica.id", target = "anagraficaId")
    CurriculumDTO toDto(Curriculum curriculum);

    @Mapping(source = "attachId", target = "attach")
    @Mapping(source = "anagraficaId", target = "anagrafica")
    Curriculum toEntity(CurriculumDTO curriculumDTO);

    default Curriculum fromId(Long id) {
        if (id == null) {
            return null;
        }
        Curriculum curriculum = new Curriculum();
        curriculum.setId(id);
        return curriculum;
    }
}
