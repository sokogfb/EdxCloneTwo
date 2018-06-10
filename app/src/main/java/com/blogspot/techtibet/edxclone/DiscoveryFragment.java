package com.blogspot.techtibet.edxclone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoveryFragment extends Fragment {
    private RecyclerView mDiscoverRecyclerView;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;
    private DiscoverRecyclerAdapter discoverRecyclerAdapter;
    private List<Discover> discoverList;


    public DiscoveryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_discovery, container, false);
        mAuth=FirebaseAuth.getInstance();
        mStore=FirebaseFirestore.getInstance();
        mDiscoverRecyclerView=(RecyclerView)view.findViewById(R.id.discover_list_view);
        discoverList=new ArrayList<>();
        discoverRecyclerAdapter=new DiscoverRecyclerAdapter(discoverList,getContext());
        mDiscoverRecyclerView.setAdapter(discoverRecyclerAdapter);
        mDiscoverRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDiscoverRecyclerView.setHasFixedSize(true);

        mStore.collection("Courses").addSnapshotListener(getActivity(),new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if(!documentSnapshots.isEmpty()){
                    for(DocumentChange doc:documentSnapshots.getDocumentChanges()){
                        if(doc.getType()==DocumentChange.Type.ADDED){
                            Discover discover=doc.getDocument().toObject(Discover.class);
                            discoverList.add(discover);
                            discoverRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }

            }
        });


        return view;
    }

}
