package com.miguelu00.peliculeoMobile.fragments;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.miguelu00.peliculeoMobile.R;
import com.miguelu00.peliculeoMobile.databinding.ActivityLoginBinding;
import com.miguelu00.peliculeoMobile.databinding.ActivityMainBinding;
import com.miguelu00.peliculeoMobile.utiles.CFG_USER;
import com.miguelu00.peliculeoMobile.utiles.TrianguloEsquina;
import com.miguelu00.peliculeoMobile.utiles.UtilesVista;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPeliculeo extends Fragment implements CFG_USER {

    ActivityLoginBinding binding;
    private Button loginBtn;
    private ImageButton pwdToggler;
    private boolean pswVisible = false;

    public static LoginPeliculeo newInstance() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Agregar triángulo a la esquina inferior derecha
        View triangleView = binding.triangleView;
        triangleView.setBackground(new ShapeDrawable(new TrianguloEsquina(Color.argb(251, 80, 100, 35))));

        ViewCompat.setOnApplyWindowInsetsListener(container.getRootView().findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        configurarInterfazBotones(container);
        return inflater.inflate(R.layout.activity_login, container, false);
    }

    public void conseguirFotoBBDD(ResultSet rs) {
        try {
            File image = new File(IMG_RUTA + "fotoPerfil.png");
            FileOutputStream fos = new FileOutputStream(image);
            byte[] buffer = new byte[1];
            InputStream is = rs.getBinaryStream("foto");
            while (is.read(buffer) > 0) {
                fos.write(buffer);
            }
            fos.close();
        } catch (IOException notFoundErr) {
            UtilesVista.sacarToast(this.getContext(), "ERROR al cargar la foto de perfil!");
        } catch (SQLException e) {
            UtilesVista.sacarToast(this.getContext(), "ERROR al conseguir la imágen de BBDD!");
        }

    }

    /**
     * Crea las variables y establece los eventos para tocar los elementos
     */
    public void configurarInterfazBotones(@Nullable ViewGroup container) {
        loginBtn = (Button) container.findViewById(R.id.loginButton);
        pwdToggler = (ImageButton) container.findViewById(R.id.showPasswordButton);


        pwdToggler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pswVisible) {
                    pwdToggler.setImageResource(R.drawable.pw_visible_on);
                } else {
                    pwdToggler.setImageResource(R.drawable.pw_visible_off);
                }
                pswVisible = !pswVisible;
            }
        });


    }

}