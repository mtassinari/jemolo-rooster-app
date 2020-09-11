package it.laziocrea.jemoloapp.repository;

import it.laziocrea.jemoloapp.domain.Comune;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Comune entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long> {
}
