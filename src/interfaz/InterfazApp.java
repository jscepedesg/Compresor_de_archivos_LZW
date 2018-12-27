package interfaz;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controlador.Controlador;
import util.Util;

public class InterfazApp extends JFrame{
	
	// Atributos de la clase
    private PanelCompresion pnlCompresion;
    private PanelOpciones pnlOpciones;
    private PanelDescripcion pnlDescripcion;
    
 // Atributo de tipo Controlador   
    Controlador ctrl;
    
    InterfazApp(Controlador ctrl) 
    {
    	setTitle("Compresion");
    	getContentPane().setLayout(null);
    	
    	//Icono JFrame
        Toolkit mipantalla = Toolkit.getDefaultToolkit();
        Image MiIcono = mipantalla.getImage("Imagenes/474.png"); //Icono
        setIconImage(MiIcono); //Poner icono
    	
     // Integra el Controlador. 
     	 this.ctrl = ctrl; 
     	 
     	// Instancia los paneles    
         pnlCompresion   = new PanelCompresion( ctrl );
         pnlOpciones  = new PanelOpciones(  );
         pnlDescripcion  = new PanelDescripcion(  ); 
         
   
         // Organizar el panel principal. 
         getContentPane( ).add( pnlOpciones );
         pnlOpciones.setBounds(5,5,270,60);      
         getContentPane( ).add( pnlCompresion );
         pnlCompresion.setBounds(5,70,270,60);
         getContentPane( ).add( pnlDescripcion );
         pnlDescripcion.setBounds(5,130,270,150);
         
         // Propiedades de la interfaz.   
         setSize( 285, 315 );      
         setResizable( false );
         setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
         
         //  Conecta objetos al controlador.
         ctrl.conectar( pnlOpciones, pnlCompresion, pnlDescripcion );
         
     //  Centrar ventana.
         Util.centrarVentana( this );
         
    }

	public static void main(String[] args) 
	{
		InterfazApp frmMain = new InterfazApp( new Controlador( ) );
		
		frmMain.setVisible(true);
	}

}
