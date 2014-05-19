package com.tubes.travelprofessor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import com.example.travelprofessor.R;
import com.example.travelprofessor.R.id;
import com.example.travelprofessor.R.layout;
import com.example.travelprofessor.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class Professor extends ActionBarActivity implements LocationListener{
	private Quest Q;
	private LocationManager locationManager;
	private String provider;
	private Location location;
	private TextView txtLoc;
	private Pengguna pengguna;
	private static final int REQUEST_CODE = 1;
	private Bitmap bitmap;
	private ImageView imageView;
	
	public void onClickVerify(View v)
    {
		String tmploc;
		
	    tmploc =  Q.LocationMember(new Lokasi("", (float)location.getLatitude(), (float)location.getLongitude(), 0, 0));
	    
	    if (!tmploc.equals("")){
	    	if (pengguna.isListLocationMember(tmploc)){
	    		Toast.makeText(this, "Location " + tmploc + " have been visited before", Toast.LENGTH_SHORT).show();
		    }else{
		    	Intent intent = new Intent();
			    intent.setType("image/*");
			    intent.setAction(Intent.ACTION_GET_CONTENT);
			    intent.addCategory(Intent.CATEGORY_OPENABLE);
			    startActivityForResult(intent, REQUEST_CODE);
			    
			    pengguna.addVisitedLocation(new Lokasi(tmploc));
			    pengguna.upExp10();
			    updateDataUser();
			    
			    UserData.writeUser(v.getContext(), pengguna);
			    Toast.makeText(this, "Congrats! You have added 10 exp points", Toast.LENGTH_SHORT).show();
	    	}
		    
	    }else{
	    	Toast.makeText(this, "Location not in Area of " + Q.getArea(), Toast.LENGTH_SHORT).show();
	    }
    }
	
	@Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    InputStream stream = null; // dapetin hasil kamera/galery
	    if (requestCode == REQUEST_CODE && resultCode == ActionBarActivity.RESULT_OK)
	      try {
	        if (bitmap != null) {
	          bitmap.recycle();
	        }
	        stream = getContentResolver().openInputStream(data.getData());
	        bitmap = BitmapFactory.decodeStream(stream);

	        imageView.setImageBitmap(bitmap);
	      } catch (FileNotFoundException e) {
	        e.printStackTrace();
	      } finally {
	        if (stream != null)
	          try {
	            stream.close();
	          } catch (IOException e) {
	            e.printStackTrace();
	          }
	      }
	  }
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_professor);
		
		Intent i = getIntent();
		pengguna = (Pengguna) i.getSerializableExtra("pengguna");
		
		try{
			UserData.bacaFile(getApplicationContext(), pengguna, false);
		}catch(LoginFailedException x){
			finish(); //gagal baca data
		}
		updateDataUser();
		imageView = (ImageView) findViewById(R.id.imageView1);
		txtLoc = (TextView) findViewById(R.id.textView8);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);
	    
	    //provider = "gps"; // untuk keperluan pengujian di emulator
	    
	    location = locationManager.getLastKnownLocation(provider);
	    if (location != null) {
	        System.out.println("Provider " + provider + " has been selected.");
	        locationManager.requestLocationUpdates(provider, 1000, 1, this);
	        onLocationChanged(location);
	    } else {
	        txtLoc.setText("Location not available");
	    }
		

		Q = new Quest("Jakarta");
        Q.setArea(Q.getArea().toUpperCase());
        Q.editDescription("Ibu Kota Negara Indonesia");
        
        assert (Q.getDescription() != "Tidak ada deskripsi");
        
        Q.addLocation(new Lokasi("Monas", 20, 30, 30, 40));
        Q.addLocation(new Lokasi("Ancol", 30, 40, 40, 50));
        Q.addLocation(new Lokasi("Musium", 60, 90, 70, 100));
        Q.addLocation(new Lokasi("ITB Coks", -7, 107, -6, 108));
        
        
        
        
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	private void updateDataUser(){
		TextView level = (TextView)findViewById(R.id.textView5);
		TextView exp = (TextView)findViewById(R.id.textView6);
		TextView next = (TextView)findViewById(R.id.textView7);
		
		level.setText(pengguna.getLevel() + "");
		exp.setText(pengguna.getExp() + "");
		next.setText(pengguna.getNextLevelExp() + "");
		
	}


	  @Override
	  public void onLocationChanged(Location location) {
	    int lat = (int) (location.getLatitude());
	    int lng = (int) (location.getLongitude());
	    this.location = location;
	    txtLoc.setText("Lat: " + String.valueOf(lat) + " Lng: " + String.valueOf(lng));
	  }
	  
	  @Override
	  public void onProviderEnabled(String provider) {
	    Toast.makeText(this, "Enabled new provider " + provider,
	        Toast.LENGTH_SHORT).show();
	
	  }
	
	  @Override
	  public void onProviderDisabled(String provider) {
	    Toast.makeText(this, "Disabled provider " + provider,
	        Toast.LENGTH_SHORT).show();
	  }
	  
	  @Override
	  public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub
	
	  }
	  
	
	  @Override
	  protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(provider, 1000, 1, this); // minta update setiap 1 detik atau perbedaan 1 lng
	    
	  }
	
	  /* Remove the locationlistener updates when Activity is paused */
	  @Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this);
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
