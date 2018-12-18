package gruv.apps.counter.presentation.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import gruv.apps.counter.domain.Constants;
import gruv.apps.counter.presentation.presenters.base.BasePresenter;
import gruv.apps.counter.presentation.ui.BaseView;

/**
 * Главный презентер (интерфейс)
 *
 * @author Goncharenko Ruslan
 */
public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {
        void setIncrementButtonEnabled(boolean enabled);

        void setDecrementButtonEnabled(boolean enabled);

        void vibrate(long duration);

        void playSound(@NonNull Constants.Sound sound);
    }

    void increase();

    void decrease();

    void clean();

    void saveState(Context context);

    void loadState(Context context);
}
