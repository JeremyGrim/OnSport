package cdi.com.onsport.userAccount;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cdi.com.onsport.Home;
import cdi.com.onsport.IService;
import cdi.com.onsport.MyContext.UserHandler;
import cdi.com.onsport.MyExterneServices;
import cdi.com.onsport.R;
import cdi.com.onsport.Utilisateur;


public class SignIn extends AppCompatActivity {
String TAG = "SignIn";
    Boolean ERROR = false;
    Utilisateur session;
    Date newdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        Slide slideOut = new Slide(Gravity.LEFT);
        getWindow().setExitTransition(slideOut);
        Slide slideIn = new Slide(Gravity.RIGHT);
        getWindow().setEnterTransition(slideIn);

        final EditText pseudo = (EditText) findViewById(R.id.pseudo);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText dateNaissance = (EditText) findViewById(R.id.dateNaissance);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText password2 = (EditText) findViewById(R.id.password2);
        final EditText codePostal = (EditText) findViewById(R.id.CodePostal);
        final EditText ville = (EditText) findViewById(R.id.Ville);








        /****************** OnClick EditText date de naissance ***************************/
        //calendrier

        dateNaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


// calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignIn.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text

                                dateNaissance.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });
        /********************** fin onclick date de naissance *************************************/




        Button signUp = (Button) findViewById(R.id.SignUp);
        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String string_pseudo = pseudo.getText().toString();
                String string_email = email.getText().toString();
                String string_password = password.getText().toString();
                String string_password2 = password2.getText().toString();
                String string_codePostal = codePostal.getText().toString();
                String string_ville = ville.getText().toString();
                String string_date = dateNaissance.getText().toString();

                if (string_email.isEmpty()||string_date.isEmpty()||string_password.isEmpty()||string_password2.isEmpty()||string_codePostal.isEmpty()||string_ville.isEmpty()){
                    Toast.makeText(SignIn.this, "Vous devez renseigner tous les champs",
                            Toast.LENGTH_SHORT).show();
                }
                else {

                    IService inscription = new MyExterneServices(false);

                    Utilisateur util = new Utilisateur();

                    /************** on insere le pseudo *************/
                    util.setPseudo(string_pseudo);
                    /************** on verifie le mail *************/
                    Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
                    Matcher m = p.matcher(string_email);
                    if (!m.matches()) {
                        Toast.makeText(SignIn.this, "Vous n'avez pas entré un mail valide",
                                Toast.LENGTH_SHORT).show();
                        ERROR = true;
                    } else {

                        util.setMail(string_email);
                    }


                    /************** on verifie la date de naissance de String a Date *************/


                        // date = new Date(sdf.parse(string_date).getTime());
                    String oldFormat= "dd/MM/yyyy";
                    String newFormat= "yyyy/MM/dd ";

                    String formatedDate ;
                    SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
                    Date myDate = null;
                    try {
                        myDate = dateFormat.parse(string_date);
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }

                    SimpleDateFormat timeFormat = new SimpleDateFormat(newFormat);
                    formatedDate = timeFormat.format(myDate);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    try {
                         newdate = format.parse(formatedDate);

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                        Log.d(TAG,"date = "+newdate);

                    // on insere la date de naissance
                   /* SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                         newdate = format.parse(string_date);

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } */


                    util.setDatedenaissance(newdate);

                    /************** on on verifie les passwords *************/
                    if (string_password.equals(string_password2)) {
                        util.setMotdepasse(string_password);
                    } else {
                        Toast.makeText(SignIn.this, "Les mots de passe ne corespondent pas",
                                Toast.LENGTH_SHORT).show();
                        ERROR=true;
                    }
                    /************** on verifie le cp *************/
                    if (string_codePostal.length() > 5) {
                        Toast.makeText(SignIn.this, "Vous n'avez pas entré un code postal valide",
                                Toast.LENGTH_SHORT).show();
                        ERROR=true;
                    } else {
                        util.setCp(string_codePostal);
                    }
                    /************** on insere la ville *************/
                    util.setVille(string_ville);

                    if (ERROR==false) {
                        Log.d(TAG, "ERROR false = " + ERROR);
                        session = inscription.register(util);

                    }
                    if (session==null) {
                        Toast.makeText(SignIn.this, "Erreur de communication avec la base de donnée",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        UserHandler.getInstance().setUser(session);
                        Intent intent = new Intent(SignIn.this, Home.class);
                        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(SignIn.this).toBundle();
                        startActivity(intent, bundle);
                    }
                }
            }
        });





    }
}
