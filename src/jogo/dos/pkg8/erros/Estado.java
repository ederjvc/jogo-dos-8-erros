package jogo.dos.pkg8.erros;

import java.util.ArrayList;
import java.util.Objects;

public class Estado implements Comparable<Estado>{
    
    public ArrayList<Integer> n;
    public int cost;
         
    
    //construtor para BFS
    public Estado (ArrayList v){
        n = new ArrayList<>(v);
    }
    
    //construtor para AStar
    public Estado(ArrayList stateIni, ArrayList stateFin){
        n = new ArrayList<>(stateIni);
        this.cost = calculateCost(stateFin);     //já calcula o custo do estado com relação ao final
    }
    
        
    //calcula o custo em relação ao estado final com base em uma tabela de custos pre-calculada
    public final int calculateCost(ArrayList<Integer> stateFinal){
        int totalCost = 0;
        int auxFinalIndex;
        int[][] costTable = {
                {0,1,2,1,2,3,2,3,4},
                {1,0,1,2,1,2,3,2,3},
                {2,1,0,3,2,1,4,3,2},
                {1,2,3,0,1,2,1,2,3},
                {2,1,2,1,0,1,2,1,2},
                {3,2,1,2,1,0,3,2,1},
                {2,3,4,1,2,3,0,1,2},
                {3,2,3,2,1,2,1,0,1},
                {4,3,2,3,2,1,2,1,0}
        };
        for(int i=0; i<this.n.size();i++){
            auxFinalIndex = stateFinal.indexOf(this.n.get(i));
            totalCost = totalCost + costTable[i][auxFinalIndex];
        }
        return totalCost;
    }
    
    
    public int getCost(){
        return cost;
    }
    
    
    
    //imprime o estado em 3x3
    public void printState(){
        for (int i=0; i<n.size();i++){
            System.out.print(n.get(i)+ "  ");
            if (i%3==2){
                System.out.println();
            }
        }
    }
    
    
    //Encontra em qual posição está o Zero
    public int findZero(){
        return n.indexOf(0);
    }
    
    ////move a casa que contem zero para CIMA se for possível, caso contrario retorna null
    public Estado moveUp(){
        Estado s = new Estado(this.n);
        int zeroPos = s.findZero();
        if((zeroPos==0) || (zeroPos==1) || (zeroPos==2)){
            s = null;
        }else{
            s.n.set(zeroPos, s.n.get(zeroPos-3));
            s.n.set(zeroPos-3, 0);
        }
        return s;
    }
    
    ////move a casa que contem zero para BAIXO se for possível, caso contrario retorna null
    public Estado moveDown(){
        Estado s = new Estado(this.n);
        int zeroPos = s.findZero();
        if((zeroPos==6) || (zeroPos==7) || (zeroPos==8)){
            s = null;
        }else{
            s.n.set(zeroPos, s.n.get(zeroPos+3));
            s.n.set(zeroPos+3, 0);
        }
        return s;
    }
    
    //move a casa que contem zero para a ESQUERDA se for possível, caso contrario retorna null
    public Estado moveLeft(){
        Estado s = new Estado(this.n);
        int zeroPos = s.findZero();
        if((zeroPos==0) || (zeroPos==3) || (zeroPos==6)){
            s = null;
        }else{
            s.n.set(zeroPos, s.n.get(zeroPos-1));
            s.n.set(zeroPos-1, 0);
        }
        return s;
    }
    
    //move a casa que contem zero para a DIREITA se for possível, caso contrario retorna null
    public Estado moveRight(){
        Estado s = new Estado(this.n);
        int zeroPos = s.findZero();
        if((zeroPos==2) || (zeroPos==5) || (zeroPos==8)){
            s = null;
        }else{
            s.n.set(zeroPos, s.n.get(zeroPos+1));
            s.n.set(zeroPos+1, 0);
        }
        return s;
    }
    
    //metodo para gerar possíveis filhos e retornar lista de filhos
    public ArrayList<Estado> genChildren(){
        ArrayList<Estado> children = new ArrayList<>();
        Estado s;
        s = this.moveUp();
        if (s != null){
            children.add(s);
        }
        s = this.moveDown();
        if (s != null){
            children.add(s);
        }
        s = this.moveLeft();
        if (s != null){
            children.add(s);
        }
        s = this.moveRight();
        if (s != null){
            children.add(s);
        }
        return children;
    }
    
    //executa n movimentos para embaralhar o tabuleiro
    public void randomize(int n){
        int zeroPos;
        int rand;
        
        for (int i=0; i<n;i++){
            zeroPos = this.findZero();
            rand = (int)Math.floor(Math.random()*(4-1+1)+1);
            switch(rand){
                case 1: { //move up
                    if((zeroPos==0) || (zeroPos==1) || (zeroPos==2)){
                        
                    }else{
                        this.n.set(zeroPos, this.n.get(zeroPos-3));
                        this.n.set(zeroPos-3, 0);
                    }
                }
                case 2: { //move down
                    if((zeroPos==6) || (zeroPos==7) || (zeroPos==8)){
                        
                    }else{
                        this.n.set(zeroPos, this.n.get(zeroPos+3));
                        this.n.set(zeroPos+3, 0);
                    }
                }
                case 3: { //move left
                    if((zeroPos==0) || (zeroPos==3) || (zeroPos==6)){
                        
                    }else{ 
                        this.n.set(zeroPos, this.n.get(zeroPos-1));
                        this.n.set(zeroPos-1, 0);
                    }
                }
                
                case 4: {//move right
                    if((zeroPos==2) || (zeroPos==5) || (zeroPos==8)){
                        
                    }else{
                        this.n.set(zeroPos, this.n.get(zeroPos+1));
                        this.n.set(zeroPos+1, 0);
                    }
                }
            }
        }
    }
    
    @Override
    public boolean equals(Object o){
        if (o == null) {
            return false;
        }
        if (o.getClass()!= this.getClass()){
            return false;
        }
        boolean result = false;
        Estado s = (Estado) o;
        for (int i=0; i<n.size(); i++){
            if (this.n.get(i).equals(s.n.get(i)) ){
                result = true;
            }else {
                return false;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.n);
        return hash;
    }
    
    //Utilizado para a PriorityQueue
    @Override
    public int compareTo(Estado s){
        return (this.getCost()-s.getCost());
    }

}
