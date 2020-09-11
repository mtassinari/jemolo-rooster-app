package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class ComuneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Comune.class);
        Comune comune1 = new Comune();
        comune1.setId(1L);
        Comune comune2 = new Comune();
        comune2.setId(comune1.getId());
        assertThat(comune1).isEqualTo(comune2);
        comune2.setId(2L);
        assertThat(comune1).isNotEqualTo(comune2);
        comune1.setId(null);
        assertThat(comune1).isNotEqualTo(comune2);
    }
}
