package com.example.helloworld;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private AudioManager audioManager;
    private NoteManager noteManager = new NoteManager();
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        createVolumeSlider();
        createClickEvents();
    }
	
	/** Define proper actions for the volume slider. **/
	private void createVolumeSlider() {
		 	audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		    int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		    int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		    SeekBar volControl = (SeekBar)findViewById(R.id.volBar);
		    volControl.setMax(maxVolume);
		    volControl.setProgress(curVolume);
		    volControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
		        @Override
		        public void onStopTrackingTouch(SeekBar arg0) {
		        }

		        @Override
		        public void onStartTrackingTouch(SeekBar arg0) {
		        }

		        @Override
		        public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, 0);
		        }
		    });
		   
		    
	}
	private void createClickEvents() {
		
		//Fix this up to go by ID.
		noteManager.defineKeyNote(this, R.id.piano_key_c, R.raw.pianoc);
		noteManager.defineKeyNote(this, R.id.piano_key_d, R.raw.pianod);
		noteManager.defineKeyNote(this, R.id.piano_key_e, R.raw.pianoe);
		noteManager.defineKeyNote(this, R.id.piano_key_f, R.raw.pianof);
		noteManager.defineKeyNote(this, R.id.piano_key_g, R.raw.pianog);
		noteManager.defineKeyNote(this, R.id.piano_key_a, R.raw.pianoa);
		noteManager.defineKeyNote(this, R.id.piano_key_b, R.raw.pianob);
		noteManager.defineKeyNote(this, R.id.piano_key_cs, R.raw.pianocs);
		noteManager.defineKeyNote(this, R.id.piano_key_eb, R.raw.pianoeb);
		noteManager.defineKeyNote(this, R.id.piano_key_fs, R.raw.pianofs);
		noteManager.defineKeyNote(this, R.id.piano_key_ab, R.raw.pianoab);
		noteManager.defineKeyNote(this, R.id.piano_key_bb, R.raw.pianobb);
		
	}
	
	/**Override KeyEvents to adjust volume slider**/
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		SeekBar volControl = (SeekBar)findViewById(R.id.volBar);
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) 
		{ 
			int index = volControl.getProgress(); 
			volControl.setProgress(index + 1); 
			return true; 
	    } 
		else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
	    {	
	    	int index = volControl.getProgress(); 
	    	volControl.setProgress(index - 1); 
	    	return true; 
	    }	
		return super.onKeyDown(keyCode, event); 
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	//EditText is a class of "view object"
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    public void gotoRecordings(View view) {
        //Start Intent in response to click
    	Intent intent = new Intent(this, RecordingsManager.class);
    	startActivity(intent);
    }
    
}
