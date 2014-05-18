package com.tubes.travelprofessor;

import com.example.travelprofessor.R;
import com.example.travelprofessor.R.id;
import com.example.travelprofessor.R.layout;
import com.example.travelprofessor.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class Professor extends ActionBarActivity {

	private Pengguna pengguna;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_professor);
		
		Intent i = getIntent();
		pengguna = (Pengguna) i.getSerializableExtra("pengguna");

		//updateDataUser();
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	private void updateDataUser(){
		TextView level = (TextView)findViewById(R.id.textView5);
		TextView exp = (TextView)findViewById(R.id.textView6);
		TextView next = (TextView)findViewById(R.id.textView7);
		
		level.setText(pengguna.getLevel());
		exp.setText(pengguna.getExp() + "");
		next.setText(pengguna.getNextLevelExp() + "");
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.professor, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_professor,
					container, false);
			return rootView;
		}
	}

}
