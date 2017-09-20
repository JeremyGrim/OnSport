package cdi.com.onsport.userAccount;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cdi.com.onsport.Home;
import cdi.com.onsport.MyContext.UserHandler;
import cdi.com.onsport.R;

import static cdi.com.onsport.R.id.textViewDDdn;

public class MonCompte extends AppCompatActivity {
    TextView Email;
    String email;
    TextView textViewPPseudo;
    String pseudo ;
    TextView textViewVVille;
    String ville;
    TextView textViewCCP;
    String ccp;
    TextView textViewCComm;
    String comm;
    Button modifier;
    String reportDate;
    Date newdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_compte);

        Slide slideOut = new Slide(Gravity.LEFT);
        getWindow().setExitTransition(slideOut);
        Slide slideIn = new Slide(Gravity.RIGHT);
        getWindow().setEnterTransition(slideIn);

        //commentaire
        textViewCComm = (TextView) findViewById(R.id.textViewCComm);
        comm = UserHandler.getInstance().getUser().getCommentaire();
        textViewCComm.setText(comm);
        // code postal
        textViewCCP = (TextView)findViewById(R.id.textViewCCP);
        ccp = UserHandler.getInstance().getUser().getCp();
        textViewCCP.setText(ccp);
        //pseudo
        textViewPPseudo = (TextView)findViewById(R.id.textViewPPseudo);
        pseudo =  UserHandler.getInstance().getUser().getPseudo();
        textViewPPseudo.setText(pseudo);
        //ville
        textViewVVille = (TextView) findViewById(R.id.textViewVVille);
        ville = UserHandler.getInstance().getUser().getVille();
        textViewVVille.setText(ville);
        // email
        Email = (TextView)findViewById(R.id.Mail);
        email =  UserHandler.getInstance().getUser().getMail();
        Email.setText(email);

        TextView birthdate =(TextView) findViewById(textViewDDdn);
        Date date_birthdate = UserHandler.getInstance().getUser().getDatedenaissance();

        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        reportDate = df.format(date_birthdate);
        String oldFormat= "EEE MMM dd HH:mm:ss zzz yyyy";
        String newFormat= "dd/MM/yyyy ";

        String formatedDate ;
        SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
        Date myDate = null;
        try {
            myDate = dateFormat.parse(reportDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat(newFormat);
        formatedDate = timeFormat.format(myDate);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            newdate = format.parse(formatedDate);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        reportDate = df.format(newdate);


        String TAG = "MON COMPTE";
        Log.d(TAG,""+reportDate);
        birthdate.setText(reportDate);



        modifier = (Button) findViewById(R.id.modifier);
        modifier.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonCompte.this, UserModifier.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MonCompte.this).toBundle();
                startActivity(intent, bundle);
            }

        });

        final ImageView connexion = (ImageView) findViewById(R.id.homeLink);
        connexion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonCompte.this, Home.class);
                startActivity(intent);
            }
        });
    }
}
