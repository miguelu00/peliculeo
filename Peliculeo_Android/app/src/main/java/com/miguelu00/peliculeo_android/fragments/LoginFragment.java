package com.miguelu00.peliculeo_android.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.miguelu00.peliculeo_android.R;
import com.miguelu00.peliculeo_android.databinding.FragmentFirstBinding;
import com.miguelu00.peliculeo_android.utiles.CFG_USER;
import com.miguelu00.peliculeo_android.utiles.UtilesVista;

public class LoginFragment extends Fragment implements CFG_USER {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.regButton.setOnClickListener(v ->
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment)

        );

        binding.loginButton.setOnClickListener(v -> {
            // Se abre un Dialog nuevo, establecemos su layout, y lo mostramos
            Dialog loadingDialog = new Dialog(getContext());
            loadingDialog.setContentView(R.layout.loading_modal);
            loadingDialog.setCancelable(false);
            loadingDialog.show();

            // Se hace el login con 2s de cortesía
            // Navigate to the next activity or update UI
            new Handler().postDelayed(() -> hacerLogin(loadingDialog), 2000);
        });
    }

    private void hacerLogin(Dialog dialogoCarga) {
        UtilesVista.sacarToast(getContext(), "Fallo al loguear!");
        dialogoCarga.dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}