package it.laziocrea.jemoloapp.repository;

import it.laziocrea.jemoloapp.domain.AmbitoCompetenza;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AmbitoCompetenza entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AmbitoCompetenzaRepository extends JpaRepository<AmbitoCompetenza, Long> {

}
