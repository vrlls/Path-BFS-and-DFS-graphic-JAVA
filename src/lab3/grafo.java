/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//import static lab3.LAB3.DFS;

/**
 *
 * @author seviloria
 */
public class grafo extends javax.swing.JFrame {

    ArrayList<Integer> verticesX = new ArrayList<>();
    ArrayList<Integer> verticesY = new ArrayList<>();
    ArrayList<Integer> clickx = new ArrayList<>();
    ArrayList<Integer> clicky = new ArrayList<>();
    ArrayList<String> verticess = new ArrayList<>();
    ArrayList<Vertice> vertices = new ArrayList<>();
    ArrayList<Aristas> aristas = new ArrayList<>();

    int i = 0;
    int pos[] = new int[100];
    static int Mat[][] = new int[101][101];
    static boolean visitado[] = new boolean[101];
    
    

    /**
     * Creates new form grafo
     */
    public grafo() {
        initComponents();
        Origen.setSelectedItem("Origen");
        
        for (int j = 0; j <= 100; j++) {
            for (int k = 1; k <= 100; k++) {
                Mat[j][k] = 0;
            }

        }
        for (int j = 0; j <= 100; j++) {
            visitado[j] = false;
        }
//System.out.println(Origen.getComponentCount());
    }

    public void eliminar(int x, int y) {
        int a = 0, b = 0, j = 0, sw = 0, q = 0, sw2 = 0;
        while (sw == 0 && j < vertices.size()) {
            if ((vertices.get(j).x < x && vertices.get(j).x + 32 > x) && (vertices.get(j).y < y && vertices.get(j).y + 32 > y) || (vertices.get(j).x == x && vertices.get(j).y == y)) {
                sw = 1;
                repaint();
                elmarista(vertices.get(j).nomb);

                repaint(vertices.get(j).x, vertices.get(j).y, 5, 5);
                for (int k = 0; k < vertices.size(); k++) {
                    for (int l = 0; l < vertices.get(k).list.size(); l++) {
                        if (vertices.get(j).nomb.equals(vertices.get(k).list.get(l))) {
                            vertices.get(k).list.remove(l);
                        }
                    }
                }
            } else {
                j++;
            }
        }
        if (sw == 1) {
            for (int k = 0; k < Mat.length; k++) {
             
                if (Mat[j][k] == 1) {
                    Mat[j][k] = 0;
                    Mat[k][j] = 0;
                }
            }
        }

        Origen.removeItemAt(j + 1);
        Destino.removeItemAt(j + 1);
        Inicio.removeItemAt(j + 1);
        vertices.remove(j);
        repintar();
    }

    public void repintar() {

        Graphics g = getGraphics();
        Icon icono = new ImageIcon(getClass().getResource("/imagenes/marcador-de-posicion.png"));
        for (int j = 0; j < vertices.size(); j++) {
            g.drawString(vertices.get(j).nomb.toUpperCase(), vertices.get(j).x, vertices.get(j).y);
            icono.paintIcon(mapa, g, vertices.get(j).x, vertices.get(j).y);
        }
        System.out.println(aristas.size());
        for (int j = 0; j < aristas.size(); j++) {

            g.drawLine(aristas.get(j).x1, aristas.get(j).x2, aristas.get(j).x3, aristas.get(j).x4);
        }
    }

    public void elmarista(String nom) {
        int sw = 0, j = 0, cont2 = 0;

        for (int k = 0; k < aristas.size(); k++) {

            if (aristas.get(k).vertice1.nomb.equals(nom) || aristas.get(k).vertice2.nomb.equals(nom)) {
                aristas.remove(k);
                k = k - 1;
            }

        }
        for (int k = 0; k < 10; k++) {
            for (int l = 0; l < 10; l++) {
                if (Mat[k][l] == 1) {
                    cont2 = cont2 + 1;
                }
            }
        }

        

    }

