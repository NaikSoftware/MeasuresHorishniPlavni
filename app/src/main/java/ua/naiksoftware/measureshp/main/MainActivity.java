 package ua.naiksoftware.measureshp.main;

 import android.arch.lifecycle.ViewModelProviders;
 import android.databinding.DataBindingUtil;
 import android.os.Bundle;
 import android.support.design.widget.Snackbar;
 import android.view.Menu;
 import android.view.MenuItem;

 import ua.naiksoftware.measureshp.R;
 import ua.naiksoftware.measureshp.common.BaseActivity;
 import ua.naiksoftware.measureshp.databinding.ActivityMainBinding;

 public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);

        setSupportActionBar(binding.toolbar);
        binding.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
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
