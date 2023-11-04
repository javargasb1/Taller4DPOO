package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.Random;

import com.formdev.flatlaf.FlatLightLaf;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

public class Ventana extends JFrame
{
	private Tablero tablero;
	private PanelTablero panelTablero;
	private PanelOpciones opciones;
	private PanelConteo conteo;
	private static PanelConfig config;
	
	private int tamano;
	private String dificultad;
	
	Top10 ranking = new Top10();
	
	public Ventana()
	{
		setLayout(new BorderLayout());
		
		
		opciones = new PanelOpciones(this);
		add(opciones,BorderLayout.EAST);
		
		config = new PanelConfig();
		config.setBackground(Color.MAGENTA);
		add(config,BorderLayout.NORTH);
		
		conteo = new PanelConteo();
		add(conteo,BorderLayout.SOUTH);
		
		paramTablero();
		iniciarTablero();
		panelTablero = new PanelTablero(this);
		add(panelTablero, BorderLayout.CENTER);
		
		setTitle("Lights Out");
		setSize(700, 700);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	public static void main(String[] args)
	{
		FlatLightLaf.install();
		new Ventana();
	}
	
	public void paramTablero()
	{
		String tamanoxtamano = config.getSeleccionComboBox();
		String[] partes = tamanoxtamano.split("x");
	    tamano = Integer.parseInt(partes[0]);
	    
	    dificultad = config.getSeleccionJRadioButton();
	}
	
	public void iniciarTablero() 
	{
		tablero = new Tablero(tamano);
		
		Random rand = new Random();
		int num = rand.nextInt(5) + 5;
		if (dificultad.equals("Facil"))
		{
			tablero.desordenar(num);
		}
		else if (dificultad.equals("Medio"))
		{
			num = rand.nextInt(5) + 15;
			tablero.desordenar(num);
		}
		else if (dificultad.equals("Dificil"))
		{
			num = rand.nextInt(5) + 25;
			tablero.desordenar(num);
		}		
	}
	
	public boolean[][] darTablero()
	{
		return tablero.darTablero();
	}
	
	public void nuevoTablero()
	{
		paramTablero();
		iniciarTablero();
		panelTablero.actualizarTablero(tablero.darTablero());
		
		int jugadas = tablero.darJugadas();
		conteo.setJugadas(jugadas);
		conteo.setNumjugadasLabel();
	}
	
	public void reiniciar()
	{
		tablero.reiniciar();
		panelTablero.actualizarTablero(tablero.darTablero());
		
		int jugadas = tablero.darJugadas();
		conteo.setJugadas(jugadas);
		conteo.setNumjugadasLabel();
	}

	public void jugar(int fila, int columna) throws FileNotFoundException, UnsupportedEncodingException 
	{
		tablero.jugar(fila, columna);
		boolean[][] nuevoTablero = tablero.darTablero();
		panelTablero.actualizarTablero(nuevoTablero);
		
		int jugadas = tablero.darJugadas();
		conteo.setJugadas(jugadas);
		conteo.setNumjugadasLabel();
		
		if (tablero.tableroIluminado())
		{
			mostrarMensajeVictoria();
			guardarJugador();
			nuevoTablero();
		}
	}
	
	private void mostrarMensajeVictoria() 
	{
		String puntaje = Integer.toString(tablero.calcularPuntaje());
		String mensaje = "¡Has ganado! \nTu puntaje fue de: " + puntaje;
        JOptionPane.showMessageDialog(null, mensaje, "Victoria", JOptionPane.INFORMATION_MESSAGE);
    }
	
	private void guardarJugador() throws FileNotFoundException, UnsupportedEncodingException
	{
		int puntaje = tablero.calcularPuntaje();
		String nombreJugador = conteo.getNombreJugador();
		boolean resultado = ranking.esTop10(puntaje);
		if (resultado)
		{
			ranking.agregarRegistro(nombreJugador, puntaje);
			File archivo = new File("data/top10.csv");
			ranking.salvarRecords(archivo);
		}
	}

	public void mostrarTop10() 
	{
		JDialog Top10puntajes = new JDialog();
		Top10puntajes.setSize(700, 700);
		Top10puntajes.setModal(true);
		Top10puntajes.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Top10puntajes.setTitle("Top 10");
		
		File archivo = new File("data/top10.csv");
		ranking.cargarRecords(archivo);
		Collection<RegistroTop10> registro = ranking.darRegistros();
		List<RegistroTop10> priorityList = new ArrayList<>(registro);

        // Crear el modelo de lista
        DefaultListModel<String> listModel = new DefaultListModel<>();
        int i = 1;
        for (RegistroTop10 element : priorityList) 
        {
        	String texto = Integer.toString(i) + ". "+ element.toString();
            listModel.addElement(texto);
            i++;
        }

        // Crear el JList con el modelo de lista
        JList<String> jList = new JList<>(listModel);

        // Agregar el JList a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(jList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        Top10puntajes.add(scrollPane);
		Top10puntajes.setVisible(true);
	}
	
}