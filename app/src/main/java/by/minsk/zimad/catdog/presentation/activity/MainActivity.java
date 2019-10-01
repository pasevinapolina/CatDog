package by.minsk.zimad.catdog.presentation.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import by.minsk.zimad.catdog.R;
import by.minsk.zimad.catdog.presentation.details.DetailsFragment;
import by.minsk.zimad.catdog.presentation.list.PetListFragment;

public class MainActivity extends AppCompatActivity implements MainView {

    private FragmentManager fragmentManager;
    private MainPresenter presenter = new MainPresenterImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void openPetList() {
        if (fragmentManager.findFragmentByTag(TAG_LIST) == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.containerList, PetListFragment.newInstance(), TAG_LIST)
                    .commit();
        }
    }

    @Override
    public void openPetDetailsScreen(int id) {
        if (fragmentManager.findFragmentByTag(TAG_DETAILS) == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.containerDetails, DetailsFragment.newInstance(id), TAG_DETAILS)
                    .addToBackStack(TAG_DETAILS)
                    .commit();
        }
    }

    @Override
    public void render(MainViewState state) {

    }

    public static final String TAG_DETAILS = "tag_details";
    public static final String TAG_LIST = "tag_list";
}
