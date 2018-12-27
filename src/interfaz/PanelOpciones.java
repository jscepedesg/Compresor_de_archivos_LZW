package interfaz;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelOpciones extends JPanel{

	
	private JRadioButton optHuffman, optLzw;
	private ButtonGroup optGrupo;
	
	public PanelOpciones()
	{
	    // ..............................................( T, L, B, R ).............................................
	       setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( " Operadores " ) ) );
	       setLayout( null );
	       
	       optGrupo    = new ButtonGroup( );
	       optHuffman = new JRadioButton( "Huffman", false );
	       optLzw   = new JRadioButton( "LZW", true );
	       
	       optGrupo.add(optHuffman); optGrupo.add(optLzw);
	       add(optHuffman); add(optLzw);
	       
	       optHuffman.setBounds(10,15,110,40);
	       optLzw.setBounds( 130,15,80,40);
	            
	}
	
	public int getSeleccion()
	{
		if(optHuffman.isSelected())
			return 0;
		return 1;
	}
	
	
}
