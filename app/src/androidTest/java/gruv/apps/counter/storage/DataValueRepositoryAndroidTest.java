package gruv.apps.counter.storage;

import android.app.Application;
import android.test.ApplicationTestCase;

import gruv.apps.counter.storage.model.StorageModel;

/**
 * Тест репозитория (save, load)
 *
 * @author Goncharenko Ruslan
 */

//@RunWith(AndroidJUnit4.class)
public class DataValueRepositoryAndroidTest extends ApplicationTestCase<Application> {

    public DataValueRepositoryAndroidTest() {
        super(Application.class);
    }

    public void testSaveLoad() {
        int intValue = 5;
        StorageModel storageModel = new StorageModel(intValue);

        DataValueRepository dataValueRepository = new DataValueRepository();

        // устанавливаем значение в репозиторий
        dataValueRepository.set(storageModel);

        // считываем значение с репозитория
        StorageModel storageModelActual = dataValueRepository.get();

        //проверяем
        assertEquals(storageModel, storageModelActual);

        //mContext = getInstrumentation().getContext();

        // сохраняем значение в репозитории
        dataValueRepository.save(mContext);

        //меняем значение в репозитории
        int newIntValue = 10;
        storageModel.setValue(newIntValue);
        dataValueRepository.set(storageModel);

        // считываем значение с репозитория
        StorageModel storageModelNewActual = dataValueRepository.get();

        //проверяем, что оно изменилось
        assertEquals(storageModel, storageModelNewActual);

        // восстанавливаем значение в репозитории
        dataValueRepository.save(mContext);

        // считываем значение с репозитория
        StorageModel storageModelActualNew = dataValueRepository.get();

        //проверяем
        assertEquals(new StorageModel(newIntValue), storageModelActualNew);
        //assertNotEquals(storageModel, storageModelActualNew);
    }
}
