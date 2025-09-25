import clasesExtra.par;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class InvitadosOptimos {
    
    public static ArrayList<Integer> candidatos = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    public static ArrayList<par<Integer, Integer>> conflictos = new ArrayList<>();

    public static void main ( String [] args ) {
        
        ArrayList<Integer> res = new ArrayList<>();
        conflictos.add(new par<>(1,2));
        conflictos.add(new par<>(1,3));
        conflictos.add(new par<>(2,3));
        conflictos.add(new par<>(1,4));
        conflictos.add(new par<>(2,4));
        conflictos.add(new par<>(3,4));


        //lector de salida
        BufferedWriter bw = new BufferedWriter (
            new OutputStreamWriter ( System . out )
        );
        //printer
        PrintWriter printer = new PrintWriter(bw);

        printer.print(invitar(res, candidatos));
        printer.flush();

    }

    public static ArrayList<Integer> invitar(ArrayList<Integer> res, ArrayList<Integer> candidatos){
        if (candidatos.isEmpty()) {
            return new ArrayList<>(res); // devolvemos copia para evitar efectos laterales
        }

        Integer candidato = candidatos.get(0);
        ArrayList<Integer> resto = new ArrayList<>(candidatos.subList(1, candidatos.size()));

        // caso 1: no lo invito
        ArrayList<Integer> sinCandidato = invitar(new ArrayList<>(res), resto);

        // caso 2: lo invito (solo si no hay conflicto)
        ArrayList<Integer> conCandidato = new ArrayList<>();
        if (!tieneConflictos(res, candidato)) {
            ArrayList<Integer> nuevoRes = new ArrayList<>(res);
            nuevoRes.add(candidato);
            conCandidato = invitar(nuevoRes, resto);
        }

        // devuelvo la lista más larga
        if (conCandidato.size() > sinCandidato.size()) {
            return conCandidato;
        } else {
            return sinCandidato;
        }
    }


    public static boolean tieneConflictos(ArrayList<Integer> invitados, Integer candidato){
        for (int i = 0; i < conflictos.size(); i++) {
            if (conflictos.get(i).getFirst().equals(candidato)){
                for (int j = 0; j < invitados.size(); j++) {
                    if (invitados.get(j).equals(conflictos.get(i).getSecond())){
                        return true;
                    }
                }
            }  
            if (conflictos.get(i).getSecond().equals(candidato)){
                for (int j = 0; j < invitados.size(); j++) {
                    if (invitados.get(j).equals(conflictos.get(i).getFirst())){
                        return true;
                    }    
                }
            }
        }
        return false;
    }
}
