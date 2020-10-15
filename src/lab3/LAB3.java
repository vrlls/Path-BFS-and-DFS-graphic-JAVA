/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author seviloria
 */
public class LAB3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        grafo g = new grafo();
        AYUDA ay= new AYUDA();
        
        g.setLocationRelativeTo(null);
        g.setVisible(true);
        ay.setLocationRelativeTo(null);
        ay.setVisible(true);
    }
    /*static int[][] M;
    public static boolean[] visitado = new boolean[M.length];

    public static void DFS(int u) {
        System.out.println(u + " ");
        visitado[u] = true;
        for (int i = 0; i < M.length; i++) {
            if (M[u][i] == 1) {
                if (!visitado[i]) {
                    DFS(i);
                }
            }
        }
    }

    public static void BFS(int i) {
        Queue<Integer> cola = new LinkedList<Integer>();
        visitado[i] = true;
        cola.add(i);
        int u;
        while (!cola.isEmpty()) {
            u = cola.remove();
            System.out.println(u + " ");
            for (int v = 0; v < M.length; v++) {
                if (M[u][v] == 1) {
                    if (!visitado[v]) {
                        visitado[v] = true;
                        cola.add(v);
                    }
                }
            }
        }
    }*/
}
