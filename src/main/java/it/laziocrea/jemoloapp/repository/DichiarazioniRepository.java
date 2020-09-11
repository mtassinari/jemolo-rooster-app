package it.laziocrea.jemoloapp.repository;

import it.laziocrea.jemoloapp.domain.Dichiarazioni;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Dichiarazioni entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DichiarazioniRepository extends JpaRepository<Dichiarazioni, Long> {
}
