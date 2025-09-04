import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main ( String [] args ) {
        //lector de entrada
        BufferedReader br = new BufferedReader( 
            new InputStreamReader(System.in)
        );
        //lector de salida
        BufferedWriter bw = new BufferedWriter (
            new OutputStreamWriter ( System . out )
        );
        //scanner
        Scanner scanner = new Scanner(br);
        //printer
        PrintWriter printer = new PrintWriter(bw);

        ArrayList<Integer> respuestas = new ArrayList<>();
        
        int casosDeTest = scanner.nextInt();
        for (int i = 0; i < casosDeTest; i++) {
            int largo = scanner.nextInt();
            String cadena = scanner.next();
            respuestas.add(contarL(cadena, 'a'));
        }

        for (int i = 0; i < respuestas.size(); i++) {
            printer.print(respuestas.get(i));
            printer.println("");
        }
        printer.flush();
    }


    public static int contarL(String cadena, char letra){

        //Caso base
        if (cadena.length()==1){
            if (cadena.charAt(0)==letra){
                return 0;
            }else{
                return 1;
            }
        }

        String izq = cadena.substring(0,cadena.length()/2);
        String der = cadena.substring(cadena.length()/2, cadena.length());

        int cambiosIzq = 0;
        int cambiosDer = 0;

        //cuento cantidad de cambios a realizar en el izq para hacerlo L lindo
        for (int i = 0; i < izq.length(); i++) {
            if (izq.charAt(i)!=letra){
                cambiosIzq +=1;
            }
        }

        //misma pero derecha
        for (int i = 0; i < der.length(); i++) {
            if (der.charAt(i)!=letra){
                cambiosDer +=1;
            }
        }
        
        //recursion
        int izqEsLindo = cambiosIzq +contarL(der, (char)(letra+1));        
        int derEsLindo = cambiosDer +contarL(izq, (char)(letra+1));        
        
        return Math.min(izqEsLindo, derEsLindo);
    }
}
    //claro, mejor contar en vez de chequear que sea L- lindo
    //listo, que capo soy
    /*
    public static boolean esLindo(String cadena, char letra){
        //caso base
        if (cadena.length() == 1){
            char v = cadena.charAt(0);
            return (v==letra);
        }

        String izq = cadena.substring(0,cadena.length()/2);
        String der = cadena.substring(cadena.length()/2, cadena.length());

        //caso cadena larga a la izq
        Boolean izqEsLinda = true;  
        int i = 0;  
        while ((izqEsLinda) && (i<izq.length())){
            if(izq.charAt(i) != letra){
                izqEsLinda = false;
            }
            i+=1;
        }
        if((izqEsLinda) && esLindo(der,(char)(letra+1))){
            return true;
        }
        
        //caso cadena larga a la der
        Boolean derEsLinda = true;  
        int j = 0;  
        while ((derEsLinda) && (j<der.length())){
            if(der.charAt(j) != letra){
                derEsLinda = false;
            }
            j+=1;
        }
        if((derEsLinda) && esLindo(izq,(char)(letra+1))){
            return true;
        }
        
        //no es ninguna de los 3
        return false;
    }
    */

