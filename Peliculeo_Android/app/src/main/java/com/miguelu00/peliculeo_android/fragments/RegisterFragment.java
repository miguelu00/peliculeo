package com.miguelu00.peliculeo_android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.miguelu00.peliculeo_android.R;
import com.miguelu00.peliculeo_android.databinding.FragmentSecondBinding;

public class RegisterFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnBack.setOnClickListener(v ->
                NavHostFragment.findNavController(RegisterFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );

        //Funcionalidad registro
        /*
        binding.btnRegister.setOnClickListener(v ->
                //INSERTAR LLAMADA A REGISTRO
        );
         */
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}