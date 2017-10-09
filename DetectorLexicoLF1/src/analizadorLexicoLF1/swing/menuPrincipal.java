package analizadorLexicoLF1.swing;

import analizadorLexico.Archivo.ManejadorArchivo;
import analizadorLexico.Errores.ErrorLexema;
import analizadorLexicolf1.manejadoresAD.detector;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author angelrg
 */
public class menuPrincipal extends javax.swing.JFrame implements ClipboardOwner{

    private String pathTemporal;
    private String path="";
    private ArrayList<ErrorLexema> errores;
    private ObservableList<ErrorLexema> listaObsErrores;
    private detector lecturaTexto;
    private ManejadorArchivo archivos;

    /**
     * Creates new form menuPrincipal
     */
    public menuPrincipal() {
        archivos = new ManejadorArchivo();
        lecturaTexto = new detector();
        errores = new ArrayList<>();
        listaObsErrores = ObservableCollections.observableList(errores);
        initComponents();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    }
    
    public void copiar(String texto){
        StringSelection textoSel = new StringSelection(texto);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(textoSel, this);
    }
    
    public String pegar() throws UnsupportedFlavorException, IOException{
        Clipboard portaPapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contenido = portaPapeles.getContents(null);
        return ((String) contenido.getTransferData(DataFlavor.stringFlavor));
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

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
        informacionMenu = new javax.swing.JMenu();
        acercaDeMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Detector Lexico");

        textoTextArea.setColumns(20);
        textoTextArea.setRows(5);
        textoTextArea.setDragEnabled(true);
        textoTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoTextAreaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(textoTextArea);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${listaObsErrores}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, erroresTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${lexema}"));
        columnBinding.setColumnName("Lexema");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${columna}"));
        columnBinding.setColumnName("Columna");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${linea}"));
        columnBinding.setColumnName("Linea");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(erroresTable);

        jLabel1.setText("Errores: ");

        archivoMenu.setText("Archivo");

        abrirMenuItem.setText("Abrir");
        abrirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirMenuItemActionPerformed(evt);
            }
        });
        archivoMenu.add(abrirMenuItem);

        nuevoMenuItem.setText("Nuevo");
        nuevoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoMenuItemActionPerformed(evt);
            }
        });
        archivoMenu.add(nuevoMenuItem);

        guardarMenuItem.setText("Guardar");
        guardarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarMenuItemActionPerformed(evt);
            }
        });
        archivoMenu.add(guardarMenuItem);

        jMenuBar1.add(archivoMenu);

        editarMenu.setText("Editar");

        pegarMenuItem.setText("Copiar");
        pegarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pegarMenuItemActionPerformed(evt);
            }
        });
        editarMenu.add(pegarMenuItem);

        copiarMenuItem.setText("Pegar");
        copiarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiarMenuItemActionPerformed(evt);
            }
        });
        editarMenu.add(copiarMenuItem);

        jMenuBar1.add(editarMenu);

        informacionMenu.setText("Informacion");

        acercaDeMenuItem.setText("Acerca de");
        acercaDeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acercaDeMenuItemActionPerformed(evt);
            }
        });
        informacionMenu.add(acercaDeMenuItem);

        jMenuBar1.add(informacionMenu);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numeroErroresLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoTextAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoTextAreaKeyPressed
        lecturaTexto.detectorLexemas(textoTextArea.getText());
        actualizarBusquedaObservableErrores(lecturaTexto.getErrores());
        System.out.println("######################################################");
    }//GEN-LAST:event_textoTextAreaKeyPressed

    private void abrirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirMenuItemActionPerformed
        JFileChooser dialogo = new JFileChooser();
        dialogo.setDialogTitle("Abrir");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo", "txt");
        dialogo.setFileFilter(filtro);
        if (dialogo.showOpenDialog(this)== JFileChooser.APPROVE_OPTION) {
            path = dialogo.getSelectedFile().getAbsolutePath();
            try {
                textoTextArea.setText(archivos.lecturaArchivo(path));
                lecturaTexto.detectorLexemas(textoTextArea.getText());
                JOptionPane.showMessageDialog(this, "Archivo abierto exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "No se ha abierto ningun archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        actualizarBusquedaObservableErrores(lecturaTexto.getErrores());
    }//GEN-LAST:event_abrirMenuItemActionPerformed

    private void nuevoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoMenuItemActionPerformed
         GuardarNuevo();
         JOptionPane.showMessageDialog(this, "Guardado Exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_nuevoMenuItemActionPerformed

    private void pegarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pegarMenuItemActionPerformed
        copiar(textoTextArea.getSelectedText());
    }//GEN-LAST:event_pegarMenuItemActionPerformed

    private void copiarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copiarMenuItemActionPerformed
        try {
            textoTextArea.setText(textoTextArea.getText()+"\n"+pegar());
        } catch (UnsupportedFlavorException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al copiar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_copiarMenuItemActionPerformed

    private void acercaDeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acercaDeMenuItemActionPerformed
        JOptionPane.showMessageDialog(this, "Estudiante: Angel O. Racancoj G. \n"
                + "Carnet: 201631547", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_acercaDeMenuItemActionPerformed

    private void guardarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarMenuItemActionPerformed
        GuardarNuevo();
        JOptionPane.showMessageDialog(this, "Nuevo archivo creado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_guardarMenuItemActionPerformed

    public void actualizarBusquedaObservableErrores(ArrayList<ErrorLexema> lista){
        this.listaObsErrores.clear();
        this.listaObsErrores.addAll(lista);
    }

    public ObservableList<ErrorLexema> getListaObsErrores() {
        return listaObsErrores;
    }

    public void setListaObsErrores(ObservableList<ErrorLexema> listaObsErrores) {
        this.listaObsErrores = listaObsErrores;
    }
    
    public void GuardarNuevo(){
        JFileChooser dialogo = new JFileChooser();
        dialogo.setDialogTitle("Guardar");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo", "txt");
        dialogo.setFileFilter(filtro);
        if (dialogo.showOpenDialog(this)== JFileChooser.APPROVE_OPTION) {
            path = dialogo.getSelectedFile().getAbsolutePath();
            try {
                archivos.guardarArchivo(path, textoTextArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "No se ha guardado el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirMenuItem;
    private javax.swing.JMenuItem acercaDeMenuItem;
    private javax.swing.JMenu archivoMenu;
    private javax.swing.JMenuItem copiarMenuItem;
    private javax.swing.JMenu editarMenu;
    private javax.swing.JTable erroresTable;
    private javax.swing.JMenuItem guardarMenuItem;
    private javax.swing.JMenu informacionMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem nuevoMenuItem;
    private javax.swing.JLabel numeroErroresLabel;
    private javax.swing.JMenuItem pegarMenuItem;
    private javax.swing.JTextArea textoTextArea;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        //To change body of generated methods, choose Tools | Templates.
    }
}
