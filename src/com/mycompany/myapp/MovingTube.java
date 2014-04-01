package com.mycompany.myapp;
import java.util.*;
import android.graphics.*;
	
public class MovingTube {
		private int x , y;
		private int width, height, height2;
		private int gap;
		private Paint paint;
		private float vX = 2;
		
		public MovingTube(int x , int y, int width,int height, int height2, int gap){
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.height2 = height2;
			this.gap = gap;
			
			paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setColor(0x6f3743ff);
			paint.setStyle(Paint.Style.FILL);
		}
		
		public void drawTube(Canvas c) {
			c.drawRect(x, y,x + width, y + height, paint);
			c.drawRect(x, y +height + gap , x + width, y+ height +height2 + gap, paint);
		}
		
		public void move () {
			x -= vX;
		}
		
		public boolean collisionWidthBall(FlappyBall ball) {
			if(!(ball.getX() + ball.R * 2 < x)) {
				ball.resetBall(45,67,45,0xffffffcc);
				return true;
			}
			return false;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY(){
			return y;
		}
		
		public int getHeight(){
			return height;
		}
		
		public int getWidth(){
			return width;
		}
		
		public int getGap(){
			return gap;
		}
		
		public void setColor(int color){
			paint.setColor(color);
		}
}
