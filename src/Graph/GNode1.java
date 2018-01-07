package Graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class GNode1 {
    public static void main(String[] args) throws IOException {
        Graph g = new Graph("g2.txt");
        g.makeGraph();
        //g.print(2);
        //g.bfsInQueue(2);
        Queue q = new LinkedList();
        q.add(2);
       // g.bfsInRecursive(q);
        //g.dfs(2);
        g.dfsInterative(1);
    }
}

class Graph{

    private Map<Integer,Node> map;
    private String fileName;
    private Set<Integer> set;
    public Graph(String fileName) throws FileNotFoundException {
        set = new HashSet<>();
        map = new HashMap<Integer,Node>();
        this.fileName = fileName;
    }
    public void makeGraph() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream(new File(fileName)));
        while(sc.hasNext()){
            String[] s = sc.next().split(",");
            int src = Integer.parseInt(s[0]);
            int dest = Integer.parseInt(s[1]);
            addEadge(src,dest);
        }
    }
    public void addEadge(int src,int dest){
        if(!map.containsKey(src)){
            map.put(src,new Node(dest));
        }else{
            Node n = create(dest,map.get(src));
            map.put(src,n);
        }
    }

    public Node create(int data,Node n){
        Node n1 = new Node(data);
        if(n == null){
            n = n1;
            return n;
        }
        n.setNext(create(data,n.getNext()));
        return n;
    }

    public void print(int src){
        Node n = map.get(src);
        while(n != null){
            System.out.print(n.getData()+"->");
            n = n.getNext();
        }
    }

    public void bfsInQueue(int src){
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        set.add(src);
        while(!q.isEmpty()){
            int v = q.poll();
            System.out.print(v+",");
            Node n = map.get(v);
            while(n != null){
                if(!set.contains(n.getData())){
                    set.add(n.getData());
                    q.add(n.getData());
                }
                n = n.getNext();
            }
        }
    }
    public void bfsInRecursive(Queue<Integer> queue){
        if(queue.isEmpty()){
            return;
        }
        int v = queue.poll();
        System.out.print(v+",");
        Node n = map.get(v);
        set.add(v);
        while(n != null){
            if(!set.contains(n.getData())){
                set.add(n.getData());
                queue.add(n.getData());
            }
            n = n.getNext();
        }
        bfsInRecursive(queue);
    }

    public void dfs(int src){
        Node v = map.get(src);
        System.out.print(src+",");
        set.add(src);
        while(v != null){
            if(!set.contains(v.getData())){
                set.add(v.getData());
                dfs(v.getData());
            }
            v = v.getNext();
        }
    }

    public void dfsInterative(int src){
        Stack<Integer> s = new Stack<>();
        s.push(src);
        set.add(src);
        while(!s.empty()){
            int n = s.pop();
            System.out.print(n+",");
            Node v = map.get(n);
            while(v != null){
                if(!set.contains(v.getData())){
                    set.add(v.getData());
                    s.push(v.getData());
                }
                v = v.getNext();
            }
        }
    }
}

class Node
{
    private Node next;
    private int data;

    public Node(){

    }
    public Node(int data){
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public  void setNext(Node next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}