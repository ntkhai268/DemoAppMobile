package com.ntkhai.appmusic;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Runnable {
    TextView tvTime, tvDuration;
    SeekBar seekBarTime, seekBarVolume;
    ImageView imgvolmunedown, imgvolumneup;
    Button btnPlay;
    MediaPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        tvTime = findViewById(R.id.tvTime);
        tvDuration = findViewById(R.id.tvDuration);
        seekBarTime = findViewById(R.id.seekBarTime);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        imgvolmunedown = findViewById(R.id.imgvolumnedown);
        imgvolumneup = findViewById(R.id.imgvolumneup);
        btnPlay = findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(this);
        musicPlayer = MediaPlayer.create(this, R.raw.cochangtraivietlencay);
        tvDuration.setText(milisecondsToString(musicPlayer.getDuration()));
        musicPlayer.setLooping(true);
        musicPlayer.seekTo(0);
        musicPlayer.setVolume(0.5f, 0.5f);
        musicPlayer.start();
        btnPlay.setBackgroundResource(R.drawable.ic_pause);

        new Thread(this).start();

        seekBarVolume.setProgress(50);
        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = progress/100f;
                musicPlayer.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarTime.setMax(musicPlayer.getDuration());
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    musicPlayer.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        imgvolumneup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentvol = musicPlayer.getDuration();
                imgvolmunedown.setImageResource(R.drawable.ic_volume_down);
                musicPlayer.setVolume(currentvol, currentvol);
                seekBarVolume.setProgress(currentvol);
            }
        });

        imgvolmunedown.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                imgvolmunedown.setImageResource(R.drawable.ic_volume_off);
                musicPlayer.setVolume(0, 0);
                seekBarVolume.setProgress(0);
            }
        });


    }

    public String milisecondsToString(int time) {
        String elapsedTime = "";
        int minutes = time / 1000 / 60;
        int seconds = time / 1000 % 60;
        elapsedTime = minutes + ":";
        if (seconds < 10) {
            elapsedTime += "0";
        }
        elapsedTime += seconds;
        return elapsedTime;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnPlay)
        {
            if(musicPlayer.isPlaying())
            {
                musicPlayer.pause();
                btnPlay.setBackgroundResource(R.drawable.ic_play);
            }
            else
            {
                musicPlayer.start();
                btnPlay.setBackgroundResource(R.drawable.ic_pause);
            }
        }
    }

    @Override
    public void run(){
        while(musicPlayer != null){
            try{
                if(musicPlayer.isPlaying()) {
                    final int current = musicPlayer.getCurrentPosition();
                    final String elapsedTime = milisecondsToString(current);
                    
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvTime.setText(elapsedTime);
                            seekBarTime.setProgress(current);
                        }
                    });
                }
                Thread.sleep(1000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}