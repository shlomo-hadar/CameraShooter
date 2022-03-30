package com.example.camerashooter;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.widget.Toast;

public class Sound {

    private SoundPool soundPool;
    private Context context;
    private boolean loaded;
    private int sound;
    private int soundID;

    public Sound(Context context, int sound){
        this.loaded = false;
        this.context = context;
        this.sound = sound;
        this.soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        soundID = soundPool.load(this.context, sound,1);
    }

    public void playSound(){
        if (loaded)
            soundPool.play(soundID,1,1,1,0,1);
        else
            Toast.makeText(context, "Couldn't load sound file", Toast.LENGTH_SHORT).show();
    }
}
