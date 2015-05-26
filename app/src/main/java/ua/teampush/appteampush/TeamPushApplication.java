package ua.teampush.appteampush;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.ParsePush;
import com.parse.SaveCallback;
/**
 * Created by A-D-L on 25.05.2015.
 */
public class TeamPushApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("I am in ", getClass().getName());
        Parse.initialize(this, "AmNcCDigddWEds9FtrLGx5CrjJiRbPGkQId8AWiK", "5002LgWgeBZi6bksZpQ0ha41jy7zF2vpIrBT5XGJ");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseUser.enableAutomaticUser();
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push"+e.getMessage(),e);
                }
            }
        });
        Log.d("Log ", "2");
    }


        //This is the last way I can fix it, it will add user, and it won't be equal to null
        /*ParseUser.enableAutomaticUser();
        Log.d("Log ", "3");
        ParseUser.getCurrentUser().saveInBackground();
        Log.d("Log ", "4");
        PushService.setDefaultPushCallback(this, MainActivity.class);
        Log.d("Log ", "5");

        Log.d("Log ", "6");*/
    //this is the way we will send notifications to everyone in room
    /*// Push to a channel from an Android client
    ParsePush push = new ParsePush();
    push.setChannel("galaxy_nexus_news");
    push.setMessage("The new Galaxy Nexus has been released!");
    push.sendInBackground();*/
}
