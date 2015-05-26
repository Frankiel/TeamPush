package ua.teampush.appteampush;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SaveCallback;

import java.util.Timer;


public class LoadingActivity extends Activity implements View.OnClickListener {
    ProgressBar bar;
    final String LOGIN = "LOGIN";
    final String PASSWORD = "PASSWORD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("I am in ", getClass().getName());


        setContentView(R.layout.activity_loading);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setOnClickListener(this);
        /*SharedPreferences pref = getPreferences(MODE_PRIVATE);
        if(pref.contains(LOGIN) && pref.contains(PASSWORD)){
            //WE NEED TO FIND OUT IF THESE LOG AND PASS ARE RIGHT!!!!!!!!!!!!!!!
            if(true){
                Intent intent = new Intent(this, WeAreInActivity.class);
                startActivityForResult(intent, 0);
            }
            else{
                Intent intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent, -1);
            }
        }
        else {
            Intent intent = new Intent(this, LoginActivity.class);
            //to make a dependence of windows at the time we close some we could use finishFromChild(Activity)
            //but we will try another cause i dont know where can i get an Activity
            startActivityForResult(intent, -1);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loading, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == -1 || requestCode == 0){
                this.finish();
            }
        }
    }

    public void onClick(View v){ //There we should connect to DB
        if(v.getId() == R.id.progressBar){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, -1);
        }
    }
}
