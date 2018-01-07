package Graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class GNode2 {
    public static void main(String[] args) throws FileNotFoundException {
        Graph1 g = new Graph1("g2.txt");
        g.makeGraph();
        boolean b = g.isTargetExist(1,90,new HashSet<>());
        System.out.println(b);
    }
}
class Graph1{
    private Map<Integer,Node1> map;
    private Set<Integer> set;
    private String fileName;
    public Graph1(String fileName){
        this.fileName = fileName;
        map = new HashMap<Integer, Node1>();
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

    public boolean isTargetExist(int src,int target,Set<Integer> visited){
        if(src == target){
            return true;
        }
        visited.add(src);
        Node1 n = map.get(src);
        while(n != null){
            if(!visited.contains(n.getData()) && isTargetExist(n.getData(),target,visited)){
                return true;
            }
            n = n.getNext();
        }
        return false;
    }

    private void addEadge(int src,int dest){
        if(!map.containsKey(src)){
            map.put(src,new Node1(dest));
        }else{
            Node1 n = map.get(src);
            map.put(src,create(dest,n));
        }
    }
    private Node1 create(int data,Node1 node){
        Node1 n = new Node1(data);
        if(node == null){
            node = n;
            return node;
        }
        node.setNext(create(data,node.getNext()));
        return node;
    }
}
class Node1{
    private Node1 next;
    private int data;
    public Node1(){

    }
    public Node1(int data){
        this.data = data;
    }

    public Node1 getNext() {
        return next;
    }

    public void setNext(Node1 next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
