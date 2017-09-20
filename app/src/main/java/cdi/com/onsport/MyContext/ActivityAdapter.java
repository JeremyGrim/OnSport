package cdi.com.onsport.MyContext;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cdi.com.onsport.Activites;
import cdi.com.onsport.ActivityController.MyActivity;
import cdi.com.onsport.R;

/**
 * Created by Administrateur on 12/09/2017.
 */

public class ActivityAdapter extends ArrayAdapter<Activites> {

    public ActivityAdapter(Context context, List<Activites> activity) {

        super(context, 0, activity);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.model_liste_activity,parent, false);
        }

        ActivityViewHolder viewHolder = (ActivityViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ActivityViewHolder();
            viewHolder.activite = (TextView) convertView.findViewById(R.id.activityType);
            viewHolder.participants = (TextView) convertView.findViewById(R.id.nbrpartipants);
            viewHolder.nbr_participants = (TextView) convertView.findViewById(R.id.Maxparticipants);
            viewHolder.lieu = (TextView) convertView.findViewById(R.id.lieu);
            viewHolder.date_debut = (TextView) convertView.findViewById(R.id.date);
            viewHolder.images = (ImageView) convertView.findViewById(R.id.picture);
            viewHolder.link = (LinearLayout) convertView.findViewById(R.id.link);
            convertView.setTag(viewHolder);
        }

        final Activites activite = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.activite.setText(activite.getActivite());
        viewHolder.lieu.setText(activite.getLieu());
        viewHolder.date_debut.setText(activite.getDate_debut().toString());
        viewHolder.participants.setText(activite.getListe_participants());
        viewHolder.nbr_participants.setText(Integer.toString(activite.getNbr_participants()));



        viewHolder.images.setImageResource(activite.getImages());
        viewHolder.link.setOnClickListener(new View.OnClickListener()  {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyActivity.class);
                intent.putExtra("id", activite.getId());
                getContext().startActivity(intent);


            }
        });

        return convertView;
    }



    private class ActivityViewHolder{
        public TextView lieu;
        public TextView date_debut;
        public TextView nbr_participants, participants;
        public TextView activite;
        public ImageView images;
        public LinearLayout link;
    }
}
