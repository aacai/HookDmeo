package caicai.hook;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import de.robv.android.xposed.XposedHelpers;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import android.util.Log;

public class MainActivity extends Activity { 
    private static final int GRANTED=0,DENIED=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        try {
            dialog.setTitle("jdbdh").
                setMessage("允许0,拒绝-1\ncheckSelfPermission= " + checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) +
                           "\ntargetSDKVersion=" + getApplicationInfo().targetSdkVersion +
                           "\n" + getClass().getMethod("checkSelfPermission", String.class))
                .setCancelable(false)
                .show();
        } catch (NoSuchMethodException |SecurityException e) {
            throw new AssertionError(e);
        }
       }
} 
