package com.example.hm5_agrawal_mishra;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.widget.Button;
import android.widget.Toast;
import com.example.hm5_agrawal_mishra.R;

public class MThread extends Thread {
	private SurfaceHolder holder;
	private Handler handler; // required for running code in the UI thread
	private boolean isRunning = false;
	Context context;
	Paint paint;
	int touchx, touchy; // x,y of touch event
	boolean touched; // true if touch happened
	private static final Object lock = new Object();

	public MThread(SurfaceHolder surfaceHolder, Context context) {
		holder = surfaceHolder;
		this.context = context;
		handler = new Handler();
		touched = false;
	}

	public void setRunning(boolean b) {
		isRunning = b; // no need to synchronize this since this is the only
						// line of code to writes this variable
	}

	// Set the touch event x,y location and flag indicating a touch has happened
	public void setXY(int x, int y) {
		synchronized (lock) {
			touchx = x;
			touchy = y;
			this.touched = true;
		}
	}

	@Override
	public void run() {
		while (isRunning) {
			// Lock the canvas before drawing
			Canvas canvas = holder.lockCanvas();
			if (canvas != null) {
				// Perform drawing operations on the canvas
				render(canvas);
				// After drawing, unlock the canvas and display it
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	private void pauseButton() {

		
	}

	// Loads graphics, etc. used in game
	private void loadData(Canvas canvas) {
		Assets.birthCount++;
		
		Bitmap bmp,bmp_lady, bmp_demon;
		int newWidth, newHeight;
		float scaleFactor;

		// Create a paint object for drawing vector graphics
		paint = new Paint();

		// Load score bar
		// ADD CODE HERE
		
		// Load food bar
		bmp = BitmapFactory.decodeResource(context.getResources(),R.drawable.foodbar);
		// Compute size of bitmap needed (suppose want height = 10% of screen
		// height)
		newHeight = (int) (canvas.getHeight() * 0.1f);
		// Scale it to a new size
		Assets.foodbar = Bitmap.createScaledBitmap(bmp, canvas.getWidth(),newHeight, false);
		// Delete the original
		bmp = null;

		// Load roach1
		bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.spider1);
		// Compute size of bitmap needed (suppose want width = 20% of screen
		// width)
		newWidth = (int) (canvas.getWidth() * 0.2f);
		// What was the scaling factor to get to this?
		scaleFactor = (float) newWidth / bmp.getWidth();
		// Compute the new height
		newHeight = (int) (bmp.getHeight() * scaleFactor);
		// Scale it to a new size
		Assets.spider1 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight,false);
		// Delete the original
		bmp = null;
		
		// Load roach2
		bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.spider2);
		// Compute size of bitmap needed (suppose want width = 20% of screen
		// width)
		newWidth = (int) (canvas.getWidth() * 0.2f);
		// What was the scaling factor to get to this?
		scaleFactor = (float) newWidth / bmp.getWidth();
		// Compute the new height
		newHeight = (int) (bmp.getHeight() * scaleFactor);
		// Scale it to a new size
		Assets.spider2 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight,false);
		// Delete the original
		bmp = null;
		
		
		
