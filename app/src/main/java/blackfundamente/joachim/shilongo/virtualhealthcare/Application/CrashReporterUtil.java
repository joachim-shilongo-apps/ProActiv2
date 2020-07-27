package blackfundamente.joachim.shilongo.virtualhealthcare.Application;
import android.app.Application;
import android.content.Context;
import android.os.Environment;

import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDex;

import com.balsikandar.crashreporter.CrashReporter;

import java.io.File;

import nouri.in.goodprefslib.GoodPrefs;
public class CrashReporterUtil extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GoodPrefs.init(getApplicationContext());

        if (BuildConfig.DEBUG) {
            String crashReporterPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "bali";
            CrashReporter.initialize(getApplicationContext());

        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

