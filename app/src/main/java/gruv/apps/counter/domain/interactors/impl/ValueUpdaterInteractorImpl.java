package gruv.apps.counter.domain.interactors.impl;

import android.support.annotation.NonNull;

import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.ValueUpdaterInteractor;
import gruv.apps.counter.domain.repository.Repository;

/**
 * Интерактор обновления значения счетчика.
 * Т.е. вывод обновленного значения в главном потоке
 *
 * @author Goncharenko Ruslan
 */
public class ValueUpdaterInteractorImpl extends UpdaterInteractor implements ValueUpdaterInteractor {

    private ValueUpdaterInteractor.Callback mCallback;
    private Repository mRepository;

    public ValueUpdaterInteractorImpl(@NonNull Executor threadExecutor,
                                      @NonNull MainThread mainThread,
                                      @NonNull Callback callback,
                                      @NonNull Repository repository) {
        super(threadExecutor, mainThread, callback, repository);
        mCallback = callback;
        mRepository = repository;
    }

    @Override
    public void run() {
        updateModel();
    }
}
