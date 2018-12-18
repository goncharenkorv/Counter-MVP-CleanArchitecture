package gruv.apps.counter.domain.executor;

import gruv.apps.counter.domain.interactors.base.AbstractInteractor;

/**
 * Этот исполнитель (Executor) отвечает за запуск интеракторов в фоновых потоках.
 *
 * @author Goncharenko Ruslan
 */
public interface Executor {

    /**
     * Этот метод должен вызывать метод интерактора и таким образом запускать интерактор.
     * Он должен вызываться в фоновом потоке, поскольку интеракторы могут выполнять длительные операции.
     *
     * @param interactor Интерактор для запуска
     */
    void execute(final AbstractInteractor interactor);
}
