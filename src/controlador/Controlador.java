package controlador;

import interfaz.*;
import mundo.*;

public class Controlador 
{
	private PanelOpciones pnlOpciones;
	private PanelDescripcion pnlDescripcion;
	private Algoritmo_LZW lzw;
	private CodigoHuffman huffman;
	
	public Controlador()
	{
		lzw = new Algoritmo_LZW();
		huffman = new CodigoHuffman();
	}
	
	public void conectar( PanelOpciones pnlOpciones,PanelCompresion pnlCompresion,PanelDescripcion pnlDescripcion )
	{ 
		this.pnlOpciones = pnlOpciones;
		this.pnlDescripcion = pnlDescripcion;
	}
	
	public void Comprimir() throws Exception
	{
		int opcion;
		opcion = pnlOpciones.getSeleccion();
		
		if(opcion == 0) // envía a mundo para logica de huffman parte de comprimir
		{
			huffman.SeleccionArchivo();
		}
		else //envía a mundo para logica de lzw parte de comprimir
		{
			lzw.SeleccionArchivo("Comprimir");
			
			pnlDescripcion.setTextolbl("<html> Nombre del archivo: "+lzw.getnAr()+ "<p>Ruta del archivo: " + lzw.getrAr() + "<p>Tamaño: " + lzw.getTamañoFichero() + " bytes"
					+ "<html>");
		}
	}
	
	public void Descomprimir() throws Exception
	{
		int opcion;
		opcion = pnlOpciones.getSeleccion();
		
		if(opcion == 0) //envia a mundo para huffman en la parte de seleccion y descomprimir
		{
			huffman.descomprimiendo();
		}
		else  //envia a mundo para lzw en la parte de seleccion y descomprimir
		{
			lzw.SeleccionArchivo("Descomprimir");
			
			pnlDescripcion.setTextolbl("<html> Nombre del archivo: "+lzw.getnAr()+ "<p>Ruta del archivo: " + lzw.getrAr() + "<p>Tamaño: " + lzw.getTamañoFichero() + " bytes"
					+ "<html>");
		}
	}
	
}
