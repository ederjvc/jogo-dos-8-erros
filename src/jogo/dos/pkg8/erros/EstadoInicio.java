package jogo.dos.pkg8.erros;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class EstadoInicio {
    Estado estadoInicial;
    Estado estadoFinal;
    PriorityQueue<Estado> pq;
    Queue<Estado> visitados;
    
    //construtor recebe o array atual e o final para ser utilizado no calculo do custo
    public EstadoInicio(ArrayList ini, ArrayList fin){
        estadoInicial = new Estado(ini,fin);
        estadoFinal = new Estado(fin,fin);
        pq = new PriorityQueue<>();
        visitados = new LinkedList<>();
        pq.add(estadoInicial);
    }
    
    //resolve a instancia do problema
    public void solve(){
        Estado estadoAtual;
        ArrayList<Estado> children;
        boolean flag;
        int countMovimentos = 0;
        int countAbertos = 0;
        
        while (!pq.isEmpty()){
            estadoAtual = pq.poll();
            this.visitados.add(estadoAtual);
            
            //imprime para teste
            countMovimentos++;                                                //
            System.out.println("Movimento: " + countMovimentos);              //
            estadoAtual.printState();                                         //
            System.out.println("Cost: " + estadoAtual.getCost());                                             //
            System.out.println();                                             //

            
            
            //compara o estado atual com a solução do problema
            if (estadoAtual.equals(this.estadoFinal)){
                System.out.println("Resolucao encontrada:");
                estadoAtual.printState();
                System.out.println("Nos abertos repetidos: " + (countAbertos - countMovimentos));           //
                return;
            }
            
            
            //gera filhos e caso seja diferente de todos visitados, adiciona na fila de prioridade pq
            children = estadoAtual.genChildren();
            for (Estado it : children){
                flag = true;
                for(Estado it2 : this.visitados){
                    if (it.equals(it2)){
                        flag = false;
                        break;
                    } 
                }
                if (flag){
                    Estado auxState = new Estado(it.n,estadoFinal.n);
                    pq.add(auxState);
                    countAbertos++;                                           //
                }
            }
        }
    }
    
    
    
}
