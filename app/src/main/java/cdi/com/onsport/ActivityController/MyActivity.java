package cdi.com.onsport.ActivityController;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cdi.com.onsport.Activites;
import cdi.com.onsport.Home;
import cdi.com.onsport.MyContext.UserHandler;
import cdi.com.onsport.MyExterneServices;
import cdi.com.onsport.R;

public class MyActivity extends AppCompatActivity {

    private int id;
    ViewGroup TransitionContainer;
    Button subscribe;
    TextView confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        TransitionContainer = (ViewGroup) findViewById(R.id.r);
        confirm = (TextView) TransitionContainer.findViewById(R.id.confirm);

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", 0);
            MyExterneServices activity = new MyExterneServices(false);
            Activites thisactivity = activity.getActivity(id);
            setDisplay(thisactivity);
        }






        final ImageView home = (ImageView) findViewById(R.id.homeLink);
        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, Home.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MyActivity.this).toBundle();
                startActivity(intent, bundle);


            }
        });

        subscribe = (Button) TransitionContainer.findViewById(R.id.subscribe);
        subscribe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (confirm.getVisibility() == View.GONE ){

                    int userid = UserHandler.getInstance().getUser().getId();
                    MyExterneServices joinActivity = new MyExterneServices(false);
                    joinActivity.joinActivity(id, userid);
                    subscribe.setText("Retourner sur la liste");

                    MyExterneServices activity = new MyExterneServices(false);
                    Activites thisactivity = activity.getActivity(id);
                    String nbr = thisactivity.getListe_participants();
                    int nbrint= Integer.parseInt(nbr);
                    TextView nbrparticipants = (TextView) findViewById(R.id.nbrparticipants);
                    nbrparticipants.setText(Integer.toString(nbrint + 1));

                    TransitionManager.beginDelayedTransition(TransitionContainer);
                    confirm.setVisibility(View.VISIBLE);
                    /*Snackbar.make(view, "Vous avez bien été enregistré", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/


                }







            }
        });




    }

    private void setDisplay(Activites thisactivity) {
        TextView activityType,lieu, date, commentaire, nbrparticipants, maxpartipants;

        activityType = (TextView) findViewById(R.id.activityType);
        lieu = (TextView) findViewById(R.id.lieu);
        date = (TextView) findViewById(R.id.date);
        commentaire = (TextView) findViewById(R.id.commentaire);
        nbrparticipants = (TextView) findViewById(R.id.nbrparticipants);
        maxpartipants = (TextView) findViewById(R.id.maxpartipants);

        activityType.setText(thisactivity.getActivite());
        lieu.setText(thisactivity.getLieu());
        date.setText(thisactivity.getDate_debut().toString());
        commentaire.setText(thisactivity.getMessages());
        nbrparticipants.setText(thisactivity.getListe_participants());
        maxpartipants.setText(Integer.toString(thisactivity.getNbr_participants()));

    }


}
