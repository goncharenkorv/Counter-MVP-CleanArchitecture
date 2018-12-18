package gruv.apps.counter.storage.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Тест модели StorageModel
 *
 * @author Goncharenko Ruslan
 */
public class StorageModelTest {

    @Test
    public void test() {
        int value = 5;
        StorageModel storageModel = new StorageModel(value);

        assertNotNull(storageModel);
        assertEquals(storageModel.getValue(), value);

        int newValue = -10;

        storageModel.setValue(newValue);

        assertNotNull(storageModel);
        assertEquals(storageModel.getValue(), newValue);
    }
}
