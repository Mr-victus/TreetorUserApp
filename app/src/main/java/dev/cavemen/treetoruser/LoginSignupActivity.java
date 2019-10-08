package dev.cavemen.treetoruser;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginSignupActivity extends AppCompatActivity {

    int log=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        final TextView login=findViewById(R.id.login);

        final TextView signup=findViewById(R.id.signup);

        Fragment fragment2=new SignupFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.login_fragment_container,fragment2).commit();
        login.setVisibility(View.INVISIBLE);


//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(log==0)
//                {
//                    log=1;
//                    login.setTextColor(Color.rgb(255,255,255));
//                    signup.setTextColor(Color.rgb(193,198,193));
//
//                    Fragment fragment1=new LoginFragment();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.login_fragment_container,fragment1).commit();
//
//
//
//                }
//
//            }
//        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(log==1)
                {
                    log=0;
                    login.setTextColor(Color.rgb(193,198,193));
                    signup.setTextColor(Color.rgb(255,255,255));

                    Fragment fragment=new SignupFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.login_fragment_container,fragment).commit();

                }
            }
        });




    }


}
