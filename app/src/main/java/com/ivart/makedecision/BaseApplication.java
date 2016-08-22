package com.ivart.makedecision;

import android.app.Application;

import com.ivart.makedecision.Model.Decision;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Ivan on 8/18/2016.
 */

public class BaseApplication extends Application {

    public static AtomicLong productPrimaryKey;

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
        productPrimaryKey = new AtomicLong(System.currentTimeMillis()*100);
    }
}
