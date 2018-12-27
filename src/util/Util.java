package util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class Util {

	/**
     * Centra una ventana en la pantalla
     * @param ventana La ventana que se va a centrar
     */
    public static void centrarVentana( JFrame ventana )
    {
    	//System.out.println("Centrar una ventana en la pantalla.");
        Dimension dPantalla = Toolkit.getDefaultToolkit( ).getScreenSize( );
        Dimension dVentana = ventana.getSize( );

        int xEsquina = ( dPantalla.width / 2 ) - ( dVentana.width / 2 );
        int yEsquina = ( dPantalla.height / 2 ) - ( dVentana.height / 2 );

        ventana.setLocation( xEsquina, yEsquina );
    }
   
    public static void centrarVentana( JDialog ventana )
    {
    	//System.out.println("Centrar una ventana en la pantalla.");
        Dimension dPantalla = Toolkit.getDefaultToolkit( ).getScreenSize( );
        Dimension dVentana = ventana.getSize( );

        int xEsquina = ( dPantalla.width / 2 ) - ( dVentana.width / 2 );
        int yEsquina = ( dPantalla.height / 2 ) - ( dVentana.height / 2 );

        ventana.setLocation( xEsquina, yEsquina );
    }

    public static void centrarVentana( JApplet ventana )
    {
    	//System.out.println("Centrar una ventana en la pantalla.");
        Dimension dPantalla = Toolkit.getDefaultToolkit( ).getScreenSize( );
        Dimension dVentana = ventana.getSize( );

        int xEsquina = ( dPantalla.width / 2 ) - ( dVentana.width / 2 );
        int yEsquina = ( dPantalla.height / 2 ) - ( dVentana.height / 2 );

        ventana.setLocation( xEsquina, yEsquina );
    }
	
}
