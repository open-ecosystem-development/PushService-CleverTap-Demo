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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Utils;
import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;
import com.huawei.hms.push.SendException;

import org.json.JSONException;

import java.util.Objects;

public class MyHmsPushService extends HmsMessageService {
    private final String TAG = "MyHmsPushService";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG,"get new token from HMS: " + s );
        sendMyBroadcast("OnNewToken",s);

        Objects.requireNonNull(CleverTapAPI.getDefaultInstance(getApplicationContext()))
                .pushHuaweiRegistrationId(s,true);

        Log.i(TAG,"on new token receive ok ");
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String msg = remoteMessage.getData();
        Log.i(TAG,"msg received data " + msg );

        try {
            @SuppressLint("RestrictedApi") Bundle extras = Utils.stringToBundle(msg);
            CleverTapAPI.createNotification(getApplicationContext(),extras);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().length()> 0) {
            msg += "Message data payload: " + remoteMessage.getData();
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            msg = msg + '\n' + "Message Notification Body: " + remoteMessage.getNotification().getBody();
        }
        sendMyBroadcast("onMessageReceived",msg);
        Log.i(TAG,"HMS Push got Data Message from CleverTap: " + msg);

    }

    private void sendMyBroadcast(String method, String msg) {
        Intent intent = new Intent();

        intent.putExtra("method",method);
        intent.putExtra("msg",msg);
        //Transfer data to activity by broadcasting
        sendBroadcast(intent);
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
        Intent intent = new Intent();

        intent.putExtra("method", "onSendError");
        intent.putExtra("msg", s + "onSendError called, message id:" + s + " ErrCode:"
                + ((SendException) e).getErrorCode() + " message:" + e.getMessage());
        sendBroadcast(intent);
    }
}
