package dev.cavemen.treetoruser;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

   FirebaseAuth auth;




    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_signup, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        auth=FirebaseAuth.getInstance();
        final EditText name,email,password,confirmpassword,phonenumber;
        Button singup;
        singup=view.findViewById(R.id.singupbtn);
        name=view.findViewById(R.id.namesignup);
        email=view.findViewById(R.id.emailsignup);
        password=view.findViewById(R.id.passwordsignup);
        confirmpassword=view.findViewById(R.id.confirmpasswordsignup);
        //phonenumber=view.findViewById(R.id.phonesignup);




        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pass,conpass,emai,nam,phone;

                pass=""+password.getText().toString();
                conpass=""+confirmpassword.getText().toString();
                emai=""+email.getText().toString();
                nam=""+name.getText().toString();
                phone="";

                if(pass.equals(conpass) && !emai.equals("") && !nam.equals("") && !pass.equals(""))
                {
                   auth.createUserWithEmailAndPassword(emai,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("users").child("students").child(auth.getCurrentUser().getUid());
                                Map map=new HashMap();
                                map.put("email",emai);
                                map.put("name",nam);
                                map.put("gender","Not Updated");
                                map.put("dob","Not Updated");
                                map.put("languages","Not Updated");
                                map.put("phone",phone);
                                map.put("address","Not Updated");
                                map.put("facebook","Not Updated");
                                map.put("hobbies","Not Updated");
                                map.put("interests","Not Updated");
                                map.put("sports","Not Updated");
                                map.put("guardian name","Not Updated");
                                map.put("guardian email","Not Updated");
                                map.put("guardian phone","Not Updated");
                                map.put("guardian dob","Not Updated");
                                map.put("guardian relation","Not Updated");
                                map.put("guardian occupation","Not Updated");
                                map.put("guardian qualification","Not Updated");
                                map.put("class","Not Updated");
                                map.put("school","Not Updated");
                                map.put("treetor center","Not Updated");
                                map.put("board","Not Updated");
                                map.put("percentage","Not Updated");
                                map.put("subjects","Not Updated");
                                map.put("best at","Not Updated");
                                map.put("weak at","Not Updated");
                                map.put("old tuition","Not Updated");
                                map.put("score","N/A");
                                map.put("rating","N/A");
                                map.put("rank","N/A");


                                reference.updateChildren(map);

                                Toast.makeText(getContext(),"Signed Up Succesfully, Signup Another Student",Toast.LENGTH_SHORT).show();

//                                Intent i=new Intent(getActivity(),MainActivity.class);
//                                startActivity(i);
                            }
                        }

                    });


                }
            }
        });


    }
}
