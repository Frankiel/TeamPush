package ua.teampush.appteampush;

import android.app.Activity;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import static android.text.TextUtils.join;
import static java.lang.Thread.sleep;

public class LoginActivity extends Activity implements View.OnClickListener {

    TextView textview;
    TextView textview2;
    Button log;
    Boolean login = false;
    Boolean changed = false;
    private void setLogin(boolean b){
        login = b;
    }
    private void change(boolean b){
        changed = b;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setResult(RESULT_OK, new Intent()); //return -1 code to parent window (loading and wearein)
        textview = (TextView) findViewById(R.id.registerText);
        textview2 = (TextView) findViewById(R.id.passwordEditText);
        textview.setOnClickListener(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        log = (Button) findViewById(R.id.loginButton);
        log.setOnClickListener(this);
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

    public void onBackPressed(){
        this.finish();
    }

    public void onClick(View v){ //There we should connect to DB
        final EditText string1 = (EditText)findViewById(R.id.loginEditText);
        final EditText string2 = (EditText)findViewById(R.id.passwordEditText);
        final Button but = (Button)findViewById(R.id.loginButton);
        login = true;
        Runnable r =  new Thread(){
            public void run() {

                try{
                    URL url = new URL("http://31.202.23.254:8080/TeamPushServer/UserServelet");
                    URLConnection connection = url.openConnection();

                    String inputString = string1.getText().toString()+"/"+string2.getText().toString();
//inputString = URLEncoder.encode(inputString, "UTF-8");

                    Log.d("inputString", inputString);

                    connection.setDoOutput(true);
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(inputString);
                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String returnString="";

                    returnString = in.readLine();
//but.setText(returnString);
                    Log.d("returnString:", returnString);

                    setLogin(returnString.equals("true"));
                    //login = returnString.split("")[0]=="true";
                    Log.d("login:", login.toString());
                    //String c = login.toString();
                    Log.d("thread:", "end");
                    change(true);
                }catch(Exception e)
                {
                    Log.d("Exception", e.toString());
                    setLogin(false);

                    Log.d("login th:", login.toString());
                }
            }
        };
        Thread t = new Thread(r);
        t.start();



        try {
            //sleep(100);
            t.join();
        } catch (InterruptedException e) {
            Log.d("Exception", e.toString());        }
        /*try {
        Thread.currentThread().join();
        } catch (InterruptedException e)
        {
        Log.d("Exception:", e.toString());
        }*/
        Log.d("login main:", login.toString());
        if(v.getId() == R.id.registerText){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivityForResult(intent, 1);
        }else if(v.getId() == R.id.loginButton){
            if(!login){
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Error logging in")
                        .setMessage("Login or password is incorrect")

                        .setCancelable(false)
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
//login = false;
            }
            else{
                Intent intent = new Intent(this, MainActivity.class);
                startActivityForResult(intent, 0);
//WE NEED TO SAVE LOG AND PASS TO PREFERENCES HERE
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK) {
            if (requestCode == 1 || requestCode == 0) {
                this.finish();
            }
        }
    }

}