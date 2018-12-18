package gruv.apps.counter.domain.interactors.impl;

import android.support.annotation.NonNull;

import gruv.apps.counter.domain.Constants;
import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.SoundVibrateInteractor;
import gruv.apps.counter.domain.interactors.ValueUpdaterInteractor;
import gruv.apps.counter.domain.interactors.base.Interactor;
import gruv.apps.counter.domain.repository.Repository;
import gruv.apps.counter.storage.model.StorageModel;

/**
 * Интерактор сброса в ноль значения счетчика (без сохранения значения) и обновления результата на экране
 *
 * @author Goncharenko Ruslan
 */
public class CleanValueInteractorImpl extends UpdaterInteractor implements Interactor {

    //private Callback mCallback;
    private SoundVibrateInteractor.Callback mSoundVibrateInteractorCallback;
    private Repository mRepository;

    public CleanValueInteractorImpl(@NonNull Executor threadExecutor,
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
        //mRepository.clear();
        StorageModel storageModel = new StorageModel(0);
        mRepository.set(storageModel);
        mSoundVibrateInteractorCallback.vibrate(Constants.VIBRATION_CLEAR_DURATION);
        mSoundVibrateInteractorCallback.playSound(Constants.Sound.CLEAR_SOUND);
        updateModel();
    }
}
