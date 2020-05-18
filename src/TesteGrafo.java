public class TesteGrafo
{
    public static void main(String args[])
    {
        Graph g = new Graph(5);
        g.addEdge(1,2);
        g.addEdge(0,1);
        g.addEdge(2,4);
        g.addEdge(4,3);
        g.addEdge(1,4);

        System.out.println("Adjacentes ao 1:");
        for(int v: g.adj(1))
        {
            System.out.println("==> "+v);
        }

        System.out.println(g.toDot());
    }
}

