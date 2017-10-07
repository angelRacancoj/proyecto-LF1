package analizadorLexicoLF1.swing;

import analizadorLexico.Errores.ErrorLexema;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author angelrg
 */
public class menuPrincipal extends javax.swing.JFrame {

    List<ErrorLexema> errores;

    /**
     * Creates new form menuPrincipal
     */
    public menuPrincipal() {
        errores = new LinkedList<>();
        initComponents();
    }

//    public int columna(String palabra){
//        textoTextArea.
//    }
//    public int fila(String palabra){
//        
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textoTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        erroresTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        numeroErroresLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        archivoMenu = new javax.swing.JMenu();
        abrirMenuItem = new javax.swing.JMenuItem();
        nuevoMenuItem = new javax.swing.JMenuItem();
        guardarMenuItem = new javax.swing.JMenuItem();
        editarMenu = new javax.swing.JMenu();
        pegarMenuItem = new javax.swing.JMenuItem();
        copiarMenuItem = new javax.swing.JMenuItem();
        acercaDeMenu = new javax.swing.JMenu();
        analizarMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Detector Lexico");

        textoTextArea.setColumns(20);
        textoTextArea.setRows(5);
        jScrollPane1.setViewportView(textoTextArea);

        erroresTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(erroresTable);

        jLabel1.setText("Errores: ");

        archivoMenu.setText("Archivo");

        abrirMenuItem.setText("Abrir");
        archivoMenu.add(abrirMenuItem);

        nuevoMenuItem.setText("Nuevo");
        archivoMenu.add(nuevoMenuItem);

        guardarMenuItem.setText("Guardar");
        archivoMenu.add(guardarMenuItem);

        jMenuBar1.add(archivoMenu);

        editarMenu.setText("Editar");

        pegarMenuItem.setText("Copiar");
        editarMenu.add(pegarMenuItem);

        copiarMenuItem.setText("Pegar");
        editarMenu.add(copiarMenuItem);

        jMenuBar1.add(editarMenu);

        acercaDeMenu.setText("Acerca de");
        jMenuBar1.add(acercaDeMenu);

        analizarMenu.setText("analizar");
        jMenuBar1.add(analizarMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numeroErroresLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(numeroErroresLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirMenuItem;
    private javax.swing.JMenu acercaDeMenu;
    private javax.swing.JMenu analizarMenu;
    private javax.swing.JMenu archivoMenu;
    private javax.swing.JMenuItem copiarMenuItem;
    private javax.swing.JMenu editarMenu;
    private javax.swing.JTable erroresTable;
    private javax.swing.JMenuItem guardarMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem nuevoMenuItem;
    private javax.swing.JLabel numeroErroresLabel;
    private javax.swing.JMenuItem pegarMenuItem;
    private javax.swing.JTextArea textoTextArea;
    // End of variables declaration//GEN-END:variables
}
