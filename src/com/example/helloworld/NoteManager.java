package com.example.helloworld;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.View;
import android.widget.LinearLayout;

public class NoteManager {

	public NoteManager() {
		//Nothing Needed, as of now.
	}

	public void defineKeyNote(final Activity A, int noteId,final int soundId) {
		LinearLayout cKey = (LinearLayout) A.findViewById(noteId);
		cKey.setSoundEffectsEnabled(false);
		cKey.setOnClickListener(new View.OnClickListener()
		{
		    @Override
		    public void onClick(View V)
		    {
		        playNote(A, V, soundId);
		    }
		});
	}
	
	/** Play the file defined by resid **/
    public void playNote(final Activity A, View view, int resid) {
        // Play a Note
    	MediaPlayer clip = MediaPlayer.create(A.getApplicationContext(), resid);
    	clip.start();	 
    	
    	OnCompletionListener listener = new OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
         };
    	clip.setOnCompletionListener(listener);
    }
	
}
