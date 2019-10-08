package dev.cavemen.treetoruser;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DiaryFragmentAdapter extends RecyclerView.Adapter<DiaryFragmentAdapter.viewholder> {

   // ArrayList<String> time,subject,venue,teach,iuid,batchuid,rating,teachername,attendancepercent;
    ArrayList<String> name,subject,msg,timedate,contact,noticeid,seen;
   // String instituteuid,batchuid;
    Map present,absent;
    FragmentManager f_manager;
    Context context;
    View view;
    int c;

    public DiaryFragmentAdapter() {

    }

    public DiaryFragmentAdapter(Context context, ArrayList<String> name,ArrayList<String>subject,ArrayList<String>msg,ArrayList<String>timedate,ArrayList<String>contact,ArrayList<String>noticeid,ArrayList<String>seen, FragmentManager f_manager){
        this.context = context;
        this.name=name;
        this.subject=subject;
        this.msg=msg;
        this.timedate=timedate;
        this.f_manager=f_manager;
        this.noticeid=noticeid;
        this.seen=seen;
        this.contact=contact;


    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.customdiary,parent,false);
        return new viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, final int position) {


//        if(stuname.size()!=position)
//        {
//            absent=new HashMap();
//
//            present.put(uid.get(position),0);
//        }

        holder.name.setText(name.get(position));
        final String na=name.get(position);
        holder.firstlettertv.setText(""+na.toUpperCase().charAt(0));
        holder.msg.setText(msg.get(position));
        holder.subject.setText(subject.get(position));
        holder.timedate.setText(timedate.get(position));
        Long ts=Long.parseLong(noticeid.get(position).toString());
        Date date=new Date(ts);
        if(seen.get(position).equals("1"))
        {
            holder.seen.setBackgroundResource(R.drawable.openeye);

        }
        else
        {
            holder.seen.setBackgroundResource(R.drawable.closedeye);
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedfragment=new NoticeMsgFragment();
                Bundle bundle=new Bundle();
                bundle.putString("from",name.get(position));
                bundle.putString("msg",msg.get(position));
                bundle.putString("subject",subject.get(position));
                bundle.putString("time","Time : 25 Sep, 2019, 1:59 PM");
                bundle.putString("noticeid",noticeid.get(position));
                bundle.putString("contact",contact.get(position));
                selectedfragment.setArguments(bundle);
                f_manager.beginTransaction().replace(R.id.fragment_container,selectedfragment).addToBackStack("diaryfragment").commit();

            }
        });




    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return name.size();
    }

    public  class viewholder extends RecyclerView.ViewHolder{

        TextView name,msg,subject,timedate,firstlettertv,seen;
        int c;
        Dialog dialog;
        Button attendance;
        View layout;
        Map presentt=new HashMap();
        public viewholder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            subject=itemView.findViewById(R.id.subject);
            msg=itemView.findViewById(R.id.msg);
            timedate=itemView.findViewById(R.id.datetime);
            firstlettertv=itemView.findViewById(R.id.firstlettertv);
            layout=itemView.findViewById(R.id.customdiary);
            seen=itemView.findViewById(R.id.seen);
            c=0;
            dialog=new Dialog(context);







            // customer_pic=(ImageView) itemView.findViewById(R.id.doctor_pic);

            Log.d("TAAAAG","kk");

        }

    }

}

