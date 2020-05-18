import java.util.HashMap;

// Implementa o algoritmo Graus de Separação, através de caminhamento
// em largura (BFS) num grafo não dirigido
public class AppDOS
{
    public static void main(String args[])
    {
        Graph g = new Graph(120000); // +- 119 mil nomes diferentes

        In arq = new In("caso1_cohen.txt");
        String aux = "";

        int tamanhoVertical = 1;
        int tamanhoHorizontal = arq.readLine().length();

        while((aux = arq.readLine()) != null){
            tamanhoVertical++;
        }

        char [][] labirinto = new char[tamanhoHorizontal][tamanhoVertical];

        arq.close();

        In arq2 = new In("caso1_cohen.txt");

        int j = 0;

        while((aux = arq2.readLine()) != null){

            for (int i=0; i<tamanhoHorizontal; i++){

                labirinto [j][i] = aux.charAt(i);

            }

            j++;
        }

        arq2.close();

        for (int i=0; i < tamanhoHorizontal; i++){

            for (int k=0; k < tamanhoVertical; k++){

                System.out.print(labirinto[i][k]);

            }

            System.out.println("");
        }

    }
}
