package cdi.com.onsport.ActivityController;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

import cdi.com.onsport.Activites;
import cdi.com.onsport.Home;
import cdi.com.onsport.MyContext.ActivityAdapter;
import cdi.com.onsport.MyExterneServices;
import cdi.com.onsport.R;

public class ListeActivity extends AppCompatActivity {

    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        Slide slideOut = new Slide(Gravity.LEFT);
        getWindow().setExitTransition(slideOut);
        Slide slideIn = new Slide(Gravity.RIGHT);
        getWindow().setEnterTransition(slideIn);

        mListView = (ListView) findViewById(R.id.liste);

        MyExterneServices es = new MyExterneServices(false);

        List<Activites> activityList = es.getListActivity("59000", new Date(), new Date());

        ActivityAdapter adapter = new ActivityAdapter(ListeActivity.this, activityList);
        mListView.setAdapter(adapter);

        final ImageView connexion = (ImageView) findViewById(R.id.homeLink);
        connexion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeActivity.this, Home.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ListeActivity.this).toBundle();
                startActivity(intent, bundle);
            }
        });




    }


}

