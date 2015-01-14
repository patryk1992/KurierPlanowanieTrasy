package Planer;

public class Package {
	
	private String id;
	private String adresNadawcy;
	private String adresOdbiorcy;
	
	public Package(String id, String adresNadawcy, String adresodbiorcy) {
		super();
		this.setId(id);
		this.setAdresNadawcy(adresNadawcy);
		this.setAdresodbiorcy(adresodbiorcy);
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
	
	

	
}
