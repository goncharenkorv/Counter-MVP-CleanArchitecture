package gruv.apps.counter.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import gruv.apps.counter.domain.repository.Repository;
import gruv.apps.counter.storage.model.StorageModel;

/**
 * Репозиторий (храналище) для счетчика
 * Умеет сохранять/восставливать значение с помощью SharedPreferences
 *
 * @author Goncharenko Ruslan
 */
public class DataValueRepository implements Repository {

    // максимальное значение счетчика:
    public static final int MAX_VALUE = 9999;

    // минимальное значение счетчика:
    public static final int MIN_VALUE = -9999;

    // Имя файла, где будут храниться данные (SharedPreferences)
    private static final String DATA_FILE_NAME = "counters";

    // Ключ для переменной value в файле
    private static final String VALUE_LEY = "key";

    // значение счетчика по-умолчанию:
    private static final int DEFAULT_VALUE = 0;

    private int value = DEFAULT_VALUE;

    private SharedPreferences sharedPreferences;

    @Override
    @NonNull
    public StorageModel get() {
        StorageModel storageModel = new StorageModel(value);
        return storageModel;
    }

    @Override
    public void set(@NonNull StorageModel storageModel) {
        value = storageModel.getValue();
    }

    @Override
    public void save(@NonNull Context context) {
        SharedPreferences.Editor dataEditor = getSharedPreferences(context).edit();
        dataEditor.putInt(VALUE_LEY, value);
        dataEditor.commit();
    }

    @Override
    public void load(@NonNull Context context) {
        if (getSharedPreferences(context).contains(VALUE_LEY)) {
            value = getSharedPreferences(context).getInt(VALUE_LEY, DEFAULT_VALUE);
        }
    }

    /**
     * Возвращает SharedPreferences, создавая при необходимости
     *
     * @return SharedPreferences
     */
    @NonNull
    private SharedPreferences getSharedPreferences(@NonNull Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(DATA_FILE_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }
}