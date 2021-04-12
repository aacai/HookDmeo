package caicai.hook;

import android.content.ContentProvider;
import android.net.Uri;
import android.database.Cursor;
import android.content.ContentValues;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import android.app.Dialog;
import com.android.dx.MethodId;
import java.lang.reflect.Method;
import android.content.Context;
import android.util.Log;
public final class DialogHook extends ContentProvider {
    private String TAG="HookDialog";
    private void hookDialog() {
        XC_MethodHook dialogHook=new XC_MethodHook(){
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam p) {
                p.args[0] = true;
            }           
        };
      
        try {
            // XposedHelpers.findAndHookMethod(Class.forName("android.app.Dialog"),"show",showHook);
            XposedHelpers.findAndHookMethod(Class.forName("android.app.Dialog"), "setCancelable", Boolean.TYPE, dialogHook);
            XposedHelpers.findAndHookMethod(Class.forName("android.app.AlertDialog$Builder"), "setCancelable", Boolean.TYPE, dialogHook);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    @Override
    public boolean onCreate() {
        hookDialog();
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

}