    public void DFS(int ver) throws InterruptedException {
        Graphics g = getGraphics();
        Thread.sleep(1000);
        g.setColor(Color.RED);
        g.drawOval(vertices.get(ver).x - 2, vertices.get(ver).y - 2, 36, 36);
        Thread.sleep(1000);
        g.setColor(Color.black);
        g.drawOval(vertices.get(ver).x - 2, vertices.get(ver).y - 2, 36, 36);
        //  int u = busc(nom);
        

        visitado[ver] = true;
        
        for (int v = 0; v < Mat.length; v++) {
            if (Mat[ver][v] == 1) {
                if (!visitado[v]) {
                    DFS(v);
                }
            }
        }

    }

    public void BFS(int ver) throws InterruptedException {
        Graphics g = getGraphics();
        Queue<Integer> cola = new LinkedList<Integer>();
        visitado[ver] = true;
        cola.add(ver);
        int u;
        while (!cola.isEmpty()) {
            u = cola.remove();

            Thread.sleep(1000);
            g.setColor(Color.RED);
            g.drawOval(vertices.get(u).x - 2, vertices.get(u).y - 2, 36, 36);
            Thread.sleep(1000);
            g.setColor(Color.BLACK);
            g.drawOval(vertices.get(u).x - 2, vertices.get(u).y - 2, 36, 36);
           
            for (int v = 0; v < Mat.length; v++) {
                if (Mat[u][v] == 1) {
                    if (!visitado[v]) {
                        visitado[v] = true;
                        cola.add(v);
                    }

                }

            }

        }

    }

    public void actcb() {
        Inicio.removeAllItems();
        for (String vertices : verticess) {
            Inicio.addItem(vertices);
            Origen.addItem(vertices);
            Destino.addItem(vertices);

        }
    }

    public int deleticon(int x, int y) {
        int j = 0, sw = 1, id = 0;
        while (j < verticesX.size() && sw == 1) {
            if (verticesX.get(j) == x && verticesY.get(j) == y) {
                sw = 0;
            } else {
                j++;
            }
        }
        if (sw == 0) {
            return j;
        } else {
            return -1;
        }
        /*  if(j<4225){
          //  System.out.println(verticesX.get(0)+","+verticesY.get(0));
                                                                                                                    }else{
        int h=j-4225;
        int p=j-h;
           // System.out.println(verticesX.get(j-h)+","+verticesY.get(j-h));
        }*/
    }

