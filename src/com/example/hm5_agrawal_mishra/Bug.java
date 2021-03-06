package com.example.hm5_agrawal_mishra;

import android.graphics.Canvas;
import android.util.Log;
import android.widget.Toast;

public class Bug {

	// States of a Bug
	enum BugState {
		Dead, ComingBackToLife, Alive, // in the game
		DrawDead, // draw dead body on screen
	};

	BugState state; // current state of bug
	int x, y; // location on screen (in screen coordinates)
	double speed; // speed of bug (in pixels per second)
	// All times are in seconds
	float timeToBirth; // # seconds till birth
	float startBirthTimer; // starting timestamp when decide to be born
	float deathTime; // time of death
	float animateTimer; // used to move and animate the bug

	// Bug starts not alive
	public Bug() {
		state = BugState.Dead;
	}

	// Bug birth processing
	public void birth(Canvas canvas) {
		
		// Bring a bug to life?
		if (state == BugState.Dead) {
			// Set it to coming alive
			state = BugState.ComingBackToLife;
			// Set a random number of seconds before it comes to life
			timeToBirth = (float) Math.random() * 5;
			// Note the current time
			startBirthTimer = System.nanoTime() / 1000000000f;
		}
		// Check if bug is alive yet
		else if (state == BugState.ComingBackToLife) {
			float curTime = System.nanoTime() / 1000000000f;
			// Has birth timer expired?
			if (curTime - startBirthTimer >= timeToBirth) {
				// If so, then bring bug to life
				state = BugState.Alive;
				// Set bug starting location at top of screen
				x = (int) (Math.random() * canvas.getWidth());
				
				if((Assets.birthCount) %3 == 0){
					
					// Keep entire bug on screen
					if (x < Assets.lady1.getWidth() / 2)
						x = Assets.lady1.getWidth() / 2;
					else if (x > canvas.getWidth() - Assets.lady1.getWidth() / 2)
						x = canvas.getWidth() - Assets.lady1.getWidth() / 2;
					y = 0;
				}
				
				else{
				
				// Keep entire bug on screen
				if (x < Assets.spider1.getWidth() / 2)
					x = Assets.spider1.getWidth() / 2;
				else if (x > canvas.getWidth() - Assets.spider1.getWidth() / 2)
					x = canvas.getWidth() - Assets.spider1.getWidth() / 2;
				y = 0;
				}
			//	y = 0;
				// Set speed of this bug
				speed = canvas.getHeight() / 4; // no faster than 1/4 a screen
												// per second
				// subtract a random amount off of this so some bugs are a
				// little slower
				// ADD CODE HERE
				// Record timestamp of this bug being born
				animateTimer = curTime;
			}
		}
	}

	// Bug movement processing
	public void move(Canvas canvas) {
		// Make sure this bug is alive
		if (state == BugState.Alive) {
			// Get elapsed time since last call here
			float curTime = System.nanoTime() / 1000000000f;
			float elapsedTime = curTime - animateTimer;
			animateTimer = curTime;
			// Compute the amount of pixels to move (vertically down the screen)
			y += (speed * elapsedTime);
			
			// Draw bug on screen
			if((Assets.birthCount)%4 ==0){
				
				Log.i("ProjectLogging","Getting birth number =" + Assets.birthCount);
				
				//canvas.drawBitmap(Assets.ladyspider, x, y, null);
				if(y%2 == 0)
				{	Assets.lady = true;
					canvas.drawBitmap(Assets.lady1, x, y, null);
						if (y >= canvas.getHeight()) {
								// Kill the bug
							state = BugState.Dead;
							// Subtract 1 life
							Assets.lady = false;
							Assets.livesLeft--;
														
						}
				}
			else{	Assets.lady = true;
					canvas.drawBitmap(Assets.lady2, x, y, null);
						if (y >= canvas.getHeight()) {
							// Kill the bug
							state = BugState.Dead;
							// Subtract 1 life
							Assets.lady = false;
							Assets.livesLeft--;
							
						}
				}	
				
			}
			else{
				
			//canvas.drawBitmap(Assets.spider1, x, y, null);
				if(y%2 == 0)
					{
						
						canvas.drawBitmap(Assets.spider1, x, y, null);
							if (y >= canvas.getHeight()) {
									// Kill the bug
								state = BugState.Dead;
								// Subtract 1 life
								Assets.livesLeft--;
								//Assets.lady = false;
							}
					}
				else{
						canvas.drawBitmap(Assets.spider2, x, y, null);
							if (y >= canvas.getHeight()) {
								// Kill the bug
								state = BugState.Dead;
								// Subtract 1 life
								Assets.livesLeft--;
							}
					}	
				
			}
			// Has it reached the bottom of the screen?
		//	if (y >= canvas.getHeight()) {
				// Kill the bug
				//state = BugState.Dead;
				// Subtract 1 life
				//Assets.livesLeft--;
			//}
		}
	}

	// Process touch to see if kills bug - return true if bug killed
	public boolean touched(Canvas canvas, int touchx, int touchy) {
		boolean touched = false;
		// Make sure this bug is alive
		if (state == BugState.Alive) {
			// Compute distance between touch and center of bug
			float dis = (float) (Math.sqrt((touchx - x) * (touchx - x)
					+ (touchy - y) * (touchy - y)));
			// Is this close enough for a kill?
			if (dis <= Assets.spider1.getWidth() * 0.75f) {
				state = BugState.DrawDead; // need to draw dead body on screen
											// for a while
				touched = true;
				// Record time of death
				deathTime = System.nanoTime() / 1000000000f;

			}
		}
		return (touched);
	}

	// Draw dead bug body on screen, if needed
	public void drawDead(Canvas canvas) {
		
		if(Assets.lady == true && state == BugState.DrawDead )
		{
			canvas.drawBitmap(Assets.lady3, x, y, null);
			Assets.lady = false;
			Assets.livesLeft= 0;
		}
		else if (state == BugState.DrawDead) {
			canvas.drawBitmap(Assets.spider3, x, y, null);
			// Get time since death
			float curTime = System.nanoTime() / 1000000000f;
			float timeSinceDeath = curTime - deathTime;
			// Drawn dead body long enough (4 seconds) ?
			if (timeSinceDeath > 4)
				state = BugState.Dead;
		}
	}
}
