package ua.naiksoftware.measureshp.main;

import android.app.ActivityOptions;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ua.naiksoftware.measureshp.R;
import ua.naiksoftware.measureshp.common.BaseActivity;
import ua.naiksoftware.measureshp.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private static final int RC_PROVIDER_ADD = 1;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);

        setSupportActionBar(binding.toolbar);
    }

    public void onAddNewProvider(View view) {
        Intent intent = new Intent(this, CreateDialogActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, binding.fab, CreateDialogActivity.DIALOG_TRANSITION_NAME);
        startActivityForResult(intent, RC_PROVIDER_ADD, options.toBundle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
