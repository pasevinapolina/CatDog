package by.minsk.zimad.catdog.presentation.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import by.minsk.zimad.catdog.R;
import by.minsk.zimad.catdog.presentation.common.PetViewState;

public class DetailsFragment extends Fragment implements DetailsView {

    private ImageView imageViewPet;
    private TextView textViewId;
    private TextView textViewTitle;

    private DetailsPresenter presenter = new DetailsPresenterImpl();

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        initViews(view);
    }

    private void initViews(View rootView) {
        imageViewPet = rootView.findViewById(R.id.imageViewPet);
        textViewId = rootView.findViewById(R.id.textViewId);
        textViewTitle = rootView.findViewById(R.id.textViewTitle);
    }

    @Override
    public void render(PetViewState state) {
        textViewId.setText(state.getId());
        textViewTitle.setText(state.getTitle());

        Picasso.with(getContext())
                .load(state.getImageUrl())
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(imageViewPet);
    }

    public static DetailsFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(ARG_PET_ID, id);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final String ARG_PET_ID = "pet_id";
}
