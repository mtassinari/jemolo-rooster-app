package it.laziocrea.jemoloapp.repository;

import it.laziocrea.jemoloapp.domain.Allegato;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Allegato entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AllegatoRepository extends JpaRepository<Allegato, Long> {
}
