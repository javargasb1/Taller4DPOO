package uniandes.dpoo.taller4.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;


import uniandes.dpoo.taller4.interfaz.Ventana;

public class PanelOpciones extends JPanel implements ActionListener
{
	private static final String BOTON_1 = "BOTON 1";
	private static final String BOTON_2 = "BOTON 2";
	private static final String BOTON_3 = "BOTON 3";
	// private static final String BOTON_4 = "BOTON 4";
	private Ventana padre;

	public PanelOpciones(Ventana laVentana)
	{
		padre = laVentana;

		setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JButton boton1 = new JButton("Nuevo Juego");
        boton1.addActionListener(this);
        boton1.setActionCommand(BOTON_1);
        constraints.gridy = 0;
        add(boton1, constraints);

        JButton boton2 = new JButton("Reiniciar");
        boton2.addActionListener(this);
        boton2.setActionCommand(BOTON_2);
        constraints.gridy = 1;
        add(boton2, constraints);

        JButton boton3 = new JButton("Top 10");
        boton3.addActionListener(this);
        boton3.setActionCommand(BOTON_3);
        constraints.gridy = 2;
        add(boton3, constraints);
        
        /**
        JButton boton4 = new JButton("Cambiar Jugador");
        boton4.addActionListener(this);
        boton4.setActionCommand(BOTON_4);
        constraints.gridy = 3;
        add(boton4, constraints);
        */
    }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String comando = e.getActionCommand();

		if (comando.equals(BOTON_1))
		{
			System.out.println("Boton 1");
			padre.nuevoTablero();
		}
		else if (comando.equals(BOTON_2))
		{
			System.out.println("Boton 2");
			padre.reiniciar();
		}
		else if (comando.equals(BOTON_3))
		{
			System.out.println("Boton 3");
			padre.mostrarTop10();
		}
		
		/**
		else if (comando.equals(BOTON_4))
		{
			System.out.println("Boton 4");
		}
		*/
	}
}
