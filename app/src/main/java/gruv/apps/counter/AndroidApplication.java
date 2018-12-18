package gruv.apps.counter;

import android.app.Application;

/**
 * @author Goncharenko Ruslan
 */
public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Инициализация Timber
        //Timber.plant(new DebugTree());
    }
}
