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

import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    static final String TAG = "MyFirebaseMessagingService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        try {
                if (remoteMessage.getData().size() > 0) {
                    Log.d(TAG,"onMessageReceived (FCM): got a message: " + remoteMessage.getMessageId());
                }
        } catch (Throwable t) {
            Log.d(TAG, "Error parsing FCM message", t);
        }
    }

    /**
     * There are two scenarios when onNewToken is called:
     * 1) When a new token is generated on initial app startup
     * 2) Whenever an existing token is changed
     * Under #2, there are three scenarios when the existing token is changed:
     * A) App is restored to a new device
     * B) User uninstalls/reinstalls the app
     * C) User clears app data
     */

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Log.d(TAG, "Refreshed token: " + token);
        Toast.makeText(this,"Got new token: " + token,Toast.LENGTH_LONG).show();

    }
}
