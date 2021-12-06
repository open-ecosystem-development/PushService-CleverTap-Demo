# CleverTap Push Service integration with Huawei Push Kit



## Table of Contents
 * [Introduction](#introduction)
 * [Getting Started](#getting-started)
 * [Sending PUSH message](#sending-push-message)
 * [License](#license) 
 * [Question](#question) 
 

## Introduction
Sample code for integration between Hms push kit and CleverTap
as of this post only the data messaging e.g. In-App is supported

## Getting Started

 **Huawei - App Gallery Connect setup**
   - Create Huawei developer account, if you don't have [from here](https://developer.huawei.com/consumer/en/). 
   - Create new project and app in App Gallery Connect [Refer](https://developer.huawei.com/consumer/en/codelab/HMSPreparation/index.html#0).   
   - Enable Huawei Push kit service in your app from Huawei App Gallery Connect [Refer](https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/android-config-agc-0000001050170137).
   
 **CleverTap setup**
   - Request your account from here [demo](https://clevertap.com/live-product-demo/)
   - Configure your OneSignal App's Huawei platform settings [Refer](https://eu1.dashboard.clevertap.com/K9Z-46W-995Z/account-setup/campaigns-journeys/channels/mobile-push/android).
   
 **Android app - client side setup**
   - Use this demo code and refactor it with your new package name.
   - Go to Huawei App Gallery Connect, find your project. Go to Project Setting->General Information, in the App information, download agconnect-services.json file      and copy it to your application's root directory [Refer](https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/android-integrating-sdk-0000001050040084). 
   - In your module-level build.gradle file, add your app-id. Configure your signing information and keystore file in the signingConfig.
     Sync gradle.   
   - Go to CleverTap Dashboard and create a campaign to test it.
   - Run and test your app on Huawei device. 
    
     Make sure your device is subscribed to get notifications  and can receive notifications sent from the OneSignal dashboard.
   - Manage your users, messages, audience through OneSignal dashboard.


## Sending PUSH message support
   in-app message, A/B tests, automated message for selected audience by creating segments.

## License
This sample code for android is licensed under the [Apache License, version 2.0.](http://www.apache.org/licenses/LICENSE-2.0)

## Question
If you have a question 
- [Stack Overflow](https://stackoverflow.com/questions/tagged/huawei-mobile-services) is the best place for any programming questions. 
  Be sure to tag your question with `huawei-mobile-services`.
