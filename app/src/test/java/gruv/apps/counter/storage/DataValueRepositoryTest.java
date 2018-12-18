package gruv.apps.counter.storage;

import gruv.apps.counter.storage.model.StorageModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тест репозитория (set, get)
 *
 * @author Goncharenko Ruslan
 */
public class DataValueRepositoryTest {

    private DataValueRepository dataValueRepository;

    @Before
    public void setUp() {
        dataValueRepository = new DataValueRepository();
    }

    @Test
    public void testSetGet() {
        int intValue = 5;
        StorageModel storageModel = new StorageModel(intValue);

        dataValueRepository.set(storageModel);

        StorageModel storageModelActual = dataValueRepository.get();

        assertEquals(storageModel, storageModelActual);
    }
}
