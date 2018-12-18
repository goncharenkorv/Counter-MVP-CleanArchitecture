package gruv.apps.counter.presentation.presenters.base;

import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;

/**
 * Это базовый класс для всех презентеров, которые общаются с интеракторами. Этот базовый класс будет содержать
 * ссылку на объекты Executor и MainThread, необходимые для запуска интеракторов в фоновом потоке.
 *
 * @author Goncharenko Ruslan
 */
public abstract class AbstractPresenter {
    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}
