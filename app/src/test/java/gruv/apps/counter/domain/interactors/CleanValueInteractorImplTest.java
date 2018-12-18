package gruv.apps.counter.domain.interactors;

import gruv.apps.counter.domain.Constants;
import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.impl.CleanValueInteractorImpl;
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

import static org.mockito.Mockito.when;

/**
 * Тест интерактора CleanValueInteractorImpl
 *
 * @author Goncharenko Ruslan
 */
public class CleanValueInteractorImplTest {

    private static final long VIBRATION_CLEAR_DURATION = 100; // Milliseconds

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

        StorageModel storageModel = new StorageModel(0);

        when(mRepository.get()).thenReturn(storageModel);

        DomainModel domainModel = new DomainModel(String.valueOf(0));

        CleanValueInteractorImpl interactor = new CleanValueInteractorImpl(mExecutor, mMainThread, mMockedValueUpdaterCallback,
                mMockedSoundVibrateCallback, mRepository);
        interactor.run();

        Mockito.verify(mRepository).set(storageModel);
        Mockito.verify(mRepository).get();

        // Проверка, не осталось ли непровернного взаимодействия с моком
        Mockito.verifyNoMoreInteractions(mRepository);

        // Проверка вызова колбэков
        Mockito.verify(mMockedValueUpdaterCallback).onValueUpdate(domainModel);
        Mockito.verify(mMockedSoundVibrateCallback).vibrate(VIBRATION_CLEAR_DURATION);
        Mockito.verify(mMockedSoundVibrateCallback).playSound(Constants.Sound.CLEAR_SOUND);
    }
}
