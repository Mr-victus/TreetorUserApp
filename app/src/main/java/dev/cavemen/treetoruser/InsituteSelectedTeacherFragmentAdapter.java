package dev.cavemen.treetoruser;

import android.content.Context;
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

import java.util.ArrayList;

public class InsituteSelectedTeacherFragmentAdapter extends RecyclerView.Adapter<InsituteSelectedTeacherFragmentAdapter.viewholder> {

    ArrayList<String> institutename,instituterating,institutesubject,instituteexperience,institutexp;

    Context context;
    View view;
    FragmentManager f_manager;


    public InsituteSelectedTeacherFragmentAdapter(ArrayList<String> institutename, ArrayList<String> instituterating, ArrayList<String> institutesubject, ArrayList<String> instituteexperience, ArrayList<String> institutexp, Context context, FragmentManager f_manager) {
        this.institutename = institutename;
        this.instituterating = instituterating;
        this.institutesubject = institutesubject;
        this.instituteexperience = instituteexperience;
        this.institutexp = institutexp;
        this.context = context;
        this.f_manager = f_manager;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.custominstituteteacherselected,parent,false);



        return new viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {



            holder.name.setText("Name: "+institutename.get(position));
        holder.rating.setText("Rating: "+instituterating.get(position));
        holder.subject.setText("subject: "+institutesubject.get(position));
        holder.area.setText("Experience: "+instituteexperience.get(position));
        //holder.availablity.setText("Name: "+institutename.get(position));
        holder.xp.setText(institutexp.get(position)+"Xp");










        // holder.customer_pic.setImageResource(customerpic.get(position));


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,""+institutename.get(position),Toast.LENGTH_SHORT).show();
                Fragment selectFragment=new InstituteSelectFragment();

                //f_manager.beginTransaction().replace(R.id.fragment_container,selectFragment).commit();

            }
        });
    }


    @Override
    public int getItemCount() {
        return institutename.size();
    }

    public  class viewholder extends RecyclerView.ViewHolder{
        TextView name,rating,subject,area,bestat,xp,kmaway,availablity;
        Button letterbtn;
        public viewholder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.allinsitutename);
            rating=(TextView)itemView.findViewById(R.id.allinsituterating);
            subject=itemView.findViewById(R.id.allinsitutesubject);
            area=itemView.findViewById(R.id.allinsitutearea);

            xp=itemView.findViewById(R.id.allinsitutexp);






            // customer_pic=(ImageView) itemView.findViewById(R.id.doctor_pic);

            Log.d("TAAAAG","kk");

        }

    }

}

