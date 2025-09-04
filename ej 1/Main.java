import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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

        String var = scanner.nextLine();

        // tomo en principio y final de la string valores para ver si algun lado es "l"
        printer.println("es "+var.charAt(var.length()-1)+ "-lindo: "+esLindo(var,var.charAt(var.length()-1)));
        printer.println("es "+var.charAt(0)+ "-lindo: "+esLindo(var,var.charAt(0)));
        printer.flush();
    
    }

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
}
