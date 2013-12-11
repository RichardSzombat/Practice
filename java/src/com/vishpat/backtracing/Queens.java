package com.vishpat.backtracing;

import java.util.Set;
import java.util.HashSet;
import java.util.Vector;
import java.util.Stack;

class Pos {
    public int x;
    public int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Pos) {
            Pos p = (Pos)obj;
            if (p.x == this.x && p.y == this.y) {
                return true;
            } 
        }
        return false;
    }

    public int hashCode() {
        return 10*this.x + this.y;
    }
}

class QueensNode {

    private Set<Pos> configuration;
    private Set<QueensNode> children;
    private int board_size;

    public QueensNode(Set<Pos> configuration, int board_size) {
        this.configuration = configuration;
        this.board_size = board_size;
    }

    public void printConfiguration() {
        for (Pos p: this.configuration) {
            System.out.format("(%d, %d), ", p.x, p.y);
        }
        System.out.println();
    }

    public Set<QueensNode> getChildren() {
        HashSet<QueensNode> children = new HashSet<QueensNode>();
        Set<Pos> positions = this.getNextValidPositions();

        for (Pos p: positions){
            HashSet<Pos> nextConfiguration = new HashSet<Pos>();
            nextConfiguration.addAll(this.configuration);
            nextConfiguration.add(p);
            QueensNode childQueensNode = new QueensNode(nextConfiguration, this.board_size); 
            children.add(childQueensNode);
        }

        return children; 
    }

    public boolean isLeaf() {
        return this.board_size == this.configuration.size();
    }

    public Set<Pos> getNextValidPositions() {
        Vector <Set<Pos>> positions = new Vector<Set<Pos>>();

        int xpos = 0;
        int ypos = 0;
        
        for (Pos pos: this.configuration) {
            Set<Pos> validPositions = new HashSet<Pos>();
            for (int i = 0; i < this.board_size; i++) {
                for (int j = 0; j < this.board_size; j++) {
                    if (i == pos.x || j == pos.y) {
                        continue;
                    }

                    if (Math.abs(i - pos.x) == Math.abs(j - pos.y)) {
                        continue;
                    }
                    
                    Pos p = new Pos(i, j);
                    validPositions.add(p);
                }
            }
            positions.add(validPositions);
        }
      
        if (positions.size() == 0) {
            return new HashSet<Pos>();
        }

        Set<Pos> validPosSet = positions.elementAt(0);    

        for (Set<Pos> validPositions: positions) {
            validPosSet.retainAll(validPositions); 
        }

        return validPosSet;
    }
}

class Queens {
    public static void main(String[] args) {
        int board_size = 8;
        
        Stack<QueensNode> stack = new Stack<QueensNode>(); 
       
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                HashSet<Pos> set = new HashSet<Pos>();
                set.add(new Pos(i, j));
                QueensNode n = new QueensNode(set, board_size);
                stack.push(n);
           }
        }
        
        while (!stack.isEmpty()) {
            QueensNode n = stack.pop();

            if (n.isLeaf()) {
                n.printConfiguration();
                break;
            } else {
                for (QueensNode child: n.getChildren()) {
                    stack.push(child);
                }
            }
        }
    }
}
