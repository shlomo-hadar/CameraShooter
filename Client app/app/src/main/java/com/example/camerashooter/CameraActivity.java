package com.example.camerashooter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.net.Socket;

public class CameraActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private Camera camera;
    private ShowCamera showCamera;
    private Button shootButton;
    private Socket server;
    private Sound sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        shootButton = findViewById(R.id.shootButton);
        sound = new Sound(this, R.raw.shot_sound); // sound

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
        } else{
            startGame();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startGame();
        } else {
            Toast.makeText(this,"Must allow camera usage", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    public void startGame(){
        frameLayout = findViewById(R.id.frame);
        camera = Camera.open();
        showCamera = new ShowCamera(this, camera, CameraActivity.this);
        frameLayout.addView(showCamera);

        shootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound.playSound();
            }
        });
    }
}