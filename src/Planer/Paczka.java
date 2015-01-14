package Planer;

import jade.util.leap.Serializable;
import shortest_path.Vertex;

public class Paczka implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String AIDKlienta;

	private String adresNadawcy;
	private String adresOdbiorcy;
	private Vertex town;
	
	
	public Paczka(String id,String AIDKlienta, String adresNadawcy, String adresodbiorcy) {
		super();
		this.setId(id);
		this.setAdresNadawcy(adresNadawcy);
		this.setAdresodbiorcy(adresodbiorcy);
		this.AIDKlienta=AIDKlienta;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdresodbiorcy() {
		return adresOdbiorcy;
	}
	public void setAdresodbiorcy(String adresodbiorcy) {
		this.adresOdbiorcy = adresodbiorcy;
	}
	public String getAdresNadawcy() {
		return adresNadawcy;
	}
	public void setAdresNadawcy(String adresNadawcy) {
		this.adresNadawcy = adresNadawcy;
	}
	
	@Override
	public String toString() {
		return "Paczka [id=" + id + ", AIDKlienta=" + AIDKlienta
				+ ", adresNadawcy=" + adresNadawcy + ", adresOdbiorcy="
				+ adresOdbiorcy + "]";
	}

	public String getAIDKlienta() {
		return AIDKlienta;
	}
	public void setAIDKlienta(String aIDKlienta) {
		AIDKlienta = aIDKlienta;
	}

}
