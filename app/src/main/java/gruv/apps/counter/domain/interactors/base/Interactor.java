package gruv.apps.counter.domain.interactors.base;

/**
 * Это основной интерфейс интерактора. Каждый интерактор обслуживает конкретный вариант использования(use case).
 *
 * @author Goncharenko Ruslan
 */
public interface Interactor {

    /**
     * Это основной метод запуска интерактора. Это обеспечит выполнение операции с интерактором в фоновом потоке.
     */
    void execute();
}
