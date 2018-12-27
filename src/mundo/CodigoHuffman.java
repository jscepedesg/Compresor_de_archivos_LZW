package mundo;

import javax.swing.JFileChooser;

public class CodigoHuffman {

	public CodigoHuffman()
	{
//		para crear menú de selección
		chooser = new JFileChooser();
	}
	
	public void SeleccionArchivo()
	{			
		int val = chooser.showOpenDialog(null);
				
		if(val == JFileChooser.APPROVE_OPTION)
		{
			//nombre del archivo
			nAr = chooser.getSelectedFile().getName();
			//ruta del archivo
			rAr = chooser.getSelectedFile().getAbsolutePath();
		}
		
		comprimiendo();
	}
	private void comprimiendo() 
	{
//			System.out.println(nAr + " " + rAr);
	}

	public void descomprimiendo()
	{
//		System.out.println(nAr + " " + rAr);
	}
	
	private JFileChooser chooser;
	String nAr, rAr;
	
}
