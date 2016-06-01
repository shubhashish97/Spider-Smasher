package com.example.hm5_agrawal_mishra;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.hm5_agrawal_mishra.R;

public class MainActivity extends Activity implements OnClickListener {

	Context context;
	ImageButton btnStartGame, btnHighScore;

	 MainView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnStartGame = (ImageButton) findViewById(R.id.imgBtnStart);
		btnHighScore = (ImageButton) findViewById(R.id.imgBtnHighScore);

		btnStartGame.setOnClickListener(this);
		btnHighScore.setOnClickListener(this);
		context = getApplicationContext();
		
		view= new MainView(this);
	//	setContentView(v);
	}

	@Override
	public void onResume() {
		super.onResume();
		view.resume();
	}

	@Override
	public void onPause() {
		super.onPause();
	view.pause();
	}

	@Override
	public void onClick(View v) {
		// Toast.makeText(context, "btnClicked11111111111111111",
		// Toast.LENGTH_SHORT).show();
		if (v == btnStartGame) {
			setContentView(view);
			// Disable the title
		//	requestWindowFeature(Window.FEATURE_NO_TITLE);
			// Make full screen
		//	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			// Start the view
		//	startActivity(new Intent(this, TitleActivity.class));
			//v = new MainView(context);
			//setContentView(v);

		} else if (v == btnHighScore) {
			// Toast.makeText(context, "btnClicked", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this, PrefsActivity.class));
			//Toast.makeText(context, "btnClicked 22",	Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onBackPressed() {
		// Display a toast		
		Toast.makeText(context, "call from main activity", Toast.LENGTH_SHORT).show();
		startActivity(new Intent(this, MainActivity.class));
	}

}
