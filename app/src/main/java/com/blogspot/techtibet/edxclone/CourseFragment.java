package com.blogspot.techtibet.edxclone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends Fragment {
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;


    public CourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_course, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.courselist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String[] course={"how to learn java in one day","Learning web development telusko learning","android app development course"};
        courseAdapter=new CourseAdapter(course);
        recyclerView.setAdapter(courseAdapter);
        return view;
    }

}
