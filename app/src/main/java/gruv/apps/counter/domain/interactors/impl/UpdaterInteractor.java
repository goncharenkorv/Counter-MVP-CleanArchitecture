package gruv.apps.counter.domain.interactors.impl;

import android.support.annotation.NonNull;

import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.ValueUpdaterInteractor;
import gruv.apps.counter.domain.interactors.base.AbstractInteractor;
import gruv.apps.counter.domain.model.DomainModel;
import gruv.apps.counter.domain.repository.Repository;
import gruv.apps.counter.storage.converters.StorageDomainModelConverter;
import gruv.apps.counter.storage.model.StorageModel;

/**
 * Абстрактный класс-интерактор обновления значения счетчика.
 * (вывод обновленного значения в главном потоке)
 *
 * @author Goncharenko Ruslan
 */
public abstract class UpdaterInteractor extends AbstractInteractor {

    private ValueUpdaterInteractor.Callback mUpdateCallback;
    private Repository mRepository;

    public UpdaterInteractor(@NonNull Executor threadExecutor,
                             @NonNull MainThread mainThread,
                             @NonNull ValueUpdaterInteractor.Callback updateCallback,
                             @NonNull Repository repository) {
        super(threadExecutor, mainThread);
        mUpdateCallback = updateCallback;
        mRepository = repository;
    }

    protected void updateModel() {
        // Получим значение
        final StorageModel storageModel = mRepository.get();

        // Мы получили наше значение, уведомим пользовательский интерфейс (UI) в главном потоке
        postValue(storageModel);
    }

    private void postValue(@NonNull final StorageModel storageModel) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                //конвертируем из модели хранилища в модель для домена
                DomainModel domainModel = StorageDomainModelConverter.convertToDomainModel(storageModel);
                mUpdateCallback.onValueUpdate(domainModel);
            }
        });
    }
}
