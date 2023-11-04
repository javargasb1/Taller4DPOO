package uniandes.dpoo.taller4.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class PanelTablero extends JPanel implements MouseListener 
{
	private boolean[][] tablero; // Tu matriz booleana
	private int rows;
	private int cols;
    private Ventana padre;

    public PanelTablero(Ventana laVentana) 
    {
    	padre = laVentana;
    	tablero = padre.darTablero();
    	rows = tablero[0].length;
    	cols = tablero.length;
        setPreferredSize(new Dimension(400, 400)); 
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int cellSize = Math.min(getWidth() / cols, getHeight() / rows);
        
        

        for (int row = 0; row < rows; row++) 
        {
            for (int col = 0; col < cols; col++) 
            {
                int x = col * cellSize;
                int y = row * cellSize;
                
                Rectangle2D rectangle = new Rectangle2D.Double(x, y, cellSize, cellSize);
                if (tablero[row][col])
                {
                    g2d.setColor(Color.WHITE); 
                    g2d.fill(rectangle);

                    g2d.setColor(Color.BLACK); 
                    g2d.draw(rectangle);
                }
                else
                {
                    g2d.setColor(Color.BLACK); 
                    g2d.fill(rectangle);

                    g2d.setColor(Color.WHITE);
                    g2d.draw(rectangle);
                }               	
            }
        }
    }
   
    
   
    public void mousePressed(MouseEvent e)
    {
    	int anchoCasilla = getWidth() / cols;
        int altoCasilla = getHeight() / rows;
        
        int fila = e.getY() / altoCasilla;
        int columna = e.getX() / anchoCasilla;
        
        try {
			padre.jugar(fila, columna);
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public void actualizarTablero(boolean[][] nuevoTablero) 
    {
        tablero = nuevoTablero;
        rows = tablero[0].length;
    	cols = tablero.length;
        repaint(); 
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}