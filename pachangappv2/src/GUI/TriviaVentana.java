package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import javax.swing.UIManager;

import base_de_datos.BD;
import dominioConHerencia.Carta;
import dominioConHerencia.Jugador;
import dominioConHerencia.Portero;
import login_registro.RegistrarVentana;
import quiz.Quiz;
import quiz.Quiz2;

import java.awt.Insets;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;

public class TriviaVentana extends JFrame implements ActionListener {

	private JFrame frmTriviafut;
	private Connection con;


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
	
	
	
	enum Posiciones{
		DEFENSA_ABAJO, 
		DEFENSA_ARRIBA, 
		DELANTERO_ARRIBA, 
		DELANTERO_ABAJO, 
		PORTERO};

	/**
	 * Create the application.
	 */
		
		
	
	public TriviaVentana() {
		/*
		this.addWindowListener(new WindowAdapter() {
        
	        @Override
	        public void windowClosing(WindowEvent arg0) {
	             
	        	System.out.println("funciona");
	        }
	        
        
        
        
	    });
		*/
		
		con = BD.initBD("data//DBTrivia.db");
		//BD.crearTablaJugador(con);
		//BD.crearTablaPortero(con);
		//BD.crearTablaUsuario(con);
		//BD.cargarCartas(con);
		//Jugador cart = (Jugador) BD.obtenerDatosCarta(con, "Salah");
		//System.out.println(cart);
		
		Map<Posiciones,Carta> cartasFicheros = new HashMap<>();
		
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
		
	
		
		// SECCIÓN DE CARTAS (INVENTARIO)

		JPanel panelCartasJugador = new JPanel(new GridLayout(0, 2));
		List<Carta> cartasInventario = new ArrayList<>();
		//Jugador Messi = new Jugador("Messi", 96, 20, "imagenes/messi.gif");
		//cartasInventario.add(Messi);
		System.out.println("Cartas inventario Size: " + cartasInventario.size());

		for (Carta carta : cartasInventario) {
			JLabel lblTiendaJugador = new JLabel();
			//lblTiendaJugador.setSize(102, 164);
			lblTiendaJugador.setSize(120, 193);
			ImageIcon imTienda = new ImageIcon(carta.getRutaFoto());
			ImageIcon imcdTienda= new ImageIcon(imTienda.getImage().getScaledInstance(lblTiendaJugador.getWidth(), lblTiendaJugador.getHeight(), Image.SCALE_DEFAULT));
			lblTiendaJugador.setIcon(imcdTienda);
			JPanel p = new JPanel(new BorderLayout());
			JPanel pw = new JPanel();
			JLabel l = new JLabel("          ");
			pw.add(l);
			p.add(pw, BorderLayout.WEST);
			p.add(lblTiendaJugador,BorderLayout.CENTER);
			panelCartasJugador.add(p);

		}

		JScrollPane panelCartasScroll = new JScrollPane(panelCartasJugador);
		panelCartasScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelGeneral.add(panelCartasScroll);

		
		//SECCIÓN DE TIENDA
		
		JPanel panelTiendaJugador = new JPanel(new GridLayout(0, 2));
		
		List<Carta> tiendaD = new ArrayList<>();
		tiendaD.addAll(BD.cargarCartas(con));
		for (Carta carta : tiendaD) {
			JLabel lblTiendaJugador = new JLabel();
			//lblTiendaJugador.setSize(102, 164);
			lblTiendaJugador.setSize(120, 193);
			ImageIcon imTienda = new ImageIcon(carta.getRutaFoto());
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
		panelTiendaJugador.addMouseListener(new MouseListener() {

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
				for (Carta carta : tiendaD) {
					System.out.println(carta.getNombre());
				}

				System.out.println("---------------------------");

				System.out.println(cartasInventario.size());
				for (Carta carta : cartasInventario) {
					System.out.println(carta);
				}


				String comprarJugador = JOptionPane.showInputDialog("Escribe el nombre del jugador a comprar: \n*Los nombres empiezan por mayúscula*");

				for (Carta carta : tiendaD) { //iteramos x todas las cartas e¡en la tienda
					System.out.println("1");
					if (carta.getNombre().equals(comprarJugador)) { //si la carta existe
						System.out.println("2");
						if (cartasInventario.contains(carta)) {
							JOptionPane.showMessageDialog(null, "Ya tienes a " + carta.getNombre() + " en el inventario.");
						}else {

							int compraResult = JOptionPane.showConfirmDialog(null, "Seguro que quieres comprar a " + carta.getNombre() + " por " + carta.getCoste() + " monedas?", "Confirmar compra", JOptionPane.YES_NO_OPTION);

							if (compraResult == 0) { //0 == Yes, 1 == No
								cartasInventario.add(carta);
								System.out.println("carta añadida: " + carta.getNombre());
							}
							System.out.println("cartas inventario: " + cartasInventario.size());
						}
					}
				}

			}


		});

		
		panelGeneral.add(panelTiendaScroll);


		// SECCIÓN DE EQUIPO

				JPanel panelEquipo = new JPanel();
				panelEquipo.setPreferredSize(new Dimension(10, 50));
				// panelEquipo.setBackground(UIManager.getColor("Button.darkShadow"));
				panelGeneral.add(panelEquipo);
				panelEquipo.setLayout(new BorderLayout());

				PanelConFondo panelEquipoCentro = new PanelConFondo(fondo.getImage());
				panelEquipoCentro.setLayout(new GridLayout(3, 3));
				
				
				
				Map<String, Carta> mapaAlineacion = new HashMap<>();
				
				//crea los labels de los jugadores del campo reescalados
				JLabel lblEquipoDefArriba = new JLabel();
				lblEquipoDefArriba.setSize(102, 164);
				ImageIcon im = new ImageIcon("imagenes\\chiellini.gif");
				ImageIcon imcd = new ImageIcon(
						im.getImage().getScaledInstance(lblEquipoDefArriba.getWidth(), lblEquipoDefArriba.getHeight(), Image.SCALE_DEFAULT));
				lblEquipoDefArriba.setIcon(imcd);
				lblEquipoDefArriba.setOpaque(false);
				lblEquipoDefArriba.addMouseListener(new MouseListener() {
					
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
						
					
						//creamos una lista para almacenar los jugadores
						HashSet<Jugador> jugadoresDisponibles = new HashSet<>();
						jugadoresDisponibles.addAll(BD.cargarJugadores(con));
						
						JComboBox<Jugador> combo = new JComboBox<>();
						
						for (Jugador jugador : jugadoresDisponibles) {
							combo.addItem(jugador);
						}
						
						JOptionPane.showMessageDialog(null, combo, "Reservas", JOptionPane.QUESTION_MESSAGE);
						
						
						for (Jugador jugador : jugadoresDisponibles) {
							if (combo.getSelectedItem().toString().equals(jugador.toString())) {
								
								cartasFicheros.put(Posiciones.DEFENSA_ARRIBA, jugador);

								lblEquipoDefArriba.setSize(102, 164);
								ImageIcon img = new ImageIcon(jugador.getRutaFoto());


								ImageIcon imgcd = new ImageIcon(img.getImage().getScaledInstance(lblEquipoDefArriba.getWidth(), lblEquipoDefArriba.getHeight(), Image.SCALE_DEFAULT));
								
								lblEquipoDefArriba.setIcon(imgcd);
							
								mapaAlineacion.put(lblEquipoDefArriba.getName(), jugador);

							}
						}
						

						
					}
				});

				

