package interfaz;

import java.awt.event.*;
import controlador.*;
import javax.swing.*;
import javax.swing.border.*;

// Aquí en este panel se encuentra lo que son los botones comprimir y descommprimir
public class PanelCompresion extends JPanel implements ActionListener
{
	//Atributo de clase
	private JButton btnCom, btnDes;

	// Atributo a controlador
	Controlador ctrl;
	
	public PanelCompresion(Controlador ctrl)
	{
		// ..............................................( T, L, B, R ).............................................
	       setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( "Compresion" ) ) );
	       setLayout( null );
		
	    //Instancia a Controlador
	      this.ctrl = ctrl;
	      
	      btnCom = new JButton("Comprimir");
	      btnCom.addActionListener(this);
	      btnCom.setBounds( 30, 25, 100, 20 );
	      add(btnCom);
	      
	      btnDes = new JButton("Descomprimir");
	      btnDes.addActionListener(this);
	      btnDes.setBounds( 140, 25, 120, 20 );
	      add(btnDes);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("Comprimir"))
		{
			try {
				ctrl.Comprimir();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
		{
			try {
				ctrl.Descomprimir();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
