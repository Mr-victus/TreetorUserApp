package dev.cavemen.treetoruser;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class InstituteSelectFragment extends Fragment {


    public InstituteSelectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_institute_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView bottomNavigationView=view.findViewById(R.id.bottomnavbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedfragment=null;
            switch (menuItem.getItemId()) {
                case R.id.profile:
                    selectedfragment = new InstituteSelectedProfileFragment();
                    break;
                case R.id.courses:
                    // selectedfragment = new ClassroomFragment();
                    break;
                case R.id.teachers:
                    selectedfragment=new InstituteSelectTeacherFragment();
            }
            FragmentManager f_manager;
            f_manager=getFragmentManager();
            f_manager.beginTransaction().replace(R.id.selected_fragment_container,selectedfragment).commit();
            return true;
        }

    };
}
