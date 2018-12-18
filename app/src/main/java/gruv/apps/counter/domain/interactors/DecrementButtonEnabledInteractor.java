package gruv.apps.counter.domain.interactors;

import gruv.apps.counter.domain.interactors.base.Interactor;

/**
 * Интерфейс доступности кнопки уменьшения
 *
 * @author Goncharenko Ruslan
 */
public interface DecrementButtonEnabledInteractor extends Interactor {

    interface Callback {
        // interactor callback методы
        void onDecrementButtonEnabled(boolean enabled);
    }

}