		// Load lady
				bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.lady1);
				// Compute size of bitmap needed (suppose want width = 20% of screen
				// width)
				newWidth = (int) (canvas.getWidth() * 0.2f);
				// What was the scaling factor to get to this?
				scaleFactor = (float) newWidth / bmp.getWidth();
				// Compute the new height
				newHeight = (int) (bmp.getHeight() * scaleFactor);
				// Scale it to a new size
				Assets.lady1 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight,false);
				// Delete the original
				bmp = null;
				
			// Load lady2
				bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.lady2);
				// Compute size of bitmap needed (suppose want width = 20% of screen
				// width)
				newWidth = (int) (canvas.getWidth() * 0.2f);
				// What was the scaling factor to get to this?
				scaleFactor = (float) newWidth / bmp.getWidth();
				// Compute the new height
				newHeight = (int) (bmp.getHeight() * scaleFactor);
				// Scale it to a new size
				Assets.lady2 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight,false);
				// Delete the original
				bmp = null;
				
			// Load ladydead
				bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.lady3);
				// Compute size of bitmap needed (suppose want width = 20% of screen
				// width)
				newWidth = (int) (canvas.getWidth() * 0.2f);
				// What was the scaling factor to get to this?
				scaleFactor = (float) newWidth / bmp.getWidth();
				// Compute the new height
				newHeight = (int) (bmp.getHeight() * scaleFactor);
				// Scale it to a new size
				Assets.lady3 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight,false);
				// Delete the original
				bmp = null;
				
			// Load demon
				bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.monsterspider);
				// Compute size of bitmap needed (suppose want width = 20% of screen
				// width)
				newWidth = (int) (canvas.getWidth() * 0.2f);
				// What was the scaling factor to get to this?
				scaleFactor = (float) newWidth / bmp.getWidth();
				// Compute the new height
				newHeight = (int) (bmp.getHeight() * scaleFactor);
				// Scale it to a new size
				Assets.demonspider = Bitmap.createScaledBitmap(bmp, newWidth, newHeight,false);
				// Delete the original
				bmp = null;
		

		// Load the other bitmaps similarly
		// ...

		// Load roach3 (dead bug)
		bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.deadspider);
		// Compute size of bitmap needed (suppose want width = 20% of screen
		// width)
		newWidth = (int) (canvas.getWidth() * 0.2f);
		// What was the scaling factor to get to this?
		scaleFactor = (float) newWidth / bmp.getWidth();
		// Compute the new height
		newHeight = (int) (bmp.getHeight() * scaleFactor);
		// Scale it to a new size
		Assets.spider3 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight,
				false);
		// Delete the original
		bmp = null;

		// Create a bug
		Assets.bug = new Bug();
	}

	// Load specific background screen
	private void loadBackground(Canvas canvas, int resId) {
		// Load background
		Bitmap bmp = BitmapFactory
				.decodeResource(context.getResources(), resId);
		// Scale it to fill entire canvas
		Assets.background = Bitmap.createScaledBitmap(bmp, canvas.getWidth(),
				canvas.getHeight(), false);
		// Delete the original
		bmp = null;
	}

	private void updateScore(Canvas canvas) {

		Paint paint = new Paint();
		// paint.setColor(Color.WHITE);
		// paint.setStyle(Style.FILL_AND_STROKE);
		// canvas.drawPaint(paint);

		paint.setColor(Color.YELLOW);
		paint.setTextSize(18);
		Assets.score = Assets.birthCount -1;
		canvas.drawText("Score:" + Assets.score, 10, 25, paint);

		if (Assets.score > Assets.high_score) {
			Assets.high_score = Assets.score;
		}

	}

	private void render(Canvas canvas) {
		int i, x, y;

		switch (Assets.state) {
		case GettingReady:
			// Load a special "getting ready screen"
			loadBackground(canvas, R.drawable.titleimage);// scaredPerson
			// Load data and other graphics needed by game
			loadData(canvas);
			// Draw the background screen
			canvas.drawBitmap(Assets.background, 0, 0, null);
			// Play a sound effect
			Assets.soundPool.play(Assets.sound_getready, 1, 1, 1, 0, 1);
			// Start a timer
			Assets.gameTimer = System.nanoTime() / 1000000000f;
			// Go to next state
			Assets.state = Assets.GameState.Starting;
			break;
		case Starting:
			// Draw the background screen
			canvas.drawBitmap(Assets.background, 0, 0, null);
			// Has 3 seconds elapsed?
			float currentTime = System.nanoTime() / 1000000000f;
			if (currentTime - Assets.gameTimer >= 3) {
				// Load game play background
				loadBackground(canvas, R.drawable.wood);
				// Goto next state
				Assets.state = Assets.GameState.Running;
			}
			break;
		case Running:
			// Draw the background screen
			canvas.drawBitmap(Assets.background, 0, 0, null);
			// Draw the score bar at top of screen
			// ADD CODE HERE
			// Draw the score on the score bar
			// canvas.drawText(....)

			// Paint paint = new Paint();
			// //paint.setColor(Color.WHITE);
			// //paint.setStyle(Style.FILL);
			// //canvas.drawPaint(paint);
			//
			// paint.setColor(Color.RED);
			// paint.setTextSize(15);
			// canvas.drawText("Score:"+Assets.score, 10, 25, paint);

			updateScore(canvas);

			// Draw the foodbar at bottom of screen
			canvas.drawBitmap(Assets.foodbar, 0, canvas.getHeight()
					- Assets.foodbar.getHeight(), null);
			// Draw one circle for each life at top right corner of screen
			// Let circle radius be 5% of width of screen
			int radius = (int) (canvas.getWidth() * 0.05f);
			int spacing = 8; // spacing in between circles
			x = canvas.getWidth() - radius - spacing; // coordinates for
														// rightmost circle to
														// draw
			y = radius + spacing;
			for (i = 0; i < Assets.livesLeft; i++) {
				paint.setColor(Color.GREEN);
				paint.setStyle(Style.FILL);
				canvas.drawCircle(x, y, radius, paint);
				paint.setColor(Color.BLACK);
				paint.setStyle(Style.STROKE);
				canvas.drawCircle(x, y, radius, paint);
				// Reposition to draw the next circle to the left
				x -= (radius * 2 + spacing);
			}

			// Process a touch
			if (touched) {
				// Set touch flag to false since we are processing this touch
				// now
				touched = false;
				// See if this touch killed a bug
				boolean bugKilled = Assets.bug.touched(canvas, touchx, touchy);
				if (bugKilled) {
					Assets.score++;
					Assets.birthCount++;
					Assets.soundPool.play(Assets.sound_squish, 1, 1, 1, 0, 1);
					updateScore(canvas);

				} else
					Assets.soundPool.play(Assets.sound_thump, 1, 1, 1, 0, 1);
			}

			// Draw dead bugs on screen
			Assets.bug.drawDead(canvas);
			// Move bugs on screen
			Assets.bug.move(canvas);
			// Bring a dead bug to life?
			Assets.bug.birth(canvas);
			
			
//			// Draw dead bugs on screen
//						Assets.bug2.drawDead(canvas);
//						// Move bugs on screen
//						Assets.bug2.move(canvas);
//						// Bring a dead bug to life?
//						Assets.bug2.birth(canvas);
		
			// ADD MORE CODE HERE TO PLAY GAME

			// Are no lives left?
			if (Assets.livesLeft == 0)
				// Goto next state
				Assets.state = Assets.GameState.GameEnding;
			break;
		case GameEnding:
			// Show a game over message
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT)
							.show();
				}
			});
			// Goto next state
			Assets.state = Assets.GameState.GameOver;
			break;
		case GameOver:
			// Fill the entire canvas' bitmap with 'black'
			//Assets.birthCount = 0;
			canvas.drawColor(Color.BLACK);
			break;
		}
	}
}
