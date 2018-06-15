import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Usuario {
	private  String user, passwd, nombre, apellido, correo;
	
	Usuario(){
		this.user = "";
		this.passwd = "";
		this.nombre = "";
		this.apellido = "";
		this.correo = "";
	}
	Usuario(String user, String passwd, String nombre, String apellido, String correo) {
		this.user = user;
		this.passwd = passwd;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
	}
	
	Usuario(String user, String passwd) throws SQLException {
		this.user = user;
		this.passwd = passwd;
		this.nombre = "";
		this.apellido = "";
		this.correo = "";		
	}
	public void login(JFrame inicio, JFrame ventana, Connection con) throws SQLException{
		if(existe(con, "select * from usuario where user = '"+user+"' and passwd = '"+passwd+"'")){
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select nombre, apellido, correo from usuario where user = '"+user+"'");
			rs.first();
			this.nombre = rs.getString("nombre");
			this.apellido = rs.getString("apellido");
			this.correo = rs.getString("correo");
			inicio.setVisible(false);
			ventana.setVisible(true);			
		}
		else JOptionPane.showMessageDialog(inicio, "Los datos introducidos son incorrectos");
	}
	private boolean existe(Connection con, String query) throws SQLException{
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(query);
		return rs.next();
	}
	public void almacenarDatos(JFrame inicio,JFrame ventana, Connection con) throws SQLException{
		Statement stm = con.createStatement();
		if(existe(con, "select * from usuario where user = '"+user+"'") == false){
			stm.executeUpdate("insert into usuario values('"+user+"','"+passwd+"','"+nombre+"','"+apellido+"','"+correo+"')");
			inicio.setVisible(false);
			ventana.setVisible(true);
		}
		else JOptionPane.showMessageDialog(inicio, "El usuario que intenta registrar ya existe");
	}
	public String getUser() {
		return user;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public String getCorreo() {
		return correo;
	}
}
