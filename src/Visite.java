
public class Visite extends RDV {
	
	private String nomEtudiant;
	private String date;
	private int note;
	private String compteRendu;

	static int nbVisites = 0;
	
	/**
	 * Constructeur
	 * 
	 * @param nomEtudiant
	 * @param date
	 * @param note
	 * @param compteRendu
	 */
	public Visite(String nomEtudiant, String date, int note, String compteRendu) {
		super(compteRendu);
		this.nomEtudiant = nomEtudiant;
		this.date = date;
		this.note = note;
	}
	
	/**
	 * Retourne le nom de l'étudiant
	 * 
	 * @return un string
	 */
	public String getNomEtudiant() {
		return nomEtudiant;
	}
	
	/**
	 * Met à jour le nom de l'étudiant
	 * 
	 * @param nomEtudiant
	 * Le nouveau nom de l'étudiant
	 */
	public void setNomEtudiant(String nomEtudiant) {
		this.nomEtudiant = nomEtudiant;
	}
	
	/**
	 * Retourne la date de la visite
	 * 
	 * @return un string
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Met à jour la date de la visite
	 * 
	 * @param date
	 * La nouvelle date de la visite
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Retourne la note de l'étudiant
	 * 
	 * @return un int
	 */
	public int getNote() {
		return note;
	}
	
	/**
	 * Met à jour la nouvelle note de l'étudiant
	 * 
	 * @param note
	 * La nouvelle note de l'étudiant
	 */
	public void setNote(int note) {
		this.note = note;
	}
	
	/**
	 * Retourne le compte rendu du rdv
	 * 
	 * @return un string
	 */
	public String getCompteRendu() {
		return compteRendu;
	}
	
	/**
	 * Met à jour le nouveau comtpe rendu
	 * 
	 * @param compteRendu
	 * Le nouveau compte rendu
	 */
	public void setCompteRendu(String compteRendu) {
		this.compteRendu = compteRendu;
	}
	
	/**
	 * Retourne le nombre de visites
	 * 
	 * @return un int
	 */
	public static int getNbVisites() {
		return nbVisites;
	}
	
	/**
	 * Met à jour le nombre de visites
	 * 
	 * @param nbVisites
	 * Le nouveau nombre de visites
	 */
	public static void setNbVisites(int nbVisites) {
		Visite.nbVisites = nbVisites;
	}	
	
	/**
	 *  Afficher les informations sur la visite
	 *  
	 *  @return un string de toutes les informations
	 */
	public String toString() {
		return this.getNomEtudiant()+", "+this.getDate()+", "+this.getNote();
	}
	
	/**
	 *  Affiche le CR de la visite
	 *  
	 *  @return un string
	 */
	public String afficherCR() {
		return super.getCompteRendu();
	}
}
