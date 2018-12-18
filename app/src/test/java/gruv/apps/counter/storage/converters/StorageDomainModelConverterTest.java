package gruv.apps.counter.storage.converters;

import gruv.apps.counter.domain.model.DomainModel;
import gruv.apps.counter.storage.model.StorageModel;

import org.junit.Test;

import gruv.apps.counter.domain.model.DomainModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Тест конвертера StorageDomainModelConverter
 *
 * @author Goncharenko Ruslan
 */
public class StorageDomainModelConverterTest {

    @Test
    public void convertToDomainModelTest() {
        int intValue = 5;
        StorageModel storageModel = new StorageModel(intValue);

        DomainModel domainModel = StorageDomainModelConverter.convertToDomainModel(storageModel);

        assertNotNull(domainModel);

        String expected = String.valueOf(intValue);
        String actual = domainModel.getValue();

        assertEquals(expected, actual);
    }

    @Test
    public void convertToStorageModelTest() {
        int intValue = 5;
        String stringValue = String.valueOf(intValue);

        DomainModel domainModel = new DomainModel(stringValue);

        StorageModel storageModel = StorageDomainModelConverter.convertToStorageModel(domainModel);

        assertNotNull(storageModel);

        int expected = intValue;
        int actual = storageModel.getValue();

        assertEquals(expected, actual);
    }
}
