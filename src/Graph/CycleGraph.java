package Graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class CycleGraph {
    public static void main(String[] args) throws FileNotFoundException {
        GraphC gc = new GraphC("g5.txt");
        gc.makeGraph();
        boolean b = gc.isCycle(0);
        System.out.println("b = " + b);
    }

}
class GraphC{
    private Map<Integer,Node4> map;
    private String fileName;
    private Set<Integer> set;
    public GraphC(String fileName){
        map = new HashMap<>();
        set = new HashSet<>();
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
    public boolean isCycle(int src){
        set.add(src);
        Node4 n = map.get(src);
        while(n != null){
            if(!set.contains(n.getData()) && isCycle(n.getData())){
                set.add(n.getData());
                return true;
            }else if(set.contains(n.getData())){
                return true;
            }
            n = n.getNext();
        }
        set.remove(src);
        return false;
    }
    public void addEadge(int src,int dest){
        if(!map.containsKey(src)){
            Node4 n = new Node4(dest);
            map.put(src,n);
        }else{
            Node4 n = create(dest,map.get(src));
            map.put(src,n);
        }
    }
    private Node4 create(int data,Node4 node){
        Node4 n = new Node4(data);
        if(node  == null){
            node = n;
            return n;
        }
        node.setNext(create(data,node.getNext()));
        return node;
    }
}
class Node4{
    private Node4 next;
    private int data;

    public Node4(){

    }
    public Node4(int data){
        this.data = data;
    }
    public Node4 getNext() {
        return next;
    }

    public void setNext(Node4 next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
