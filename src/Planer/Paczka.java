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

	private Vertex adresNadawcy;
	private Vertex adresOdbiorcy;
	
	
	public Paczka(String id,String AIDKlienta, Vertex adresNadawcy, Vertex adresodbiorcy) {
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
	public Vertex getAdresodbiorcy() {
		return adresOdbiorcy;
	}
	public void setAdresodbiorcy(Vertex adresodbiorcy) {
		this.adresOdbiorcy = adresodbiorcy;
	}
	public Vertex getAdresNadawcy() {
		return adresNadawcy;
	}
	public void setAdresNadawcy(Vertex adresNadawcy) {
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
