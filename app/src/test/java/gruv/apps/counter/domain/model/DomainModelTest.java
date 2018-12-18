package gruv.apps.counter.domain.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Тест модели DomainModel
 *
 * @author Goncharenko Ruslan
 */
public class DomainModelTest {

    @Test
    public void test() {
        int value = 5;
        String string = String.valueOf(value);
        DomainModel domainModel = new DomainModel(string);

        assertNotNull(domainModel);
        assertEquals(domainModel.getValue(), string);

        int newValue = -10;
        String newString = String.valueOf(newValue);
    }
}
