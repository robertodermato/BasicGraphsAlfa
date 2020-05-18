/******************************************************************************
 *  Compilation:  javac WebCrawler.java In.java
 *  Execution:    java WebCrawler url
 *  Dependencies: Queue.java In.java
 *
 *  Downloads the web page and prints out all urls on the web page.
 *  Gives an idea of how Google's spider crawls the web. Instead of
 *  looking for hyperlinks, we just look for patterns of the form:
 *  http:// followed by an alternating sequence of alphanumeric
 *  characters and dots, ending with a sequence of alphanumeric
 *  characters.
 *
 *  % java WebCrawler http://www.slashdot.org
 *  http://www.slashdot.org
 *  http://www.osdn.com
 *  http://sf.net
 *  http://thinkgeek.com
 *  http://freshmeat.net
 *  http://newsletters.osdn.com
 *  http://slashdot.org
 *  http://osdn.com
 *  http://ads.osdn.com
 *  http://sourceforge.net
 *  http://www.msnbc.msn.com
 *  http://www.rhythmbox.org
 *  http://www.apple.com
 *  ...
 *
 *  % java WebCrawler http://www.cs.princeton.edu
 *  http://www.cs.princeton.edu
 *  http://www.w3.org
 *  http://maps.yahoo.com
 *  http://www.princeton.edu
 *  http://www.Princeton.EDU
 *  http://ncstrl.cs.Princeton.EDU
 *  http://www.genomics.princeton.edu
 *  http://www.math.princeton.edu
 *  http://libweb.Princeton.EDU
 *  http://libweb2.princeton.edu
 *  http://www.acm.org
 *  ...
 *
 *
 *  Instead of setting the system property in the code, you could do it
 *  from the commandline
 *  % java -Dsun.net.client.defaultConnectTimeout=250 WebCrawler http://www.cs.princeton.edu
 *
 ******************************************************************************/

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URL;
import java.io.IOException;

public class WebCrawler {

    public static void main(String[] args) {

        // timeout connection after 500 miliseconds
       System.setProperty("sun.net.client.defaultConnectTimeout", "500");
        //System.setProperty("sun.net.client.defaultReadTimeout",    "5000");

        // initial web page
        String s = args[0];

        StdOut.println("digraph sitemap {");
        StdOut.println("   overlap=false;");
        StdOut.println("   bgcolor=transparent;");
        StdOut.println("   splines=true;");
        StdOut.println("   rankdir=TB;");
        StdOut.println("   node [shape=Mrecord, fontname=\"Arial\", fontsize=18, style=filled, fillcolor=deepskyblue];");
        // list of web pages to be examined
        Queue<String> queue = new Queue<String>();
        queue.enqueue(s);
        int ind = 0;

        // dicionário de páginas web -> ids
        Map<String, Integer> marked = new HashMap<>();

        // conjunto de arestas já geradas
        Set<String> generated = new HashSet<>();

        marked.put(s, ind);

        // breadth first search crawl of web
        while (!queue.isEmpty()) {
            String v = queue.dequeue();

            String input = null;
            try {
                In in = new In(new URL(v));
                input = in.readAll();
            }
            catch (IllegalArgumentException e) {
//                StdOut.println("[could not open " + v + "]");
                continue;
            }
            catch (IOException e) {
                StdOut.println("Invalid URL");
                continue;
            }

            // if (input == null) continue;


            /*************************************************************
             *  Find links of the form: http://xxx.yyy.zzz
             *  \\w+ for one or more alpha-numeric characters
             *  \\. for dot
             *  could take first two statements out of loop
             *************************************************************/
            String regexp = "http://(\\w+\\.)+(\\w+)";
            Pattern pattern = Pattern.compile(regexp);

            Matcher matcher = pattern.matcher(input);

            // find and print all matches
            while (matcher.find()) {
                String w = matcher.group();
                //System.out.println(w);
                if (!marked.containsKey(w)) {
                    queue.enqueue(w);
                    marked.put(w, ++ind);
                    if(w.contains("pucrs"))
                        StdOut.println("\""+w+"\" [fillcolor=orange];");
                    //System.out.println("\""+marked.get(v)+"\" -> \""+marked.get(w)+"\"");
                }
                // Gera aresta se ela ainda não existe no grafo
                if(!generated.contains(v+w)) {
                    System.out.println("\""+v+"\" -> \""+w+"\"");
                    generated.add(v+w);
                }
            }

        }
        System.out.println("}");
    }
}
