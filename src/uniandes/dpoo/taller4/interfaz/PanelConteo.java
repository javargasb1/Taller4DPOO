package uniandes.dpoo.taller4.interfaz;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelConteo extends JPanel
{
	private int jugadas = 0;
	private JLabel numjugadasLabel;
	private JTextField jugadorText;
	
	public PanelConteo()
	{
        JLabel jugadaLabel = new JLabel("Jugadas: ");
        jugadaLabel.setFont(new Font("Courier New", Font.BOLD, 18));
        
        numjugadasLabel = new JLabel(Integer.toString(jugadas));
        numjugadasLabel.setFont(new Font("Courier New", Font.BOLD, 18));

        JLabel jugadorLabel = new JLabel("Jugador:");
        jugadorLabel.setFont(new Font("Courier New", Font.BOLD, 18));
        
        jugadorText = new JTextField(20);
        jugadorText.setFont(new Font("Courier New", Font.BOLD, 18));

        add(jugadaLabel);
        add(numjugadasLabel);
        add(Box.createHorizontalStrut(10));
        add(jugadorLabel);
        add(jugadorText);
	}
	
	public void setJugadas(int jugadas) 
	{
		this.jugadas = jugadas;
	}

	public void setNumjugadasLabel() 
	{
		numjugadasLabel.setText(Integer.toString(jugadas));
	}
	
	public String getNombreJugador()
	{
		return jugadorText.getText();
	}
	
}