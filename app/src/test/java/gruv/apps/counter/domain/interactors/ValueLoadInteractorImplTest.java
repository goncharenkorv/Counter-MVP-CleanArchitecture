package gruv.apps.counter.domain.interactors;

import android.content.Context;

import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.impl.ValueLoadInteractorImpl;
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
 * Тест интерактора ValueLoadInteractorImpl
 *
 * @author Goncharenko Ruslan
 */
public class ValueLoadInteractorImplTest {

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
        int value = 1;

        StorageModel storageModel = new StorageModel(value);

        when(mRepository.get()).thenReturn(storageModel);

        Context context = null;

        ValueLoadInteractorImpl interactor = new ValueLoadInteractorImpl(mExecutor, mMainThread, mMockedValueUpdaterCallback, mRepository, context);
        interactor.run();

        Mockito.verify(mRepository).load(null);
        Mockito.verify(mRepository).get();//in updateModel()

        // Проверка, не осталось ли непровернного взаимодействия с моком
        Mockito.verifyNoMoreInteractions(mRepository);

        DomainModel domainModelNew = new DomainModel(String.valueOf(value));

        // Проверка вызова колбэков
        Mockito.verify(mMockedValueUpdaterCallback).onValueUpdate(domainModelNew);
    }
}
