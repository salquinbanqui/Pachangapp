package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import javax.swing.UIManager;
import java.awt.Insets;

public class TriviaVentana extends JFrame implements ActionListener {

	private JFrame frmTriviafut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TriviaVentana window = new TriviaVentana();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TriviaVentana() {
		ImageIcon fondo = new ImageIcon("imagenes/fondo.jpg");
		JPanel panelGeneral = new PanelConFondo(fondo.getImage());
		panelGeneral.setLayout(new CardLayout(0, 0));
		getContentPane().add(panelGeneral, BorderLayout.CENTER);
		setTitle("TriviaFut");
		setBounds(100, 100, 400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// SECCIÓN DE TRIVIA

		JPanel panelTrivia = new JPanel();
		panelTrivia.setBackground(UIManager.getColor("Button.darkShadow"));
		panelGeneral.add(panelTrivia);

		JLabel labelTrivia = new JLabel("Trivia");
		panelTrivia.add(labelTrivia);

		
		// SECCIÓN DE CARTAS

		JPanel panelCartaJugador = new JPanel(new GridLayout(0, 2));
		
		JScrollPane panelCartasScroll = new JScrollPane(panelCartaJugador);
		panelCartasScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelGeneral.add(panelCartasScroll);

		
		//SECCIÓN DE TIENDA
		
		JPanel panelTiendaJugador = new JPanel(new GridLayout(0, 2));
		for(int i=0;i<50;i++) {
			JLabel lblTiendaJugador = new JLabel();
			//lblTiendaJugador.setSize(102, 164);
			lblTiendaJugador.setSize(120, 193);
			ImageIcon imTienda = new ImageIcon("imagenes/lewan.gif");
			ImageIcon imcdTienda= new ImageIcon(imTienda.getImage().getScaledInstance(lblTiendaJugador.getWidth(), lblTiendaJugador.getHeight(), Image.SCALE_DEFAULT));
			lblTiendaJugador.setIcon(imcdTienda);
			JPanel p = new JPanel(new BorderLayout());
			JPanel pw = new JPanel();
			JLabel l = new JLabel("          ");
			pw.add(l);
			p.add(pw, BorderLayout.WEST);
			p.add(lblTiendaJugador,BorderLayout.CENTER);
			panelTiendaJugador.add(p);
		}
		JScrollPane panelTiendaScroll = new JScrollPane(panelTiendaJugador);
		panelTiendaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelGeneral.add(panelTiendaScroll);
		

		// SECCIÓN DE EQUIPO

		JPanel panelEquipo = new JPanel();
		panelEquipo.setPreferredSize(new Dimension(10, 50));
		// panelEquipo.setBackground(UIManager.getColor("Button.darkShadow"));
		panelGeneral.add(panelEquipo);
		panelEquipo.setLayout(new BorderLayout());

		PanelConFondo panelEquipoCentro = new PanelConFondo(fondo.getImage());
		panelEquipoCentro.setLayout(new GridLayout(3, 3));
		
		//crea los labels de los jugadores del campo reescalados
		JLabel lblEquipoDefArriba = new JLabel();
		lblEquipoDefArriba.setSize(102, 164);
		ImageIcon im = new ImageIcon("imagenes\\lewan.gif");
		ImageIcon imcd = new ImageIcon(
				im.getImage().getScaledInstance(lblEquipoDefArriba.getWidth(), lblEquipoDefArriba.getHeight(), Image.SCALE_DEFAULT));
		lblEquipoDefArriba.setIcon(imcd);
		lblEquipoDefArriba.setOpaque(false);

		JLabel lblEquipoDelanteroArriba = new JLabel();
		lblEquipoDelanteroArriba.setSize(102, 164);
		ImageIcon im2 = new ImageIcon("imagenes\\messi.gif");
		ImageIcon imcd2 = new ImageIcon(
				im2.getImage().getScaledInstance(lblEquipoDelanteroArriba.getWidth(), lblEquipoDelanteroArriba.getHeight(), Image.SCALE_DEFAULT));
		lblEquipoDelanteroArriba.setIcon(imcd2);
		lblEquipoDelanteroArriba.setOpaque(false);

		JLabel lblEquipoPortero = new JLabel();
		lblEquipoPortero.setSize(102, 164);
		ImageIcon im3 = new ImageIcon("imagenes\\neuer.gif");
		ImageIcon imcd3 = new ImageIcon(
				im3.getImage().getScaledInstance(lblEquipoPortero.getWidth(), lblEquipoPortero.getHeight(), Image.SCALE_DEFAULT));
		lblEquipoPortero.setIcon(imcd3);
		lblEquipoPortero.setOpaque(false);

		JLabel lblEquipoDelanteroDebajo = new JLabel();
		lblEquipoDelanteroDebajo.setSize(102, 164);
		ImageIcon im4 = new ImageIcon("imagenes\\ronaldo.gif");
		ImageIcon imcd4 = new ImageIcon(
				im4.getImage().getScaledInstance(lblEquipoDelanteroDebajo.getWidth(), lblEquipoDelanteroDebajo.getHeight(), Image.SCALE_DEFAULT));
		lblEquipoDelanteroDebajo.setIcon(imcd4);
		lblEquipoDelanteroDebajo.setOpaque(false);

		JLabel lblEquipoDefensaDebajo = new JLabel();
		lblEquipoDefensaDebajo.setSize(102, 164);
		ImageIcon im5 = new ImageIcon("imagenes\\neuer.gif");
		ImageIcon imcd5 = new ImageIcon(
				im5.getImage().getScaledInstance(lblEquipoDefensaDebajo.getWidth(), lblEquipoDefensaDebajo.getHeight(), Image.SCALE_DEFAULT));
		lblEquipoDefensaDebajo.setIcon(imcd5);
		lblEquipoDefensaDebajo.setOpaque(false);

		//crea los huecos vacios (tanto los jugadores como los huecos vacios
		//tienen el setOpaque en false para que se vea el fondo
		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		JPanel p2 = new JPanel();
		p2.setOpaque(false);
		JPanel p3 = new JPanel();
		p3.setOpaque(false);
		JPanel p4 = new JPanel();
		p4.setOpaque(false);
		
		
		//inserta los jugadores en algunos grids, otros de quedan vacios
		//NO ALTERAR EL ORDEN
		panelEquipoCentro.add(p1); //VACIO, hueco: fila 1, columna 1
		panelEquipoCentro.add(lblEquipoDefArriba); //hueco: fila 1, columna 2
		panelEquipoCentro.add(lblEquipoDelanteroArriba); //hueco: fila 1, columna 3
		panelEquipoCentro.add(lblEquipoPortero);//hueco: fila 2, columna 1
		panelEquipoCentro.add(p2); //VACIO, hueco: fila 2, columna 2
		panelEquipoCentro.add(p3); //VACIO, hueco: fila 2, columna 3
		panelEquipoCentro.add(p4); //VACIO, hueco: fila 3, columna 1
		panelEquipoCentro.add(lblEquipoDefensaDebajo);//hueco: fila 3, columna 2
		panelEquipoCentro.add(lblEquipoDelanteroDebajo);//hueco: fila 3, columna 3

		//panelEquipoBot
		JPanel panelEquipoBot = new JPanel();
		panelEquipoBot.setPreferredSize(new Dimension(10, 50));
		panelEquipo.add(panelEquipoBot, BorderLayout.SOUTH);
		panelEquipo.add(panelEquipoCentro, BorderLayout.CENTER);
		
		JButton btnEquipoCargar = new JButton("Cargar Equipo");
		btnEquipoCargar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnEquipoCargar.setPreferredSize(new Dimension(160, 40));
		panelEquipoBot.add(btnEquipoCargar);

		JButton btnEquipoGuardar = new JButton("Guardar equipo");
		btnEquipoGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnEquipoGuardar.setPreferredSize(new Dimension(160, 40));
		panelEquipoBot.add(btnEquipoGuardar);
		
		
		//PANEL TOP
		
		JPanel panelTop = new JPanel();
		getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BorderLayout(0, 0));

		JLabel labelDinero = new JLabel("520💰");
		labelDinero.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		labelDinero.setPreferredSize(new Dimension(150, 14));
		panelTop.add(labelDinero, BorderLayout.EAST);

		JLabel labelNombre = new JLabel("javisito15");
		labelNombre.setPreferredSize(new Dimension(150, 14));
		panelTop.add(labelNombre, BorderLayout.WEST);

		
		//PANEL BOT
		
		JPanel panelBot = new JPanel();
		getContentPane().add(panelBot, BorderLayout.SOUTH);

		JButton btnTrivia = new JButton("Trivia");
		btnTrivia.setMargin(new Insets(2, 22, 2, 22));
		btnTrivia.setAlignmentX(Component.RIGHT_ALIGNMENT);

		JButton btnCartas = new JButton("Cartas");
		btnCartas.setMargin(new Insets(2, 22, 2, 22));

		JButton btnTienda = new JButton("Tienda");
		btnTienda.setMargin(new Insets(2, 22, 2, 22));

		JButton btnEquipo = new JButton("Equipo");
		btnEquipo.setMargin(new Insets(2, 22, 2, 22));
		
		panelBot.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelBot.add(btnTrivia);
		panelBot.add(btnCartas);
		panelBot.add(btnTienda);
		panelBot.add(btnEquipo);

		//cambiar de sección dentro de TriviaVentana
		btnEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// borra el antiguo panel
				panelGeneral.removeAll();
				panelGeneral.repaint();
				panelGeneral.revalidate();
				// pone el nuevo panel
				panelGeneral.add(panelEquipo);
				panelGeneral.repaint();
				panelGeneral.revalidate();

			}
		});
		btnTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// borra el antiguo panel
				panelGeneral.removeAll();
				panelGeneral.repaint();
				panelGeneral.revalidate();
				// pone el nuevo panel
				// panelGeneral.add(panelTiendaScroll);
				panelGeneral.add(panelTiendaScroll);
				panelGeneral.repaint();
				panelGeneral.revalidate();

			}
		});
		btnCartas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// borra el antiguo panel
				panelGeneral.removeAll();
				panelGeneral.repaint();
				panelGeneral.revalidate();
				// pone el nuevo panel
				panelGeneral.add(panelCartasScroll);
				panelGeneral.repaint();
				panelGeneral.revalidate();

			}
		});
		
		btnTrivia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// borra el antiguo panel
				panelGeneral.removeAll();
				panelGeneral.repaint();
				panelGeneral.revalidate();
				// pone el nuevo panel
				panelGeneral.add(panelTrivia);
				panelGeneral.repaint();
				panelGeneral.revalidate();
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}