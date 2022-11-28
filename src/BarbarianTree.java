import java.util.ArrayList;

import Entities.*;

public class BarbarianTree {
    public Node root;
    public long size;

    public BarbarianTree(){
        this.root = null;
        size = 0;
    }

    public Node find(String name) {
        return findNode(root, name);
    }
    
    private Node findNode(Node node, String name){
        if (node.getBarbarian().getName().equals(name))
            return node;
        
        for (var obj: node.sons){
            Node aux = findNode(obj, name);
            
            if (aux != null)
                return aux;
        }
        
        return null;
    }

    public void putFirst(Barbarian barbarian){
        barbarian.setGeneration(0);
        barbarian.setHeritage(barbarian.getLands());
        this.root = new Node(barbarian);
        this.size++;
    }

    public boolean put(String name, Barbarian son){
        Node father = find(name);
        son.setGeneration((father.getBarbarian().getGeneration() + 1));

        father.addSon(son);
        size++;
        return true;
    }
    
    public long getSize(){
        return this.size;
    }

    public ArrayList<Barbarian> toList(){
        var list = new ArrayList<Barbarian>();

        return toList(root, list);
    }

    private ArrayList<Barbarian> toList(Node node, ArrayList<Barbarian> list){
        list.add(node.barbarian);
        
        if (node.hasSons()){
            var lands = node.getBarbarian().getHeritage() / node.sons.size();
            for (var obj: node.sons){
                obj.getBarbarian().setHeritage(lands);
                toList(obj, list);
            }
        }
            
        return list;
    }

    public class Node {
        private Barbarian barbarian;
        private ArrayList<Node> sons = new ArrayList<Node>();

        public Node(Barbarian root){
            this.barbarian = root;
        }

        public boolean hasSons(){
            return this.sons.size() > 0;
        }

        public long countSons(){
            return this.sons.size();
        }

        public ArrayList<Node> getSons(){
            return new ArrayList<Node>(this.sons);
        }

        public Barbarian getBarbarian(){
            return this.barbarian;
        }

        public void addSon(Barbarian son){
            this.sons.add(new Node(son));
        }
    }
}