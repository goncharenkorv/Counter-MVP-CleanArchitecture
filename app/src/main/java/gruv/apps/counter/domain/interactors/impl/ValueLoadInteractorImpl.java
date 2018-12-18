package gruv.apps.counter.domain.interactors.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.ValueUpdaterInteractor;
import gruv.apps.counter.domain.interactors.base.Interactor;
import gruv.apps.counter.domain.repository.Repository;

/**
 * Интерактор восстановления значения счетчика (чтения сохраненного значения)
 *
 * @author Goncharenko Ruslan
 */
public class ValueLoadInteractorImpl extends UpdaterInteractor implements Interactor {

    //private Callback mCallback;
    private Repository mRepository;
    Context mContext;

    public ValueLoadInteractorImpl(@NonNull Executor threadExecutor,
                                   @NonNull MainThread mainThread,
                                   @NonNull ValueUpdaterInteractor.Callback callback,
                                   @NonNull Repository repository,
                                   @NonNull Context context) {
        super(threadExecutor, mainThread, callback, repository);
        //mCallback = callback;
        mRepository = repository;
        mContext = context;
    }

    @Override
    public void run() {
        mRepository.load(mContext);
        updateModel();
    }
}
