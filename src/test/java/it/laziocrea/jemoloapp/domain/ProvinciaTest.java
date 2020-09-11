package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class ProvinciaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Provincia.class);
        Provincia provincia1 = new Provincia();
        provincia1.setId(1L);
        Provincia provincia2 = new Provincia();
        provincia2.setId(provincia1.getId());
        assertThat(provincia1).isEqualTo(provincia2);
        provincia2.setId(2L);
        assertThat(provincia1).isNotEqualTo(provincia2);
        provincia1.setId(null);
        assertThat(provincia1).isNotEqualTo(provincia2);
    }
}
