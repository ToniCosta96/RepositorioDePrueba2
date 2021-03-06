package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import BDD.Conexio;
import java.awt.Font;

public class Menu extends JPanel implements ActionListener{
	private Ventanas ventanaPrincipal;
	private Conexio conexio;
	private ArrayList<String> arrayIdioma;
	private DefaultTableModel dtm;
	private JTable datos;
	private JPanel panelTabla;
	private JLabel lblIrAlPerfil;
	private JButton btnPlanificacion;
	private JButton btnEnviarPlanificacin;
	private JButton btnHazTuPropio;
	private JButton btnConsultarPlatos;
	private JButton btnConsultarAlimentos;
	private JLabel lblEverhealth;

	/**
	 * Create the frame.
	 */
	public Menu(Ventanas v,Conexio conexio, ArrayList<String> arrayIdioma) {
		ventanaPrincipal=v;
		this.arrayIdioma=arrayIdioma;
		this.conexio=conexio;
		setBackground(new Color(255, 255, 153));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelCabezera = new JPanel();
		panelCabezera.setOpaque(false);
		add(panelCabezera, BorderLayout.NORTH);
		panelCabezera.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panelCalorias = new JPanel();
		panelCalorias.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panelCalorias.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		flowLayout.setVgap(10);
		flowLayout.setHgap(25);
		panelCabezera.add(panelCalorias);
		
		lblEverhealth = new JLabel();
		lblEverhealth.setText("EverHealth");
		lblEverhealth.setForeground(new Color(255, 160, 122));
		lblEverhealth.setFont(new Font("Serif", Font.BOLD, 45));
		panelCalorias.add(lblEverhealth);
		
		JPanel panelIrAlPerfil = new JPanel();
		panelIrAlPerfil.setOpaque(false);
		FlowLayout flowLayout_1 = (FlowLayout) panelIrAlPerfil.getLayout();
		flowLayout_1.setHgap(25);
		flowLayout_1.setVgap(15);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelCabezera.add(panelIrAlPerfil);
		
		lblIrAlPerfil = new JLabel();
		lblIrAlPerfil.setForeground(Color.BLUE);
		lblIrAlPerfil.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblIrAlPerfil.setText(arrayIdioma.get(12));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblIrAlPerfil.setText("<html><u>"+arrayIdioma.get(12)+"</u></html>");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ventanaPrincipal.cambiapanel("CrearUsuario");
				ventanaPrincipal.setTitle(arrayIdioma.get(7));
			}
		});
		
		btnEnviarPlanificacin = new JButton();
		btnEnviarPlanificacin.setActionCommand("btnEnviarPlanificacin");
		btnEnviarPlanificacin.addActionListener(this);
		panelIrAlPerfil.add(btnEnviarPlanificacin);
		panelIrAlPerfil.add(lblIrAlPerfil);
		
		JPanel panelDivisor = new JPanel();
		panelDivisor.setOpaque(false);
		add(panelDivisor, BorderLayout.CENTER);
		panelDivisor.setLayout(new BoxLayout(panelDivisor, BoxLayout.X_AXIS));
		
		JPanel panelBotones = new JPanel();
		panelBotones.setOpaque(false);
		panelBotones.setLayout(new GridLayout(4, 1, 0, 65));
		panelDivisor.add(panelBotones);
		
		btnPlanificacion = new JButton();
		btnPlanificacion.setActionCommand("MenuBtnPlanificacion");
		btnPlanificacion.addActionListener(this);
		panelBotones.add(btnPlanificacion);
		
		btnHazTuPropio = new JButton();
		btnHazTuPropio.setActionCommand("MenuBtnCrearPlato");
		btnHazTuPropio.addActionListener(this);
		panelBotones.add(btnHazTuPropio);
		
		btnConsultarPlatos = new JButton();
		btnConsultarPlatos.setActionCommand("MenuBtnConsultarPlatos");
		btnConsultarPlatos.addActionListener(this);
		panelBotones.add(btnConsultarPlatos);
		
		btnConsultarAlimentos = new JButton();
		btnConsultarAlimentos.setActionCommand("MenuBtnConsultarAlimentos");
		btnConsultarAlimentos.addActionListener(this);
		panelBotones.add(btnConsultarAlimentos);
		
		//Crear y mostrar la tabla de la planificación
		
		dtm= new DefaultTableModel();
		String nombresColumnas[]= new String[7];
		for(int i=0;i<nombresColumnas.length;i++){
			nombresColumnas[i]=arrayIdioma.get(i+24);
		}
		String nombresFilas[]= new String[15];
		int plato=0;
		int cuenta=0;
		for(int i=0;i<nombresFilas.length;i++){
			nombresFilas[i]=arrayIdioma.get(31+plato);
			nombresFilas[i]+=arrayIdioma.get(13+cuenta);
			cuenta++;
			if(cuenta>=3){
				cuenta=0;
				plato++;
			}
		}
		
		String[] datosTabla= new String[8];
		dtm.addColumn("");
		for(int columna=0;columna<nombresColumnas.length;columna++){
			dtm.addColumn(nombresColumnas[columna]);
		}
		for(int fila=0;fila<nombresFilas.length;fila++){
			datosTabla[0]=nombresFilas[fila];
			for(int columna=1;columna<datosTabla.length;columna++){
				datosTabla[columna]="";
			}
			dtm.addRow(datosTabla);
		}
		panelTabla = new JPanel();
		panelTabla.setOpaque(false);
		datos= new JTable(dtm) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (0 == column)
					return false;
				return super.isCellEditable(row, column);
			}
			@Override
			public void changeSelection(int rowIndex, int columnIndex,
					boolean toggle, boolean extend) {
				if (columnIndex == 0)
					super.changeSelection(rowIndex, columnIndex + 1, toggle, extend);
				else
					super.changeSelection(rowIndex, columnIndex, toggle, extend);
					}
				};
		//Se cambia el aspecto de la primera columna y se alinea su texto a la izquierda.
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr=(DefaultTableCellRenderer) datos.getTableHeader().getDefaultRenderer();
		tcr.setHorizontalAlignment(JTextField.LEFT);
		datos.getColumnModel().getColumn(0).setCellRenderer(tcr);
		//datos.getColumnModel().getColumn(0).setCellRenderer(datos.getTableHeader().getDefaultRenderer());
		datos.getColumnModel().getColumn(0).setPreferredWidth(150);
		datos.setRowHeight(25);
		
		JScrollPane scrollPane = new JScrollPane(datos);
		scrollPane.setOpaque(false);
		scrollPane.setPreferredSize(new Dimension(600, 398));
		panelTabla.add(scrollPane);
		panelDivisor.add(panelTabla);
		
		/*Cargar labels*/
		cargarNombresLabels();
	}
	
	public void cargarNombresLabels(){
		btnPlanificacion.setText(arrayIdioma.get(7));
		lblIrAlPerfil.setText(arrayIdioma.get(12));
		btnEnviarPlanificacin.setText(arrayIdioma.get(11));
		btnHazTuPropio.setText(arrayIdioma.get(8));
		btnConsultarPlatos.setText(arrayIdioma.get(9));
		btnConsultarAlimentos.setText(arrayIdioma.get(10));
	}
	public void recargarTabla(){
		String nombresColumnas[]= new String[7];
		for(int i=0;i<nombresColumnas.length;i++){
			nombresColumnas[i]=arrayIdioma.get(i+24);
		}
		String nombresFilas[]= new String[15];
		int plato=0;
		int cuenta=0;
		for(int i=0;i<nombresFilas.length;i++){
			nombresFilas[i]=arrayIdioma.get(31+plato);
			nombresFilas[i]+=arrayIdioma.get(13+cuenta);
			cuenta++;
			if(cuenta>=3){
				cuenta=0;
				plato++;
			}
		}
		for(int i=0;i<nombresColumnas.length;i++){
			TableColumn column1 = datos.getTableHeader().getColumnModel().getColumn(i+1);
			column1.setHeaderValue(nombresColumnas[i]);
		} 
		
		String[] datosTabla= new String[8];
		for(int fila=0;fila<nombresFilas.length;fila++){
			datosTabla[0]=nombresFilas[fila];
			dtm.setValueAt(nombresFilas[fila], fila, 0);
		}
	}
	public DefaultTableModel getDtm(){
		return dtm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String accio=e.getActionCommand();
		if(accio.compareTo("MenuBtnPlanificacion")==0){
			ventanaPrincipal.cambiapanel("Planificacion");
			ventanaPrincipal.setTitle(arrayIdioma.get(21));
		}else if(accio.compareTo("MenuBtnConsultarPlatos")==0){
			ventanaPrincipal.cambiapanel("ConsultarPlatos");
			ventanaPrincipal.setTitle(arrayIdioma.get(86));
		}else if(accio.compareTo("MenuBtnConsultarAlimentos")==0){
			ventanaPrincipal.cambiapanel("ConsultarAlimentos");
			ventanaPrincipal.setTitle(arrayIdioma.get(96));
		}else if(accio.compareTo("MenuBtnCrearPlato")==0){
			ventanaPrincipal.cambiapanel("CrearPlato");
			ventanaPrincipal.setTitle(arrayIdioma.get(67));
		}else if(accio.compareTo("btnEnviarPlanificacin")==0){
			new EmailVentana(conexio, arrayIdioma).setVisible(true);
		}
	}
}
