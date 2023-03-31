"This Project has been archived by the owner, who is no longer providing support.  The project remains available to authorized users on a "read only" basis."

# CleverTap Push Service integration with Huawei Push Kit



## Table of Contents
 * [Introduction](#introduction)
 * [Getting Started](#getting-started)
 * [Sending PUSH message](#sending-push-message)
 * [License](#license) 
 * [Question](#question) 
 * [Acknoledgement](#acknowledgement)
 

## Introduction

This app demonstrates integration of CleverTap Push service through Huawei Push messaging service.
## Getting Started

 **Huawei - App Gallery Connect setup**
   - Create Huawei developer account, if you don't have one [from here](https://developer.huawei.com/consumer/en/). 
   - Create new project and app in App Gallery Connect [here](https://developer.huawei.com/consumer/en/service/josp/agc/index.html#/)
   -  then download the 'agconnect-services.json' file from project and copy it to your application's [root directory](https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/android-integrating-sdk-0000001050040084). 
   - Enable Huawei Push kit service in your app from Huawei App Gallery Connect [here](https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/android-config-agc-0000001050170137).
   
**Firebase Cloud Messaging setup**
   - Create firebase account, if you don't have one [from here](https://firebase.google.com/). 
   - Follow their instruction to create project.
   - Add your app to the project and use the same package name as in App Gallery Connect
   - From project overview page click on the gear icon to go to setting page
   - In the setting page 
      - download the 'google-services.json' file and copy it to your project [root directory](https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/android-integrating-sdk-0000001050040084). 
      - click on cloud messaging tab to copy the 'server key' and 'senderID' info to be fill in CleverTap setting page
      
 **CleverTap setup**
   - Request your account [from here](https://clevertap.com/live-product-demo/)
   - Configure your CleverTap App's for Huawei platform settings [here](https://developer.clevertap.com/docs/clevertap-huawei-push-integration#section-integrate-huawei-hms-sdk). 
   - Configuring for Firebase Cloud Messaging
     - On settings gear>Channels>Mobile Push> enable notification channels for Android 8+ and above
         - add your channel name that you have created in your app using *CleverTapAPI.createNotificationChannel* function 
         - for each channel that created
   - ChangeLog: for more information check the change log [here](https://github.com/CleverTap/clevertap-android-sdk/blob/master/docs/CTHUAWEIPUSHCHANGELOG.md)
   

 **Android app - client side setup**
   - Use this demo code and refactor it with your new package name.
   - Go to Huawei App Gallery Connect, find your project. Go to Project Setting>General Information, in the App information, download agconnect-services.json file and copy it to your application's [root directory](https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/android-integrating-sdk-0000001050040084). 
   - In your manifest file: 
     - Add your CleverTap account info and token. 
     - Add senderID for FCM 
     - Request all necessary permissions
     - Add necessary services
   - Configure your signing information and keystore file in the signingConfig.
   - Sync gradle.   
  

## Sending PUSH message 
   - Currently these feature are support:
    - in-app message, A/B tests, automated message for selected audience by creating segments.
    - notification via FCM
  - Currently notification via HMS Push is not support yet
  - Run and test your app on Huawei device and GMS device
    - Go to CleverTap dashboard and create a campaign to test:
      - notification:
         - choose the channel (that you have created) to send notification message
         - HMS only device won't get notification (not support yet)
      - In-app
  
  - Your device should receive notification messaging sending from CleverTap dashboard in the lock screen
  - Your device should receive in-App messaging sending from CleverTap dashboard.
  - You can view the report and manage the campaign through the dashboard.
 

## License
This sample code for android is licensed under the [Apache License, version 2.0.](http://www.apache.org/licenses/LICENSE-2.0)

## Question
If you have a question 
- [Stack Overflow](https://stackoverflow.com/questions/tagged/huawei-mobile-services) is the best place for any programming questions. 
  Be sure to tag your question with `huawei-mobile-services`.
  
 ## Acknowledgement
 Thank for code contribution from CleverTap specifically
 
 Darshan Pania <darshan@clevertap.com>
 
 Piyush Kukadiya piyush.kukadiya@clevertap.com 

