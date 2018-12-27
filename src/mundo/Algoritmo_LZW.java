package mundo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Tiempo.Stopwatch;

public class Algoritmo_LZW 
{
	private DataOutputStream data_out;
	private static String partir="";
	
	public Algoritmo_LZW()	{
		chooser = new JFileChooser();		 
	}
		
	public void SeleccionArchivo(String accion) throws Exception
	{
		int val = chooser.showOpenDialog(null);

		if(val == JFileChooser.APPROVE_OPTION)
		{
			//nombre del archivo
			nAr = chooser.getSelectedFile().getName();
			//ruta del archivo
			rAr = chooser.getSelectedFile().getAbsolutePath();
			// Tamaño del archivo
			fichero = new File(rAr);
		}
		
		if(accion.equals("Comprimir")){
	
			Stopwatch timer1  = new Stopwatch();
			ArrayList<Integer> comprimido = lecturaComprimir(rAr);
			double time1 = timer1.elapsedTime();
			System.out.println("\nEl tiempo fue de: " + time1 + " segundos (parte lectura_comprimir)");
			Stopwatch timer2 = new Stopwatch();
			corrimientoCreaFichero(comprimido);
			double time2 = timer2.elapsedTime();
			System.out.println("\nEl tiempo fue de: " + time2 + " segundos (parte corrimientoCreaFichero)");
	
	
		}else if(accion.equals("Descomprimir")){
					
			Stopwatch timer3  = new Stopwatch();
			List<Integer> descorrida = lecturaDescorrida(rAr);
			double time3 = timer3.elapsedTime();
			System.out.println("\nEl tiempo fue de: " + time3 + " segundos (parte lectura_descorrida)");
			Stopwatch timer4  = new Stopwatch();
			descomprimeCreaFichero(descorrida, rAr);
			double time4 = timer4.elapsedTime();
			System.out.println("\nEl tiempo fue de: " + time4 + " segundos (parte descomprimeCreaFichero)");
						
		}			
	}
	
	

	public  ArrayList<Integer> lecturaComprimir(String ruta){
	
		boolean primera=true;
		String ps="";
		int tamaño = 257;
		
		Map<String,Integer> diccionario = new HashMap<String,Integer>();
		for(int i=1;i<257;i++)
			diccionario.put("" + (i-1), i);
		
		String pe="";
		ArrayList<Integer> salida = new ArrayList<Integer>();
        try {
            FileInputStream archivo_lectura = new FileInputStream(ruta); //Crea un archivo de lectura
            boolean final_ar = false; //Inicializa en falso
            while (!final_ar) {
                int byte_entrada = archivo_lectura.read(); //Convierte el archivo en bytes
  
                if (byte_entrada != -1) {
                	if(primera){
        				ps=""+byte_entrada;
        				primera=false;
                	}
        			else
        				ps=pe+"-"+byte_entrada;
        			if(diccionario.containsKey(ps))
        				pe=ps;
        			else{
        				salida.add(diccionario.get(pe));
        				diccionario.put(ps,  tamaño++);
        				
        				pe = "" + byte_entrada;
        			}
                }	                	
                else 
                    final_ar = true;  //Haga verdadero             
            }
            archivo_lectura.close(); //Cierre el archivo
        } catch (IOException e) {
            System.out.println("Error al acceder a la imagen."); //Mensaje en caso de error
        }
		
		if(!pe.equals(""))
			salida.add(diccionario.get(pe));

		return salida;
	
	}
	
