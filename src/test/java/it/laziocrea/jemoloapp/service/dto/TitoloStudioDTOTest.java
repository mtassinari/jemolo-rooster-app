package it.laziocrea.jemoloapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class TitoloStudioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TitoloStudioDTO.class);
        TitoloStudioDTO titoloStudioDTO1 = new TitoloStudioDTO();
        titoloStudioDTO1.setId(1L);
        TitoloStudioDTO titoloStudioDTO2 = new TitoloStudioDTO();
        assertThat(titoloStudioDTO1).isNotEqualTo(titoloStudioDTO2);
        titoloStudioDTO2.setId(titoloStudioDTO1.getId());
        assertThat(titoloStudioDTO1).isEqualTo(titoloStudioDTO2);
        titoloStudioDTO2.setId(2L);
        assertThat(titoloStudioDTO1).isNotEqualTo(titoloStudioDTO2);
        titoloStudioDTO1.setId(null);
        assertThat(titoloStudioDTO1).isNotEqualTo(titoloStudioDTO2);
    }
}
