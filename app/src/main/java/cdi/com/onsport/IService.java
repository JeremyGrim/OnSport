package cdi.com.onsport;

import java.util.Date;
import java.util.List;

public interface IService {

	Utilisateur authenticate(String email, String password);
	
	Utilisateur register(Utilisateur utilisateur);
	
	List<Activites> getListActivity(String codepostal, Date debut, Date fin);
	
	List<Activites> getListActivity(String codepostal, Date debut, Date fin, Integer num);

	Activites getActivity(int id);

    List<Activites> activitiesByDayAndLieu(Date date,String str);

    List<Activites> activitiesByLast(int i);

    List<Activites> activitiesByLieu(String lieu);

    List<Activites> activitiesByTypeAndLieu(String type,String lieu);

    Activites addActivity(Activites activite);

    List<Utilisateur> details(String str);

    int getParticipantSize(int i);

    String joinActivity(int i,int j);

	
}
