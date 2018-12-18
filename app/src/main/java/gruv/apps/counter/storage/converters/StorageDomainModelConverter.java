package gruv.apps.counter.storage.converters;

import android.support.annotation.NonNull;

import gruv.apps.counter.domain.model.DomainModel;
import gruv.apps.counter.storage.model.StorageModel;

/**
 * Конвертеры из StorageModel в DomainModel и обратно
 *
 * @author Goncharenko Ruslan
 */
public class StorageDomainModelConverter {

    /**
     * Конвертер из StorageModel в DomainModel (из внешнего уровня во внутренний)
     */
    @NonNull
    public static DomainModel convertToDomainModel(@NonNull StorageModel storageModel) {
        int intValue = storageModel.getValue();
        String stringValue = String.valueOf(intValue);
        DomainModel model = new DomainModel(stringValue);
        return model;
    }

    /**
     * Конвертер из DomainModel в StorageModel (из внутреннего уровня во внешний)
     */
    @NonNull
    public static StorageModel convertToStorageModel(@NonNull DomainModel domainModel) {
        String stringValue = domainModel.getValue();
        int intValue = 0;
        if (stringValue != null) {
            intValue = Integer.valueOf(stringValue);
        }
        StorageModel model = new StorageModel(intValue);
        return model;
    }
}
