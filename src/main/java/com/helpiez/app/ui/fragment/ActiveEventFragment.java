package com.helpiez.app.ui.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.helpiez.app.R;
import com.helpiez.app.adapters.EventAdapter;
import com.helpiez.app.model.NGOEvent;
import com.helpiez.app.ui.activity.LandingActivity;
import com.helpiez.app.ui.util.ParseServer;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActiveEventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActiveEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActiveEventFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EventAdapter mAdapter;
    RecyclerView recyclerView;
    private LandingActivity mActivity;
    public ActiveEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActiveEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActiveEventFragment newInstance(String param1, String param2) {
        ActiveEventFragment fragment = new ActiveEventFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new EventAdapter(mActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_active_event, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        // use a  layout manager
        RecyclerView.LayoutManager lm = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(mAdapter);
        refreshProjectList();
        return view;
    }

    private void refreshProjectList() {
        ParseServer.getProjects(mActivity, mHandler.obtainMessage());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AppCompatActivity) {
            mActivity = (LandingActivity) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ArrayList<NGOEvent> posts = (ArrayList<NGOEvent>)msg.obj;
            mAdapter.set(posts, 2);
            Toast.makeText(mActivity, "Done", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
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
        void onFragmentInteraction(Uri uri);
    }
}
