/*
      Copyright 2021. Futurewei Technologies Inc. All rights reserved.
      Licensed under the Apache License, Version 2.0 (the "License");
      you may not use this file except in compliance with the License.
      You may obtain a copy of the License at
        http:  www.apache.org/licenses/LICENSE-2.0
      Unless required by applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License.
*/
/*    CleverTap contribution

      Darshan Pania darshan@clevertap.com
      Piyush Kukadiya piyush.kukadiya@clevertap.com
*/
package com.futurewei.hmsclevertapapp;

import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.clevertap.android.sdk.CleverTapAPI;
import com.google.firebase.messaging.FirebaseMessaging;
import com.huawei.agconnect.AGConnectOptionsBuilder;
import com.huawei.hms.aaid.HmsInstanceId;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       CleverTapAPI CTInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
       assert CTInstance != null;
       // Create FCM notification channel
       CleverTapAPI.createNotificationChannel(getApplicationContext(),
                "FCM","FCM",
                "testing get fcm message from clevertap",
                 NotificationManager.IMPORTANCE_MAX,true);

        // Create HMS notification channel
       CleverTapAPI.createNotificationChannel(getApplicationContext(),
                "huawei","huawei",
                "testing get HMS message from clevertap",
                NotificationManager.IMPORTANCE_MAX,true);

       CTInstance.pushEvent("FCM-HMS Event");

       FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registered token failed: ", task.getException());
                        return;
                    }
                    // Get new FCM registration token
                    String token = task.getResult();
                    Log.d(TAG, "Got registered token: " + token);
                    Toast.makeText(MainActivity.this, "Got registered token: " + token, Toast.LENGTH_SHORT).show();
                });

        sync_getHmsToken();
    }

    public void sync_getHmsToken() {
        new Thread() {
            @Override
            public void run() {
                try {
                        String appId = new AGConnectOptionsBuilder()
                                .build(MainActivity.this)
                                .getString("client/app_id");
                        String getToken = HmsInstanceId
                                .getInstance(getApplicationContext())
                                .getToken(appId, "HCM");
                        if (getToken == null || getToken.trim().length() < 1) {
                            getToken = HmsInstanceId
                                    .getInstance(getApplicationContext())
                                    .getToken(appId, "HCM");
                        }
                        Log.d(TAG, "getToken: " + getToken);

                        Objects.requireNonNull(CleverTapAPI
                                .getDefaultInstance(getApplicationContext()))
                                .pushHuaweiRegistrationId(getToken,true);
                } catch (Exception e) {
                    Log.i(TAG, "getToken failed due to: " + e.getMessage());
                }
            }
        }.start();
    }
}
