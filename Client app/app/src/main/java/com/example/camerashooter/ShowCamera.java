package com.example.camerashooter;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback {

    Camera camera;
    SurfaceHolder holder;
    Context mat;


    private Camera.Size previewSize;
    private int[] pixels;
    private String targetColor;

    public ShowCamera(Context context, Camera camera, Context mat) {
        super(context);
        this.camera = camera;
        this.mat = mat;
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (camera != null) {
            Camera.Parameters params = camera.getParameters();
            if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                params.set("orientation", "portrait");
                camera.setDisplayOrientation(90);
                params.setRotation(90);
            } else {
                params.set("orientation", "landscape");
                camera.setDisplayOrientation(0);
                params.setRotation(0);
            }

            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            Log.i("Pixels", params.getMaxZoom() + "");
            previewSize = params.getPreviewSize();
            pixels = new int[previewSize.width * previewSize.height];

            camera.setPreviewCallback(this);
            camera.setParameters(params);
            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
//        camera.stopPreview();
//        camera.release();
//        camera = null;
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        decodeYUV420SP(pixels, data, previewSize.width, previewSize.height);
        targetColor = String.format("#%06X", (0xFFFFFF & getPixel(previewSize.width / 2, previewSize.height / 2)));
    }

    void decodeYUV420SP(int[] rgb, byte[] yuv420sp, int width, int height) {

        final int frameSize = width * height;

        for (int j = 0, yp = 0; j < height; j++) {
            int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
            for (int i = 0; i < width; i++, yp++) {
                int y = (0xff & ((int) yuv420sp[yp])) - 16;
                if (y < 0)
                    y = 0;
                if ((i & 1) == 0) {
                    v = (0xff & yuv420sp[uvp++]) - 128;
                    u = (0xff & yuv420sp[uvp++]) - 128;
                }

                int y1192 = 1192 * y;
                int r = (y1192 + 1634 * v);
                int g = (y1192 - 833 * v - 400 * u);
                int b = (y1192 + 2066 * u);

                if (r < 0) r = 0;
                else if (r > 262143)
                    r = 262143;
                if (g < 0) g = 0;
                else if (g > 262143)
                    g = 262143;
                if (b < 0) b = 0;
                else if (b > 262143)
                    b = 262143;

                rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
            }
        }
    }

    public String getTargetColor() {
        return targetColor;
    }

    public int getPixel(int x, int y) {
        //W is your width (number of pixels in a row) == 32
        return pixels[x + previewSize.width * y];
    }

    public Camera getCamera() {
        if (camera != null)
            return camera;
        return null;
    }

//    @Override
//    public SurfaceHolder getHolder() {
//        return holder;
//    }
}