    public boolean bck(int x, int y) {
        int j = 0, sw = 1;
        while (j < verticesX.size() && sw == 1) {
            if (verticesX.get(j) == x) {
                if (verticesY.get(j) == y) {
                    sw = 0;
                } else {
                    j++;
                }
            } else {
                j++;
            }
        }
        if (sw == 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean buscc(int x, int y) {

        for (Vertice vertice : vertices) {
            if (((vertice.x < x && vertice.x + 32 > x || vertice.x < x + 32 && vertice.x + 32 > x + 32) && (vertice.y < y && vertice.y + 32 > y || vertice.y < y + 32 && vertice.y + 32 > y + 32)) || (vertice.x == x && vertice.y == y)) {

                return true;
            }

        }
        return false;

    }

    public int busc(String nom) {
        int j = 0;
        while (j <= vertices.size()) {
            if (!vertices.get(j).nomb.equals(nom)) {
                j++;
            } else {
                return j;
            }

        }
        return 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mapa = new javax.swing.JLabel();
        Origen = new javax.swing.JComboBox<>();
        Destino = new javax.swing.JComboBox<>();
        texto2 = new javax.swing.JLabel();
        texto1 = new javax.swing.JLabel();
        close = new javax.swing.JButton();
        arista = new javax.swing.JButton();
        DFS = new javax.swing.JButton();
        BFS = new javax.swing.JButton();
        Inicio = new javax.swing.JComboBox<>();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Hola, bien venido al laboratorio de grafos y recorrido. Las siguientes son las instrucciones de como funciona:\nDibujar grafo:\n1. Presione click en cualquier posicion disponibles del mapa para hubicar sus vertices.\n2. Seleccione con el combobox de Origen y el combobox de Destino y luego presione el boton unir para asignar la arista que une sus dos vertices.\nRecorridos:\n1. Despues de haber creado su grafo, seleccione un punto de inicio en el combobox Inicio. posteriormente elija alguno de los dos recorridos dando click en el boton respectivo.\n2. Elija alguno de los dos recorridos dando click en el boton respectivo.\nNota: los vertices se iluminaran a medida que el recorrido pase por ellos; al finalizar se mostrara un mensaje de finalizado.\nEliminar: \n1. Sleccione el vertice que desea eliminar.\n2. Precione aceptar para eliminar.\n3. De aceptar al mensaje emergente despues de eliminar.\nNota2: Los vertices no cuentan con un ordenados, para su comodidad digite los vertices en el orden de prioridad esperado.\n\nSiempre haga buen uso del program.\nGracias por su atencion.\n");
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 650, 310));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Laboratorio grafos y recorridos (BFS Y DFS) en ambiente grafico.");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Instrucciones:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Galaxia.jpg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 380));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/unm.png"))); // NOI18N
        mapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mapaMouseClicked(evt);
            }
        });
        getContentPane().add(mapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 670));

        Origen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Origen" }));
        Origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrigenActionPerformed(evt);
            }
        });
        getContentPane().add(Origen, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 690, -1, -1));

        Destino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Destino" }));
        Destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DestinoActionPerformed(evt);
            }
        });
        getContentPane().add(Destino, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 690, -1, -1));

        texto2.setText("hasta");
        getContentPane().add(texto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 690, 40, 20));

        texto1.setText("Desde");
        getContentPane().add(texto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, -1, -1));

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Button-Close-icon.png"))); // NOI18N
        close.setBorder(null);
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        getContentPane().add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 680, 40, 30));

        arista.setText("unir");
        arista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aristaActionPerformed(evt);
            }
        });
        getContentPane().add(arista, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 690, -1, -1));

        DFS.setText("DFS");
        DFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DFSActionPerformed(evt);
            }
        });
        getContentPane().add(DFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 690, -1, -1));

        BFS.setText("BFS");
        BFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BFSActionPerformed(evt);
            }
        });
        getContentPane().add(BFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 690, -1, -1));

        Inicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inicio" }));
        getContentPane().add(Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 690, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
public boolean compro(String nom) {
        int cont = 0;
        if (nom != null) {
            if (!nom.equals("")) {
                for (int j = 0; j < vertices.size(); j++) {
                    if (vertices.get(j).nomb.equals(nom)) {
                        cont = cont + 1;
                    }
                }
            } else {
                cont = 1;
            }
        } //else {
        //      cont = 1;
        //    }
        if (cont != 0) {
            return false;
        } else {

            return true;
        }

    }

    public void dibujaricono(int x, int y) {
        boolean sw = false;
        String nom = "";
        Graphics g = getGraphics();
        nom = JOptionPane.showInputDialog("Digite un nombre para el vertice");

        while (!compro(nom)) {
            nom = JOptionPane.showInputDialog("Digite un nombre para el vertice");
        }
        if (nom != null) {
            Icon icono = new ImageIcon(getClass().getResource("/imagenes/marcador-de-posicion.png"));
            Vertice vr = new Vertice(nom, x, y);
            icono.paintIcon(mapa, g, x, y);
            g.drawString(nom.toUpperCase(), x, y);
            vertices.add(vr);
            Origen.addItem(nom);
            Destino.addItem(nom);
            Inicio.addItem(nom);
        }
    }

    private void mapaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapaMouseClicked

        Graphics g = getGraphics();
        Icon icono = new ImageIcon(getClass().getResource("/imagenes/marcador-de-posicion.png"));;
        //   ImageIcon img =new ImageIcon(getClass().getResource("/imagenes/marcador-de-posicion.png")) {};
        int x = evt.getX();
        int y = evt.getY();
        if (!buscc(x, y)) {
           
            dibujaricono(x, y);
            repintar();
        } else {

      

            int a = JOptionPane.showConfirmDialog(mapa, "Esta posicion ya se encuentra ocupada por un vertice. ¿Desea eliminarlo?");

            if (a < 1) {
                eliminar(x, y);
                repintar();
                repaint();
                mapa.update(g);
                JOptionPane.showMessageDialog(null,"Vertice eliminado" , "Proceso", 2, icono);
              //  JOptionPane.showMessageDialog(null, "Eliminado");
                repintar();

            }
        }
        g.dispose();
    }//GEN-LAST:event_mapaMouseClicked

    private void OrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrigenActionPerformed
         if (Origen.getSelectedItem().toString().equals("Origen")) {
            Destino.setEnabled(false);

        } else {
            Destino.setEnabled(true);
            System.out.println(Origen.getSelectedIndex());
            //DFS(5);
        }
    }//GEN-LAST:event_OrigenActionPerformed

    private void DestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DestinoActionPerformed


    }//GEN-LAST:event_DestinoActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        System.exit(0);
    }//GEN-LAST:event_closeActionPerformed

    private void aristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aristaActionPerformed
        int a = 0, b = 0;

        Graphics g = getGraphics();

        for (int j = 0; j < vertices.size(); j++) {

            if (vertices.get(j).nomb.equals(Origen.getSelectedItem())) {
                a = j;
            }
        }
        for (int j = 0; j < vertices.size(); j++) {

            if (vertices.get(j).nomb.equals(Destino.getSelectedItem())) {
                b = j;
            }
        }
     
        vertices.get(a).list.add(vertices.get(b).nomb);
        vertices.get(b).list.add(vertices.get(a).nomb);
        Mat[a][b] = 1;
        Mat[b][a] = 1;
        Aristas ar = new Aristas(vertices.get(a).x + 16, vertices.get(a).y + 16, vertices.get(b).x + 16, vertices.get(b).y + 16, vertices.get(a), vertices.get(b));
        aristas.add(ar);
        
        
        g.drawLine(vertices.get(a).x + 16, vertices.get(a).y + 16, vertices.get(b).x + 16, vertices.get(b).y + 16);


    }//GEN-LAST:event_aristaActionPerformed

    private void DFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DFSActionPerformed
        Graphics g = getGraphics();
        int cont = 0;
        for (int j = 0; j <= 100; j++) {
            visitado[j] = false;
        }
        try {
            DFS(Inicio.getSelectedIndex() - 1);
        } catch (InterruptedException ex) {
            Logger.getLogger(grafo.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int j = 0; j < 100; j++) {
            if (visitado[j] == false) {
                cont = cont + 1;
            }
        }
        repintar();
        repaint();
        mapa.update(g);
        JOptionPane.showMessageDialog(null, "Fin DFS");
        repintar();
    }//GEN-LAST:event_DFSActionPerformed

    private void BFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BFSActionPerformed
        Graphics g = getGraphics();
        for (int j = 0; j <= 100; j++) {
            visitado[j] = false;
        }
        try {
            BFS(Inicio.getSelectedIndex() - 1);
        } catch (InterruptedException ex) {
            Logger.getLogger(grafo.class.getName()).log(Level.SEVERE, null, ex);
        }
        repintar();
        repaint();
        mapa.update(g);
        JOptionPane.showMessageDialog(null, "Fin BFS");
        repintar();
    }//GEN-LAST:event_BFSActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(grafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(grafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(grafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(grafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new grafo().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BFS;
    private javax.swing.JButton DFS;
    private javax.swing.JComboBox<String> Destino;
    private javax.swing.JComboBox<String> Inicio;
    private javax.swing.JComboBox<String> Origen;
    private javax.swing.JButton arista;
    private javax.swing.JButton close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel mapa;
    private javax.swing.JLabel texto1;
    private javax.swing.JLabel texto2;
    // End of variables declaration//GEN-END:variables
}
