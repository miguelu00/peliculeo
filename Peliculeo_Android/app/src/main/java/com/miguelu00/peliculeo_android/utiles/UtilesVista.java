package com.miguelu00.peliculeo_android.utiles;

import android.content.Context;
import android.widget.Toast;

/**
 * Facilitar la codificación para mostrar Vistas
 */
public class UtilesVista {

    public static void sacarToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }
}
