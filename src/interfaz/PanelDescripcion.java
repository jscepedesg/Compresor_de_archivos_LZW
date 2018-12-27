package interfaz;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelDescripcion extends JPanel{

	
	private JTextArea miArea;
	private JScrollPane barraLamina;
	private JLabel lbl;
	public PanelDescripcion()
	{
		// ..............................................( T, L, B, R ).............................................
	       setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( "Descripción" ) ) );
	       setLayout( null );	
	       
	       /*miArea = new JTextArea(); //crea area de texto
	       barraLamina = new JScrollPane(miArea); //agrega barra de desplazamiento
	       miArea.setEnabled(false); //impide que el usuario escriba en el texto de área
	       miArea.setLineWrap(true); //esto para que no crezca a lo ancho
	       barraLamina.setBounds(10, 20, 250, 120); //ubicacion al objeto*/
	       
	       lbl = new JLabel( "" );
	       lbl.setBounds(10,20,250,120);
	       add(lbl);

	}
	
	/*public void setTexto(String resultado)
	{
		miArea.setText(resultado);
	}*/
	
	public void setTextolbl(String resultado)
	{
		lbl.setText(resultado);
	}
	
}
