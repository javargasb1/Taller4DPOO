package uniandes.dpoo.taller4.interfaz;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelConfig extends JPanel
{
	private JComboBox<String> comboBox;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
	private String seleccionComboBox;
	private String seleccionJRadioButton;
	
	public PanelConfig()
	{
		JLabel tamano = new JLabel("Tamaño: ");
		// Se crea una lista de elementos para el JComboBox
        String[] opciones = {"5x5", "7x7", "9x9"};

        // Se crea un JComboBox y se agrega las opciones
        comboBox = new JComboBox<>(opciones);
        add(comboBox);
        seleccionComboBox = (String) comboBox.getSelectedItem();
        
     // Crear un ButtonGroup para agrupar los JRadioButton
        ButtonGroup buttonGroup = new ButtonGroup();

        // Crear varios JRadioButton con botones al lado del nombre
        option1 = new JRadioButton("Facil");
        option1.setSelected(true);
        option2 = new JRadioButton("Medio");
        option3 = new JRadioButton("Dificil");

        // Agregar los JRadioButton al ButtonGroup
        buttonGroup.add(option1);
        buttonGroup.add(option2);
        buttonGroup.add(option3);

        // Agregar los JRadioButton al panel
        add(option1);
        add(option2);
        add(option3);
       
	}

	public String getSeleccionComboBox() 
	{
		seleccionComboBox = (String) comboBox.getSelectedItem();
		return seleccionComboBox;
	}

	public String getSeleccionJRadioButton() 
	{
		if (option1.isSelected()) 
        {
        	seleccionJRadioButton = "Facil";
        } 
        else if (option2.isSelected()) 
        {
        	seleccionJRadioButton = "Medio";
        } 
        else if (option3.isSelected()) 
        {
        	seleccionJRadioButton = "Dificil";
        }
		return seleccionJRadioButton;
	}
	
}