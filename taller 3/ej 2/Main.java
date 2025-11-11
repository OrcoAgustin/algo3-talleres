import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
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

        int n = scanner.nextInt();
        int m = scanner.nextInt();


        //aristas

        ArrayList<long[]>[] listaDeAdyacencia = new ArrayList[n];        
        for (int i = 0; i < n; i++) {
            listaDeAdyacencia[i] = new ArrayList<long[]>();
        }


        for (int i = 0; i < m; i++) {
            int primerP = scanner.nextInt()-1;
            int segundoP = scanner.nextInt()-1;
            long costo = scanner.nextLong();

            listaDeAdyacencia[primerP].add(new long[]{segundoP,costo});
            listaDeAdyacencia[segundoP].add(new long[]{primerP,costo});
        }
    

        //conflictos v2

        //indice es planeta-1 y las tuplas son donde empieza la ventana de ocupacion y donde termina
        ArrayList<long[]>[] matrizDeConflictos = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            int cantidadDeConflictos = scanner.nextInt();
            ArrayList<long[]> nuevaLinea = new ArrayList<>();

            if (cantidadDeConflictos == 0) {
                matrizDeConflictos[i] = nuevaLinea;
                continue;
            }

            long inicio = scanner.nextLong();
            long anterior = inicio;

            for (int j = 1; j < cantidadDeConflictos; j++) {
                long actual = scanner.nextLong();

                if (actual == anterior + 1) {
                    anterior = actual; 
                } else {
                    
                    nuevaLinea.add(new long[]{inicio, anterior});
                    inicio = actual;
                    anterior = actual;
                }
            }

            nuevaLinea.add(new long[]{inicio, anterior});
            matrizDeConflictos[i] = nuevaLinea;
        }


        printer.println(pasosHastaM(listaDeAdyacencia, matrizDeConflictos));
        printer.flush();
    }

    public static long pasosHastaM(ArrayList<long[]>[] listaDeAdyacencia, ArrayList<long[]>[] matrizDeConflictos){
        //dijkstra v2 (echu's dream)
        //proba si pasa con matriz, sino sufrimos con la queue
        //yo del futuro: no funciona. Usa queue
    
        int n = listaDeAdyacencia.length;
        //almacenar (tiempo, planeta)
        PriorityQueue<long[]> restantes = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0]) 
        );

        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            res[i] = Long.MAX_VALUE;
        }
        res[0] = 0L;

        restantes.add(new long[]{0L,0L});

        while(!restantes.isEmpty()){
            
            long[] actual = restantes.poll();
            long tiempoActual = actual[0];
            int planetaActual = (int) actual[1];

            //ya calculado?
            if(tiempoActual > res[planetaActual]){
                continue;
            }

            //llegamos?
            if(planetaActual == n-1){
                return tiempoActual;
            }
            
            //calculamos el tiempo de salida
            long tiempoSalida = encontrarTiempo(tiempoActual, planetaActual, listaDeAdyacencia, matrizDeConflictos);
            
            for (int i = 0; i < listaDeAdyacencia[planetaActual].size(); i++) {
                //primero: planeta, segundo: costo de llegar
                int planetaDestino = (int) listaDeAdyacencia[planetaActual].get(i)[0];
                long costoDeViaje =  listaDeAdyacencia[planetaActual].get(i)[1];
                 
                long nuevoTiempoDeLlegada = tiempoSalida + costoDeViaje;

                if (nuevoTiempoDeLlegada < res[planetaDestino]){
                    res[planetaDestino] = nuevoTiempoDeLlegada;
                    restantes.add(new long[]{(long) nuevoTiempoDeLlegada,(long) planetaDestino});
                }
            }
        }

        //inalcanzable el destino
        return -1;
    }
    
    //CREO el problema esta aca, es lineal toma demasiado. Pensa como mejorarlo
    public static long encontrarTiempo(long  tiempoLlegada, int planetaActual, ArrayList<long[]>[] listaDeAdyacencia, ArrayList<long[]>[] matrizDeConflictos ){ 
        //si le implementas busqueda binaria en otra f re sale segun tobi
        long tiempoSalida = tiempoLlegada;

        //catch por si esta vacia
        if(matrizDeConflictos[planetaActual].isEmpty()){
            return tiempoSalida;
        }

        int izquierda = 0;
        int derecha = matrizDeConflictos[planetaActual].size()-1;

        while(izquierda <= derecha){
            int medio = (izquierda + derecha)/2;
            
            if(tiempoSalida < matrizDeConflictos[planetaActual].get(medio)[0]){
                derecha = medio - 1; 
            }
            else if(tiempoSalida > matrizDeConflictos[planetaActual].get(medio)[1]){
                izquierda = medio + 1; 
            }
            else{
                tiempoSalida=matrizDeConflictos[planetaActual].get(medio)[1] + 1;
                return tiempoSalida;
            }
        }
        return tiempoSalida;
    }
}

