package com.example.hm5_agrawal_mishra;

import android.graphics.Bitmap;
import android.media.SoundPool;

public class Assets {

	static Bitmap background;
	static Bitmap foodbar;
	static Bitmap spider1;
	static Bitmap spider2;
	static Bitmap spider3;
	static Bitmap lady1;
	static Bitmap lady2;
	static Bitmap lady3;
	static Bitmap demonspider;
	
	

	// States of the Game Screen
	enum GameState {
	  GettingReady,	// play "get ready" sound and start timer, goto next state 
	  Starting,		// when 3 seconds have elapsed, goto next state
	  Running, 		// play the game, when livesLeft == 0 goto next state
	  GameEnding,	// show game over message
	  GameOver,		// game is over, wait for any Touch and go back to title activity screen
	};
	static GameState state;		// current state of the game
	static float gameTimer;	// in seconds
	static int livesLeft;		// 0-3
	
	static SoundPool soundPool;
	static int sound_getready;
	static int sound_squish;
	static int sound_thump;
	
	static int high_score = 0;
	static int score = 0;
	static Bug bug;
	static Bug2 bug2;
	static boolean lady = false;
	
	static int birthCount=0;
}
