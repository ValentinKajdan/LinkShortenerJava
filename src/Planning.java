import java.util.ArrayList;

public class Planning {

	private String nomProf;
	private ArrayList<Visite> ListeVisites;
	
	
	/** 
	 * Constructeur
	 * @param nomProf
	 * @param ListeVisites
	 */
	public Planning(String nomProf, ArrayList<Visite> ListeVisites) {
		this.nomProf = nomProf;
		this.ListeVisites = ListeVisites;
	}
	
	/**
	 * Retourne le nom du prof
	 * 
	 * @return un string
	 */
	public String getNomProf() {
		return nomProf;
	}
	
	/**
	 * Met à jour le nom du prof
	 * 
	 * @param nomProf
	 * Le nouveau nom du prof
	 */
	public void setNomProf(String nomProf) {
		this.nomProf = nomProf;
	}	
	/**
	 * Retourne la liste des visites
	 * 
	 * @return une ArrayList de visites
	 */
	public ArrayList<Visite> getListeVisites() {
		return ListeVisites;
	}
	
	/**
	 * Met à jour la liste de visites
	 * 
	 * @param listeVisites
	 * La nouvelle liste de visites
	 */
	public void setListeVisites(ArrayList<Visite> listeVisites) {
		ListeVisites = listeVisites;
	}
	
	/** 
	 * Ajouter une visite à partir d'un objet visite
	 * 
	 * @param v
	 * La viste à ajouter
	 * 
	 */
	public void ajouterVisite(Visite v) {
		Visite.setNbVisites(Visite.getNbVisites() + 1);
		this.ListeVisites.add(v);
	}
	
	/**
	 * Supprimer une visite à partir d'un objet visite
	 * 
	 * @param v
	 * La visite à supprimer
	 */
	public void supprimerVisite(Visite v) {
		Visite.setNbVisites(Visite.getNbVisites()-1);
		// On incrémente le nombre de visites
		this.ListeVisites.remove(v);		
	}
	
	/**
	 * Supprimer une visite à partir de l'étudiant
	 * 
	 * @param nomEtudiant
	 * Le nom de l'étudiant qui doit matcher avec la viste à supprimer
	 */
	public void supprimerVisite(String nomEtudiant) {
		for (int i = 0; i < this.getListeVisites().size(); i++) {
			int result = nomEtudiant.compareTo(this.getListeVisites().get(i).getNomEtudiant());
			// Si les deux noms correspondent alors on supprimer à l'index i
			if (result == 0) {
				this.ListeVisites.remove(i);
				// On décrémente le nombre de visites
				Visite.setNbVisites(Visite.getNbVisites()-1);
			}					
		}
	}
	
	/**
	 *  Afficher les informations sur le planning
	 *  
	 *  @return un string de toutes les informations
	 */
	public String toString() {
		String intro = "Planning de "+this.getNomProf()+" :\n";
		String visites = "";
		int nbVisites = 0;
		// S'il n'y a pas de visites
		if (this.getListeVisites().isEmpty()) {
			visites = "Aucune visites de prévues\n";
		} else {
			// S'il y a des visites, on boucle la liste
			for (int i = 0; i < this.getListeVisites().size(); i++) {
				visites += "Visite : ";
				visites += this.getListeVisites().get(i);
				visites += "\n";
				visites += "CR : "+this.getListeVisites().get(i).afficherCR();
				visites += "\n";
				nbVisites = Visite.getNbVisites();
			}			
		}
		visites += "Nombre de visites : "+nbVisites;
		visites += "\n";
				
		return intro + visites;
	}
}
