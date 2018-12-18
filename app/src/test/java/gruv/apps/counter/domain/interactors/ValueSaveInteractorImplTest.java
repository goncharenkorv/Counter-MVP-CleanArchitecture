package gruv.apps.counter.domain.interactors;

import android.content.Context;

import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.impl.ValueSaveInteractorImpl;
import gruv.apps.counter.domain.repository.Repository;
import gruv.apps.counter.threading.MainThreadTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Тест интерактора ValueSaveInteractorImpl
 *
 * @author Goncharenko Ruslan
 */
public class ValueSaveInteractorImplTest {

    private MainThread mMainThread;
    @Mock
    private Executor mExecutor;
    @Mock
    private Repository mRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new MainThreadTest();
    }

    @Test
    public void runTest() {
        Context context = null;

        ValueSaveInteractorImpl interactor = new ValueSaveInteractorImpl(mExecutor, mMainThread, mRepository, context);
        interactor.run();

        Mockito.verify(mRepository).save(null);

        // Проверка, не осталось ли непровернного взаимодействия с моком
        Mockito.verifyNoMoreInteractions(mRepository);
    }
}
