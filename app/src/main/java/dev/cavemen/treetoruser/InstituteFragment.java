package dev.cavemen.treetoruser;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class InstituteFragment extends Fragment {
    ArrayList<String> institutename,instituterating,institutesubject,institutearea,institutebestat,institutexp,institutekmaway,instituteavailablity;
    RecyclerView recyclerView;
    //AllInsituteFragmentAdapter adapter;
    TextView serachbox;
    int counter=0;
    public InstituteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_institute, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        serachbox=view.findViewById(R.id.searchforallstudent);
        institutename=new ArrayList<>();
        instituterating=new ArrayList<>();
        institutesubject=new ArrayList<>();
        institutexp=new ArrayList<>();
        institutebestat=new ArrayList<>();
        institutekmaway=new ArrayList<>();
        instituteavailablity=new ArrayList<>();
        institutearea=new ArrayList<>();

        recyclerView=view.findViewById(R.id.allinstituterecyclerview);


//        institutename.add("Treetor Coaching");
//        institutename.add("Institute of Treetor");
//        institutename.add("Treetor Academy Of...");
//        institutename.add("Trident Institute Of...");
//        institutename.add("Trident school Of...");
//
//        instituterating.add("4.5");
//        instituterating.add("4.5");
//        instituterating.add("4.5");
//        instituterating.add("4.5");
//        instituterating.add("4.5");
//
//        institutexp.add("1669");
//        institutexp.add("1669");
//        institutexp.add("1669");
//        institutexp.add("1669");
//        institutexp.add("1669");
//
//        institutesubject.add("maths,physics,chem..");
//        institutesubject.add("maths,physics,chem..");
//        institutesubject.add("maths,physics,chem..");
//        institutesubject.add("maths,physics,chem..");
//        institutesubject.add("maths,physics,chem..");
//
//        institutekmaway.add("7.9");
//        institutekmaway.add("7.9");
//        institutekmaway.add("7.9");
//        institutekmaway.add("7.9");
//        institutekmaway.add("7.9");
//
//        institutearea.add("patia");
//        institutearea.add("patia");
//        institutearea.add("patia");
//        institutearea.add("patia");
//        institutearea.add("patia");
//
//        institutebestat.add("maths");
//        institutebestat.add("maths");
//        institutebestat.add("maths");
//        institutebestat.add("maths");
//        institutebestat.add("maths");
//
//        instituteavailablity.add("yes");
//        instituteavailablity.add("yes");
//        instituteavailablity.add("yes");
//        instituteavailablity.add("yes");
//        instituteavailablity.add("yes");

        serachbox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty())
                {
                    //setadapter(editable.toString().toLowerCase());
                }
                else {
                    //casedetail.clear();
                    institutename.clear();

                    recyclerView.removeAllViews();
                }
            }
        });



    }


//    private void setadapter(final String query)
//    {
//
//
//
//
//
//        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("users").child("institutes");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                institutename.clear();
//                instituterating.clear();
//                institutesubject.clear();
//                institutexp.clear();
//                institutebestat.clear();
//                institutekmaway.clear();
//                instituteavailablity.clear();
//                institutearea.clear();
//                recyclerView.removeAllViews();
//                counter=0;
//                for(DataSnapshot snapshot:dataSnapshot.getChildren())
//                {
//
//
//                    counter=counter+1;
//                    if(counter==4)
//                    {
//
//                    }
//                    if(snapshot.child("courses").exists()) {
//                        for (DataSnapshot snapshot1 : snapshot.child("courses").getChildren()) {
//
//                            for(DataSnapshot snapshot2:snapshot1.child("subjects").getChildren()) {
//
//                                if (snapshot1.getKey().toLowerCase().contains(query) || snapshot.child("name").getValue().toString().toLowerCase().contains(query)||snapshot2.getKey().toLowerCase().contains(query)) {
//
//                                    institutename.add(snapshot.child("name").getValue().toString());
//                                    instituterating.add("4.8");
//
//                                    institutexp.add("1669");
//                                    institutesubject.add("maths,physics,chem..");
//
//                                    institutekmaway.add("7.9");
//
//
//                                    institutearea.add(snapshot.child("area").getValue().toString());
//                                    institutebestat.add("english");
//
//                                    instituteavailablity.add("yes");
//
//                                    FragmentManager f_manager;
//                                    f_manager = getFragmentManager();
//
//                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                                    adapter = new AllInsituteFragmentAdapter(institutename, instituterating, institutesubject, institutearea, institutebestat, institutexp, institutekmaway, instituteavailablity, getContext(), f_manager);
//                                    recyclerView.setAdapter(adapter);
//                                }
//                            }
//                        }
//                    }
//
////                    DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("users").child("institutes").child(snapshot.child("uid").getValue().toString()).child("cases").child(snapshot.getKey());
////                    reference.addValueEventListener(new ValueEventListener() {
////                        @Override
////                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////
////
////
////
////                            if (dataSnapshot.child("current status").exists() && dataSnapshot.child("court").getValue().toString().toLowerCase().contains(query.toLowerCase())) {
////
////
////                                FragmentManager f_manager;
////                                f_manager=getFragmentManager();
////
////                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
////                                adapter = new AllInsituteFragmentAdapter(institutename,instituterating,institutesubject,institutearea,institutebestat,institutexp,institutekmaway,instituteavailablity,getContext(),f_manager);
////                                recyclerView.setAdapter(adapter);
////                            }
////
////                        }
////
////                        @Override
////                        public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                        }
////                    });
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//    }
}
