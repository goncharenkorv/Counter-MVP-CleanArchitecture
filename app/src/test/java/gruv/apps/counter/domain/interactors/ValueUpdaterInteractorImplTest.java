package gruv.apps.counter.domain.interactors;

import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.impl.ValueUpdaterInteractorImpl;
import gruv.apps.counter.domain.model.DomainModel;
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
 * Тест интерактора ValueUpdaterInteractorImpl
 *
 * @author Goncharenko Ruslan
 */
public class ValueUpdaterInteractorImplTest {

    private static final int TEST_VALUE = 1;

    private MainThread mMainThread;
    @Mock
    private Executor mExecutor;
    @Mock
    private Repository mRepository;
    @Mock
    private ValueUpdaterInteractor.Callback mMockedValueUpdaterCallback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new MainThreadTest();
    }

    @Test
    public void runTest() {

        StorageModel storageModel = new StorageModel(TEST_VALUE);

        when(mRepository.get()).thenReturn(storageModel);

        DomainModel domainModel = new DomainModel(String.valueOf(TEST_VALUE));

        ValueUpdaterInteractorImpl interactor = new ValueUpdaterInteractorImpl(mExecutor, mMainThread, mMockedValueUpdaterCallback, mRepository);
        interactor.run();

        Mockito.verify(mRepository).get();

        // Проверка, не осталось ли непровернного взаимодействия с моком
        Mockito.verifyNoMoreInteractions(mRepository);

        // Проверка вызова колбэка
        Mockito.verify(mMockedValueUpdaterCallback).onValueUpdate(domainModel);
    }
}
