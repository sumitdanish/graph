package Graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
/*
* https://www.hackerrank.com/contests/indeed-prime-challenge-june-2015/challenges/course-dilemma
* */
public class CourseDelima {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new FileInputStream(new File("c.txt")));
        int testCase = sc.nextInt();
        while(testCase > 0 && sc.hasNext()){
            int course = sc.nextInt();
            int x = sc.nextInt();
            Graph5 g = new Graph5(course,sc,x);
            g.courseNumber();
            testCase--;
        }
    }
}

class Graph5{

    private Map<Integer,Node5> map;
    private Queue<Integer> queue;
    private Map<Integer,Integer> indegree;
    private List<Integer> list;
    private int eadge = 0;
    private int pre = 0;
    public Graph5(int eadge,Scanner scanner,int pre){
        map = new HashMap<>();
        queue = new LinkedList<>();
        indegree = new HashMap<>();
        this.eadge = eadge;
        this.pre = pre;
        this.list = new ArrayList<>();

        makeGraph(eadge,scanner,pre);
    }
    private void makeGraph(int eadge,Scanner scanner,int pre){
        while(eadge > 0 && scanner.hasNext() && pre > 0){
            addEadge(scanner.nextInt(),scanner.nextInt());
            pre--;
            eadge--;
        }
    }
    private void addEadge(int src,int dest){
        if(!map.containsKey(src)){
            Node5 n = new Node5(dest);
            map.put(src,n);
        }else{
            map.put(src,createNode(dest,map.get(src)));
        }
        if(!indegree.containsKey(src)){
            indegree.put(src,0);
        }
        if(!indegree.containsKey(dest)){
            indegree.put(dest,1);
        }else if(indegree.containsKey(dest)){
            indegree.put(dest,indegree.get(dest)+1);
        }
    }

    public void courseNumber(){
        zero(queue);
        int x = 0;
        int cnt = 0;
        while(!queue.isEmpty()){
            int v = queue.poll();
            Node5 n = map.get(v);
            if(indegree.get(v) == 0){
               x++;
            }
            while(n != null){
                int l = indegree.get(n.getData());
                l--;
                indegree.put(n.getData(),l);
                if(l == 0){
                    queue.add(n.getData());
                }
                n = n.getNext();
            }
            cnt++;
        }
        cnt--;
        if(cnt != this.pre){
            System.out.println("No course = "+(cnt+1));
            return;
        }
        System.out.println("x = " + x);
    }


    private Node5 createNode(int data,Node5 node){
        Node5 n = new Node5(data);
        if(node == null){
            node = n;
            return node;
        }
        node.setNext(createNode(data,node.getNext()));
        return node;
    }

    private void zero(Queue<Integer> queue){
        for(Map.Entry m : indegree.entrySet()){
            if((Integer)m.getValue() == 0){
                queue.add((Integer)m.getKey());
            }
        }
    }
}

class Node5{
    private Node5 next;
    private int data;

    public Node5(){

    }

    public Node5(int data){
        this.data = data;
    }
    public Node5 getNext() {
        return next;
    }

    public void setNext(Node5 next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
