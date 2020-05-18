import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Appmovies {

        public static void main(String args[]) {

            imprimeResultado("tinyMovies.txt");

                    }

        //Método para facilitar impressão de resultado
        public static void imprimeResultado(String arquivo) {

            try {

                Map<String, String> filmes = colocaNomesNoDicionario(arquivo);

                for (String tituloDoFilme : filmes.keySet()) {
                    System.out.println(String.format("Título: %s", tituloDoFilme));
                }

                for (Map.Entry<String, String> entry : filmes.entrySet()) {
                    String titulo = entry.getKey();
                    String ator = entry.getValue();
                    System.out.println(String.format("Título do filme: %s | Ator: %s", titulo, ator));
                }

                filmes.forEach((titulo, ator) -> System.out.println(String.format("Título: %s | Ator: %s", titulo, ator)));

            } catch (ArithmeticException x) {
                System.out.println("Tentativa de divisão por zero! " + x);
                System.out.println("");

            } catch (IllegalArgumentException x) {
                System.out.println("Erro no arquivo: " + x);
                System.out.println("");
            }

        }

    //lê o arquivo e coloca ranges na árvore
    public static Map colocaNomesNoDicionario(String arquivo) {
        Path path1 = Paths.get(arquivo);

        // dicionário de Filmes -> atores
        Map<String, String> filmes = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(path1.getFileName(), Charset.forName("utf8"))) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                if(!line.isEmpty()){
                    line = line.trim();

                    Integer indexDaBarra = line.indexOf("/");

                    String nomeDoFilme = line.substring(0, indexDaBarra);
                    String ator = line.substring(indexDaBarra+1, line.length());

                    filmes.put(nomeDoFilme, ator);

                    }
            }

        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }

        return filmes;

    }


}
