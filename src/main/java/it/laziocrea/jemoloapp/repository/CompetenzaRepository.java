package it.laziocrea.jemoloapp.repository;

import it.laziocrea.jemoloapp.domain.Competenza;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Competenza entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompetenzaRepository extends JpaRepository<Competenza, Long> {

}
