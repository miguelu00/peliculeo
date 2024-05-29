package com.miguelu00.peliculeoMobile;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.miguelu00.peliculeoMobile.utiles.CFG_USER;
import com.miguelu00.peliculeoMobile.utiles.UtilesVista;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity implements CFG_USER {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void conseguirFotoBBDD(ResultSet rs) {
        try {
            File image = new File(IMG_RUTA);
            FileOutputStream fos = new FileOutputStream(image);
            byte[] buffer = new byte[1];
            InputStream is = rs.getBinaryStream("foto");
            while (is.read(buffer) > 0) {
                fos.write(buffer);
            }
            fos.close();
        } catch (IOException notFoundErr) {
            UtilesVista.sacarToast(this.getApplicationContext(), "ERROR al cargar la foto de perfil!");
        } catch (SQLException e) {
            UtilesVista.sacarToast(this.getApplicationContext(), "ERROR al conseguir la imágen de BBDD!");
        }

    }
}