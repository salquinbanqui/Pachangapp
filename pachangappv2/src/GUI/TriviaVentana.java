package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

import dominioConHerencia.Carta;
import dominioConHerencia.Jugador;
import dominioConHerencia.Portero;

import java.awt.Insets;
import java.awt.Font;
import java.awt.Frame;

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
				
				//cuando nos funcione el inventario se cogera la lista de cartas de ahí,
				//pero por ahora se crean los jugadores a mano
				Jugador Messi = new Jugador("Messi", 96, 20, "url_de_la_carta");
				Portero Neuer = new Portero("Neuer", 97, 25, "url_de_la_carta");
				Portero Otro = new Portero("Otro", 91, 25, "url_de_la_carta");
				Jugador a = new Jugador("a", 92, 20, "url_de_la_carta");
				Jugador b = new Jugador("b", 93, 20, "url_de_la_carta");
				Jugador c = new Jugador("c", 95, 20, "url_de_la_carta");
				Jugador d = new Jugador("d", 94, 20, "url_de_la_carta");
				
				//creamos y añadimos las cartas a la lista
				//cuando tengamos bien hecho el inventario esto no hará falta
				List<Jugador> cartasDisponibles = new ArrayList<>();
				cartasDisponibles.add(Messi);
				cartasDisponibles.add(a);
				cartasDisponibles.add(b);
				cartasDisponibles.add(c);
				cartasDisponibles.add(d);

				//creamos una lista para almacenar los jugadores
				List<Jugador> jugadoresDisponibles = new ArrayList<>();
				
				//separamos las cartas y nos quedamos solo con los jugadores
				for (Jugador carta : cartasDisponibles) {
					if (carta.getClass().toString().equals("class dominioConHerencia.Jugador")) {
						jugadoresDisponibles.add(carta);
					}
				}
				//FALTARÍA CREAR EL JOPTIONPANE O UNA VENTANA NUEVA PARA PODER ELEGIR EL JUGADOR QUE SE QUIERA COLOCAR
				//HAY QUE RECORRER LA LISTA DEL INVENTARIO PARA IR COLOCANDO LOS JUGADORES (TODOS MENOS LOS QUE YA ESTEN
				//COLOCADOS EN EL CAMPO YA) Y QUE AL SELECCIONARLO SE REEMPLACE POR EL JUGADOR QUE ESTE EN EL MOMENTO
				
			}
		});


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
		btnEquipoCargar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEquipoCargar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
				
				//creamos una lista para almacenar los porteros y otra para los jugadores
				List<Carta> listaJugador = new ArrayList<>();
				List<Carta> listaPortero = new ArrayList<>();
				
				//separamos las cartas en porteros y jugadores para manipularlos por separado
				for (Carta carta : listaCartas) {
					if (carta.getClass().toString().equals("class dominioConHerencia.Jugador")) {
						listaJugador.add(carta);
					}else if (carta.getClass().toString().equals("class dominioConHerencia.Portero")) {
						listaPortero.add(carta);
					}
				}
				
				//He dejado los comentarios para que se entienda mejor la movida esta, cuando os ubiqueis los podeis quitar
				System.out.println("portero size " + listaPortero.size());
				System.out.println("jugador size " + listaJugador.size());
				
				for (Carta carta : listaPortero) {
					System.out.println("Portero: " + carta.getNombre());
				}
				
				for (Carta carta : listaJugador) {
					System.out.println("Jugador: " + carta.getNombre());
				}

				//se crea la listaMejorEquipo, que ira almacenando las mejores cartas de cada posicion
				List<Carta> listaMejorEquipo = new ArrayList<>();
				
				//se crea la listaTemporal, es una lista que usaremos para ir metiendo las mejores cartas en cada iteracion
				List<Carta> listaTemporal = new ArrayList<>();
				
				//llamamos al metodo recursivo para hallar al portero con mayor valoración
				listaPortero = mejorEquipoR(listaPortero, listaTemporal, 1);
				System.out.println("size listaPortero: "+listaPortero.size());
				System.out.println("size listaMejorequipo: "+listaMejorEquipo.size());
				
				//añadimos el mejor portero a la lista de mejor equipos
				for (Carta carta : listaPortero) {
					listaMejorEquipo.add(carta);
					System.out.println("a listaMejorEquipo: "+carta.getNombre());
				}
				System.out.println("lista listaMejorequipo: "+listaMejorEquipo.size());
				
				System.out.println("size listaTemporal: "+listaTemporal.size());
				
				//vaciamos la lista temporal para volver a usarla en la llamada recursiva para los jugadores
				listaTemporal.clear();
				System.out.println("size listaTemporal, deberia ser 0: "+listaTemporal.size());
				System.out.println("ahora toca lista rcursiva a Juador");
				
				//llamada recursiva para hallar a los 4 jugadores con mayor valoración
				listaJugador = mejorEquipoR(listaJugador, listaTemporal, 4);
				
				//añadimos los mejores 4 jugadores a la lista de mejor equipo
				for (Carta carta : listaJugador) {
					listaMejorEquipo.add(carta);
					System.out.println("a listaMejorEquipo: "+carta.getNombre());
				}
				
				System.out.println("size listaMejorEquipo, deberia ser 5: "+listaMejorEquipo.size());
				
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
	
    public static String invertirReverse(String str) {
    	StringBuffer builder = new StringBuffer(str);
    	builder.reverse();
    	return builder.toString();
    }
    
	
    
    
    
    
    //listaCartas: una lista que contiene todas las cartas de un tipoconcreto (Portero o Jugador) que tiene el usuario
    //mejoresCartasRec: una lista donde se guardan las mejores "numStop" cartas contenidas en listaCartas
    //numStop: un int que representa el numero de cartas que deberian estar almacenadas en la lista mejoresCartasRec
	public static List<Carta> mejorEquipoR(List<Carta> listaCartas, List<Carta> mejoresCartasRec, int numStop) {
		 
		//los prints es para entender mejor el proceso, se pueden borrar sin problema
		System.out.println("Lista cartas size: " + listaCartas.size());
		for (Carta carta : listaCartas) {
			System.out.println(carta.getPuntos());
		}
		System.out.println("Mejores cartas size: " + mejoresCartasRec.size());
		
		
		//caso base, si la lista ya tiene los elementos que se piden por parametro return mejoresCartasRec
		if(mejoresCartasRec.size() == numStop) {
			
			System.out.println("despues de esto deberia acabar, ns pq sigue");
			
			//NO ENTINEDO PORQUE EL METODO NO ACABA AL LLEGAR A ESTE RETURN, YA LO MIRARÉ
			return mejoresCartasRec;
			
		}else { //caso recursivo, si la lista tiene menos elementos de los que se piden por parametro
			int num = 0;
			
			
			//se elige la carta con mayor valoracion de la lista listaCartas
			Carta jugadorMaxValoracion = null;
			for (Carta jugador : listaCartas) {
				if(jugador.getPuntos() > num) {
					num = jugador.getPuntos();
					jugadorMaxValoracion = jugador;
					System.out.println("puntos del jugador max valoracion: " + jugadorMaxValoracion.getPuntos());
				}
			}
			
			//se añade la carta con mayor valoracion en la lista mejoresCartasRec y se elimina de listaCartas
			mejoresCartasRec.add(jugadorMaxValoracion);
			System.out.println("se ha añadido " + jugadorMaxValoracion.getNombre());
			listaCartas.remove(jugadorMaxValoracion);
			
			//se repite el proceso de forma recursiva hasta que el tamaño de cartas almacenadas en mejoresCartasRec sea el especificado por parametro
			mejorEquipoR(listaCartas, mejoresCartasRec, numStop);
			
			
		}
		System.out.println("hola");
		return mejoresCartasRec;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	


}