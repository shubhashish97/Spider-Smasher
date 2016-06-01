package com.example.hm5_agrawal_mishra;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
//import android.support.annotation.MainThread;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class MainView extends SurfaceView {

	private SurfaceHolder holder = null;
	Context context;
	private MThread t = null;


	// Constructor
	public MainView(Context context) {
		super(context);
		// Retrieve the SurfaceHolder instance associated with this SurfaceView.
		holder = getHolder();

		// Initialize variables
		this.context = context;
		Assets.state = Assets.GameState.GettingReady;
		Assets.livesLeft = 3;

		// Load the sound effects
		Assets.soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		Assets.sound_getready = Assets.soundPool.load(context, R.raw.getready,
				1);
		Assets.sound_squish = Assets.soundPool.load(context, R.raw.squeezsound,
				1);
		Assets.sound_thump = Assets.soundPool.load(context, R.raw.thump, 1);
	}

	public void pause() {

		t.setRunning(false);
		while (true) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
		t = null;
	}

	public void resume() {		
		t = new MThread(holder, context);
		t.setRunning(true);
		t.start();
		setFocusable(true); // make sure we get events
	}

	 @Override
	 public boolean onTouchEvent(MotionEvent event)
	 {
	 float x, y;
	 int action = event.getAction();
	 x = event.getX();
	 y = event.getY();
	 // if (action==MotionEvent.ACTION_MOVE) {
	 // }
	 // if (action==MotionEvent.ACTION_DOWN){
	 // }
	 if (action == MotionEvent.ACTION_UP) {
	 t.setXY ((int)x, (int)y);
	 // Log.i("ProjectLogging", "Touch at: " + x + "," + y);
	 }
	 return true; // to indicate we have handled this event
	 }
	 
	 //Key Event for BackPress
	  	public boolean onKeyDown(int keyCode, KeyEvent event) {
	  	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	  	    	Toast.makeText(context, "call from main view back", Toast.LENGTH_SHORT).show();
	  	        //moveTaskToBack(false);
	  	        return true;
	  	    }
	  	    return super.onKeyDown(keyCode, event);
	  	}
	  	
	  
		public void onBackPressed() {
			// Display a toast		
			Toast.makeText(context, "call from main view back 2", Toast.LENGTH_SHORT).show();
		}
}
