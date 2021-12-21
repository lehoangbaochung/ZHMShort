package com.zitherharpmusic.zhmshort;

import android.app.Application;
import android.content.Context;

import com.zitherharpmusic.zhmshort.ui.user.User;

public class MainApplication extends Application {
    private static User user;

    @Override
    public void onCreate() {
        super.onCreate();
        MainApplication.user = new User(getApplicationContext());
    }

    public static User getUser() {
        return MainApplication.user;
    }
}
