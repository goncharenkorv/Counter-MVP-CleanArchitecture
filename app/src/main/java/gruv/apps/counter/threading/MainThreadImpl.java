package gruv.apps.counter.threading;

import android.os.Handler;
import android.os.Looper;

import gruv.apps.counter.domain.executor.MainThread;

/**
 * Этот класс гарантирует, что предоставляемый нами runnable будет выполняться в основном потоке пользовательского интерфейса.
 *
 * @author Goncharenko Ruslan
 */
public class MainThreadImpl implements MainThread {

    private static MainThread sMainThread;

    private Handler mHandler;

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThreadImpl();
        }

        return sMainThread;
    }
}
