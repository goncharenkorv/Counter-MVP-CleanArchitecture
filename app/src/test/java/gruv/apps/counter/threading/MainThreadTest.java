package gruv.apps.counter.threading;

import gruv.apps.counter.domain.executor.MainThread;

/**
 * @author Goncharenko Ruslan
 */
public class MainThreadTest implements MainThread {
    @Override
    public void post(Runnable runnable) {
        // тесты могут выполняться в этом потоке, нет необходимости вызывать другие потоки
        runnable.run();
    }
}
