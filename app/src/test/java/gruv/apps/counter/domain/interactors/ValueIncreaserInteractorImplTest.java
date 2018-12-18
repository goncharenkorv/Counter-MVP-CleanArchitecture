package gruv.apps.counter.domain.interactors;

import gruv.apps.counter.domain.Constants;
import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.impl.ValueIncreaserInteractorImpl;
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
 * Тест интерактора ValueIncreaserInteractorImpl
 *
 * @author Goncharenko Ruslan
 */
public class ValueIncreaserInteractorImplTest {

    private static final long VIBRATION_ENCREASE_DURATION = 40; // Milliseconds

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

        ValueIncreaserInteractorImpl interactor = new ValueIncreaserInteractorImpl(mExecutor, mMainThread, mMockedValueUpdaterCallback,
                mMockedSoundVibrateCallback, mRepository);
        interactor.run();

        Mockito.verify(mRepository).set(storageModel);
        Mockito.verify(mRepository, times(2)).get();

        // Проверка, не осталось ли непровернного взаимодействия с моком
        Mockito.verifyNoMoreInteractions(mRepository);

        DomainModel domainModelNew = new DomainModel(String.valueOf(value + 1));

        // Проверка вызова колбэков
        Mockito.verify(mMockedValueUpdaterCallback).onValueUpdate(domainModelNew);
        Mockito.verify(mMockedSoundVibrateCallback).vibrate(VIBRATION_ENCREASE_DURATION);
        Mockito.verify(mMockedSoundVibrateCallback).playSound(Constants.Sound.INCREMENT_SOUND);
    }
}