	public  void corrimientoCreaFichero(final List<Integer> comprimido) throws Exception{	
		final StringBuilder texto = new StringBuilder(""); //Variable para almacenar una cadena de ceros y unos
		String padding = "00000000"; // padding inicial de 8 ceros para hacer padding-left 
		int aux=8; //Inicia almacenando de a 8 bits
		FileOutputStream fichero_nuevo = null; //Fichero para crear el archivo
		try{      
	        fichero_nuevo = new FileOutputStream("C:/Users/Usuario/Desktop/Comprimido" + this.nAr); //Cree un archivo en el escritorio
	        //En esta parte se recorre la lista comprimida y se hace el corrimiento     
			for(int i=0; i<comprimido.size(); i++){ //Recorrer lista ya comprimida 		
				final String numeroEnBinario = Integer.toBinaryString(comprimido.get(i)); // convertimos el numero a un String con su representacion en binario
				if(numeroEnBinario.length() > aux){ //Se mira si es necesario un comodin, es decir que no cabe en 8 bits
					texto.append(padding);// agregamos el 'comodin'
					aux++;  //Aumentamos aux, ya no agregara de a 8 bits, sino de a 9 bits
					padding = String.format("%0"+(aux)+"d",0); //se actualiza el padding dependiendo del valor de aux
				}
				texto.append(padding.substring(0,(aux-numeroEnBinario.length())) + numeroEnBinario); //agregamos la cadena binaria haciendo padding-left con ceros  	
			}
			//Con esta parte se cogen cada 8 bits de la cadena para escribir el archivo
			final String[] vector = texto.toString().split("(?<=\\G........)"); // partimos el texto en un arreglo de cadenas de 8 digitos
			for (int i=0; i<vector.length ;i++) {
					fichero_nuevo.write( Integer.parseInt(vector[i],2) ); //Se escribe en el nuevo fichero
				//Se recorre el vector obtenido	
			}
		}catch (Exception e) { 
            System.out.println("Error al crear el archivo."); //Mensaje en caso de error
        } finally {
        	fichero_nuevo.close();//Se cierra el fichero
        }   
	}

	
	public  List<Integer> lecturaDescorrida(String ruta) throws Exception{
		File archivo = new File(ruta);
		int contador=0;
		final StringBuilder texto2 = new StringBuilder("");
		String padding1 = "00000000";
		int aux2=8;
		BufferedInputStream archivo_lectura = null;	
        try {
        		archivo_lectura = new BufferedInputStream(new FileInputStream(ruta)); //Crea un archivo de lectura
            	int byte_entrada = 0;
            	do {
            		byte_entrada = archivo_lectura.read(); //Va almacenando los bits oBtenidos
            		contador++;//Saber en que momento es el ultimo caso de lectura
            		if (byte_entrada != -1) {
	            		final String numeroEnBinario = Integer.toBinaryString(byte_entrada);// convertimos el numero a un String con su representacion en binario	
						if(contador!=archivo.length()) {//Todos menos el ultimo
							texto2.append(padding1.substring(0,(aux2-numeroEnBinario.length())) + numeroEnBinario);//agregamos la cadena binaria haciendo padding-left con ceros
						} else {
							texto2.append(numeroEnBinario);//No agregamos padding al ultimo caso
						}
            		}
            	}while(byte_entrada != -1);
        	} 
        catch (IOException e) 
        {
            System.out.println("Error al acceder a la imagen."); //Mensaje en caso de error
        }finally {
        	archivo_lectura.close();//Se cierra el fichero
        }
		return generarArrayDeBytes(texto2.toString());
	}
	
	
	private List<Integer> generarArrayDeBytes(final String cadenaBinaria){	
		final List<Integer> vec2 = new ArrayList<Integer>();
		String padding1 = "00000000";
		//Generar
				int aux3=8;
				for(int i=0;i<cadenaBinaria.length();i=i+aux3)//Va de a 8 hasta modificar aux3 para aumentar el rango del substring
				{
					String numeroEnBinario1 = cadenaBinaria.substring(i,i+aux3);//Numero en binario, segun el rango
					if(numeroEnBinario1.equals(padding1) ) //Encontro comodin 00000000 
					{
						 i--;//para coomenzar una posicion anterior de la terminada antes de la ejecucion de la decicion
						aux3++; //Aunmenta el rango del substring
						padding1 = String.format("%0"+(aux3)+"d",0); //se actualiza el padding dependiendo del valor de aux
					}else
					{
						vec2.add(Integer.parseInt(numeroEnBinario1,2)); //Agregar el bit al arraylist
					}
				}
		return vec2;
	}
	
	
	
	public void descomprimeCreaFichero(List<Integer> comprimido, String ruta){
		System.out.println(comprimido);
	
		try{
		
        File file = new File(ruta); //Crea un archivo con la ruta
        FileOutputStream fichero_nuevo = new FileOutputStream("C:/Users/Usuario/Desktop/Descomprimido" + file.getName());
		
		int tamaño = 257;

		Map<Integer,String> diccionario = new HashMap<Integer,String>();
		for(int i=1;i<257;i++)
			diccionario.put(i, "" + (i-1));
				
		String pe = "" + ((int)comprimido.remove(0)-1)+"-";
		StringBuffer salida = new StringBuffer(pe);
		
		for(int se : comprimido){	
			String ps="";
			if (diccionario.containsKey(se))
				if(diccionario.get(se).charAt(diccionario.get(se).length()-1)=='-')
					ps = diccionario.get(se) ;
				else
					ps = diccionario.get(se) + "-" ;			
			else if (se == tamaño){
				
                if(pe.charAt(1)=='-')
					ps = pe + pe.substring(0, 2) ;	
				else if(pe.charAt(2)=='-')
					ps = pe + pe.substring(0, 3) ;		
				else 
					ps = pe + pe.substring(0, 4) ;	
			}
			else
				 throw new IllegalArgumentException("Mal comprimido se: " + se);
			
			salida.append(ps);

			if(ps.charAt(1)=='-')
				diccionario.put(tamaño++, pe + ps.charAt(0));
			else if(ps.charAt(2)=='-')
				diccionario.put(tamaño++, pe + ps.charAt(0)+ ps.charAt(1));
			else 
				diccionario.put(tamaño++, pe + ps.charAt(0)+ ps.charAt(1)+ ps.charAt(2));
			
			pe = ps;			
		}
			
		String aux="";

		for(int i=0;i<salida.length();i++){
			if(salida.charAt(i)!='-')
				aux+=salida.charAt(i);
			else{
			    fichero_nuevo.write(Integer.parseInt(aux)); 
				aux="";
			}		
		}
		
		 fichero_nuevo.close();
		// JOptionPane.showMessageDialog(null, "Archivo descomprimido creado.");
		 
		}catch (IOException e) {
            System.out.println("Error al crear el archivo."); //Mensaje en caso de error
        }
		
		
	}
	
	public static int binaryToDecimal(String number) {
		return Integer.parseInt(number,2);
		}

	
	
	public String getnAr() {
		return nAr; //Nombre archivo
	}
	
	public String getrAr() {
		return rAr; //Ruta del archivo
	}

	public long getTamañoFichero()	{
		if(rAr != null)		//Tamaño archivo
			return fichero.length();
		
		return 0;
	}
	private JFileChooser chooser;
	private String nAr, rAr;
	private File fichero;
	
}

