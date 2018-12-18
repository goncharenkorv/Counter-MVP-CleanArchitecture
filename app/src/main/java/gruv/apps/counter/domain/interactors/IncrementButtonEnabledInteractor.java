package gruv.apps.counter.domain.interactors;

import gruv.apps.counter.domain.interactors.base.Interactor;

/**
 * Интерфейс доступности кнопки увеличения
 *
 * @author Goncharenko Ruslan
 */
public interface IncrementButtonEnabledInteractor extends Interactor {

    interface Callback {
        // interactor callback методы
        void onIncrementButtonEnabled(boolean enabled);
    }

}