/* DELIRIO, QUE HACES?!?
 * //como el tp toma al menos 1 seg si cayese justo en el borde tendria que esperar
        if(izquierda < matrizDeConflictos[planetaActual].size()){
            if(tiempoSalida == matrizDeConflictos[planetaActual].get(izquierda)[0]-1){
                return matrizDeConflictos[planetaActual].get(izquierda)[1] + 1; 
            }
        } 
*/
//otro tle pero este es resolvible crep porque pensalo asi si caemos justo antes del intervalo, igual 
//hay que esperar porque el tp toma >0 segs entonces si pasa eso dijkstra vuelve a llamar,
// queremos que no llame de mas. algun catch deberia funcionar supongo yo


//idea magistral, cambia la f de encontrar tiempo y sumale busqueda binaria adentro
//descenso a la locura, ya no se que es

//ya no se que mas optimizar, memo en dijkstra?!?!?!?!?!?!?!?


//Nota:
//repensar conflictos? si los ves como una tupla, podes ahorrarte tiempo con las busqeudas no?


/* MAS LEGACY!1!1!1!

public static int busquedaBinaria(ArrayList<Long[]> lista, long objetivo){
        
        int izquierda = 0;
        int derecha = lista.size();

        while (izquierda < derecha){
            int indice = izquierda + (derecha-izquierda) /2;

            long valor = lista.get(indice)[0];

            if(valor < objetivo){
                izquierda = indice + 1;
            }
            else{
                derecha = indice ;
            }
        }

        return izquierda;
    }
} */
//LOS INT TIENEN LIMITE; CABEZA DE TERMO USA LONG :)

 /*
    public static int encontrarTiempo(int tiempoLlegada, int planetaActual, ArrayList<Integer[]>[] listaDeAdyacencia, ArrayList<Integer>[] matrizDeConflictos ){ 
        int tiempoSalida = tiempoLlegada;
        
        boolean encontrado = false;
        int indice =matrizDeConflictos[planetaActual].size();
        while(!encontrado){
            if(indice/2 == tiempoSalida){
                tiempoSalida +=1;
            }
        }

        return tiempoSalida;
    }        
 */

/* 
        for (int i = 0; i < matrizDeConflictos[planetaActual].size(); i++) {
            if (tiempoSalida == matrizDeConflictos[planetaActual].get(i)){
                tiempoSalida += 1;
            }
        }
        */


/* printer de input
        printer.println("n es: " + n);
        printer.println("m es: " + m);
        printer.flush();


        //print de adyacencia
        printer.println("la matriz de adyacencia se ve asi:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                printer.print(matrizDeAdyacencia[i][j]+" ");
            }
            printer.println();
        }
        printer.flush();


        //print
        printer.println("la matriz de conflictos se ve asi:");
        for (int i = 0; i < matrizDeConflictos.length; i++) {
            printer.print("i = " + i + ":");
            
            ArrayList linea=matrizDeConflictos[i];

            if(!linea.isEmpty()){
                for (int j = 0; j < linea.size(); j++) {
                    printer.print(linea.get(j) + " ");
                }
            }else{
                printer.print(0);
            }
            printer.println();
        }
        printer.flush();

        */

        /* con el cambio de conflictos, esto quedo en legacy jajaj
        
        
        
        public static int encontrarDisponibilidad(ArrayList<Long> lista, int inicio){
        //es como una busqueda binaria pero particular
        //devuelve el primer hueco que encuentre
        int izquierda = inicio;
        int derecha = lista.size()-1;

        int utlimoConflictoValido = izquierda;
        long valorIzquierda = lista.get(inicio);

        while(izquierda <= derecha){
            int medio = izquierda + (derecha - izquierda) /2;

            if(lista.get(medio) == valorIzquierda + (medio - inicio)){
                utlimoConflictoValido = medio;
                izquierda = medio + 1;
            }
            else{
                derecha = medio -1;
            }
        }
    return utlimoConflictoValido;
    } */