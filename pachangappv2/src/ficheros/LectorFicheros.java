package ficheros;

import java.io.*;
import java.util.ArrayList;

public class LectorFicheros {
	
	public static ArrayList leerCSV(ArrayList<Preguntas> listPreguntas, String nomFichero) throws IOException {
		
		listPreguntas.clear();
		
		String delimiter = ",";
		String tempId = null;
		String tempPregunta = null;
		String tempRespA = null;
		String tempRespB = null;
		String tempRespC = null;
		String tempRespD = null;
		String tempRespCorr = null;
		
		int tempIdInt;
		
		Preguntas tempPre;
		//es posible que esto de error ya que he puesto el path relativo
		//pero en caso de que lo cambie al absoluto por si no me funcione cada
		//cual tendria que cambiar el suyo en su ordenador
		//esta hecho para que tengas que meter tu el fichero pero se puede cambiar
		//para que siempre sea el mismo
		File f = new File(nomFichero);
		
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			String[] tempArr;
			while ((line = br.readLine()) != null) {
				tempArr = line.split(delimiter);
				
				tempId = tempArr[0];
				tempPregunta = tempArr[1];
				tempRespA = tempArr[2];
				tempRespB = tempArr[3];
				tempRespC= tempArr[4];
				tempRespD= tempArr[5];
				tempRespCorr= tempArr[6];
				
				tempIdInt = Integer.parseInt(tempId);
				
				tempPre = new Preguntas(tempIdInt, tempPregunta, tempRespA, tempRespB, tempRespC, tempRespD, tempRespCorr);
				System.out.println("añadido la pregunta :" + tempPre.toString());
				listPreguntas.add(tempPre);
			}
			
			br.close();
			
			return listPreguntas;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listPreguntas;
	}
	
	/*private static void leerRecursoNormal( BufferedReader br ) throws IOException {
		String linea = br.readLine();
		while (linea!=null) {
			if (!linea.isEmpty()) System.out.println( linea );
			linea = br.readLine();
		}
	}
	private static void leerRecursoRec( BufferedReader br ) throws IOException {
		String linea = br.readLine();
		if (linea!=null) {
			if (!linea.isEmpty()) System.out.println( linea );
			leerRecursoRec( br );
		}
	}
	// Método Shell - no recursivo - inicializar y llamar primera vez al recursivo
		private static void leerRecurso( String nombreRecurso ) {
			try {
				BufferedReader br = new BufferedReader( new InputStreamReader( 
						LectorFichero.class.getResourceAsStream( nombreRecurso ) ) );
				leerRecursoRec( br );
				// leerRecursoNormal( br );  // Esto sería en interativo
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public static void main(String[] args) {
			leerRecurso( "..." );
		}*/
}
