package com.miguelu00.peliculeo_mobile.utiles;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.shapes.Shape;

public class TrianguloEsquina extends Shape {
    private final Paint paint;

    public TrianguloEsquina(int color) {
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
    }

    /**
     * Método draw() propio de la clase heredada SHAPE.
     * <p>
     * En este caso, coloca el rectángulo en la parte inferior derecha de la pantalla
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(canvas.getWidth(), canvas.getHeight());
        path.lineTo(canvas.getWidth(), 0);
        path.close();

        canvas.drawPath(path, this.paint);
    }
}
