package cdi.com.onsport;

import java.io.Serializable;
import java.util.Date;


public class Activites implements Serializable {

	private static final long serialVersionUID = 1L;


	private int id;

	private String titre;

	private String lieu;

	private Date date_debut;
	
	private Date date_fin;

	private int nbr_participants;

	private String activite;
	
	private String liste_participants;
	
	private String status;
	
	private String messages;
	
	private int images;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public int getNbr_participants() {
		return nbr_participants;
	}
	public void setNbr_participants(int nbr_participants) {
		this.nbr_participants = nbr_participants;
	}

	public String getActivite() {
		return activite;
	}
	public void setActivite(String activite) {
		this.activite = activite;
	}

	public String getListe_participants() {
		return liste_participants;
	}
	public void setListe_participants(String liste_participants) {
		this.liste_participants = liste_participants;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}

	public int getImages() {
		return images;
	}
	public void setImages(int images) {
		this.images = images;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Activites(String lieu, Date date_debut, Date date_fin, int nbr_participants, String activite,
			String messages) {
		super();
		this.lieu = lieu;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.nbr_participants = nbr_participants;
		this.activite = activite;
		this.messages = messages;
	}


	public Activites(String titre,String lieu, Date date_debut, int nbr_participants, String activite,String messages) {
		super();
		this.titre = titre ;
		this.lieu = lieu;
		this.date_debut = date_debut;
		this.nbr_participants = nbr_participants;
		this.activite = activite;
		this.messages = messages;


	}
}
