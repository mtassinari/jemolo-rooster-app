package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class CandidatoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Candidato.class);
        Candidato candidato1 = new Candidato();
        candidato1.setId(1L);
        Candidato candidato2 = new Candidato();
        candidato2.setId(candidato1.getId());
        assertThat(candidato1).isEqualTo(candidato2);
        candidato2.setId(2L);
        assertThat(candidato1).isNotEqualTo(candidato2);
        candidato1.setId(null);
        assertThat(candidato1).isNotEqualTo(candidato2);
    }
}
