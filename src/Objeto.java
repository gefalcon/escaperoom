
public class Objeto {
	private int con_obj;
	private String nombre, descrip;
	private int cod_hab, contiene, interactua;
	
	public Objeto(int con_obj, String nombre, String descrip, int cod_hab, int contiene, int interactua) {
		this.con_obj = con_obj;
		this.nombre = nombre;
		this.descrip = descrip;
		this.cod_hab = cod_hab;
		this.contiene = contiene;
		this.interactua = interactua;
	}

	public int getCon_obj() {
		return con_obj;
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
