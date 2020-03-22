package it.laziocrea.jemoloapp.repository;

import it.laziocrea.jemoloapp.domain.AnagraficaCandidato;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AnagraficaCandidato entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnagraficaCandidatoRepository extends JpaRepository<AnagraficaCandidato, Long>, JpaSpecificationExecutor<AnagraficaCandidato> {

}
