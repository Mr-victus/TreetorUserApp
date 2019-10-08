package dev.cavemen.treetoruser;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeMsgFragment extends Fragment {


    Bundle bundle;
    RequestQueue requestQueue;
    FirebaseAuth auth;
    public NoticeMsgFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bundle=getArguments();
        return inflater.inflate(R.layout.fragment_notice_msg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth=FirebaseAuth.getInstance();
        String from=bundle.getString("from");
        String msg=bundle.getString("msg");
        String subject=bundle.getString("subject");
        String time=bundle.getString("time");
        final String contact=bundle.getString("contact");
        String noticeid=bundle.getString("noticeid");


        TextView fromm,msgg,subjectt,timee,first;
        Button contactt;

        contactt=view.findViewById(R.id.contacttoenquire);
        fromm=view.findViewById(R.id.from);
        msgg=view.findViewById(R.id.msg);
        subjectt=view.findViewById(R.id.subject);
        timee=view.findViewById(R.id.timedate);
        first=view.findViewById(R.id.firstlettertv);

        first.setText(""+from.charAt(0));

        fromm.setText("From :"+from);
        msgg.setText(""+msg);
        subjectt.setText("Subject: "+subject);
        timee.setText(""+time);


        requestQueue= Volley.newRequestQueue(getContext());
        String url ="https://treetor.in/mail-viewed/?uid="+auth.getCurrentUser().getUid()+"&notice_id="+noticeid;
        Log.d("HelloURL",url);
        jsonparse(url);
        contactt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contact.equals("Not Updated"))
                {
                    Toast.makeText(getContext(),"Phone Number Not Updated",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact));
                    startActivity(intent);
                }

            }
        });

    }
    public void jsonparse(final String url)
    {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Fetching...");
        progressDialog.setMessage("please wait..");
        progressDialog.show();
        final JSONObject[] jsonObject = {null};
        HttpsTrustManager.allowAllSSL();

        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                // Toast.makeText(getContext(),"s1"+response.toString(),Toast.LENGTH_SHORT).show();
                try {
                    for(int i=0;i<response.length();i++)
                    {
                        JSONObject list=response.getJSONObject(i);
                        //Log.d("HelloHO",list.get("sender").toString());


                    }
                    FragmentManager f_manager;
                    f_manager = getFragmentManager();
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
                    progressDialog.hide();
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
}
