package com.example.boosters.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.boosters.R;
import com.example.boosters.ui.CatalogoVideojuegos.CatalogoVideojuegos;
import com.example.boosters.ui.CatalogoVideojuegos.CatalogoVideojuegosViewModel;

public class HomeFragment extends Fragment {


    private CatalogoVideojuegosViewModel catalogoVideojuegosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        catalogoVideojuegosViewModel =
                new ViewModelProvider(this).get(CatalogoVideojuegosViewModel.class);
        View root = inflater.inflate(R.layout.catalogo_videojuegos_fragment, container, false);
        return root;
    }


}