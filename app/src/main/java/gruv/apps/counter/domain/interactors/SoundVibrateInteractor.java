package gruv.apps.counter.domain.interactors;

import gruv.apps.counter.domain.Constants;
import gruv.apps.counter.domain.interactors.base.Interactor;

/**
 * Интерфейс для вибратора и звука
 *
 * @author Goncharenko Ruslan
 */
public interface SoundVibrateInteractor extends Interactor {

    interface Callback {

        // interactor callback методы
        void vibrate(long duration);

        void playSound(Constants.Sound sound);
    }

}
