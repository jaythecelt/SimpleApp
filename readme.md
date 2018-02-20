# SimpleApp - To demonstarte a bug in Android Things Dev Preview 6.1
This app demonstrates sluggish UI response with Android Things Dev Preview 6.1 running on i.MX7 (PICO-PI-IMX7-STARTKIT).

The bug was reported here: https://issuetracker.google.com/issues/70974565 and I commented on that bug ticket to indicate that I was seeing the same problem.  This app is another example to illustrate the issue.

## The environment
Hardware is the TechNexion PICO-PI-IMX7 starter kit: https://shop.technexion.com/pico-pi-imx7-startkit.html with the 5 inch display connected, WiFi enabled, no camera attached.  

The IMX7 is flashed with Anrdroid Things Dev Preview 6.1

## The problem
Sluggish UI response when switching activities (takes over 4 seconds to switch activities); response of all views is sluggish - buttons, seek bars, checkboxes, etc...  The original reporter of this bug (no...@gmail.com) tracked the problem down to portiat mode; this example is another confirmation of this analysis. 

## Runing the app
SimpleApp contains two activities, each with some dummy Buttons, SeekBars, Checkboxes and TextViews.  Both activites are set to portrait mode in the app's manifest:

```
<activity android:name=".MainActivity"
          android:theme="@style/Theme.AppCompat.Light.NoActionBar"
          android:screenOrientation="landscape">
```
and  
```<activity android:name=".AnotherActivity"
            android:theme="@style/Theme.AppCompat.Light.NoActionBar"
            android:screenOrientation="landscape">
            
```

The app starts in MainActivity, then after 5 sec runs AnotherActivity.  

Statements in AnotherActivity:onCreate(..) log the execution time of  ```setContentView(R.layout.activity_another)```.


