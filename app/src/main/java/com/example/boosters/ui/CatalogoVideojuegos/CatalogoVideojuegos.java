package com.example.boosters.ui.CatalogoVideojuegos;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.boosters.MainActivity;
import com.example.boosters.R;
import com.example.boosters.catalogo_boosters;

public class CatalogoVideojuegos extends Fragment {

    Activity context;

    private CatalogoVideojuegosViewModel mViewModel;

    public static CatalogoVideojuegos newInstance() {
        return new CatalogoVideojuegos();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        context = getActivity();
        return inflater.inflate(R.layout.catalogo_videojuegos_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CatalogoVideojuegosViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onStart() {
        super.onStart();
        ImageButton btn = (ImageButton) context.findViewById(R.id.imageButton7);
        ImageButton btn1 = (ImageButton) context.findViewById(R.id.imageButton8);
        ImageButton btn2 = (ImageButton) context.findViewById(R.id.imageButton9);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, catalogo_boosters.class);
                startActivity(intent);
            }

        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, catalogo_boosters.class);
                startActivity(intent);
            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, catalogo_boosters.class);
                startActivity(intent);
            }

        });
    }

}