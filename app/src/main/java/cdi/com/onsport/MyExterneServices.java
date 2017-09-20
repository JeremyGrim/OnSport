package cdi.com.onsport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrateur on 11/09/2017.
 */

public class MyExterneServices implements IService {
    protected boolean prod;
    protected ClientWS cws;

    public MyExterneServices(boolean prod) {
        this.prod = prod;
    }

    @Override
    public Utilisateur authenticate(String email, String password) {
        Date currentDate = Calendar.getInstance().getTime();
        if (!prod) {
            // creation d'un fake pour test
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(1);
            utilisateur.setPseudo("toto");
            utilisateur.setMail("toto@mail.com");
            utilisateur.setMotdepasse("1234");
            utilisateur.setDatedenaissance(currentDate);
            utilisateur.setVille("Lille");
            utilisateur.setCp("59000");
            // retourne le fake si login correct null sinon
            if (email.equals(utilisateur.getMail()) && password.equals(utilisateur.getMotdepasse())) {
                return utilisateur;
            } else {

                return null;
            }
        } else {
            ClientWS cws = new ClientWS();
            return cws.authenticate(email, password);
        }

    }

    @Override
    public Utilisateur register(Utilisateur utilisateur) {
        if (!prod) {
            return utilisateur;
        } else {
            ClientWS cws = new ClientWS();
            return cws.register(utilisateur);
        }
    }

    @Override
    public List<Activites> getListActivity(String codepostal, Date debut, Date fin) {
        List<Activites> la = new ArrayList<>();
        if (!prod) {
            for (int i = 0; i < 10; i++) {
                Activites activite = new Activites("Lille" + i, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), 10 + i,
                        "fouteux de brun", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam elit lorem, luctus non nisi sagittis," +
                        " ultrices facilisis nisl. Maecenas eget purus a justo efficitur vestibulum a vitae turpis. Quisque pretium leo id ultrices" +
                        " tristique. Nullam lacinia suscipit finibus. Aliquam tempus justo neque, a volutpat tellus maximus at. ");
                activite.setActivite("activité :" + i);
                activite.setListe_participants(Integer.toString(5 + i));
                activite.setId(i+1);
                la.add(activite);
            }
            return la;
        } else {
            ClientWS cws = new ClientWS();
            return cws.getListActivity(codepostal, debut, fin);
        }

    }

    @Override
    public List<Activites> getListActivity(String codepostal, Date debut, Date fin, Integer num) {
        List<Activites> la = new ArrayList<>();
        if (!prod) {
            for (int i = 0; i < 10; i++) {
                Activites activite = new Activites("Lille" + i, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), 10 + i,
                        "fouteux de brun", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam elit lorem, luctus non nisi sagittis," +
                        " ultrices facilisis nisl. Maecenas eget purus a justo efficitur vestibulum a vitae turpis. Quisque pretium leo id ultrices" +
                        " tristique. Nullam lacinia suscipit finibus. Aliquam tempus justo neque, a volutpat tellus maximus at. ");
                activite.setActivite("activité :" + i);
                activite.setListe_participants(Integer.toString(5 + i));
                activite.setId(i+1);
                la.add(activite);
            }
            return la;
        } else {
            ClientWS cws = new ClientWS();
            return cws.getListActivity(codepostal, debut, fin, num);
        }
    }

    @Override
    public Activites getActivity(int id) {
        Activites act=new Activites("Lille", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), 10,
                "fouteux de brun", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam elit lorem, luctus non nisi sagittis," +
                " ultrices facilisis nisl. Maecenas eget purus a justo efficitur vestibulum a vitae turpis. Quisque pretium leo id ultrices" +
                " tristique. Nullam lacinia suscipit finibus. Aliquam tempus justo neque, a volutpat tellus maximus at. ");
        act.setListe_participants(Integer.toString(5));
        act.setId(10);
        return act;
    }

    @Override
    public List<Activites> activitiesByDayAndLieu(Date date, String str) {
        return null;
    }

    @Override
    public List<Activites> activitiesByLast(int i) {
        return null;
    }

    @Override
    public List<Activites> activitiesByLieu(String lieu) {
        return null;
    }

    @Override
    public List<Activites> activitiesByTypeAndLieu(String type, String lieu) {
        return null;
    }

    @Override
    public Activites addActivity(Activites activite) {
        return null;
    }

    @Override
    public List<Utilisateur> details(String str) {
        return null;
    }

    @Override
    public int getParticipantSize(int i) {
        return 0;
    }

    @Override
    public String joinActivity(int i, int j) {
        return null;
    }
}
