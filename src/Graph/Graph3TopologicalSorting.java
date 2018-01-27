package Graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Graph3TopologicalSorting {
    public static void main(String[] args) {
        GraphT g = new GraphT("g3.txt");
        g.makeGraph();
        //g.print(5);
        g.topologicalSort();
    }
}

class GraphT {
    private Map<Integer, Node3> map;
    private Set<Integer> set;
    private Map<Integer, Integer> indeg;
    private String fileName;
    private Queue<Integer> queue;
    private Set<Integer> vertex;
    public GraphT(String fileName) {
        map = new HashMap<>();
        set = new HashSet<>();
        indeg = new HashMap<>();
        queue = new LinkedList<>();
        vertex = new HashSet<>();
        this.fileName = fileName;
    }

    public void makeGraph() {
        try {
            Scanner sc = new Scanner(new FileInputStream(new File(fileName)));
            while (sc.hasNext()) {
                String[] s = sc.next().split(",");
                int src = Integer.parseInt(s[0]);
                int dest = Integer.parseInt(s[1]);
                vertex.add(src);
                vertex.add(dest);
                addEadge(src, dest);
            }
        } catch (IOException ex) {

        }
    }

    public void addEadge(int src, int dest) {
        if (!map.containsKey(src)) {
            map.put(src, new Node3(dest));
        } else {
            Node3 n = map.get(src);
            map.put(src, create(dest, n));
        }

        if(!indeg.containsKey(src)){
            indeg.put(src,0);
        }
        if(!indeg.containsKey(dest)){
            indeg.put(dest,1);
        }else if(indeg.containsKey(dest)){
            indeg.put(dest,indeg.get(dest)+1);
        }
    }

    public void print(int src) {
        Node3 n = map.get(src);
        System.out.print(src + " -> ");
        while (n != null) {
            System.out.print(n.getData() + " -> ");
            n = n.getNext();
        }
    }
    public void zeroIndegree(){
        for(Map.Entry m : indeg.entrySet()){
            if((Integer)m.getValue() == 0){
                queue.add((Integer) m.getKey());
            }
        }
    }
    public Node3 create(int data, Node3 node) {
        Node3 n = new Node3(data);
        if (node == null) {
            node = n;
            return node;
        }
        node.setNext(create(data, node.getNext()));
        return node;
    }

    public void topologicalSort(){
        zeroIndegree();
        int cnt = 0;
        while(!queue.isEmpty()){
            int v = queue.poll();
            if(indeg.get(v) == 0){
                System.out.print(v+",");
            }
            Node3 n = map.get(v);
            while(n != null){
                int x = indeg.get(n.getData());
                x--;
                indeg.put(n.getData(),x);
                if(x == 0){
                    queue.add(n.getData());
                }
                n = n.getNext();
            }
            cnt++;
        }
        cnt--;
        if(cnt != 5){
            System.out.println("No Sorting..");
        }
        /*st.push(src);
        set.add(src);
        System.out.print(src+", ");
        while(!st.isEmpty()){
            int v = st.pop();
            System.out.println("src : "+v+", ");
            Node3 n = map.get(v);
            while(n !=null){
                int x = indeg.get(n.getData());
                x--;
                indeg.put(n.getData(),x);
                if(indeg.get(n.getData()) == 0){
                    System.out.print(n.getData()+", ");
                }
                st.push(n.getData());
                n = n.getNext();
            }
        }*/
    }
}

class Node3 {
    private Node3 next;
    private int data;

    public Node3() {

    }

    public Node3(int data) {
        this.data = data;
    }

    public Node3 getNext() {
        return next;
    }

    public void setNext(Node3 next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
