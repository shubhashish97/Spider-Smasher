package com.example.hm5_agrawal_mishra;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.example.hm5_agrawal_mishra.R;

public class TitleActivity extends Activity {

	@Override	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
		
		requestWindowFeature (Window.FEATURE_NO_TITLE);
		// Make full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	public class MyView extends View {
		Bitmap bmp;
		boolean switchToGameScreen;
		public MyView(Context context) {
			super(context); 
			bmp = BitmapFactory.decodeResource (getResources(), R.drawable.titleimage);
			switchToGameScreen = false;
		}

		@Override
		protected void onDraw (Canvas canvas) {  
			// Draw the title full screen
			Rect dstRect = new Rect();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(bmp, null, dstRect, null);
            // On click switch to main (game) activity
            if (switchToGameScreen) {
            	switchToGameScreen = false;
            	startActivity(new Intent(TitleActivity.this, MainActivity.class));
            }
            else
            	invalidate();
        }
     	@Override
    	public boolean onTouchEvent(MotionEvent event) 
    	{
     		// On click set flag to switch to main (game) activity
    		if (event.getAction() == MotionEvent.ACTION_UP)
    			switchToGameScreen = true;	
    		return true; // to indicate we have handled this event
    	}
     }
}
