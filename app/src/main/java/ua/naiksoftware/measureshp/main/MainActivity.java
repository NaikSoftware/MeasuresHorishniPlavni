package ua.naiksoftware.measureshp.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ua.naiksoftware.measureshp.R;
import ua.naiksoftware.measureshp.common.BaseActivity;
import ua.naiksoftware.measureshp.databinding.ActivityMainBinding;

import static android.transition.TransitionSet.ORDERING_TOGETHER;

public class MainActivity extends BaseActivity {

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
        CreateDialogFragment dialog = new CreateDialogFragment();
        dialog.setSharedElementEnterTransition(new DetailsTransition());
        dialog.setSharedElementReturnTransition(new DetailsTransition());
//        dialog.show(getSupportFragmentManager(), "create_dialog");
        getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(view, "create_dialog_transition")
                .add(R.id.coordinator_layout, dialog)
                .commit();
    }

    public class DetailsTransition extends TransitionSet {
        public DetailsTransition() {
            setOrdering(ORDERING_TOGETHER);
            addTransition(new ChangeBounds()).
                    addTransition(new ChangeTransform()).
                    addTransition(new ChangeImageTransform());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
