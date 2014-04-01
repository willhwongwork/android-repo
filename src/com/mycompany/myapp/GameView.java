package com.mycompany.myapp;

import android.view.View;
import android.content.*;
import android.util.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import java.util.*;
public class GameView extends View
{
	private FlappyBall ball;
	private List<MovingTube> tubes = new ArrayList<MovingTube>();
	private MovingTube ts;
	private long frameTime;
	private long lastTime;
	private long deltaTime;
	private Random ran = new Random();
	
	private int width, height;
	
	private Canvas mCanvas;
	
	private int index = 0;
	
	
	public GameView(Context context, AttributeSet attrs){
		super(context, attrs);
		//ball = new FlappyBall(getWidth() /4, 100, 20);
		ball = new FlappyBall();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		width = w;
		height = h;
		ball.resetBall(w/4, h/ 3, w/22, 0xfff56123);
		initTubes();
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	

	@Override
	protected void onDraw(Canvas canvas)
	{
		mCanvas = canvas;
		frameTime = SystemClock.elapsedRealtime();
		deltaTime = (frameTime - lastTime) /1000;
		canvas.drawRGB(200, 200, 200);
		//ball.resetBall(getWidth()/ 4, 100, getWidth() / 20,0xfffffca1);
	
		update();
		
		lastTime = frameTime;
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		ball.up();
		return true;
	}
	
	public void update (){
		ball.drawBall(mCanvas);
		ball.fall(deltaTime);
		
		drawTubes();
		for(int i = 0; i < index; i ++){
			collision(i);
			//tubes.get(i).collisionWidthBall(ball);
		}
		
		if(ball.getY() + ball.R > mCanvas.getHeight() * 0.75) {
			ball.resetBall(width / 4, height / 2,width /22, 0xfffffcff);
		}
		if(tubes.get(index).getX()<  width / 3) {
		    initTubes();
			index++;
			
		}
	}
	
	private void initTubes(){
		MovingTube t;
		t = new MovingTube(width, 0,width / 6,ran.nextInt(10) * (height / 20), 1000,height / 6);
		tubes.add(t);
	}
	
	private void drawTubes(){
		for( MovingTube tube : tubes) {
			tube.drawTube(mCanvas);
			tube.move();
		}
	}
	
	private void collision(int i){
			if(!(ball.getX() + ball.R *2 < tubes.get(i).getX() 
				||ball.getX() > tubes.get(i).getX()+ width /6 
				||ball.getY() > tubes.get(i).getHeight() 
				)||!(ball.getY() + ball.R * 2 < tubes.get(i).getHeight() + height / 6
					|| ball.getX() +ball.R*2 < tubes.get(i).getX()
					||ball.getX() > tubes.get(i).getX() + width / 6
					)){
						ball.resetBall(width / 4, height / 2, width / 22, 0xffffffbc);
						
				}
			}
		
	
	
	
}
