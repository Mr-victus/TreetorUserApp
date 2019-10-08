package dev.cavemen.treetoruser;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryFragment extends Fragment {
    RecyclerView recyclerView;
    DiaryFragmentAdapter adapter;
   // ArrayList<String> time,subject,venue,teach,batchuid,iuid,rating,teachername,attendancepercent,subjectid;
    ArrayList<String> name,subject,msg,timedate,contact,noticeid,seen;
    RequestQueue requestQueue;
    String dt;
    FirebaseAuth auth;
    public DiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.attendancerecyclerview);

        subject=new ArrayList<>();
        name=new ArrayList<>();
        timedate=new ArrayList<>();
        msg=new ArrayList<>();
        contact=new ArrayList<>();
        noticeid=new ArrayList<>();
        seen=new ArrayList<>();
        auth= FirebaseAuth.getInstance();
        final TextView n=view.findViewById(R.id.name);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("users").child("students").child(auth.getCurrentUser().getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n.setText("Welcome, "+dataSnapshot.child("name").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        time.add("10:00AM");
//        time.add("10:00AM");
//        time.add("10:00AM");
//        time.add("10:00AM");
//
//        subject.add("Maths");
//        subject.add("Maths");
//        subject.add("Maths");
//        subject.add("Maths");
//
//        teach.add("trignometry");
//        teach.add("trignometry");
//        teach.add("trignometry");
//        teach.add("trignometry");
//
//        venue.add("TAT");
//        venue.add("TAT");
//        venue.add("TAT");
//        venue.add("TAT");
        /* starts before 1 month from now */


//        horizontalCalendar.refresh();

        requestQueue= Volley.newRequestQueue(getContext());
        String url ="https://treetor.in/diary-output/?uid="+auth.getCurrentUser().getUid();
        Log.d("HelloURL",url);
        jsonparse(url);




    }

    public void jsonparse(final String url)
    {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Logging In");
        progressDialog.setMessage("please wait..");
        progressDialog.show();
        final JSONObject[] jsonObject = {null};
        HttpsTrustManager.allowAllSSL();
        recyclerView.removeAllViews();

        subject.clear();
        name.clear();
        msg.clear();
        timedate.clear();
        noticeid.clear();
        seen.clear();
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
               // Toast.makeText(getContext(),"s1"+response.toString(),Toast.LENGTH_SHORT).show();
                try {
                    for(int i=0;i<response.length();i++)
                    {
                        JSONObject list=response.getJSONObject(i);
                        Log.d("HelloHO",list.get("sender").toString());
                        msg.add(list.get("message").toString());
                        name.add(list.get("sender").toString());
                        subject.add(list.get("subject").toString());
                        String timedatebuilder=list.get("h24").toString()+"|"+list.get("date").toString()+" "+list.get("month").toString();
                        timedate.add(timedatebuilder);
                        noticeid.add(list.get("id").toString());
                        contact.add(list.get("contact").toString());
                        seen.add(list.get("viewed").toString());

                    }
                    Collections.reverse(msg);
                    Collections.reverse(name);
                    Collections.reverse(subject);
                    Collections.reverse(timedate);
                    Collections.reverse(noticeid);
                    Collections.reverse(contact);
                    Collections.reverse(seen);
                    FragmentManager f_manager;
                    f_manager = getFragmentManager();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new DiaryFragmentAdapter(getContext(),name,subject,msg,timedate,contact,noticeid,seen,f_manager);
                    recyclerView.setAdapter(adapter);
                    progressDialog.hide();
                    //jsonObject[0] = new JSONObject(response);

//                    Toast.makeText(getContext(),"s"+array.toString(),Toast.LENGTH_SHORT).show();
//                    for(int i=0;i<array.length();i++)
//                    {
//                        JSONObject list=array.getJSONObject(i);
//
//
//
//                        //Toast.makeText(getContext(),"s"+list.toString(),Toast.LENGTH_SHORT).show();
//
//
//
//
//
//
//
//
//                    }
                } catch (JSONException e) {
                    Log.d("HelloTRY",""+e);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getContext(),"e"+error,Toast.LENGTH_SHORT).show();
                if(error.toString().equals("com.android.volley.NoConnectionError: javax.net.ssl.SSLHandshakeException: Handshake failed"))
                {
                    jsonparse(url);
                    Log.d("HelloBAHar",""+error);
                    error.printStackTrace();
                }
                Log.d("HelloBAHar",""+error);
                progressDialog.hide();
            }
        });
        requestQueue.add(request);
    }

    void anotherfunc()
    {
        String url = "http://192.168.43.89/get-batches/?uid=mxEAZomwlpZ0JPuL6jz8BNPp9kH3&date=2019/8/11";
        HttpsTrustManager.allowAllSSL();
//        String  tag_string_req = "string_req";
//        StringRequest strReq = new StringRequest(Request.Method.POST,
//                url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("HelloNew", "response :"+response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("HelloError", "Error: " + error.getMessage());
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("username", "max");
//                params.put("password", "123456");
//                return params;
//            }
//        };
//        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Hello",response.toString());

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

// Access the RequestQueue through your singleton class.
        requestQueue.add(jsonObjectRequest);
    }
}
