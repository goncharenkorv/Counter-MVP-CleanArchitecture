package gruv.apps.counter.presentation.presenters.base;

/**
 * Базовый презентер (интерфейс)
 *
 * @author Goncharenko Ruslan
 */
public interface BasePresenter {
    /**
     * Метод, управляющий жизненным циклом представления.
     * Он должен вызываться в методе onResume() представления (Activity или Fragment).
     */
    void resume();

    /**
     * Метод, управляющий жизненным циклом представления.
     * Он должен вызываться в методе onPause() представления (Activity или Fragment).
     */
    void pause();

    /**
     * Метод, управляющий жизненным циклом представления.
     * Он должен вызываться в методе onStop() представления (Activity или Fragment).
     */
    void stop();

    /**
     * Метод, управляющий жизненным циклом представления.
     * Он должен вызываться в методе onDestroy() представления (Activity или Fragment).
     */
    void destroy();

    /**
     * Метод, который должен сигнализировать соответствующему виду,
     * чтобы показать соответствующую ошибку с предоставленным сообщением.
     */
    void onError(String message);
}
