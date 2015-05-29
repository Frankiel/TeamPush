package ua.teampush.appteampush;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


public class RegisterActivity extends Activity implements View.OnClickListener {
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signup = (Button) findViewById(R.id.signupButton);
        signup.setOnClickListener(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

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

    public void onClick(View v){
        final EditText string1 = (EditText)findViewById(R.id.loginEditText);
        final EditText string2 = (EditText)findViewById(R.id.passwordEditText);
        final Button but = (Button)findViewById(R.id.loginButton);
        new Thread(new Runnable() {
            public void run() {

                try{
                    URL url = new URL("http://31.202.23.254:8080/TeamPushServer/AddUser");
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
                    but.setText(returnString);
                    in.close();

                }catch(Exception e)
                {
                    Log.d("Exception",e.toString());
                }

            }
        }).start();
        if(v.getId() == R.id.signupButton){
            Intent intent = new Intent(this, MainActivity.class);
            setResult(RESULT_OK, new Intent());
            startActivityForResult(intent, 0);
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
