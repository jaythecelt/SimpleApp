# SimpleApp - To demonstarte a bug in Android Things Dev Preview 6.1
This app demonstrates sluggish UI response with Android Things Dev Preview 6.1 running on i.MX7 (PICO-PI-IMX7-STARTKIT).

The bug was reported here: https://issuetracker.google.com/issues/70974565 and I commented on that bug ticket to indicate that I was seeing the same problem.  This app is another example to illustrate the issue.

## The environment
Hardware is the TechNexion PICO-PI-IMX7 starter kit: https://shop.technexion.com/pico-pi-imx7-startkit.html with the 5 inch display connected, WiFi enabled, no camera attached.  

The IMX7 is flashed with Anrdroid Things Dev Preview 6.1

## The problem
Sluggish UI response when switching activities (takes over 4 seconds to switch activities); response of all views is sluggish - buttons, seek bars, checkboxes, etc...  The original reporter of this bug (no...@gmail.com) tracked the problem down to portriat mode; this example is another confirmation of this analysis. 

## The code
SimpleApp contains two activities, each with some dummy Buttons, SeekBars, Checkboxes and TextViews.  Both activites are set to portrait mode in the app's manifest:

```
<activity android:name=".MainActivity"
          ...
          android:screenOrientation="portrait">
```
and  
```
<activity android:name=".AnotherActivity"
            ...
            android:screenOrientation="portrait">
            
```

The app starts in MainActivity, then after 5 sec runs AnotherActivity.  

Statements in AnotherActivity:onCreate(..) log the execution time of  ```setContentView(R.layout.activity_another)```

## Run the app to observe the problem in Portrait mode
1. Execute the app
2. Observe that MainActivity is in portrait mode.
3. After 5 sec the app switches to AnotherActivity, note that this activity is also in portrait mode.
4. Note the log statement that indicates the execution time of ```setContentView(...)```.  This illustrates the problem that the call to setContentView(...) is taking about 4 sec.
Ex:  
```I/AnotherActivity: =========== onCreate call to setContentView took: 4.125 sec ========```  
5. Move the SeekBars, press the Buttons and Checkbox; note the sluggish response  
  

## Run the app to observe the problem does not exist in Landscape mode
1. In the app's mainfest change to landscape mode:  
```
<activity android:name=".MainActivity"
          ...
          android:screenOrientation="landscape">
```
and  
```
<activity android:name=".AnotherActivity"
            ...
            android:screenOrientation="landscape">
            
```
2. Observe that MainActivity is in landscape mode.
3. After 5 sec the app switches to AnotherActivity, note that this activity is also in landscape mode.
4. Note the log statement that indicates the execution time of ```setContentView(...)```.  Compare this time with the above execution time in portrait mode.  
Ex:  
```I/AnotherActivity: =========== onCreate call to setContentView took: 0.341 sec ========```  
5. Move the SeekBars, press the Buttons and Checkbox; note the response is as expected.

## My results (from the log)
### Portrait Mode:
```  
02/20 10:49:09: Launching app
$ adb install-multiple -r -t -p d.htp.simpleapp C:\AT\exApps\SimpleApp\app\build\intermediates\split-apk\debug\slices\slice_9.apk 
Split APKs installed
$ adb shell am start -n "d.htp.simpleapp/d.htp.simpleapp.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
Client not ready yet..Waiting for process to come online
Connected to process 3675 on device google-iot_imx7d_pico-000000749f839b02
Capturing and displaying logcat messages from application. This behavior can be disabled in the "Logcat output" section of the "Debugger" settings page.
I/InstantRun: starting instant run server: is main process
I/MainActivity: Switching to AnotherActivity in 5 sec.
D/vndksupport: Loading /vendor/lib/hw/android.hardware.graphics.mapper@2.0-impl.so from current namespace instead of sphal namespace.
D/vndksupport: Loading /vendor/lib/hw/gralloc.imx7d.so from current namespace instead of sphal namespace.
I/display: IonManager used
D/OpenGLRenderer: HWUI GL Pipeline
I/zygote: Background concurrent copying GC freed 1834(288KB) AllocSpace objects, 0(0B) LOS objects, 27% free, 1330KB/1842KB, paused 136us total 118.235ms
W/zygote: Verification of java.lang.Object d.htp.simpleapp.AnotherActivity.access$super(d.htp.simpleapp.AnotherActivity, java.lang.String, java.lang.Object[]) took 178.975ms
I/AnotherActivity: =========== onCreate call to setContentView took: 3.314 sec ========
I/Choreographer: Skipped 209 frames!  The application may be doing too much work on its main thread.
I/Choreographer: Skipped 44 frames!  The application may be doing too much work on its main thread.
I/zygote: Do partial code cache collection, code=25KB, data=29KB
I/zygote: After code cache collection, code=25KB, data=29KB
I/zygote: Increasing code cache capacity to 128KB
I/zygote: Background concurrent copying GC freed 1920(157KB) AllocSpace objects, 0(0B) LOS objects, 24% free, 1607KB/2MB, paused 129us total 216.716ms

```  

### Landscape Mode:
```  
02/20 10:51:38: Launching app
$ adb install-multiple -r -t -p d.htp.simpleapp C:\AT\exApps\SimpleApp\app\build\outputs\apk\debug\app-debug.apk 
Split APKs installed
$ adb shell am start -n "d.htp.simpleapp/d.htp.simpleapp.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
Client not ready yet..Waiting for process to come online
Connected to process 3834 on device google-iot_imx7d_pico-000000749f839b02
Capturing and displaying logcat messages from application. This behavior can be disabled in the "Logcat output" section of the "Debugger" settings page.
I/InstantRun: starting instant run server: is main process
I/MainActivity: Switching to AnotherActivity in 5 sec.
D/vndksupport: Loading /vendor/lib/hw/android.hardware.graphics.mapper@2.0-impl.so from current namespace instead of sphal namespace.
D/vndksupport: Loading /vendor/lib/hw/gralloc.imx7d.so from current namespace instead of sphal namespace.
I/display: IonManager used
D/OpenGLRenderer: HWUI GL Pipeline
I/AnotherActivity: =========== onCreate call to setContentView took: 0.324 sec ========
I/zygote: Do partial code cache collection, code=26KB, data=30KB
I/zygote: After code cache collection, code=26KB, data=30KB
I/zygote: Increasing code cache capacity to 128KB
```  






