package cdi.com.onsport;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cdi.com.onsport.MyContext.UserHandler;
import cdi.com.onsport.userAccount.SignIn;

public class MainActivity extends AppCompatActivity {

    ViewGroup TransitionContainer;
    private EditText email, password ;
    private TextView loginError;
    private Button abort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TransitionContainer = (ViewGroup) findViewById(R.id.r);

        email = (EditText) TransitionContainer.findViewById(R.id.email);
        password = (EditText) TransitionContainer.findViewById(R.id.password);
        loginError = (TextView) TransitionContainer.findViewById(R.id.loginError);
        abort = (Button) TransitionContainer.findViewById(R.id.abort);

        Slide slideOut = new Slide(Gravity.LEFT);
        getWindow().setExitTransition(slideOut);

        final Button signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignIn.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
                startActivity(intent, bundle);
            }
        });

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (email.getVisibility() == View.GONE && password.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(TransitionContainer);
                    email.setVisibility(View.VISIBLE);
                    password.setVisibility(View.VISIBLE);
                    signIn.setVisibility(View.GONE);
                    abort.setVisibility(View.VISIBLE);

                } else {
                    final String Email = email.getText().toString();
                    final String Password = password.getText().toString();
                    MyExterneServices login = new MyExterneServices(false);
                    Utilisateur  utilisateur = login.authenticate(Email, Password);
                    if (utilisateur != null ) {
                        UserHandler.getInstance().setUser(utilisateur);
                        Intent intent = new Intent(MainActivity.this, Home.class);
                        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
                        startActivity(intent, bundle);



                    } else {
                        TransitionManager.beginDelayedTransition(TransitionContainer);
                        loginError.setVisibility(View.VISIBLE);


                        /*Toast toast = Toast.makeText(getApplicationContext(),  email.getText().toString(), Toast.LENGTH_SHORT);
                        toast.show();*/


                    }
                }
            }
        });

        final Button abort = (Button) findViewById(R.id.abort);
        abort.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    TransitionManager.beginDelayedTransition(TransitionContainer);
                    email.setVisibility(View.GONE);
                    password.setVisibility(View.GONE);
                    signIn.setVisibility(View.VISIBLE);
                    abort.setVisibility(View.GONE);
                    loginError.setVisibility(View.GONE);


            }
        });


    }
}
