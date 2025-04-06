package com.miguelu00.peliculeo_android.utiles;

import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DecoRecyclerView extends RecyclerView.ItemDecoration {
    private final int hueco_dp;

    public DecoRecyclerView(int hueco_dp) {
        this.hueco_dp = hueco_dp;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = hueco_dp;
        outRect.right = hueco_dp;
        outRect.bottom = hueco_dp;

        // Margen de arriba solo para la primera columna
        if (parent.getChildAdapterPosition(view) < 2) {
            outRect.top = hueco_dp;
        }
    }
}
