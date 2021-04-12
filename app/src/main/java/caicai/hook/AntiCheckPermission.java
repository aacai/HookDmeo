package caicai.hook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public final class AntiCheckPermission extends ContentProvider {
    /* public */static final int DENIED=-1;
    /* public */static final int GRANTED=0;
    private static final String TAG="AntiPermission";
    private final void hookCheckPermission() {
        final XC_MethodHook hook=new XC_MethodHook(){
            public void beforeHookedMethod(XC_MethodHook.MethodHookParam param) {
                param.setResult(GRANTED);
            }
        };
        try {
            Class<?> clazz=Class.forName("android.content.ContextWrapper");
            XposedHelpers.findAndHookMethod(clazz, 
                                            "checkSelfPermission", String.class, hook);
            XposedHelpers.findAndHookMethod(clazz,
                                            "checkPermission", String.class, int.class, int.class, hook);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
        if (getContext().getApplicationInfo().targetSdkVersion < 23) {
            try {
                XposedHelpers.findAndHookMethod(Class.forName("androidx.core.content.PermissionChecker"), 
                                                "checkSelfPermission", Context.class, String.class, hook);
                XposedHelpers.findAndHookMethod(Class.forName("androidx.core.content.PermissionChecker"), 
                                                "checkPermission", Context.class, String.class, int.class, int.class, hook);
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "androidx.core.content.PermissionChecker Not Found");
            }
        }
    }
    
    @Override
    public boolean onCreate() {
        hookCheckPermission();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] p2, String p3, String[] p4, String p5) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues p2) {
        return null;
    }

    @Override
    public int delete(Uri uri, String p2, String[] p3) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues p2, String p3, String[] p4) {
        return 0;
    }
    static{
       
    }
    public AntiCheckPermission() {}
}
