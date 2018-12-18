package gruv.apps.counter.domain.interactors.impl;

import android.support.annotation.NonNull;

import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.DecrementButtonEnabledInteractor;
import gruv.apps.counter.domain.interactors.base.AbstractInteractor;
import gruv.apps.counter.domain.repository.Repository;
import gruv.apps.counter.storage.DataValueRepository;
import gruv.apps.counter.storage.model.StorageModel;

/**
 * Интерактор доступности кнопки уменьшения
 *
 * @author Goncharenko Ruslan
 */
public class DecrementButtonEnabledInteractorImpl extends AbstractInteractor implements DecrementButtonEnabledInteractor {

    private Callback mCallback;
    private Repository mRepository;

    public DecrementButtonEnabledInteractorImpl(@NonNull Executor threadExecutor,
                                                @NonNull MainThread mainThread,
                                                @NonNull Callback callback,
                                                @NonNull Repository repository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mRepository = repository;
    }

    @Override
    public void run() {
        // Получим значение
        //final boolean result = mRepository.getDecrementButtonEnabled();
        StorageModel storageModel = mRepository.get();
        int value = storageModel.getValue();
        final boolean result = (value > DataValueRepository.MIN_VALUE);

        // Мы получили наше значение, уведомим пользовательский интерфейс (UI) в главном потоке
        post(result);
    }

    private void post(final boolean value) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onDecrementButtonEnabled(value);
            }
        });
    }
}
