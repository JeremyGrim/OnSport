package cdi.com.onsport;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrateur on 11/09/2017.
 */

public class ClientWS implements IService {
    private final String wsUrl="http://192.168.23.117:8888/ws/dao";
    private final String soapUurl="http://localhost:9998/service/";
    @Override
    public Utilisateur authenticate(String email, String password) {
        return null;
    }

    @Override
    public Utilisateur register(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public List<Activites> getListActivity(String codepostal, Date debut, Date fin) {
        return null;
    }

    @Override
    public List<Activites> getListActivity(String codepostal, Date debut, Date fin, Integer num) {
        return null;
    }

    @Override
    public Activites getActivity(int id) {
        return null;
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
