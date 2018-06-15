import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class Frames {

	private JFrame ventana, inicio;
	private JButton btn_jugar, btn_entrar, btn_enviar, btn_inventario, btn_pistas, btn_guardar, btn_newuser, btn_sesion, btn_registrarse, btn_atrasR, btn_atrasL, btn_enviar2;
	private JTextField user, teclado, newuser, nombre, apellidos, email;
	private JLabel userText, passwdText, imagen, newpasswdText1, nombreText, apellidosText, emailText, newpasswdText2, newuserText;
	private JPasswordField passwd, newpasswd1, newpasswd2;
	private JPanel panel_imagen, pantalla;
	private JTextArea texto;
	private JButton btn_Admin;
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializarFrame() throws IOException {
		inicializarVentana();
		inicializarInicio();
		
	}
	public void accionBotonesInicio(){
		btn_jugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn_jugar.setVisible(false);
				btn_sesion.setVisible(true);
				btn_newuser.setVisible(true);
				
			}
		});
		btn_sesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn_sesion.setVisible(false);
				btn_newuser.setVisible(false);
				user.setVisible(true);
				passwd.setVisible(true);
				passwdText.setVisible(true);
				userText.setVisible(true);
				btn_entrar.setVisible(true);
				btn_atrasL.setVisible(true);
			}
		});
		btn_atrasL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn_sesion.setVisible(true);
				btn_newuser.setVisible(true);
				user.setVisible(false);
				passwd.setVisible(false);
				passwdText.setVisible(false);
				userText.setVisible(false);
				btn_entrar.setVisible(false);
				btn_atrasL.setVisible(false);
			}
		});
		btn_newuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn_newuser.setVisible(false);
				btn_sesion.setVisible(false);
				newuserText.setVisible(true);
				newuser.setVisible(true);
				newpasswdText1.setVisible(true);
				newpasswd1.setVisible(true);
				nombreText.setVisible(true);
				newpasswdText2.setVisible(true);
				newpasswd2.setVisible(true);
				nombre.setVisible(true);
				apellidos.setVisible(true);
				apellidos.setVisible(true);
				email.setVisible(true);
				btn_registrarse.setVisible(true);	
				apellidosText.setVisible(true);
				emailText.setVisible(true);
				btn_atrasR.setVisible(true);
			}
		});
		btn_atrasR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn_newuser.setVisible(true);
				btn_sesion.setVisible(true);
				newuserText.setVisible(false);
				newuser.setVisible(false);
				newpasswdText1.setVisible(false);
				newpasswd1.setVisible(false);
				nombreText.setVisible(false);
				newpasswdText2.setVisible(false);
				newpasswd2.setVisible(false);
				nombre.setVisible(false);
				apellidos.setVisible(false);
				apellidos.setVisible(false);
				email.setVisible(false);
				btn_registrarse.setVisible(false);	
				apellidosText.setVisible(false);
				emailText.setVisible(false);
				btn_atrasR.setVisible(false);
			}
		});
	}
	public void accionBotonesVentana(){
		btn_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicio.setVisible(true);
			}
		});
		btn_pistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_inventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	public void inicializarInicio(){
		/* Frame inicial */
		inicio = new JFrame();
		inicio.setBounds(11,11, 400,500);
		inicio.setResizable(false);
		inicio.getContentPane().setBackground(Color.DARK_GRAY);
		inicio.getContentPane().setLayout(null);
		
		/* Boton inicial */
		btn_jugar = new JButton("Jugar");
		btn_jugar.setBounds(111, 122, 160, 60);
		inicio.getContentPane().setLayout(null);
		btn_jugar.setBackground(new Color(255, 255, 255));
		inicio.getContentPane().add(btn_jugar);		
		
		/* LOGIN */
		user = new JTextField();
		user.setBounds(111, 142, 160, 20);
		inicio.getContentPane().add(user);
		user.setColumns(10);
		
		userText = new JLabel("Nombre de usuario: ");
		userText.setForeground(new Color(255, 255, 0));
		userText.setBounds(111, 125, 160, 14);
		inicio.getContentPane().add(userText);
		passwdText = new JLabel("Contraseña: ");
		passwdText.setForeground(new Color(255, 255, 0));
		passwdText.setBounds(111, 173, 160, 14);
		inicio.getContentPane().add(passwdText);
		
		btn_entrar = new JButton("Entrar");
		btn_entrar.setBackground(Color.WHITE);
		btn_entrar.setBounds(143, 253, 89, 23);
		inicio.getContentPane().add(btn_entrar);
		
		passwd = new JPasswordField();
		passwd.setBounds(111, 189, 160, 20);
		inicio.getContentPane().add(passwd);
		
		btn_sesion = new JButton("Iniciar sesi\u00F3n");
		btn_sesion.setBackground(Color.WHITE);
		btn_sesion.setBounds(133, 122, 115, 36);
		inicio.getContentPane().add(btn_sesion);
		
		btn_newuser = new JButton("Registrarse");
		btn_newuser.setBackground(Color.WHITE);
		btn_newuser.setBounds(133, 162, 115, 36);
		inicio.getContentPane().add(btn_newuser);
		
		user.setVisible(false);
		passwd.setVisible(false);
		passwdText.setVisible(false);
		userText.setVisible(false);
		btn_entrar.setVisible(false);
		btn_newuser.setVisible(false);
		btn_sesion.setVisible(false);
		
		/* Cajas y botones de registro */
		newuserText = new JLabel("Nombre de Usuario: ");
		newuserText.setForeground(new Color(255, 255, 0));
		newuserText.setBounds(111, 37, 160, 14);
		inicio.getContentPane().add(newuserText);
		
		newuser = new JTextField();
		newuser.setBounds(111, 62, 160, 20);
		inicio.getContentPane().add(newuser);
		newuser.setColumns(10);
		
		newpasswdText1 = new JLabel("Nueva Contrase\u00F1a: ");
		newpasswdText1.setForeground(Color.YELLOW);
		newpasswdText1.setBounds(111, 93, 160, 14);
		inicio.getContentPane().add(newpasswdText1);
		
		newpasswd1 = new JPasswordField();
		newpasswd1.setBounds(111, 118, 160, 20);
		inicio.getContentPane().add(newpasswd1);
		
		nombreText = new JLabel("Nombre:");
		nombreText.setForeground(Color.YELLOW);
		nombreText.setBounds(111, 205, 160, 14);
		inicio.getContentPane().add(nombreText);
		
		newpasswdText2 = new JLabel("Repita Contrase\u00F1a: ");
		newpasswdText2.setForeground(Color.YELLOW);
		newpasswdText2.setBounds(111, 149, 160, 14);
		inicio.getContentPane().add(newpasswdText2);
		
		newpasswd2 = new JPasswordField();
		newpasswd2.setBounds(111, 174, 160, 20);
		inicio.getContentPane().add(newpasswd2);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(111, 230, 160, 20);
		inicio.getContentPane().add(nombre);
		
		apellidosText = new JLabel("Apellidos: ");
		apellidosText.setForeground(Color.YELLOW);
		apellidosText.setBounds(111, 261, 160, 14);
		inicio.getContentPane().add(apellidosText);
		
		apellidos = new JTextField();
		apellidos.setColumns(10);
		apellidos.setBounds(111, 286, 160, 20);
		inicio.getContentPane().add(apellidos);
		
		emailText = new JLabel("Email: ");
		emailText.setForeground(Color.YELLOW);
		emailText.setBounds(111, 317, 160, 14);
		inicio.getContentPane().add(emailText);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(111, 342, 160, 20);
		inicio.getContentPane().add(email);
		
		btn_registrarse = new JButton("Registrarse");		
		btn_registrarse.setBackground(Color.WHITE);
		btn_registrarse.setBounds(133, 373, 106, 32);
		inicio.getContentPane().add(btn_registrarse);
		
		btn_atrasR = new JButton("Atras");
		btn_atrasR.setBackground(Color.WHITE);
		btn_atrasR.setBounds(143, 416, 89, 23);
		inicio.getContentPane().add(btn_atrasR);
		
		btn_atrasL = new JButton("Atras");
		btn_atrasL.setBackground(Color.WHITE);
		btn_atrasL.setBounds(143, 287, 89, 23);
		inicio.getContentPane().add(btn_atrasL);
		
		btn_Admin = new JButton("Admin");
		btn_Admin.setBounds(143, 437, 89, 23);
		inicio.getContentPane().add(btn_Admin);
		inicio.setBounds(10,10,400,500);
		
//		btn_Admin.setVisible(false);
		newuserText.setVisible(false);
		newuser.setVisible(false);
		newpasswdText1.setVisible(false);
		newpasswd1.setVisible(false);
		nombreText.setVisible(false);
		newpasswdText2.setVisible(false);
		newpasswd2.setVisible(false);
		nombre.setVisible(false);
		apellidosText.setVisible(false);
		apellidos.setVisible(false);
		emailText.setVisible(false);
		email.setVisible(false);
		btn_registrarse.setVisible(false);
		btn_atrasR.setVisible(false);
		btn_atrasL.setVisible(false);
	}
	private void inicializarVentana(){
		/* Ventana Principal */
		ventana = new JFrame();
		ventana.getContentPane().setBackground(Color.DARK_GRAY);
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//Esto es para cuando se buguee el WindowBuilder
//		ventana.setBounds(10,10,1600,900);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
		ventana.setVisible(false);
		
		/* Label donde se coloca la imagen */
		imagen = new JLabel("");
		imagen.setBounds(10, 11, 1200, 750);
		ventana.getContentPane().add(imagen);
		
		/* Pantalla donde aparecerá el texto */
		pantalla = new JPanel();
		pantalla.setBorder(new LineBorder(new Color(0, 0, 0)));
		pantalla.setBounds(1220, 11, 350, 750);
		ventana.getContentPane().add(pantalla);
		pantalla.setLayout(null);
		
		texto = new JTextArea();
		texto.setEditable(false);
		texto.setBounds(10, 6, 330, 733);
		texto.setLineWrap(true);
		pantalla.add(texto);
		
		/* Donde el usuario introduce cosas por teclado */
		teclado = new JTextField();
		teclado.setBackground(Color.WHITE);
		teclado.setBounds(1220, 772, 255, 23);
		ventana.getContentPane().add(teclado);
		teclado.setColumns(10);
		
		btn_enviar = new JButton("Enviar");
		btn_enviar.setBackground(Color.WHITE);
		btn_enviar.setBounds(1481, 772, 89, 23);
		ventana.getContentPane().add(btn_enviar);
		
		teclado.setEnabled(false);
		btn_enviar.setEnabled(false);
		
		/* Boton del inventario */
		btn_inventario = new JButton("Abrir Inventario");
		btn_inventario.setBackground(Color.WHITE);
		btn_inventario.setBounds(461, 772, 121, 23);
		ventana.getContentPane().add(btn_inventario);
		
		/* Boton de las pistas */
		btn_pistas = new JButton("Ver Pistas");
		btn_pistas.setBackground(Color.WHITE);
		btn_pistas.setBounds(592, 772, 113, 23);
		ventana.getContentPane().add(btn_pistas);
		
		btn_guardar = new JButton("Opciones");
		btn_guardar.setBackground(Color.WHITE);
		btn_guardar.setBounds(715, 771, 148, 23);
		ventana.getContentPane().add(btn_guardar);
		
		btn_enviar2 = new JButton("Enviar");
		btn_enviar2.setEnabled(false);
		btn_enviar2.setBackground(Color.WHITE);
		btn_enviar2.setBounds(1481, 772, 89, 23);
		ventana.getContentPane().add(btn_enviar2);
		btn_enviar2.setVisible(false);
		btn_enviar2.setEnabled(true);
	}
	public JFrame getVentana() {
		return ventana;
	}
	public JFrame getInicio() {
		return inicio;
	}
	public JButton getBtn_jugar() {
		return btn_jugar;
	}
	public JButton getBtn_entrar() {
		return btn_entrar;
	}
	public JTextField getUser() {
		return user;
	}
	public JLabel getUserText() {
		return userText;
	}
	public JLabel getPasswdText() {
		return passwdText;
	}
	public JPasswordField getPasswd() {
		return passwd;
	}
	public JButton getBtn_enviar() {
		return btn_enviar;
	}
	public JButton getBtn_inventario() {
		return btn_inventario;
	}
	public JButton getBtn_pistas() {
		return btn_pistas;
	}
	public JButton getBtn_guardar() {
		return btn_guardar;
	}
	public JButton getBtn_newuser() {
		return btn_newuser;
	}
	public JButton getBtn_sesion() {
		return btn_sesion;
	}
	public JButton getBtn_registrarse() {
		return btn_registrarse;
	}
	public JTextField getTeclado() {
		return teclado;
	}
	public JTextField getNewuser() {
		return newuser;
	}
	public JTextField getNombre() {
		return nombre;
	}
	public JTextField getApellidos() {
		return apellidos;
	}
	public JTextField getEmail() {
		return email;
	}
	public JLabel getImagen() {
		return imagen;
	}
	public JPasswordField getNewpasswd1() {
		return newpasswd1;
	}
	public JPanel getPanel_imagen() {
		return panel_imagen;
	}
	public JPanel getPantalla() {
		return pantalla;
	}
	public JTextArea getTexto() {
		return texto;
	}
	public JPasswordField getNewpasswd2() {
		return newpasswd2;
	}
	public JButton getBtn_Admin() {
		return btn_Admin;
	}
	public JButton getBtn_enviar2() {
		return btn_enviar2;
	}
}
