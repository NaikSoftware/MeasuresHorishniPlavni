package ua.naiksoftware.measureshp;

import timber.log.Timber;

/**
 * Created by naik on 14.08.17.
 */

public class Application extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
