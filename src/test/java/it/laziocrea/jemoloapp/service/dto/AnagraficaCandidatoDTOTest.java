package it.laziocrea.jemoloapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class AnagraficaCandidatoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnagraficaCandidatoDTO.class);
        AnagraficaCandidatoDTO anagraficaCandidatoDTO1 = new AnagraficaCandidatoDTO();
        anagraficaCandidatoDTO1.setId(1L);
        AnagraficaCandidatoDTO anagraficaCandidatoDTO2 = new AnagraficaCandidatoDTO();
        assertThat(anagraficaCandidatoDTO1).isNotEqualTo(anagraficaCandidatoDTO2);
        anagraficaCandidatoDTO2.setId(anagraficaCandidatoDTO1.getId());
        assertThat(anagraficaCandidatoDTO1).isEqualTo(anagraficaCandidatoDTO2);
        anagraficaCandidatoDTO2.setId(2L);
        assertThat(anagraficaCandidatoDTO1).isNotEqualTo(anagraficaCandidatoDTO2);
        anagraficaCandidatoDTO1.setId(null);
        assertThat(anagraficaCandidatoDTO1).isNotEqualTo(anagraficaCandidatoDTO2);
    }
}
