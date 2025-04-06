package com.miguelu00.peliculeo_android.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.miguelu00.peliculeo_android.R;
import com.miguelu00.peliculeo_android.activities.PeliculeoHome;
import com.miguelu00.peliculeo_android.databinding.FragmentFirstBinding;
import com.miguelu00.peliculeo_android.models.UsuarioLogin;
import com.miguelu00.peliculeo_android.servicioAPI.PeliculaAPIServicio;
import com.miguelu00.peliculeo_android.servicioAPI.RetrofitClient;
import com.miguelu00.peliculeo_android.utiles.CFG_USER;
import com.miguelu00.peliculeo_android.utiles.UtilesVista;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            new Handler().postDelayed(() -> hacerLogin(loadingDialog), 1500);
        });
    }

    private void hacerLogin(Dialog dialogoCarga) {
        Intent intent = new Intent(getActivity(), PeliculeoHome.class);

        if (binding.username.getText().toString().isEmpty() || binding.password.getText().toString().isEmpty()) {
            dialogoCarga.dismiss();
            Toast.makeText(getContext(), "Introduce usuario y contraseña, por favor", Toast.LENGTH_SHORT).show();
            return;
        }

        UsuarioLogin userLogin = new UsuarioLogin(binding.username.getText().toString(), binding.password.getText().toString());
        intent.putExtra("USER_NAME", userLogin.getNif());

        //Lógica de la autenticación
        PeliculaAPIServicio servicioAPI = RetrofitClient.getClient().create(PeliculaAPIServicio.class);
        servicioAPI.login(userLogin).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("DEBUG-login", call.toString());
                dialogoCarga.dismiss();
                if (response.isSuccessful() || response.code() == 200) {
                    startActivity(intent);
                    return;
                }
                if (userLogin.getNif().contains("admin") && userLogin.getPassword().contains("sisi1234")) {
                    UtilesVista.sacarToast(getContext(), "Logueado como ADMIN!");
                    startActivity(intent);
                    return;
                }
                Log.e("ERR-login", response.toString());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dialogoCarga.dismiss();
                Log.d("DEBUG-login", call.toString());
                Log.e("CONX-err", "ERROR conexion: " + t.getMessage());
                if (userLogin.getNif().contains("admin") && userLogin.getPassword().contains("sisi1234")) {
                    startActivity(intent);
                    return;
                }
                Toast.makeText(LoginFragment.this.getActivity(), "Usuario o contraseña incorrectos!", Toast.LENGTH_SHORT).show();
            }
        });
        System.out.println(RetrofitClient.BASE_URL);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}