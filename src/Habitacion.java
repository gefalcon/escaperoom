
public class Habitacion {
	private int cod_hab;
	private String nombre, url;
	
	Habitacion(int cod_hab, String nombre, String url) {
		this.cod_hab = cod_hab;
		this.nombre = nombre;
		this.url = url;
	}

	public int getCod_hab() {
		return cod_hab;
	}

	public String getNombre() {
		return nombre;
	}

	public String getUrl() {
		return url;
	}
	
}
