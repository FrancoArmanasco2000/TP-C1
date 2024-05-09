package proyecto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ReservarPeriodo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAlumnos;
	private JTextField textFieldApellidoyNombre;
	private JTextField textFieldAsignatura;
	private JTextField textFieldCorreo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservarPeriodo frame = new ReservarPeriodo();
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
	public ReservarPeriodo() {
		setTitle("Reservar Periodo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Agregar Día");
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		btnNewButton.setBounds(28, 11, 130, 29);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 55, 500, 160);
		contentPane.add(scrollPane);
		
		JLabel lblPeriodo = new JLabel("Periodo (*)");
		lblPeriodo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblPeriodo.setBounds(28, 240, 104, 24);
		contentPane.add(lblPeriodo);
		
		JComboBox comboBoxPeriodo = new JComboBox();
		comboBoxPeriodo.setBounds(240, 242, 123, 26);
		contentPane.add(comboBoxPeriodo);
		
		JLabel lblCantAlumnos = new JLabel("Cantidad de Alumnos (*)");
		lblCantAlumnos.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblCantAlumnos.setBounds(28, 301, 189, 24);
		contentPane.add(lblCantAlumnos);
		
		textFieldAlumnos = new JTextField();
		textFieldAlumnos.setBounds(240, 301, 65, 24);
		contentPane.add(textFieldAlumnos);
		textFieldAlumnos.setColumns(10);
		
		JLabel lblTipoDeAula = new JLabel("Tipo de Aula (*)");
		lblTipoDeAula.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblTipoDeAula.setBounds(28, 354, 137, 24);
		contentPane.add(lblTipoDeAula);
		
		JComboBox comboBoxAula = new JComboBox();
		comboBoxAula.setBounds(240, 354, 123, 24);
		contentPane.add(comboBoxAula);
		
		JLabel lblApellidoYNombre = new JLabel("Apellido y Nombre (*)");
		lblApellidoYNombre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblApellidoYNombre.setBounds(28, 411, 168, 24);
		contentPane.add(lblApellidoYNombre);
		
		textFieldApellidoyNombre = new JTextField();
		textFieldApellidoyNombre.setBounds(242, 411, 168, 25);
		contentPane.add(textFieldApellidoyNombre);
		textFieldApellidoyNombre.setColumns(10);
		
		JLabel lblAsignatura = new JLabel("Asignatura (*)");
		lblAsignatura.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblAsignatura.setBounds(28, 466, 123, 24);
		contentPane.add(lblAsignatura);
		
		textFieldAsignatura = new JTextField();
		textFieldAsignatura.setColumns(10);
		textFieldAsignatura.setBounds(240, 465, 168, 25);
		contentPane.add(textFieldAsignatura);
		
		JLabel lblCorreoElectronico = new JLabel("Correo Electrónico (*)");
		lblCorreoElectronico.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblCorreoElectronico.setBounds(28, 527, 168, 24);
		contentPane.add(lblCorreoElectronico);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setColumns(10);
		textFieldCorreo.setBounds(240, 532, 168, 25);
		contentPane.add(textFieldCorreo);
		
		JLabel lblCamposObligatorios = new JLabel("(*) Campos Obligatorios");
		lblCamposObligatorios.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblCamposObligatorios.setBounds(28, 606, 189, 24);
		contentPane.add(lblCamposObligatorios);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnAceptar.setBounds(390, 607, 97, 24);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnCancelar.setBounds(497, 607, 97, 24);
		contentPane.add(btnCancelar);
	}
}
