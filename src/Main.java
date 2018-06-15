
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class Main {
	static int habActual, ultiHab;
	static Habitacion hab;
	static Usuario usu;
	static Objeto[] obj;
	static Herramienta[] herr;
	static Inventario inv0[];
	static Inventario inv1[];
	static Objeto objActual;
	static Inventario invActual;
	
	private static Connection conexion() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		return (DriverManager.getConnection("jdbc:mysql://localhost/escaperoom", "root", "root"));
	}
	private static void rellenarHab(Connection con) throws SQLException{
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery("select * from habitacion where cod_hab = "+habActual+"");
		rs.first();
		hab = new Habitacion(rs.getInt("cod_hab"), rs.getString("nombre"), rs.getString("url"));
	}
	private static void rellenarObjeto(Connection con) throws SQLException{
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery("select count(cod_obj) as count from objeto where cod_hab = "+habActual+"");
		rs.first();
		int dim = rs.getInt("count");
		obj = new Objeto[dim];
		int i = 0;		
		rs = stm.executeQuery("select * from objeto where cod_hab = "+habActual+"");
		while(rs.next()){
			obj[i] = new Objeto(rs.getInt("cod_obj"), rs.getString("nombre"), rs.getString("descrip"), rs.getInt("cod_hab"), rs.getInt("contiene"), rs.getInt("interactua"));
			i++;
		}
	}
	private static void rellenarHerr(Connection con) throws SQLException{
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery("select count(cod_herr) as count from herramienta where cod_herr in (select contiene from objeto where cod_hab = "+habActual+")");
		rs.first();
		int dim = rs.getInt("count");
		herr = new Herramienta[dim];
		int i = 0;		
		rs = stm.executeQuery("select * from herramienta where cod_herr in (select contiene from objeto where cod_hab = "+habActual+")");
		boolean pista;
		while(rs.next()){
			if(rs.getInt("pista") == 0)
				pista = false;
			else pista = true;
			herr[i] = new Herramienta(rs.getInt("cod_herr"), rs.getString("nombre"), rs.getString("descrip"), pista);
			i++;
		}
	}
	private static boolean existe(Connection con, String query) throws SQLException{
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(query);
		return rs.next();
	}
	private static void rellenarInventario(Connection con, int pista) throws SQLException{
		if(existe(con, "select * from inventario where user = '"+usu.getUser()+"' and pista = "+pista+"")){
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select count(cod_herr) as count from inventario where user = '"+usu.getUser()+"' and pista = "+pista+"");
			rs.first();
			if(pista == 0)
				inv0 = new Inventario[rs.getInt("count")];
			else inv1 = new Inventario[rs.getInt("count")];
			int i = 0;
			rs = stm.executeQuery("select * from inventario where user = '"+usu.getUser()+"' and pista = "+pista+"");
			while(rs.next()){
				if(pista == 0)
					inv0[i] = new Inventario(rs.getInt("cod_herr"), rs.getInt("pista"));
				else inv1[i] = new Inventario(rs.getInt("cod_herr"), rs.getInt("pista"));
				i++;
			}
		}
	}
	private static void mostrarObj(JTextArea txt, boolean f){
		if(f)
			txt.setText(hab.getNombre().toUpperCase() + ": ");
		else txt.setText(txt.getText() + "\n\n" + hab.getNombre().toUpperCase() + ": ");
		for(int i = 0; i < obj.length; i++){
			txt.setText(txt.getText() + "\n\n" + (i + 1) + ".- " + obj[i].getNombre());
		}
	}
	@SuppressWarnings("unused")
	private static String getStringDB(Connection con, String query) throws SQLException{
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(query);
		rs.first();
		return rs.getString(1);
	}
	private static int getIntDB(Connection con, String query) throws SQLException{
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(query);
		rs.first();
		return rs.getInt(1);
	}
	private static void enableDisableEnviar(JTextField x, JButton y, boolean valor){
		x.setEnabled(valor);
		y.setEnabled(valor);
	}
	private static int pedirOpc(JTextField teclado, int max){
		String num = teclado.getText();
		if(num.matches("[1-"+max+"]{1}"))
			return Integer.parseInt(num);
		else return 0;
	}
	private static boolean existeEnInv(Inventario[] inv, int cod){
		boolean enc = false;
		if(inv == null)
			return enc;
		else{
			for(int i = 0; i < inv.length && enc == false; i++){
				if(inv[i].getCod_herr() == cod)
					enc = true;
			}
			return enc;
		}
	}
	private static String getPronombre(String cad){
		if(cad.charAt((cad.length() - 1)) == 'a'){
			return "de la";
		}
		else return "del";
	}
	private static Herramienta buscarHerr(int cod){ //Posible fallo si no el cod que le paso no existe en la habitacion actual
		boolean enc = false;
		int j = 0;
		for(int i = 0; i < herr.length && enc == false; i++){
			if(herr[i].getCod_herr() == cod){
				j = i;
				enc = true;
			}
		}
		return herr[j];
	}
	private static void encontrarObjeto(JTextArea txt, Connection con, Herramienta contiene, int pista) throws SQLException{
		if(existe(con, "select * from inventario where cod_herr = "+contiene.getCod_herr()+" and user = '"+usu.getUser()+"'")){
			if(pista == 0)
				txt.setText(txt.getText() + "\n\n" + "Aquí había: " + contiene.getNombre() + "\n\nLo tienes guardado en tu inventario.");
			else txt.setText(txt.getText() + "\n\n" + "Aquí había: " + contiene.getNombre() + "\n\nLo tienes guardado en tu libreta de notas.");
		}
		else {
			Statement stm = con.createStatement();
			stm.executeUpdate("insert into inventario values('"+usu.getUser()+"',"+contiene.getCod_herr()+", "+pista+")");
			rellenarInventario(con, pista);
			if(pista == 0)
				txt.setText(txt.getText() + "\n\n" + "Has encontrado: " + contiene.getNombre() + "\n\nLo has guardado en tu inventario.");
			else txt.setText(txt.getText() + "\n\n" + "Has encontrado: " + contiene.getNombre() + "\n\nLo has guardado en tu libreta de notas.");
		}
	}
	private static void interactuarObjeto(Connection con, Frames window, JTextArea txt, Objeto x, int cod_herr) throws SQLException, IOException{
		Herramienta contiene;
		txt.setText(x.getDescrip());
		if(x.getInteractua() == 0){ //SI NO INTERACTUA
			if(x.getContiene() == 0){
				txt.setText(txt.getText() + "\n\n" + "El objeto no contiene nada útil");
				mostrarObj(window.getTexto(), false);
			}
			else{
				contiene = buscarHerr(x.getContiene());
				if(contiene.isPista())
					encontrarObjeto(txt, con, contiene, 1);		
				else
					encontrarObjeto(txt, con, contiene, 0);			
				mostrarObj(window.getTexto(), false);
			}
		}
		else { //SI INTERACTUA
			if(existeEnInv(inv0, x.getInteractua())){
				if(x.getNombre().equals("Puerta")){ //AQUI SE COMPRUEBA SI SE GANA O SE AVANZA DE HABITACION
					if(x.getInteractua() == cod_herr){
						txt.setText(txt.getText() + "\n\n" +"Has abierto la puerta " + getPronombre(hab.getNombre()) + " " + hab.getNombre());
						if(habActual == ultiHab){
							JOptionPane.showMessageDialog(window.getVentana(), "¡HAS SALIDO DE LA CASA! ¡HAS GANADO!");
							window.getTexto().setText(window.getTexto().getText() + "\n\n" + "¡HAS SALIDO DE LA CASA! ¡HAS GANADO!");
							window.getImagen().setEnabled(false);
							window.getPantalla().setEnabled(false);
							window.getTeclado().setEnabled(false);
							window.getBtn_enviar().setEnabled(false);
							window.getBtn_inventario().setEnabled(false);
							window.getBtn_pistas().setEnabled(false);
							window.getBtn_guardar().setEnabled(false);
						}
						else{
							int habact = habActual + 1;
							inicializarJuego(con, window, habact);
						}
					}
					else{
						txt.setText(txt.getText() + "\n\nUsar esta herramienta no ha tenido ningún efecto");
						mostrarObj(window.getTexto(), false);
					}
				}
				else{ //SI NO HAS SELECCIONADO LA PUERTA
					if(x.getInteractua() == cod_herr){
						txt.setText(txt.getText() + "\n\n" + "Has abierto:  " + x.getNombre());
						if(x.getContiene() == 0)
							txt.setText(txt.getText() + "\n\n" + "El objeto no contiene nada útil");
						else{
							contiene = buscarHerr(x.getContiene());
							if(contiene.isPista())
								encontrarObjeto(txt, con, contiene, 1);			
							else
								encontrarObjeto(txt, con, contiene, 0);
							mostrarObj(window.getTexto(), false);
						}
					}
					else {
						txt.setText(txt.getText() + "\n\nUsar esta herramienta no ha tenido ningún efecto");
						mostrarObj(window.getTexto(), false);
					}
				}
			}
			else{
				txt.setText(txt.getText() + "\n\nNo es posible interactuar con este objeto. Requieres de una herramienta especial.");
				mostrarObj(window.getTexto(), false);
			}
		}
	}
	private static void botonesRegLogin(Frames window, Connection con){
		window.getBtn_entrar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					usu = new Usuario(window.getUser().getText(), new String(window.getPasswd().getPassword()));
					usu.login(window.getInicio(), window.getVentana(), con);
					inicializarJuego(con, window, 1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		window.getBtn_registrarse().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pass1 = new String(window.getNewpasswd1().getPassword()), pass2 = new String(window.getNewpasswd2().getPassword());
				if(pass1.equals(pass2)){
					usu = new Usuario(window.getNewuser().getText(), pass1, window.getNombre().getText(), window.getApellidos().getText(), window.getEmail().getText());
					try {
						usu.almacenarDatos(window.getInicio(), window.getVentana(), con);
						inicializarJuego(con, window, 1);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else JOptionPane.showMessageDialog(window.getInicio(), "Los campos de contraseña no cohinciden");
			}
		});
	}
	private static void inicializarJuego(Connection con, Frames window, int habact) throws SQLException{
		habActual = habact;
		ultiHab = getIntDB(con, "select max(cod_hab) from habitacion");
		rellenarHab(con);
		window.getImagen().setIcon(new ImageIcon(hab.getUrl()));
		rellenarObjeto(con);
		rellenarInventario(con, 0);
		rellenarInventario(con, 1);
		rellenarHerr(con);
		mostrarObj(window.getTexto(), true);
		enableDisableEnviar(window.getTeclado(), window.getBtn_enviar(), true);	
	}
	private static String getNombreInv(Connection con, int cod) throws SQLException{
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery("select nombre from herramienta where cod_herr = "+cod+"");
		rs.first();
		return rs.getString("nombre");
	}
	private static void mostrarInv(Connection con, JTextArea txt, boolean f) throws SQLException{
		if(f)
			txt.setText(objActual.getNombre().toUpperCase() + ": ");
		else txt.setText(txt.getText() + "\n\n" + objActual.getNombre().toUpperCase() + ": ");
		for(int i = 0; i < inv0.length; i++){
			txt.setText(txt.getText() + "\n\n" + (i + 1) + ".- " + getNombreInv(con, inv0[i].getCod_herr()));
		}
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		Connection con = conexion();
		Frames window = new Frames();
		window.inicializarFrame();
		window.getInicio().setVisible(true);
		window.getVentana().setVisible(false);			
		window.accionBotonesInicio();
		
		window.getBtn_Admin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					usu = new Usuario("pipo", "pepito");
					inicializarJuego(con, window, 1);
					window.getInicio().setVisible(false);
					window.getVentana().setVisible(true);	
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		botonesRegLogin(window,con);		
		
		window.getBtn_enviar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = pedirOpc(window.getTeclado(), obj.length);
				window.getTeclado().setText("");
				if(x != 0){
						try {
							objActual = obj[(x - 1)];
							if(objActual.getInteractua() != 0){
								if(inv0 != null){
									mostrarInv(con, window.getTexto(), true);
									window.getBtn_enviar().setVisible(false);
									window.getBtn_enviar2().setVisible(true);
								}
								else{
									window.getTexto().setText("No tienes ninguna herramienta útil en tu inventario.");
									mostrarObj(window.getTexto(), false);
								}
							}								
							else{
								interactuarObjeto(con, window, window.getTexto(), objActual, 0);
							}
						} catch (SQLException | IOException e) {e.printStackTrace();}
				}
				else JOptionPane.showMessageDialog(window.getVentana(), "El nº introducido no es válido");
			}
		});
		
		window.getBtn_enviar2().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = pedirOpc(window.getTeclado(), inv0.length);
				window.getTeclado().setText("");
				if(x != 0){
					try{
						try {
							invActual = inv0[(x - 1)];
							interactuarObjeto(con, window, window.getTexto(), objActual, invActual.getCod_herr());
						} catch (SQLException e3) {e3.printStackTrace();}
					}catch(IOException e2){e2.printStackTrace();}
					window.getBtn_enviar().setVisible(true);
					window.getBtn_enviar2().setVisible(false);
				}
				else JOptionPane.showMessageDialog(window.getVentana(), "El nº introducido no es válido");
			}
		});
	}
}
