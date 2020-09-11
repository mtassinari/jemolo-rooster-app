package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class AnagraficaCandidatoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnagraficaCandidato.class);
        AnagraficaCandidato anagraficaCandidato1 = new AnagraficaCandidato();
        anagraficaCandidato1.setId(1L);
        AnagraficaCandidato anagraficaCandidato2 = new AnagraficaCandidato();
        anagraficaCandidato2.setId(anagraficaCandidato1.getId());
        assertThat(anagraficaCandidato1).isEqualTo(anagraficaCandidato2);
        anagraficaCandidato2.setId(2L);
        assertThat(anagraficaCandidato1).isNotEqualTo(anagraficaCandidato2);
        anagraficaCandidato1.setId(null);
        assertThat(anagraficaCandidato1).isNotEqualTo(anagraficaCandidato2);
    }
}
