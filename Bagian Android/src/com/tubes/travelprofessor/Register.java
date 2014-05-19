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
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class Register extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void onClickDaftar(View v)
    {
		EditText mEdit1, mEdit2;
		
		mEdit1 = (EditText) findViewById(R.id.editText1);
		mEdit2 = (EditText) findViewById(R.id.editText2);
		
		Pengguna p = new Pengguna(mEdit1.getText().toString(), "");
		
		try{
			UserData.bacaFile(v.getContext(), p, true);
			Toast.makeText(this, "User " + mEdit1.getText().toString() + " sudah terdaftar!", Toast.LENGTH_LONG).show();
		}catch(LoginFailedException x){
			p.setInit(0, 0, mEdit2.getText().toString());
			UserData.writeUser(v.getContext(), p);
			
			Toast.makeText(this, "Berhasil mendaftarkan user " + mEdit1.getText().toString() +  " !", Toast.LENGTH_LONG).show();
			
			Intent mainIntent = new Intent(v.getContext(),MainActivity.class);
	        startActivity(mainIntent);
	        finish();
		}
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
			View rootView = inflater.inflate(R.layout.fragment_register,
					container, false);
			return rootView;
		}
	}

}
