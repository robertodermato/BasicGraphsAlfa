import java.util.HashMap;

// Implementa o algoritmo Graus de Separação, através de caminhamento
// em largura (BFS) num grafo não dirigido
public class AppDOS
{

    public static void main(String args[]){

        executaLabirinto("caso1_cohen.txt");
        executaLabirinto("caso2_cohen.txt");
        executaLabirinto("caso3_cohen.txt");
        executaLabirinto("caso4_cohen.txt");
        //executaLabirinto("caso5_cohen.txt");
        //executaLabirinto("caso6_cohen.txt");
        //executaLabirinto("caso7_cohen.txt");

    }


    public static void executaLabirinto (String arquivo)
    {

        long tempoInicio = System.currentTimeMillis();

        In arq = new In(arquivo);
        String aux = "";

        int tamanhoVertical = 1;
        int tamanhoHorizontal = arq.readLine().length();

        while((aux = arq.readLine()) != null){
            tamanhoVertical++;
        }

        arq.close();

        int localHeroiX = 0;
        int localHeroiY = 0;

        int localVilaoX = 0;
        int localVilaoY = 0;

        int [][] labirintoMatriz = new int[tamanhoVertical][tamanhoHorizontal];
        int y = 0;
        In arq2 = new In(arquivo);

        /*
        * 1 - A
        * 2 - B
        * A partir do 4 teremos os pontos transformados em números
        * 0 - #
        * */

        int vertice = 4;
        while((aux = arq2.readLine()) != null){

            for (int x=0; x<tamanhoHorizontal; x++){

                if(aux.charAt(x) == '.'){
                    labirintoMatriz [y][x] = vertice;
                    vertice++;
                }else if(aux.charAt(x) == 'A'){
                    labirintoMatriz [y][x] = 1;
                    localHeroiX = x;
                    localHeroiY = y;

                }else if(aux.charAt(x) == 'B'){
                    labirintoMatriz [y][x] = 2;
                    localVilaoX = x;
                    localVilaoY = y;
                }
            }

            y++;
        }
        arq2.close();

        System.out.println("O heroi está em X: " + localHeroiX + " Y: " + localHeroiY);
        System.out.println("O vilão está em X: " + localVilaoX + " Y: " + localVilaoY);

        Graph grafoLabirinto = new Graph(tamanhoHorizontal * tamanhoVertical);

        for (int i=0; i < tamanhoVertical; i++){

            for (int k=0; k < tamanhoHorizontal; k++){

                if(labirintoMatriz[i][k] != 0){
                    if(labirintoMatriz[i+1][k] != 0){
                        grafoLabirinto.addEdge(labirintoMatriz[i][k], labirintoMatriz[i+1][k]);
                    }
                    if(labirintoMatriz[i][k+1] != 0){
                        grafoLabirinto.addEdge(labirintoMatriz[i][k], labirintoMatriz[i][k+1]);
                    }
                }

            }
        }

        // Agora achamos o caminho mais curto.

        BreadthFirstPaths caminhamentoLargura = new BreadthFirstPaths(grafoLabirinto, 1);
        int tamanhoMenorCaminho = caminhamentoLargura.distTo(2);
        System.out.println("O menor caminho entre o heroi e o bandido é: " + tamanhoMenorCaminho);
        Iterable<Integer> path = caminhamentoLargura.pathTo(2);

        for (int vertex : path){
            for(int i=0; i<tamanhoVertical; i++){
                for(int k=0; k<tamanhoHorizontal; k++){
                    if(labirintoMatriz[i][k] == vertex){
                        labirintoMatriz[i][k] = 3;
                    }
                }
            }
        }
        char [][] labirintoFinal = new char[tamanhoVertical][tamanhoHorizontal];

        for(int i=0; i<tamanhoVertical; i++){
            for(int k=0; k<tamanhoHorizontal; k++){

                if(labirintoMatriz[i][k] == 3){
                    labirintoFinal[i][k] = 'X';
                }else if(labirintoMatriz[i][k] == 0){
                    labirintoFinal[i][k] = '#';
                }else{
                    labirintoFinal[i][k] = '.';
                }
            }
        }
        labirintoFinal[localHeroiY][localHeroiX] = 'A';
        labirintoFinal[localVilaoY][localVilaoX] = 'B';
/*
        for(int i=0; i<tamanhoVertical; i++){
            for(int k=0; k<tamanhoHorizontal; k++){
                System.out.print(labirintoFinal[i][k]);
            }
            System.out.println("");
        }


 */
        long tempoFinal = System.currentTimeMillis();
        double tempoTotal = (tempoFinal - tempoInicio)*1.0/1000;
        System.out.println("Tempo de execução de " + arquivo + " é: " + tempoTotal + "s.");
        System.out.println("");
        System.out.println("");

    }


}
