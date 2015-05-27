package ua.teampush.appteampush;

import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Анна on 27.05.2015.
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


    private ArrayList<Message> GetSearchResults(){
        ArrayList<Message> results = new ArrayList<>();
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
        results.add(new Message(1,"12th message","user2","05/02/2015", "1st room"));
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
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast toast = Toast.makeText(getActivity(), "Пора кормить кота!", Toast.LENGTH_SHORT);
        toast.show();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        messages = GetSearchResults();
        Log.d("mess", "1  enter on cr");

        View view = inflater.inflate(R.layout.messages_fragment, container, false);
        messageListView = (ListView) view.findViewById(R.id.listR);
        messageListView.setAdapter(new MessageList(getActivity(), messages));

        editText = (EditText)view.findViewById(R.id.editText);
        //editText = new EditText (getActivity());
        sendBtn = (Button)view.findViewById(R.id.btnSend);
        //sendBtn = new Button (getActivity());
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = editText.getText().toString();
                editText.setText("mess:" + s);
				ParsePush push = new ParsePush();
                push.setChannel("");
                push.setMessage(s);
                push.sendInBackground();
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
