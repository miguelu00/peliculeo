package com.miguelu00.peliculeoMobile.fragments;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.miguelu00.peliculeoMobile.R;
import com.miguelu00.peliculeoMobile.utiles.TrianguloEsquina;

public class RegistroActivity extends Fragment {

    public static RegistroActivity newInstance() {
        RegistroActivity registroAct = new RegistroActivity();
        return registroAct;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Agregar triángulo a la esquina inferior derecha
        View triangleView = container.getRootView().findViewById(R.id.triangleView);
        triangleView.setBackground(new ShapeDrawable(new TrianguloEsquina(Color.argb(251, 80, 0, 252))));

        ViewCompat.setOnApplyWindowInsetsListener(container.getRootView().findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        return inflater.inflate(R.layout.activity_registro, container, false);
    }
}