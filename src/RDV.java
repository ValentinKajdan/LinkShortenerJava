public class RDV {

	private String compteRendu;
	
	/**
	 * Constructeur
	 * 
	 * @param compteRendu
	 */
	public RDV(String compteRendu) {
		this.compteRendu = compteRendu;
	}

	/**
	 * Retourne le compte rendu
	 * 
	 * @return un string
	 */
	public String getCompteRendu() {
		return compteRendu;
	}

	/**
	 * Met à jour le compte rendu
	 * 
	 * @param compteRendu
	 * Le nouveau compte rendu de la visite
	 */
	public void setCompteRendu(String compteRendu) {
		this.compteRendu = compteRendu;
	}
	
	/**
	 * Retourne le CR
	 * 	
	 * @return un string
	 */
	public String afficherCR() {
		return "RDV="+this.getCompteRendu();
	}
}
