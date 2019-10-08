package dev.cavemen.treetoruser;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class InstituteSelectTeacherFragment extends Fragment {

    ArrayList<String> institutename,instituterating,institutesubject,institutearea,institutebestat,institutexp,institutekmaway,instituteavailablity;
    RecyclerView recyclerView;
    InsituteSelectedTeacherFragmentAdapter adapter;
    public InstituteSelectTeacherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_institute_select_teacher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        institutename=new ArrayList<>();
        instituterating=new ArrayList<>();
        institutesubject=new ArrayList<>();
        institutexp=new ArrayList<>();
        institutebestat=new ArrayList<>();
        institutekmaway=new ArrayList<>();
        instituteavailablity=new ArrayList<>();
        institutearea=new ArrayList<>();

        recyclerView=view.findViewById(R.id.instituteselectedteacherrecyclerview);


        institutename.add("Prakash Sharma");
        institutename.add("Prakash Sharma");
        institutename.add("Prakash Sharma");
        institutename.add("Prakash Sharma");
        institutename.add("Prakash Sharma");


        instituterating.add("4.5");
        instituterating.add("4.5");
        instituterating.add("4.5");
        instituterating.add("4.5");
        instituterating.add("4.5");

        institutexp.add("1669");
        institutexp.add("1669");
        institutexp.add("1669");
        institutexp.add("1669");
        institutexp.add("1669");

        institutesubject.add("maths,physics,chem..");
        institutesubject.add("maths,physics,chem..");
        institutesubject.add("maths,physics,chem..");
        institutesubject.add("maths,physics,chem..");
        institutesubject.add("maths,physics,chem..");



        institutearea.add("2 yrs");
        institutearea.add("2 yrs");
        institutearea.add("2 yrs");
        institutearea.add("2 yrs");
        institutearea.add("2 yrs");




        FragmentManager f_manager;
        f_manager=getFragmentManager();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new InsituteSelectedTeacherFragmentAdapter(institutename,instituterating,institutesubject,institutearea,institutexp,getContext(),f_manager);
        recyclerView.setAdapter(adapter);
    }
}
