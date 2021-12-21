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
      Darshan Pania <darshan@clevertap.com>
      Darshan Pania piyush.kukadiya@clevertap.com 
*/

package com.futurewei.hmsclevertapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;

import com.clevertap.android.sdk.Application;
import com.clevertap.android.sdk.CTInboxStyleConfig;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       CleverTapAPI CTInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.createNotificationChannel(getApplicationContext(),
                "huawei","huawei",
                "testing hms to clevertap",
                NotificationManager.IMPORTANCE_MAX,true);
        assert CTInstance != null;
        CTInstance.pushEvent("Huawei Event");

        sync_getToken();
    }

    public void sync_getToken() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String appId = AGConnectServicesConfig.fromContext(MainActivity.this).getString("client/app_id");
                    String getToken = HmsInstanceId.getInstance(getApplicationContext()).getToken(appId, "HCM");
                    if (getToken == null || getToken.trim().length() < 1) {
                        getToken = HmsInstanceId.getInstance(getApplicationContext()).getToken(appId, "HCM");
                    }
                    Log.d(TAG, "getToken: " + getToken);

                    Objects.requireNonNull(CleverTapAPI.getDefaultInstance(getApplicationContext())).pushHuaweiRegistrationId(getToken,true);
                } catch (Exception e) {
                    Log.i(TAG, "getToken failed.");
                }
            }
        }.start();
    }
}
