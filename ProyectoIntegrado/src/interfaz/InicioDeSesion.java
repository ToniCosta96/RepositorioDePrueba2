package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BDD.CargarIdioma;
import BDD.Conexio;
import BDD.Consultas;

public class InicioDeSesion extends JFrame implements KeyListener{

	private static Conexio conexio;
	private ArrayList<String> arrayIdioma;
	private JPanel contentPane;
	private JLabel lblNombreDeUsuario;
	private JTextField textFieldNombre;
	private JLabel lblContrasea;
	private JLabel lblCrearUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioDeSesion frame = new InicioDeSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InicioDeSesion() {
		conexio=new Conexio();
		arrayIdioma= new ArrayList<String>();
		new CargarIdioma(conexio,arrayIdioma);
		setTitle(arrayIdioma.get(0));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 150);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(".\\img\\logoEverHealth1.png").getImage());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panelCampos = new JPanel();
		panelCampos.setOpaque(false);
		contentPane.add(panelCampos);
		panelCampos.setLayout(new GridLayout(2, 2, 0, 0));
		
		lblNombreDeUsuario = new JLabel(arrayIdioma.get(1));
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelCampos.add(lblNombreDeUsuario);
		
		textFieldNombre = new JTextField();
		textFieldNombre.addKeyListener(this);
		panelCampos.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblContrasea = new JLabel(arrayIdioma.get(2));
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelCampos.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(this);
		panelCampos.add(passwordField);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setOpaque(false);
		contentPane.add(panelBotones);
		
		JButton btnIniciarSesion = new JButton(arrayIdioma.get(0));
		btnIniciarSesion.addKeyListener(this);
		btnIniciarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Aqu� se accede a la base de datos para introducir el usuario.
				iniciarSesion();
			}
		});
		panelBotones.add(btnIniciarSesion);
		
		lblCrearUsuario = new JLabel(arrayIdioma.get(3));
		lblCrearUsuario.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCrearUsuario.setText(arrayIdioma.get(3));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCrearUsuario.setText("<html><u>"+arrayIdioma.get(3)+"</u></html>");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ventanas('c');
				dispose();
			}
		});
		panelBotones.add(lblCrearUsuario);
	}
	
	private void iniciarSesion(){
		/*Se comprueba la conexi�n y se inicia sesi�n si los datos est�n correctos*/
		if(new Consultas(conexio).iniciarSesionUsuario(textFieldNombre.getText(), String.valueOf(passwordField.getPassword()))){
			conexio.setUsuario(textFieldNombre.getText());
			ventanas('m');
			dispose();
		}else{
			//Mostrar mensaje de error.
			JOptionPane.showMessageDialog(null,
				    "Usuario o contrase�a incorrectos.",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void ventanas(char ventana){
		Ventanas frame = new Ventanas(ventana, conexio, arrayIdioma);
		frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			iniciarSesion();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
