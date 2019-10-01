package by.minsk.zimad.catdog.presentation.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import by.minsk.zimad.catdog.R;
import by.minsk.zimad.catdog.presentation.common.PetViewState;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private static final String KEY_TITLE = "key_title";
    private static final String KEY_IMAGE = "key_image";

    private final OnItemClickListener onItemClickLitener;
    private final List<PetViewState> pets = Collections.emptyList();

    public PetAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickLitener = onItemClickListener;
    }

    void update(List<PetViewState> newPets) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new PetDiffCallback(pets, newPets));

        pets.clear();
        pets.addAll(newPets);

        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_pet, parent);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder petViewHolder, int position) {
        PetViewState pet = pets.get(position);
        petViewHolder.update(pet);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position, @NonNull List<Object> payloads) {
        if(payloads.isEmpty()) return;

        Bundle o = (Bundle) payloads.get(0);
        for (String key : o.keySet()) {
            if(key.equals(KEY_TITLE)){
                    holder.updateTitle(o.getString(key));
            }else if(key.equals(KEY_IMAGE)){
                holder.updateImage(o.getString(key));
            }
        }
    }

    @Override
    public int getItemCount() {
        return pets != null ? pets.size() : 0;
    }

    class PetViewHolder extends RecyclerView.ViewHolder {
        private PetViewState pet;

        public PetViewHolder(View view) {
            super(view);
            view.setOnClickListener(v -> onItemClickLitener.onItemClicked(pet.getId()));
        }

        void update(PetViewState pet) {
            this.pet = pet;
            onItemUpdated();
        }

        void updateTitle(String title) {
            final TextView textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewTitle.setText(title);
        }

        void updateImage(String imageUrl) {
            final ImageView imageViewPet = itemView.findViewById(R.id.imageViewPet);
            Picasso.with(itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .into(imageViewPet);
        }

        private void onItemUpdated() {
            final TextView textViewId = itemView.findViewById(R.id.textViewId);
            textViewId.setText(pet.getId());

            updateTitle(pet.getTitle());
            updateImage(pet.getImageUrl());

        }
    }

    private class PetDiffCallback extends DiffUtil.Callback {

        private final List<PetViewState> oldItems;
        private final List<PetViewState> newItems;

        PetDiffCallback(List<PetViewState> oldItems, List<PetViewState> newItems) {
            this.oldItems = oldItems;
            this.newItems = newItems;
        }

        @Override
        public int getOldListSize() {
            return oldItems != null ? oldItems.size() : 0;
        }

        @Override
        public int getNewListSize() {
            return newItems != null ? newItems.size() : 0;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).getId() == newItems.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).equals(newItems.get(newItemPosition));
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            PetViewState newPet = newItems.get(newItemPosition);
            PetViewState oldPet = oldItems.get(oldItemPosition);
            Bundle diffBundle = new Bundle();
            if (!newPet.getTitle().equals(oldPet.getTitle())) {
                diffBundle.putString(KEY_TITLE, newPet.getTitle());
            }
            if (!newPet.getImageUrl().equals(oldPet.getImageUrl())) {
                diffBundle.putString(KEY_IMAGE, newPet.getImageUrl());
            }
            if (diffBundle.size() == 0) return null;
            return diffBundle;
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(int id);
    }
}
