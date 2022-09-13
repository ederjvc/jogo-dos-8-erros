package jogo.dos.pkg8.erros;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    
    Estado estadoInicial;
    Estado estadoFinal;
    Queue<Estado> q;
    Queue<Estado> visitados;
    
    
    //construtor
    public BFS(ArrayList ini, ArrayList fin){
        estadoInicial = new Estado(ini);
        estadoFinal = new Estado(fin);
        q = new LinkedList<>();
        visitados = new LinkedList<>();
        q.add(estadoInicial);
    }
    
    //resolve essa instancia do problema
    public void solve(){
        Estado estadoAtual;
        ArrayList<Estado> children;
        boolean flag;
        int countMovimentos = 0;
        int countAbertos = 0;
        
        while (!q.isEmpty()){
            estadoAtual = q.remove();
            this.visitados.add(estadoAtual);
            
            
            
            //imprime para teste
            countMovimentos++;                                                //
            System.out.println("Movimento: " + countMovimentos);              //
            estadoAtual.printState();                                         //
            System.out.println();                                             //

            
            
            //compara o estado atual com a solução do problema
            if (estadoAtual.equals(this.estadoFinal)){
                System.out.println("Resolucao encontrada:");
                estadoAtual.printState();
                System.out.println("Nos abertos repetidos: " + (countAbertos - countMovimentos));           //
                return;
            }
            
            
            //gera filhos e caso seja diferente de todos visitados, adiciona na fila q
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
                    q.add(it);
                    countAbertos++;                                           //
                }
            }
        }
    }
    
}
