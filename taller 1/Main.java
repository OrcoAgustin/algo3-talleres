    import java . io .*;
    import java . util .*;

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

            int a = scanner.nextInt(); //inicio 
            int b = scanner.nextInt(); //objetivo
            ArrayList<Integer> hist = new ArrayList<>(); //se guardan aca los valores que van pasando

            aux(a,b,hist, printer);

            printer.flush();
        }

        //funcion aux//
        public static void aux(Integer obj, Integer actual, ArrayList<Integer> hist, PrintWriter printer ){
            hist.add(actual);

            if((0>=actual)||(actual<obj)||(((actual%2)!=0)&&((actual%10)!=1))){
                printer.println("NO");
                return;
            }
            if (obj.equals(actual)){
                printer.println("YES");
                printer.println(hist.size());
                //loop para juntar la respuesta
                for (int i = (hist.size()-1); i >= 0; i--) {
                    printer.print(hist.get(i)+" ");
                }  
                printer.println(); 
                return;
            }
            if((actual%10)==1){
                aux(obj,(actual-1)/10,hist,printer);
            }
            
            if((actual%2)==0){
                aux(obj,actual/2,hist,printer);
            }
        }
    }

