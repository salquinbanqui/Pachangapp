package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dominioConHerencia.Carta;
import dominioConHerencia.Jugador;
import dominioConHerencia.Portero;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class SeleccionarEquipoCarta {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionarEquipoCarta window = new SeleccionarEquipoCarta();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SeleccionarEquipoCarta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 100, 300, 450);
		frame.setResizable(false);
		frame.setTitle("Selecciona una carta");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		List<Jugador> listaJugadores = new ArrayList<Jugador>();
		List<Portero> listaPorteros = new ArrayList<Portero>();
		
		List<Carta> listaCartasInventario = TriviaVentana.listaCartasInventario();
		for (Carta carta : listaCartasInventario) {
			//System.out.println(carta.getNombre());
			
			//separamos las cartas y nos quedamos solo con los jugadores
			if (carta.getClass().toString().equals("class dominioConHerencia.Jugador")) {
				listaJugadores.add((Jugador) carta);
			}else if (carta.getClass().toString().equals("class dominioConHerencia.Portero")) {
				listaPorteros.add((Portero) carta);
			}
		}
		
		System.out.println("Porteros: ");
		for (Portero portero : listaPorteros) {
			System.out.println(portero.getNombre());
		}
		
		System.out.println("Jugadores: ");
		for (Jugador jugador : listaJugadores) {
			System.out.println(jugador.getNombre());
		}
		

		
		JPanel panelSeleccionarCarta = new JPanel(new GridLayout(0, 2));
		List<Carta> hola = new ArrayList<>();
		
		for (Carta carta : listaCartasInventario) {
			JLabel lblTiendaJugador = new JLabel();
			lblTiendaJugador.setSize(102, 164);
			//lblTiendaJugador.setSize(120, 193);
			ImageIcon imTienda = new ImageIcon(carta.getRutaFoto());
			ImageIcon imcdTienda= new ImageIcon(imTienda.getImage().getScaledInstance(lblTiendaJugador.getWidth(), lblTiendaJugador.getHeight(), Image.SCALE_DEFAULT));
			lblTiendaJugador.setIcon(imcdTienda);
			JPanel p = new JPanel(new BorderLayout());
			JPanel pw = new JPanel();
			p.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println(carta.getNombre());
					hola.add(carta);
					System.out.println(hola.size());
				}
			});
			p.add(pw, BorderLayout.WEST);
			p.add(lblTiendaJugador,BorderLayout.CENTER);
			panelSeleccionarCarta.add(p);
		}
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		JScrollPane panelTiendaScroll = new JScrollPane(panelSeleccionarCarta);
		panelTiendaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(panelTiendaScroll);
		
		


		//System.out.println(panelSeleccionarCarta.getComponents().toString());
		//public static void cartaSeleccionada() {
			
		//}
		
		//for (Carta carta2 : hola) {
		//	System.out.println(carta2.getNombre());
		//	System.out.println("aqui andomio");
		//}
		
		
		
		
		
		
		
		
		
		
		
		/*
		JPanel panelSeleccionarCarta = new JPanel(new GridLayout(0, 2));
		for(int i=0;i<10;i++) {
			JLabel lblTiendaJugador = new JLabel();
			lblTiendaJugador.setSize(102, 164);
			//lblTiendaJugador.setSize(120, 193);
			ImageIcon imTienda = new ImageIcon("imagenes/lewan.gif");
			ImageIcon imcdTienda= new ImageIcon(imTienda.getImage().getScaledInstance(lblTiendaJugador.getWidth(), lblTiendaJugador.getHeight(), Image.SCALE_DEFAULT));
			lblTiendaJugador.setIcon(imcdTienda);
			JPanel p = new JPanel(new BorderLayout());
			JPanel pw = new JPanel();
			//JLabel l = new JLabel("          ");
			//pw.add(l);
			p.add(pw, BorderLayout.WEST);
			p.add(lblTiendaJugador,BorderLayout.CENTER);
			panelSeleccionarCarta.add(p);
		}
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		JScrollPane panelTiendaScroll = new JScrollPane(panelSeleccionarCarta);
		//panelTiendaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(panelTiendaScroll);
		*/
	}
	
	//hay que hacerlo estatic o algo para que se le pueda llamar desde TriviaVentana
	public static Jugador listaJugadoresInventario(List<Jugador> listaJugadoresInventario){
		List<Jugador> hola = new ArrayList<>();
		
		JPanel panelSeleccionarCarta = new JPanel(new GridLayout(0, 2));
		for (Jugador carta : listaJugadoresInventario) {
			JLabel lblTiendaJugador = new JLabel();
			lblTiendaJugador.setSize(102, 164);
			//lblTiendaJugador.setSize(120, 193);
			ImageIcon imTienda = new ImageIcon(carta.getRutaFoto());
			ImageIcon imcdTienda= new ImageIcon(imTienda.getImage().getScaledInstance(lblTiendaJugador.getWidth(), lblTiendaJugador.getHeight(), Image.SCALE_DEFAULT));
			lblTiendaJugador.setIcon(imcdTienda);
			JPanel p = new JPanel(new BorderLayout());
			JPanel pw = new JPanel();
			p.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println(carta.getNombre());
					hola.add(carta);
					System.out.println(hola.size());
				}
			});
			p.add(pw, BorderLayout.WEST);
			p.add(lblTiendaJugador,BorderLayout.CENTER);
			panelSeleccionarCarta.add(p);
		}
		//frame.getContentPane().setLayout(new BorderLayout(0, 0));
		JScrollPane panelTiendaScroll = new JScrollPane(panelSeleccionarCarta);
		panelTiendaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//frame.getContentPane().add(panelTiendaScroll);
		
		
		System.out.println(hola.get(0));
		return hola.get(0);
		
	}
	
	//hay que hacerlo estatic o algo para que se le pueda llamar desde TriviaVentana
	public void listaPorterosInventario(List<Portero> listaPorterosInventario) {
		
		JPanel panelSeleccionarCarta = new JPanel(new GridLayout(0, 2));
		for (Carta carta : listaPorterosInventario) {
			JLabel lblTiendaJugador = new JLabel();
			lblTiendaJugador.setSize(102, 164);
			//lblTiendaJugador.setSize(120, 193);
			ImageIcon imTienda = new ImageIcon(carta.getRutaFoto());
			ImageIcon imcdTienda= new ImageIcon(imTienda.getImage().getScaledInstance(lblTiendaJugador.getWidth(), lblTiendaJugador.getHeight(), Image.SCALE_DEFAULT));
			lblTiendaJugador.setIcon(imcdTienda);
			JPanel p = new JPanel(new BorderLayout());
			JPanel pw = new JPanel();
			p.add(pw, BorderLayout.WEST);
			p.add(lblTiendaJugador,BorderLayout.CENTER);
			panelSeleccionarCarta.add(p);
		}
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		JScrollPane panelTiendaScroll = new JScrollPane(panelSeleccionarCarta);
		panelTiendaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(panelTiendaScroll);
				
	}

}
