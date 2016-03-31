package com.helpiez.app.ui.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.helpiez.app.R;
import com.helpiez.app.SessionManager;
import com.helpiez.app.adapters.EventAdapter;
import com.helpiez.app.model.BusinessObject;
import com.helpiez.app.model.EventDetail;
import com.helpiez.app.model.EventsList;
import com.helpiez.app.ui.activity.LandingActivity;
import com.helpiez.app.volley.FeedManager;
import com.helpiez.app.volley.FeedParams;
import com.helpiez.app.volley.Interfaces;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpcomingEventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpcomingEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpcomingEventFragment extends Fragment {

    EventAdapter mAdapter;
    RecyclerView recyclerView;
    private LandingActivity mActivity;
    public UpcomingEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingEventFragment newInstance(String param1, String param2) {
        UpcomingEventFragment fragment = new UpcomingEventFragment();
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
        Log.e("Rahul", "upcoming_event Create");
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
        //ParseServer.getProjects(mActivity, mHandler.obtainMessage());
        //Call Service for data
        Log.e("Rahul", "active_event refreshProjectList");
        HashMap<String, String> hmpRegType = new HashMap<>();
        hmpRegType.put("session_id", SessionManager.getSessionId(mActivity));
        hmpRegType.put("user1_id", SessionManager.getUserId(mActivity));
        String url = "http://rahuljaiswal.me/api/getevent.php";

        FeedParams feedParams = new FeedParams(url, EventsList.class, new Interfaces.IDataRetrievalListener() {
            @Override
            public void onDataRetrieved(BusinessObject businessObject) {
                if(businessObject != null && businessObject instanceof EventsList){
                    EventsList events = (EventsList) businessObject;
                    if(events.getStatus() == 1){
                        ArrayList<EventDetail> posts = events.getEventDetail();
                        Log.e("Rahul", "Size = "+posts.size());
                        mAdapter.set(posts, 1);
                    }
                    else {
                        Log.e("Rahul", "No Response");
                    }
                }
            }
        });
        feedParams.setShouldCache(true);
        feedParams.setMethod(Request.Method.POST);
        feedParams.setPostParams(hmpRegType);
        FeedManager feedManager = new FeedManager();
        feedManager.queueJob(feedParams);
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

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
