package ua.teampush.appteampush;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

/**
 * Created by Anna on 27.05.2015.
 */
public class Messages_Fragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    ArrayList<Message> messages;
    private ListView messageListView;
    private Button sendBtn;
    private EditText editText;
    String or = "<!5#, some text#, user#, 12.12.2012#, r;oom#> <!6#, so6me text#, us6er#, 12.12.2062#, r6oom#>";

    private ArrayList<Message> messegeParser(String origin){
        Pattern pattern = Pattern.compile("<!([^#]+)#, ([^#]+)#, ([^#]+)#, ([^#]+)#, ([^#]+)#>");
        Matcher result = pattern.matcher(origin);
        ArrayList<Message> messages = new ArrayList<>();
        while (result.find()) {
            messages.add(
                    new Message(Integer.parseInt(result.group(1)), result.group(2),
                            result.group(3), result.group(4), result.group(5)));
        }
        return messages;
    }

    private ArrayList<Message> GetSearchResults(){
        ArrayList<Message> results = new ArrayList<>();
        results = messegeParser(or);
        /*
        results.add(new Message(1,"1st message","user1","02/02/2015", "1st room"));
        results.add(new Message(1,"2nd message","user2","02/02/2015", "1st room"));
        results.add(new Message(1,"3rd message","user1","03/02/2015", "1st room"));
        results.add(new Message(1,"4th message","user1","03/02/2015", "1st room"));
        results.add(new Message(1,"5th message","user2","04/02/2015", "1st room"));
        results.add(new Message(1,"6rd message","user1","04/02/2015", "1st room"));
        results.add(new Message(1,"7th message","user1","04/02/2015", "1st room"));
        results.add(new Message(1,"8th message","user2","04/02/2015", "1st room"));
        results.add(new Message(1,"9th message","user2","04/02/2015", "1st room"));
        results.add(new Message(1,"10rd message","user3","05/02/2015", "1st room"));
        results.add(new Message(1,"11th message","user3","05/02/2015", "1st room"));
        results.add(new Message(1,"12th message","user2","05/02/2015", "1st room"));*/

        return results;
    }
    /*
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyRooms_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyRooms_Fragment newInstance(int param) {
        MyRooms_Fragment fragment = new MyRooms_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param);
        fragment.setArguments(args);
        return fragment;
    }

    public Messages_Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast toast = Toast.makeText(getActivity(), "Пора кормить кота!", Toast.LENGTH_SHORT);
        //toast.show();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        messages = GetSearchResults();

        Log.d("mess", "1  enter on cr");
        Runnable r =  new Thread(){
            public void run() {
                Log.d("mess th", "1  enter");
                try{
                    URL url = new URL("http://31.202.23.254:8080/TeamPushServer/GetMessages");
                    URLConnection connection = url.openConnection();
                    Log.d("mess th", "1  url");

                    String inputString = "0";
                    Log.d("mess th  inputString", inputString);

                    connection.setDoOutput(true);
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(inputString);
                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String returnString="";

                    Log.d("mess th before retStr", "1");
                    returnString = in.readLine();
                    or = returnString;
                    Log.d("mess th returnString:", returnString);


                    messages.addAll(messegeParser(returnString));
                    in.close();
                    Log.d("mess th   returnString:", returnString);
                    //messageListView.setAdapter(new MessageList(getActivity(), messages));
                }catch(Exception e){
                    Log.d("Exception",e.toString());
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
        try {
            //sleep(100);
            t.join();
        } catch (InterruptedException e) {
            Log.d("Exception", e.toString());
        }
        View view = inflater.inflate(R.layout.messages_fragment, container, false);
        Log.d("mess","after th. view" );
        messageListView = (ListView) view.findViewById(R.id.listR);
        messageListView.setAdapter(new MessageList(getActivity(), messages));
        messageListView.setDivider(new ColorDrawable(0x009D91));
        messageListView.setDividerHeight(3);
        editText = (EditText)view.findViewById(R.id.editText);
        //editText = new EditText (getActivity());
        sendBtn = (Button)view.findViewById(R.id.btnSend);
        //sendBtn = new Button (getActivity());
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = editText.getText().toString();
                //editText.setText("mess:" + s);
               ParseQuery query = ParseInstallation.getQuery();
                query.whereEqualTo("channels", "");
                query.whereNotEqualTo("installationId",
                        ParseInstallation.getCurrentInstallation().getInstallationId());
                ParsePush androidPush = new ParsePush();
                androidPush.setMessage(s);
                androidPush.setQuery(query);
                androidPush.sendInBackground();

                new Thread(new Runnable() {
                    public void run() {
                        Log.d("mess th", "1  enter");
                        try{
                            String separator = "#, ";
                            //<!21#, mess:    #, q1#, 12.12.2012#, ua.teampush.appteampush.Room@5289a7b8#>
                            //"<!5#, some text#, user#, 12.12.2012#, r;oom#> <!6#, so6me text#, us6er#, 12.12.2062#, r6oom#>";
                            String mess = "<!" + messages.get(messages.size() - 1).getId() + 1 + separator +
                                    editText.getText().toString() + separator +
                                    (MainActivity.getCurrUser()+1) + separator +
                                    "12.12.2012" + separator +
                                    MyRooms_Fragment.getCurrRoom() + "#>";
                            URL url = new URL("http://31.202.23.254:8080/TeamPushServer/AddMessage");
                            URLConnection connection = url.openConnection();
                            Log.d("mess th", "1  url");

                            String inputString = mess;
                            Log.d("mess th  inputString", inputString);

                            connection.setDoOutput(true);
                            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                            out.write(inputString);
                            out.close();
                            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                        }catch(Exception e)
                        {
                            Log.d("Exception",e.toString());
                        }
                    }
                }).start();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
