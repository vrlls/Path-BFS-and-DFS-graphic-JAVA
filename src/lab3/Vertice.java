/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Sebastian Viloria
 */
public class Vertice {
    int x,y;
    String nomb;
    Vertice sgt;
ArrayList<String> list;
    public Vertice(String nomb, int x, int y) {
        this.nomb = nomb;
        this.x= x;
        this.y=y;
        list   = new ArrayList<>();
    }
}
