package by.minsk.zimad.catdog.presentation.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import by.minsk.zimad.catdog.R;
import by.minsk.zimad.catdog.presentation.common.PetViewState;

public class PetListFragment extends Fragment implements PetListView {

    private PetListPresenter presenter = new PetListPresenterImpl();

    private RecyclerView recyclerViewPets;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_pet_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewPets = view.findViewById(R.id.recyclerViewPets);
        progressBar = view.findViewById(R.id.progressBar);
        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void initViews() {
        recyclerViewPets.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewPets.setAdapter(new PetAdapter(onItemClickListener));
        recyclerViewPets.setHasFixedSize(false);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewPets.addItemDecoration(divider);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setPets(List<PetViewState> pets) {
        PetAdapter adapter = (PetAdapter) recyclerViewPets.getAdapter();
        if (adapter != null) adapter.update(pets);
    }

    @Override
    public void showError() {

    }

    @Override
    public void hideError() {

    }

    @Override
    public void render(PetListViewState state) {
        Log.d("PetList", "render: pets" + state.getPets().size());
        renderLoading(state.isLoading());
        renderError(state.isError());
        setPets(state.getPets());
    }

    private void renderLoading(boolean show) {
        if (show) {
            showLoading();
        } else {
            hideLoading();
        }
    }

    private void renderError(boolean show) {
        if (show) {
            showError();
        } else {
            hideError();
        }
    }

    @Override
    public void openPetDetailsScreen(int id) {
        // TODO
    }

    private final PetAdapter.OnItemClickListener onItemClickListener = new PetAdapter.OnItemClickListener() {
        @Override
        public void onItemClicked(int id) {
            presenter.onPetClicked(id);
        }
    };

    public static PetListFragment newInstance() {
        return new PetListFragment();
    }
}
