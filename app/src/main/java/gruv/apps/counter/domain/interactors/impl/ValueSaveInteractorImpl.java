package gruv.apps.counter.domain.interactors.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.base.AbstractInteractor;
import gruv.apps.counter.domain.interactors.base.Interactor;
import gruv.apps.counter.domain.repository.Repository;

/**
 * Интерактор сохранения значения счетчика (записи значения)
 *
 * @author Goncharenko Ruslan
 */
public class ValueSaveInteractorImpl extends AbstractInteractor implements Interactor {

    //private Callback mCallback;
    private Repository mRepository;
    Context mContext;

    public ValueSaveInteractorImpl(@NonNull Executor threadExecutor,
                                   @NonNull MainThread mainThread,
                                   //@NonNull Callback callback,
                                   @NonNull Repository repository,
                                   @NonNull Context context) {

        super(threadExecutor, mainThread);
        //mCallback = callback;
        mRepository = repository;
        mContext = context;
    }

    @Override
    public void run() {
        mRepository.save(mContext);
    }
}
