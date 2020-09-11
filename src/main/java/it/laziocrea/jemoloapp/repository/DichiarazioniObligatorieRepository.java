package it.laziocrea.jemoloapp.repository;

import it.laziocrea.jemoloapp.domain.DichiarazioniObligatorie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DichiarazioniObligatorie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DichiarazioniObligatorieRepository extends JpaRepository<DichiarazioniObligatorie, Long> {
}
