package it.laziocrea.jemoloapp.repository;

import it.laziocrea.jemoloapp.domain.Lingua;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Lingua entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LinguaRepository extends JpaRepository<Lingua, Long> {
}
