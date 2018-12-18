package gruv.apps.counter.domain.executor;

import android.support.annotation.NonNull;

/**
 * Этот интерфейс определяет класс, который позволит интеракторам выполнять определенные операции в основном потоке (UI).
 * Например, если интерактор должен показать объект в пользовательском интерфейсе, это можно использовать, чтобы убедиться,
 * что метод show вызывается в потоке пользовательского интерфейса (UI).
 *
 * @author Goncharenko Ruslan
 */
public interface MainThread {

    /**
     * Выполняет runnable-операцию в главном потоке.
     *
     * @param runnable Runnable для запуска.
     */
    void post(@NonNull final Runnable runnable);
}
