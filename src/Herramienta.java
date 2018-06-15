
public class Herramienta {
	private int cod_herr;
	private String nombre, descrip;
	private boolean pista;
	
	Herramienta(int cod_herr, String nombre, String descrip, boolean pista) {
		this.cod_herr = cod_herr;
		this.nombre = nombre;
		this.descrip = descrip;
		this.pista = pista;
	}
	public int getCod_herr() {
		return cod_herr;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescrip() {
		return descrip;
	}
	public boolean isPista() {
		return pista;
	}	
}
