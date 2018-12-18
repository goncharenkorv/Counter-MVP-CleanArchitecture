package gruv.apps.counter.domain.interactors;

import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.impl.IncrementButtonEnabledInteractorImpl;
import gruv.apps.counter.domain.repository.Repository;
import gruv.apps.counter.storage.model.StorageModel;
import gruv.apps.counter.threading.MainThreadTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

/**
 * Тест интерактора IncrementButtonEnabledInteractorImpl
 *
 * @author Goncharenko Ruslan
 */
public class IncrementButtonEnabledInteractorImplTest {

    private MainThread mMainThread;
    @Mock
    private Executor mExecutor;
    @Mock
    private Repository mRepository;
    @Mock
    private IncrementButtonEnabledInteractor.Callback mCallback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new MainThreadTest();
    }

    @Test
    public void runTrueTest() {
        int value = 5;

        StorageModel storageModel = new StorageModel(value);

        when(mRepository.get()).thenReturn(storageModel);

        IncrementButtonEnabledInteractorImpl interactor = new IncrementButtonEnabledInteractorImpl(mExecutor, mMainThread, mCallback, mRepository);
        interactor.run();

        Mockito.verify(mRepository).get();

        // Проверка, не осталось ли непровернного взаимодействия с моком
        Mockito.verifyNoMoreInteractions(mRepository);

        Mockito.verify(mCallback).onIncrementButtonEnabled(true);

    }

    @Test
    public void runFalseTest() {
        int value = 1000000;

        StorageModel storageModel = new StorageModel(value);

        when(mRepository.get()).thenReturn(storageModel);

        IncrementButtonEnabledInteractorImpl interactor = new IncrementButtonEnabledInteractorImpl(mExecutor, mMainThread, mCallback, mRepository);
        interactor.run();

        Mockito.verify(mRepository).get();

        // Проверка, не осталось ли непровернного взаимодействия с моком
        Mockito.verifyNoMoreInteractions(mRepository);

        Mockito.verify(mCallback).onIncrementButtonEnabled(false);

    }
}
