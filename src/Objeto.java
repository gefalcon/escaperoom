
public class Objeto {
	private int cod_obj;
	private String nombre, descrip;
	private int cod_hab, contiene, interactua;
	
	Objeto(int cod_obj, String nombre, String descrip, int cod_hab, int contiene, int interactua) {
		this.cod_obj = cod_obj;
		this.nombre = nombre;
		this.descrip = descrip;
		this.cod_hab = cod_hab;
		this.contiene = contiene;
		this.interactua = interactua;
	}

	public int getCod_obj() {
		return cod_obj;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescrip() {
		return descrip;
	}

	public int getCod_hab() {
		return cod_hab;
	}

	public int getContiene() {
		return contiene;
	}

	public int getInteractua() {
		return interactua;
	}
	
	
}