				JLabel lblEquipoDelanteroArriba = new JLabel();
				lblEquipoDelanteroArriba.setSize(102, 164);
				ImageIcon im2 = new ImageIcon("");
				ImageIcon imcd2 = new ImageIcon(
						im2.getImage().getScaledInstance(lblEquipoDelanteroArriba.getWidth(), lblEquipoDelanteroArriba.getHeight(), Image.SCALE_DEFAULT));
				lblEquipoDelanteroArriba.setIcon(imcd2);
				lblEquipoDelanteroArriba.setOpaque(false);
				lblEquipoDelanteroArriba.addMouseListener(new MouseListener() {
					
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
						
					
						//creamos una lista para almacenar los jugadores
						HashSet<Jugador> jugadoresDisponibles = new HashSet<>();
						jugadoresDisponibles.addAll(BD.cargarJugadores(con));
						
						JComboBox<Jugador> combo = new JComboBox<>();
						
						for (Jugador jugador : jugadoresDisponibles) {
							combo.addItem(jugador);
						}
						
						JOptionPane.showMessageDialog(null, combo, "Reservas", JOptionPane.QUESTION_MESSAGE);
						
						
						for (Jugador jugador : jugadoresDisponibles) {
							if (combo.getSelectedItem().toString().equals(jugador.toString())) {
								
								cartasFicheros.put(Posiciones.DELANTERO_ARRIBA, jugador);
								
								lblEquipoDelanteroArriba.setSize(102, 164);
								ImageIcon img = new ImageIcon(jugador.getRutaFoto());


								ImageIcon imgcd = new ImageIcon(img.getImage().getScaledInstance(lblEquipoDelanteroArriba.getWidth(), lblEquipoDelanteroArriba.getHeight(), Image.SCALE_DEFAULT));
								
								lblEquipoDelanteroArriba.setIcon(imgcd);
							
								mapaAlineacion.put(lblEquipoDelanteroArriba.getName(), jugador);

							}
						}
						

						
					}
				});

				JLabel lblEquipoPortero = new JLabel();
				lblEquipoPortero.setSize(102, 164);
				ImageIcon im3 = new ImageIcon("imagenes\\neuer.gif");
				ImageIcon imcd3 = new ImageIcon(
						im3.getImage().getScaledInstance(lblEquipoPortero.getWidth(), lblEquipoPortero.getHeight(), Image.SCALE_DEFAULT));
				lblEquipoPortero.setIcon(imcd3);
				lblEquipoPortero.setOpaque(false);
				lblEquipoPortero.addMouseListener(new MouseListener() {
					
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
						
					
						//creamos una lista para almacenar los porteros
						HashSet<Portero> porterosDisponibles = new HashSet<>();
						porterosDisponibles.clear();
						porterosDisponibles.addAll(BD.cargarPorteros(con));
						
						//el iterator lo hago pq ns pq el primer portero que entraba era null y daba errores
						Iterator<Portero> it = porterosDisponibles.iterator();
						
						while(it.hasNext()) {
							if (it.next() == null) {
								it.remove();
							}
						}
						
						JComboBox<Portero> combo = new JComboBox<>();
						
						for (Portero portero : porterosDisponibles) {
							combo.addItem(portero);
						}
						
						JOptionPane.showMessageDialog(null, combo, "Porteros", JOptionPane.QUESTION_MESSAGE);
						
						
						for (Portero portero : porterosDisponibles) {
							if (combo.getSelectedItem().toString().equals(portero.toString())) {
								
								cartasFicheros.put(Posiciones.PORTERO, portero);
								

								lblEquipoPortero.setSize(102, 164);
								ImageIcon img = new ImageIcon(portero.getRutaFoto());


								ImageIcon imgcd = new ImageIcon(img.getImage().getScaledInstance(lblEquipoPortero.getWidth(), lblEquipoPortero.getHeight(), Image.SCALE_DEFAULT));
								
								lblEquipoPortero.setIcon(imgcd);
							
								mapaAlineacion.put(lblEquipoPortero.getName(), portero);

							}
						}
						

						
					}
				});
				
				//System.out.println(im3.getDescription().toString());

				JLabel lblEquipoDelanteroDebajo = new JLabel();
				lblEquipoDelanteroDebajo.setSize(102, 164);
				ImageIcon im4 = new ImageIcon("imagenes\\ronaldo.gif");
				ImageIcon imcd4 = new ImageIcon(
						im4.getImage().getScaledInstance(lblEquipoDelanteroDebajo.getWidth(), lblEquipoDelanteroDebajo.getHeight(), Image.SCALE_DEFAULT));
				lblEquipoDelanteroDebajo.setIcon(imcd4);
				lblEquipoDelanteroDebajo.setOpaque(false);
				lblEquipoDelanteroDebajo.addMouseListener(new MouseListener() {
					
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
						
					
						//creamos una lista para almacenar los jugadores
						HashSet<Jugador> jugadoresDisponibles = new HashSet<>();
						jugadoresDisponibles.addAll(BD.cargarJugadores(con));
						
						JComboBox<Jugador> combo = new JComboBox<>();
						
						for (Jugador jugador : jugadoresDisponibles) {
							combo.addItem(jugador);
						}
						
						JOptionPane.showMessageDialog(null, combo, "Reservas", JOptionPane.QUESTION_MESSAGE);
						
						
						for (Jugador jugador : jugadoresDisponibles) {
							if (combo.getSelectedItem().toString().equals(jugador.toString())) {
								
								cartasFicheros.put(Posiciones.DELANTERO_ABAJO, jugador);

								lblEquipoDelanteroDebajo.setSize(102, 164);
								ImageIcon img = new ImageIcon(jugador.getRutaFoto());


								ImageIcon imgcd = new ImageIcon(img.getImage().getScaledInstance(lblEquipoDelanteroDebajo.getWidth(), lblEquipoDelanteroDebajo.getHeight(), Image.SCALE_DEFAULT));
								
								lblEquipoDelanteroDebajo.setIcon(imgcd);
							
								mapaAlineacion.put(lblEquipoDelanteroDebajo.getName(), jugador);

							}
						}
						

						
					}
				});

				JLabel lblEquipoDefensaDebajo = new JLabel();
				lblEquipoDefensaDebajo.setSize(102, 164);
				ImageIcon im5 = new ImageIcon("imagenes\\pique.gif");
				ImageIcon imcd5 = new ImageIcon(
						im5.getImage().getScaledInstance(lblEquipoDefensaDebajo.getWidth(), lblEquipoDefensaDebajo.getHeight(), Image.SCALE_DEFAULT));
				lblEquipoDefensaDebajo.setIcon(imcd5);
				lblEquipoDefensaDebajo.setOpaque(false);
				lblEquipoDefensaDebajo.addMouseListener(new MouseListener() {
					
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
						
					
						//creamos una lista para almacenar los jugadores
						HashSet<Jugador> jugadoresDisponibles = new HashSet<>();
						jugadoresDisponibles.addAll(BD.cargarJugadores(con));
						
						JComboBox<Jugador> combo = new JComboBox<>();
						
						for (Jugador jugador : jugadoresDisponibles) {
							combo.addItem(jugador);
						}
						
						JOptionPane.showMessageDialog(null, combo, "Reservas", JOptionPane.QUESTION_MESSAGE);
						
						
						for (Jugador jugador : jugadoresDisponibles) {
							if (combo.getSelectedItem().toString().equals(jugador.toString())) {
								
								cartasFicheros.put(Posiciones.DEFENSA_ABAJO, jugador);

								lblEquipoDefensaDebajo.setSize(102, 164);
								ImageIcon img = new ImageIcon(jugador.getRutaFoto());


								ImageIcon imgcd = new ImageIcon(img.getImage().getScaledInstance(lblEquipoDefensaDebajo.getWidth(), lblEquipoDefensaDebajo.getHeight(), Image.SCALE_DEFAULT));
								
								lblEquipoDefensaDebajo.setIcon(imgcd);
							
								mapaAlineacion.put(lblEquipoDefensaDebajo.getName(), jugador);

							}
						}
						

						
					}
				});
		
		
		
		
		
		

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
		btnEquipoCargar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		
		
		btnEquipoCargar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				
				
				try {
					BufferedReader br = new BufferedReader(new FileReader("data/Personas.txt")); //Hemos abierto el fichero
					String linea = ""; //Leo la línea de títulos del fichero
					
					//Leemos la línea del fichero
					
					while((linea = br.readLine()) != null) {
						
						String [] parametros = linea.split("¿");
												
						//fila 1
						
						Posiciones variablePosiciones = Posiciones.valueOf(parametros[0]);
						
						
						switch (variablePosiciones) {
						case PORTERO: {
						
							lblEquipoPortero.setIcon(new ImageIcon(new ImageIcon(parametros[4]).getImage().getScaledInstance(lblEquipoDelanteroArriba.getWidth(), lblEquipoDelanteroArriba.getHeight(), Image.SCALE_DEFAULT)));
							cartasFicheros.put(variablePosiciones, new Portero(parametros[1], Integer.parseInt(parametros[2]), Integer.parseInt(parametros[3]), parametros[1]));
							break;
						}
						case DEFENSA_ABAJO: {

							lblEquipoDefensaDebajo.setIcon(new ImageIcon(new ImageIcon(parametros[4]).getImage().getScaledInstance(lblEquipoDelanteroArriba.getWidth(), lblEquipoDelanteroArriba.getHeight(), Image.SCALE_DEFAULT)));
							cartasFicheros.put(variablePosiciones, new Jugador(parametros[1], Integer.parseInt(parametros[2]), Integer.parseInt(parametros[3]), parametros[1]));
							break;
						}
						case DEFENSA_ARRIBA: {
							
							lblEquipoDefArriba.setIcon(new ImageIcon(new ImageIcon(parametros[4]).getImage().getScaledInstance(lblEquipoDelanteroArriba.getWidth(), lblEquipoDelanteroArriba.getHeight(), Image.SCALE_DEFAULT)));
							cartasFicheros.put(variablePosiciones, new Jugador(parametros[1], Integer.parseInt(parametros[2]), Integer.parseInt(parametros[3]), parametros[1]));
							break;
						}
						case DELANTERO_ARRIBA: {
							
							lblEquipoDelanteroArriba.setIcon(new ImageIcon(new ImageIcon(parametros[4]).getImage().getScaledInstance(lblEquipoDelanteroArriba.getWidth(), lblEquipoDelanteroArriba.getHeight(), Image.SCALE_DEFAULT)));
							cartasFicheros.put(variablePosiciones, new Jugador(parametros[1], Integer.parseInt(parametros[2]), Integer.parseInt(parametros[3]), parametros[1]));
							break;
						}
						case DELANTERO_ABAJO: {
							
							lblEquipoDelanteroDebajo.setIcon(new ImageIcon(new ImageIcon(parametros[4]).getImage().getScaledInstance(lblEquipoDelanteroArriba.getWidth(), lblEquipoDelanteroArriba.getHeight(), Image.SCALE_DEFAULT)));
							cartasFicheros.put(variablePosiciones, new Jugador(parametros[1], Integer.parseInt(parametros[2]), Integer.parseInt(parametros[3]), parametros[1]));
							break;
						}
						
						default:
							throw new IllegalArgumentException("Unexpected value: " + variablePosiciones);
						}
						
					}
					br.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEquipoCargar.setPreferredSize(new Dimension(120, 40));
		panelEquipoBot.add(btnEquipoCargar);

		JButton btnEquipoGuardar = new JButton("Guardar equipo");
		btnEquipoGuardar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEquipoGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					File fichero = new File("data/Personas.txt");
					fichero.delete();
					fichero.createNewFile();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					//Crear un documento en blanco
					//PrintWriter pw = new PrintWriter("Personas.txt");
					//Crear un documento para añadir al final
					PrintWriter pw = new PrintWriter(new FileOutputStream("data/Personas.txt", true));
					pw.print("");
					
					//Escribo los datos en el documento
					
					for (Entry<Posiciones, Carta> carta : cartasFicheros.entrySet()) {
						
						pw.println(carta.getKey() + 
								"¿" + carta.getValue().getNombre() + 
								"¿" + carta.getValue().getPuntos() +
								"¿" + carta.getValue().getCoste() +
								"¿" + carta.getValue().getRutaFoto()
								);
						
					}
					
					
					//Cerrar el fichero
					pw.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					
				}
				
			}
		});
		btnEquipoGuardar.setPreferredSize(new Dimension(120, 40));
		panelEquipoBot.add(btnEquipoGuardar);
		
		
		JButton btnEquipoMejor = new JButton("Mejor Equipo");
		btnEquipoMejor.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEquipoMejor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//cuando nos funcione el inventario se cogera la lista de cartas de ahí,
				//pero por ahora se crean los jugadores a mano
				/*
				Jugador Messi = new Jugador("Messi", 96, 20, "url_de_la_carta");
				Portero Neuer = new Portero("Neuer", 97, 25, "url_de_la_carta");
				Portero Otro = new Portero("Otro", 91, 25, "url_de_la_carta");
				Jugador a = new Jugador("a", 92, 20, "url_de_la_carta");
				Jugador b = new Jugador("b", 93, 20, "url_de_la_carta");
				Jugador c = new Jugador("c", 95, 20, "url_de_la_carta");
				Jugador d = new Jugador("d", 94, 20, "url_de_la_carta");
				
				//creamos y añadimos las cartas a la lista
				List<Carta> listaCartas = new ArrayList<>();
				listaCartas.add(Messi);
				listaCartas.add(Neuer);
				listaCartas.add(Otro);
				listaCartas.add(a);
				listaCartas.add(b);
				listaCartas.add(c);
				listaCartas.add(d);
				*/
				
				//uso la lista de soporte esta para eliminar las cartas que sean null
				List<Carta> listaPorteroSupport = new ArrayList<>();
				listaPorteroSupport.addAll(BD.cargarPorteros(con));
				
				List<Carta> listaPortero = new ArrayList<>();
				for (Carta carta : listaPorteroSupport) {
					if (carta != null) {
						listaPortero.add(carta);
					}
				}
				
				List<Carta> listaJugador = new ArrayList<>();
				listaJugador.addAll(BD.cargarJugadores(con));
				
				//System.out.println("el portero" + listaPortero.get(0).getNombre());
				
				//DESCOMENTAR EN CASO DE ERROR
				/*
				System.out.println("portero size " + listaPortero.size());
				System.out.println("jugador size " + listaJugador.size());
				
				for (Carta carta : listaPortero) {
					System.out.println("Portero: " + carta.getNombre());
				}
				
				for (Carta carta : listaJugador) {
					System.out.println("Jugador: " + carta.getNombre());
				}
				*/
				
				//se crea la listaMejorEquipo, que ira almacenando las mejores cartas de cada posicion
				List<Carta> listaMejorEquipo = new ArrayList<>();
				
				//se crea la listaTemporal, es una lista que usaremos para ir metiendo las mejores cartas en cada iteracion
				List<Carta> listaTemporal = new ArrayList<>();
				
				//llamamos al metodo recursivo para hallar al portero con mayor valoración
				listaPortero = mejorEquipoR(listaPortero, listaTemporal, 1);
				
				//DESCOMENTAR EN CASO DE ERROR
				/*
				System.out.println("size listaPortero: "+listaPortero.size());
				System.out.println("size listaMejorequipo: "+listaMejorEquipo.size());
				*/
				
				//añadimos el mejor portero a la lista de mejor equipos
				for (Carta carta : listaPortero) {
					listaMejorEquipo.add(carta);
					
					//DESCOMENTAR EN CASO DE ERROR
					//System.out.println("a listaMejorEquipo: "+carta.getNombre());
				}
				//DESCOMENTAR EN CASO DE ERROR
				/*
				System.out.println("lista listaMejorequipo: "+listaMejorEquipo.size());
				
				System.out.println("size listaTemporal: "+listaTemporal.size());
				*/
				
				//vaciamos la lista temporal para volver a usarla en la llamada recursiva para los jugadores
				listaTemporal.clear();
				
				//DESCOMENTAR EN CASO DE ERROR
				/*
				System.out.println("size listaTemporal, deberia ser 0: "+listaTemporal.size());
				System.out.println("ahora toca lista rcursiva a Juador");
				*/
				
				//llamada recursiva para hallar a los 4 jugadores con mayor valoración
				listaJugador = mejorEquipoR(listaJugador, listaTemporal, 4);
				
				//añadimos los mejores 4 jugadores a la lista de mejor equipo
				for (Carta carta : listaJugador) {
					listaMejorEquipo.add(carta);
					
					//DESCOMENTAR EN CASO DE ERROR
					//System.out.println("a listaMejorEquipo: "+carta.getNombre());
				}
				
				//DESCOMENTAR EN CASO DE ERROR
				//System.out.println("size listaMejorEquipo, deberia ser 5: "+listaMejorEquipo.size());
				
				//se vacia la listaTemporal porsiaca, no esestrictamente necesario
				listaTemporal.clear();
				
				//se crea el array del mejor equipo disponible, con el mejor portero y los 4 mejores jugadores
				String arrayMejorEquipo = "Portero: ";
				int num = 1;
				for (Carta jugador : listaMejorEquipo) {
					arrayMejorEquipo += jugador.getNombre();
					arrayMejorEquipo += "\nJugador " + num + ": ";
					num += 1;
				}
				
				//se elimina el string "\nJugador 5:"
				arrayMejorEquipo = arrayMejorEquipo.substring(0, arrayMejorEquipo.length()-12);
				
				//se crea el JOptionPane displayeando el mejor equipo disponible
				JOptionPane.showMessageDialog(null, arrayMejorEquipo.toString(), "Equipo disponible con mayor valoración", 1);
				
				
			}
		});
		btnEquipoMejor.setPreferredSize(new Dimension(120, 40));
		panelEquipoBot.add(btnEquipoMejor);
		
		
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
		btnTrivia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Quiz2 quiz = new Quiz2();
				//frmTriviafut.dispose();
				
			}
		});
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
                panelCartasJugador.removeAll();
                for (Carta carta : cartasInventario) {
                        JLabel lblTiendaJugador = new JLabel();
                        //lblTiendaJugador.setSize(102, 164);
                        lblTiendaJugador.setSize(120, 193);
                        ImageIcon imTienda = new ImageIcon(carta.getRutaFoto());
                        ImageIcon imcdTienda= new ImageIcon(imTienda.getImage().getScaledInstance(lblTiendaJugador.getWidth(), lblTiendaJugador.getHeight(), Image.SCALE_DEFAULT));
                        lblTiendaJugador.setIcon(imcdTienda);
                        JPanel p = new JPanel(new BorderLayout());
                        JPanel pw = new JPanel();
                        JLabel l = new JLabel("          ");
                        pw.add(l);
                        p.add(pw, BorderLayout.WEST);
                        p.add(lblTiendaJugador,BorderLayout.CENTER);
                        panelCartasJugador.add(p);
                }
                System.out.println("Cartas inventario Size: " + cartasInventario.size());
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
	
    public static String invertirReverse(String str) {
    	StringBuffer builder = new StringBuffer(str);
    	builder.reverse();
    	return builder.toString();
    }
    
    
    // NO FUNCIONA, SERIA LA BASE PARA SACAR LAS COSAS DE panelCartaJugador Y METERLO EN BASE DE DATOS
    
    /*
    public static ArrayList<Carta> hacerInventario(){
    	ArrayList<Carta> inventario = new ArrayList<Carta>();
    	
    	for (int i = 0; i < panelCartaJugador.getRowCount(); i++) {
			 System.out.println("Registro número: "+i);
			 System.out.println("ID: "+TablaDatos.getValueAt(i, 0));
			 System.out.println("NOMBRE: "+TablaDatos.getValueAt(i, 1));
			 System.out.println("TELÉFONO: "+TablaDatos.getValueAt(i, 2));
			 }
    	
    	return inventario;
    }
    
    */
    
    
    
    
    //listaCartas: una lista que contiene todas las cartas de un tipoconcreto (Portero o Jugador) que tiene el usuario
    //mejoresCartasRec: una lista donde se guardan las mejores "numStop" cartas contenidas en listaCartas
    //numStop: un int que representa el numero de cartas que deberian estar almacenadas en la lista mejoresCartasRec
	public static List<Carta> mejorEquipoR(List<Carta> listaCartas, List<Carta> mejoresCartasRec, int numStop) {
		 
		//los prints es para entender mejor el proceso, se pueden borrar sin problema
		//DESCOMENTAR EN CASO DE ERROR
		/*
		System.out.println("Lista cartas size: " + listaCartas.size());
		for (Carta carta : listaCartas) {
			System.out.println(carta.getPuntos());
		}
		System.out.println("Mejores cartas size: " + mejoresCartasRec.size());
		*/
		
		//caso base, si la lista ya tiene los elementos que se piden por parametro return mejoresCartasRec
		if(mejoresCartasRec.size() == numStop) {
			
			//System.out.println("despues de esto deberia acabar, ns pq sigue");
			
			//NO ENTINEDO PORQUE EL METODO NO ACABA AL LLEGAR A ESTE RETURN, YA LO MIRARÉ
			//PARA QUE ESTE MAS LIMPIO DEBERÍA HABER HECHO OTRO METODO DE SOPORTE QUE LLAME A ESTE
			return mejoresCartasRec;
			
		}else { //caso recursivo, si la lista tiene menos elementos de los que se piden por parametro
			int num = 0;
			
			
			//se elige la carta con mayor valoracion de la lista listaCartas
			Carta jugadorMaxValoracion = null;
			for (Carta jugador : listaCartas) {
				if(jugador.getPuntos() > num) {
					num = jugador.getPuntos();
					jugadorMaxValoracion = jugador;
					
					//DESCOMENTAR EN CASO DE ERROR
					//System.out.println("puntos del jugador max valoracion: " + jugadorMaxValoracion.getPuntos());
				}
			}
			
			//se añade la carta con mayor valoracion en la lista mejoresCartasRec y se elimina de listaCartas
			mejoresCartasRec.add(jugadorMaxValoracion);
			
			//DESCOMENTAR EN CASO DE ERROR
			//System.out.println("se ha añadido " + jugadorMaxValoracion.getNombre());
			listaCartas.remove(jugadorMaxValoracion);
			
			//se repite el proceso de forma recursiva hasta que el tamaño de cartas almacenadas en mejoresCartasRec sea el especificado por parametro
			mejorEquipoR(listaCartas, mejoresCartasRec, numStop);
			
			
		}
		return mejoresCartasRec;
		
	}
	
	public static List<Carta> listaCartasInventario(){
		List<Carta> arrayCartasInventario = new ArrayList<>();
		
		//aqui habria que recorrer el inventario y añadir todas las cartas a este array, y luego devolverlo
		//este metodo se usaria para rellenar la ventana "SeleccionarEquipoCarta"
		
		
		//voy a rellenarlo a mano para hacer las pruebas
		Jugador Messi = new Jugador("Messi", 96, 20, "imagenes/messi.gif");
		Portero Neuer = new Portero("Neuer", 97, 25, "imagenes/Neuer.gif");
		Portero Otro = new Portero("Otro", 91, 25, "imagenes/lewan.gif");
		Jugador a = new Jugador("a", 92, 20, "imagenes/lewan.gif");
		Jugador b = new Jugador("b", 93, 20, "imagenes/lewan.gif");
		Jugador c = new Jugador("c", 95, 20, "imagenes/lewan.gif");
		Jugador d = new Jugador("d", 94, 20, "imagenes/lewan.gif");
		

		arrayCartasInventario.add(Messi);
		arrayCartasInventario.add(Neuer);
		arrayCartasInventario.add(Otro);
		arrayCartasInventario.add(a);
		arrayCartasInventario.add(b);
		arrayCartasInventario.add(c);
		arrayCartasInventario.add(d);
		
		return arrayCartasInventario;
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	


}