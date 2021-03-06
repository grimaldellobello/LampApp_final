package com.flask.colorpicker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Xfermode;
import android.graphics.drawable.ColorDrawable;

import com.flask.colorpicker.builder.PaintBuilder;

public class CircleColorDrawable extends ColorDrawable {
	private float strokeWidth;
	private float strokeWidth1;
	private Paint strokePaint = PaintBuilder.newPaint().style(Paint.Style.STROKE).stroke(strokeWidth).color(0xffffffff).build();
	private Paint fillPaint = PaintBuilder.newPaint().style(Paint.Style.FILL).color(0).build();
	private Paint fillPaint1 = PaintBuilder.newPaint().style(Paint.Style.FILL).color(0).build();
	private Paint fillBackPaint = PaintBuilder.newPaint().shader(PaintBuilder.createAlphaPatternShader(16)).build();

	public CircleColorDrawable() {
		super();
	}

	public CircleColorDrawable(int color) {
		super(color);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(0);

		int width = canvas.getWidth();
		float radius = width / 2f;
		strokeWidth = radius / 12f;

		this.strokePaint.setStrokeWidth(strokeWidth);
		this.fillPaint.setColor(getColor());
		canvas.drawCircle(radius, radius, radius - strokeWidth * 1.5f, fillBackPaint);
		canvas.drawCircle(radius, radius, radius - strokeWidth * 1.5f, fillPaint);
		canvas.drawCircle(radius, radius, radius - strokeWidth, strokePaint);

		float radius1 = width/ 4f;
		strokeWidth1 = radius/ 24f;
        fillPaint1.setColor(Color.BLACK);
        canvas.drawCircle(radius, radius, radius - strokeWidth1 * 1.5f, fillPaint1);
        canvas.drawCircle(radius, radius, radius - strokeWidth1, strokePaint);
	}

	@Override
	public void setColor(int color) {
		super.setColor(color);
		invalidateSelf();
	}
}