package ua.naiksoftware.measureshp.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import ua.naiksoftware.measureshp.provider.Provider;
import ua.naiksoftware.measureshp.widget.MorphDialogActivity;

/**
 * Created by savchenko_n on 14.08.17.
 */

public class CreateDialogActivity extends MorphDialogActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Provider[] providers = Provider.getProviders();
        String[] items = new String[providers.length];
        for (int i = 0; i < items.length; i++) {
            items[i] = getString(providers[i].getTitle());
        }
    }
}
