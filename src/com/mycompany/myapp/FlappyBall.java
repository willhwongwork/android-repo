package com.mycompany.myapp;
import android.graphics.*;

public class FlappyBall
{
	public int R;
	private float velY;
	private static final float ACEL = 1;
	private int x, y;
	private Paint paint;
    
	public FlappyBall(int x , int y, int R){
		velY = 0;
		this.x = x;
		this.y = y;
		this.R = R;
		
		initPaint();
	}
	
	public FlappyBall(){
		initPaint();
	}
	
	public void drawBall(Canvas canvas) {
		canvas.drawCircle(x, y, R, paint);
	}
	
	public void fall(long deltaTime) {
		velY *= 0.99;
		velY += ACEL / 2;
		y += velY ;
	}
	
	public void up() {
		velY = -R / 5;
		
	}
	
	public void resetBall(int x, int y , int R, int color){
		this.x = x;
		this.y = y;
		this.R = R;
		paint.setColor(color);
	}
	
	public int getY() {
		return y - R;
	}
	
	public int getX(){
		return x - R;
	}
	
	private void initPaint(){
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(0x8f481880);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
	}

}

