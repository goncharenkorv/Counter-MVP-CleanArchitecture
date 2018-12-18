package gruv.apps.counter.domain.interactors.impl;

import android.support.annotation.NonNull;

import gruv.apps.counter.domain.Constants;
import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.SoundVibrateInteractor;
import gruv.apps.counter.domain.interactors.ValueUpdaterInteractor;
import gruv.apps.counter.domain.interactors.base.Interactor;
import gruv.apps.counter.domain.repository.Repository;
import gruv.apps.counter.storage.DataValueRepository;
import gruv.apps.counter.storage.model.StorageModel;

/**
 * Интерактор увеличения значения счетчика
 *
 * @author Goncharenko Ruslan
 */
public class ValueIncreaserInteractorImpl extends UpdaterInteractor implements Interactor {

    //private Callback mCallback;
    private SoundVibrateInteractor.Callback mSoundVibrateInteractorCallback;
    private Repository mRepository;

    public ValueIncreaserInteractorImpl(@NonNull Executor threadExecutor,
                                        @NonNull MainThread mainThread,
                                        @NonNull ValueUpdaterInteractor.Callback callback,
                                        @NonNull SoundVibrateInteractor.Callback soundVibrateInteractorCallback,
                                        @NonNull Repository repository) {
        super(threadExecutor, mainThread, callback, repository);
        //mCallback = callback;
        mRepository = repository;
        mSoundVibrateInteractorCallback = soundVibrateInteractorCallback;
    }

    @Override
    public void run() {
        //mRepository.increase();
        StorageModel storageModel = mRepository.get();
        int value = storageModel.getValue();
        if (value < DataValueRepository.MAX_VALUE) {
            value++;
            storageModel.setValue(value);
            mRepository.set(storageModel);

            mSoundVibrateInteractorCallback.vibrate(Constants.VIBRATION_ENCREASE_DURATION);
            mSoundVibrateInteractorCallback.playSound(Constants.Sound.INCREMENT_SOUND);
            updateModel();
        }
    }
}
