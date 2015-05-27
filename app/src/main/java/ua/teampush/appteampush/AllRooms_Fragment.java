package ua.teampush.appteampush;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyRooms_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyRooms_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllRooms_Fragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView roomListView;
    ArrayList<Room> rooms;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyRooms_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllRooms_Fragment newInstance(int param) {
        AllRooms_Fragment fragment = new AllRooms_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param);
        fragment.setArguments(args);
        return fragment;
    }

    public AllRooms_Fragment() {
        // Required empty public constructor
    }
	
    private ArrayList<Room> GetSearchResults(){
        ArrayList<Room> results = new ArrayList<>();
        results.add(new Room(1,"1st in all room","pass","admin1", 11));
        results.add(new Room(1,"2nd in all room","pass","admin2", 12));
        results.add(new Room(1,"3rd in all room","pass","admin3", 13));
        results.add(new Room(1, "4th in all room", "pass", "admin4", 14));
        results.add(new Room(1, "5th in all room", "pass", "admin5", 12));
        return results;
    }
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rooms = GetSearchResults();
        Log.d("room", "1");
        roomListView= (ListView) inflater.inflate(R.layout.myrooms_fragment, container, false);
        roomListView.setAdapter(new RoomList(getActivity(), rooms));
        roomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment t = new Messages_Fragment();
                fragmentManager.beginTransaction().add(R.id.container, t)
                        .addToBackStack("").commit();

            }
        });

        ((MainActivity) getActivity()).setActionBarTitle("All rooms");
        Log.d("room", "2");
        return roomListView;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
