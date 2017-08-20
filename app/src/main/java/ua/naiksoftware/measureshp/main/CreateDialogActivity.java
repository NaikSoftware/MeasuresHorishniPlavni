package ua.naiksoftware.measureshp.main;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import ua.naiksoftware.measureshp.R;
import ua.naiksoftware.measureshp.common.morph.MorphTransition;
import ua.naiksoftware.measureshp.databinding.ActivityDialogProvidersBinding;
import ua.naiksoftware.measureshp.provider.Provider;

/**
 * Created by savchenko_n on 14.08.17.
 */

public class CreateDialogActivity extends AppCompatActivity {

    public static final String DIALOG_TRANSITION_NAME = "dialog_transition";

    private ActivityDialogProvidersBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dialog_providers);
        Provider[] providers = Provider.getProviders();
        String[] items = new String[providers.length];
        for (int i = 0; i < items.length; i++) {
            items[i] = getString(providers[i].getTitle());
        }

        binding.container.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));

        Resources res = getResources();
        MorphTransition.makeTransition(this, binding.container, res.getColor(R.color.colorAccent),
                res.getColor(R.color.white), 100, 0, DIALOG_TRANSITION_NAME);
    }

    @Override
    protected void onDestroy() {
        finishAfterTransition();
        super.onDestroy();
    }
}
