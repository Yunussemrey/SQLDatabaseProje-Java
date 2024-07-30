package com.yunusemre.ogrencikayit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yunusemre.ogrencikayit.databinding.OgrenciCardTasarimBinding;

import java.util.ArrayList;

public class OgrenciAdapter extends RecyclerView.Adapter<OgrenciAdapter.OgrenciHolder> {
    private ArrayList<Ogrenci> ogrenciArrayList;

    public OgrenciAdapter(ArrayList<Ogrenci> ogrenciArrayList) {
        this.ogrenciArrayList = ogrenciArrayList;
    }
    @NonNull
    @Override
    public OgrenciAdapter.OgrenciHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OgrenciCardTasarimBinding ogrenciCardTasarimBinding = OgrenciCardTasarimBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new OgrenciHolder(ogrenciCardTasarimBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OgrenciAdapter.OgrenciHolder holder, int position) {
                //ogrenciArrayList = new ArrayList<>();
        holder.ogrenciCardTasarimBinding.textViewAd.setText(ogrenciArrayList.get(position).getOgrenciAd());
        holder.ogrenciCardTasarimBinding.textViewSoyad.setText(ogrenciArrayList.get(position).getOgrenciSoyad());
    }

    @Override
    public int getItemCount() {
        return ogrenciArrayList.size();
    }

    public class OgrenciHolder extends RecyclerView.ViewHolder {
        private OgrenciCardTasarimBinding ogrenciCardTasarimBinding;
        public OgrenciHolder(OgrenciCardTasarimBinding binding) {
            super(binding.getRoot());
            this.ogrenciCardTasarimBinding = binding;
        }
    }
}
