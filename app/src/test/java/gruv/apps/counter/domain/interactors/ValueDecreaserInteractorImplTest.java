package gruv.apps.counter.domain.interactors;

import gruv.apps.counter.domain.Constants;
import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.impl.ValueDecreaserInteractorImpl;
import gruv.apps.counter.domain.model.DomainModel;
import gruv.apps.counter.domain.repository.Repository;
import gruv.apps.counter.storage.model.StorageModel;
import gruv.apps.counter.threading.MainThreadTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import gruv.apps.counter.presentation.ui.activities.MainActivity;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Тест интерактора ValueDecreaserInteractorImpl
 *
 * @author Goncharenko Ruslan
 */
public class ValueDecreaserInteractorImplTest {

    private static final long VIBRATION_DECREASE_DURATION = 60; // Milliseconds

    private MainThread mMainThread;
    @Mock
    private Executor mExecutor;
    @Mock
    private Repository mRepository;
    @Mock
    private ValueUpdaterInteractor.Callback mMockedValueUpdaterCallback;
    @Mock
    private SoundVibrateInteractor.Callback mMockedSoundVibrateCallback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new MainThreadTest();
    }

    @Test
    public void runTest() {
        int value = 5;

        StorageModel storageModel = new StorageModel(value);

        when(mRepository.get()).thenReturn(storageModel);

        ValueDecreaserInteractorImpl interactor = new ValueDecreaserInteractorImpl(mExecutor, mMainThread, mMockedValueUpdaterCallback,
                mMockedSoundVibrateCallback, mRepository);
        interactor.run();

        Mockito.verify(mRepository).set(storageModel);
        Mockito.verify(mRepository, times(2)).get();

        // Проверка, не осталось ли непровернного взаимодействия с моком
        Mockito.verifyNoMoreInteractions(mRepository);

        DomainModel domainModelNew = new DomainModel(String.valueOf(value - 1));

        // Проверка вызова колбэков
        Mockito.verify(mMockedValueUpdaterCallback).onValueUpdate(domainModelNew);
        Mockito.verify(mMockedSoundVibrateCallback).vibrate(VIBRATION_DECREASE_DURATION);
        Mockito.verify(mMockedSoundVibrateCallback).playSound(Constants.Sound.DECREMENT_SOUND);
    }
}
