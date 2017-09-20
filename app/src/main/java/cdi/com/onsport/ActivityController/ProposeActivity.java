package cdi.com.onsport.ActivityController;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cdi.com.onsport.Activites;
import cdi.com.onsport.IService;
import cdi.com.onsport.MyExterneServices;
import cdi.com.onsport.R;

public class ProposeActivity extends AppCompatActivity {

    EditText titre, lieu, date, activity, nbrParticipants, commentaire;
    String titre1;
    String TAG = "ProposeActivity";
    Button SignUp;
    int id_activity;
    Date newdate=null;
    int int_nombreParticipants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propose);

        titre = (EditText) findViewById(R.id.titre);
        date = (EditText) findViewById(R.id.Date);
        lieu = (EditText) findViewById(R.id.lieu);
        activity= (EditText) findViewById(R.id.activity);
        nbrParticipants = (EditText) findViewById(R.id.nbrParticipants);
        commentaire = (EditText) findViewById(R.id.Comment);





        /****************** OnClick EditText date  ***************************/
        //calendrier

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


// calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(ProposeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text

                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });
        /********************** fin onclick date de naissance *************************************/



        final String string_titre = titre.getText().toString();
        final String string_date = date.getText().toString();
        final String string_lieu = lieu.getText().toString();
        final String string_activity = activity.getText().toString();
        final String string_nbrParticipants = nbrParticipants.getText().toString();
        final String string_commentaire = commentaire.getText().toString();
        try {
            int_nombreParticipants = Integer.parseInt(string_nbrParticipants);
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }



        Button add = (Button) findViewById(R.id.AddActivity);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (string_titre.isEmpty()||string_date.isEmpty()||string_lieu.isEmpty()||string_activity.isEmpty()||string_nbrParticipants.isEmpty()||string_commentaire.isEmpty()){
                    Toast.makeText(ProposeActivity.this, "Vous devez renseigner tous les champs",
                            Toast.LENGTH_SHORT).show();
                }
                else {



/***************************************************************************/
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



                    /********************************************************************/

                    Activites myActivity = new Activites(string_titre,string_lieu,newdate,int_nombreParticipants,string_activity,string_commentaire);
                    IService add_activity = new MyExterneServices(false);
                    //int id_activity = add_activity.AJOUTERACTIVITE(myActivity);
                    Intent intent = new Intent(ProposeActivity.this, MyActivity.class);
                    intent.putExtra("id", id_activity);
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ProposeActivity.this).toBundle();
                    startActivity(intent, bundle);
                }
            }


        });
    }



}