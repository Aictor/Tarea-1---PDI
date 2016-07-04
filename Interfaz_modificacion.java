package pdi_tarea_1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Interfaz_modificacion extends javax.swing.JFrame {
    
    public int parada;
    public int negativo;
    public int flip_h;
    public int flip_v;
    public int cw_90;
    public int cw_180;
    public int cw_270;
    public int ccw_90;
    public int ccw_180;
    public int ccw_270;
    
    public Interfaz_modificacion() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        initComponents();
        parada = 0;
        negativo = 0;
        flip_h = 0;
        flip_v = 0;
        cw_90 = 0;
        cw_180 = 0;
        cw_270 = 0;
        ccw_90 = 0;
        ccw_180 = 0;
        ccw_270 = 0;
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Cuadro_imagen = new javax.swing.JLayeredPane();
        canvas1 = new java.awt.Canvas();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        button_negativo = new javax.swing.JButton();
        CW_90 = new javax.swing.JButton();
        CW_180 = new javax.swing.JButton();
        CW_270 = new javax.swing.JButton();
        CCW_90 = new javax.swing.JButton();
        CCW_180 = new javax.swing.JButton();
        CCW_270 = new javax.swing.JButton();
        flip_hor = new javax.swing.JButton();
        flip_ver = new javax.swing.JButton();
        button_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modificador de Imagen");
        setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel1.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel1.setText("IMAGEN PRINCIPAL - VISTA PREVIA");

        Cuadro_imagen.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue));

        javax.swing.GroupLayout Cuadro_imagenLayout = new javax.swing.GroupLayout(Cuadro_imagen);
        Cuadro_imagen.setLayout(Cuadro_imagenLayout);
        Cuadro_imagenLayout.setHorizontalGroup(
            Cuadro_imagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Cuadro_imagenLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        Cuadro_imagenLayout.setVerticalGroup(
            Cuadro_imagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Cuadro_imagenLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        Cuadro_imagen.setLayer(canvas1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel2.setText("Operaciones sobre la imagen:");

        button_negativo.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        button_negativo.setText("Aplicar negativo");
        button_negativo.setToolTipText("Aplica filtro negativo sobre la imagen");
        button_negativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_negativoActionPerformed(evt);
            }
        });

        CW_90.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        CW_90.setText("Girar 90° CW");
        CW_90.setToolTipText("Gira 90° hacia la derecha");
        CW_90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CW_90ActionPerformed(evt);
            }
        });

        CW_180.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        CW_180.setText("Girar 180° CW");
        CW_180.setToolTipText("Gira 180° hacia la derecha");
        CW_180.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CW_180ActionPerformed(evt);
            }
        });

        CW_270.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        CW_270.setText("Girar 270° CW");
        CW_270.setToolTipText("Gira 270° hacia la derecha");
        CW_270.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CW_270ActionPerformed(evt);
            }
        });

        CCW_90.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        CCW_90.setText("Girar 90° CCW");
        CCW_90.setToolTipText("Gira 90° hacia la izquierda");
        CCW_90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CCW_90ActionPerformed(evt);
            }
        });

        CCW_180.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        CCW_180.setText("Girar 180° CCW");
        CCW_180.setToolTipText("Gira 180° hacia la izquierda");
        CCW_180.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CCW_180ActionPerformed(evt);
            }
        });

        CCW_270.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        CCW_270.setText("Girar 270° CCW");
        CCW_270.setToolTipText("Gira 270° hacia la izquierda");
        CCW_270.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CCW_270ActionPerformed(evt);
            }
        });

        flip_hor.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        flip_hor.setText("Flip Horizontal");
        flip_hor.setToolTipText("Aplica un espejo horizontal sobre la imagen");
        flip_hor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flip_horActionPerformed(evt);
            }
        });

        flip_ver.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        flip_ver.setText("Flip Vertical");
        flip_ver.setToolTipText("Aplica espejo vertical sobre la imagen");
        flip_ver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flip_verActionPerformed(evt);
            }
        });

        button_salir.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        button_salir.setText("Salir");
        button_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 227, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(179, 179, 179))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Cuadro_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(flip_hor, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                        .addComponent(flip_ver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(button_negativo))
                                .addGap(59, 59, 59)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(button_salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CW_270, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CW_180, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CW_90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CCW_180, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CCW_270, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CCW_90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(Cuadro_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CW_90)
                    .addComponent(button_negativo)
                    .addComponent(CCW_90))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CCW_180)
                    .addComponent(flip_hor)
                    .addComponent(CW_180))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flip_ver)
                    .addComponent(CW_270)
                    .addComponent(CCW_270))
                .addGap(18, 18, 18)
                .addComponent(button_salir)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_salirActionPerformed
        parada = 1;
    }//GEN-LAST:event_button_salirActionPerformed

    private void button_negativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_negativoActionPerformed
        if (parada==-1){
            try {
                PDI_Tarea_1.Aplicar_Negativo_Min(negativo);
                negativo++;
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_modificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        if (parada==-2){
            try {
                PDI_Tarea_1.Aplicar_Negativo(negativo);
                negativo++;
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_modificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }//GEN-LAST:event_button_negativoActionPerformed

    private void flip_horActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flip_horActionPerformed
        if (parada==-1){
            try {
                PDI_Tarea_1.Aplicar_Flip_Hor_Min(flip_h);
                flip_h++;
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_modificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
        
        if (parada==-2){
            try {
                PDI_Tarea_1.Aplicar_Flip_Hor(flip_h);
                flip_h++;
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_modificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_flip_horActionPerformed

    private void flip_verActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flip_verActionPerformed
        if (parada==-1){
            try {
                PDI_Tarea_1.Aplicar_Flip_Ver_Min(flip_v);
                flip_v++;
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_modificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
        
        if (parada==-2){
            try {
                PDI_Tarea_1.Aplicar_Flip_Ver(flip_v);
                flip_v++;
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_modificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_flip_verActionPerformed

    private void CW_90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CW_90ActionPerformed
        if (parada==-1){
            PDI_Tarea_1.Girar_CW_90_Min(cw_90);
            cw_90++;
        } 
        
        if (parada==-2){
            try {
                PDI_Tarea_1.Girar_CW_90(cw_90);
                cw_90++;
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_modificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_CW_90ActionPerformed

    private void CW_180ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CW_180ActionPerformed
        if (parada==-1){
            
        }
        
        if (parada==-2){
            try {
                PDI_Tarea_1.Girar_CW_180(cw_180);
                cw_180++;
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_modificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_CW_180ActionPerformed

    private void CW_270ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CW_270ActionPerformed
        if (parada==-1){
            
        }
        if (parada==-2){
            try {
                PDI_Tarea_1.Girar_CW_270(cw_270);
                cw_270++;
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_modificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_CW_270ActionPerformed

    private void CCW_90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CCW_90ActionPerformed
        if (parada==-1){
            try {
                PDI_Tarea_1.Girar_CCW_90_Min(ccw_90);
                ccw_90++;
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_modificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
        
        if (parada==-2){
            try {
                PDI_Tarea_1.Girar_CCW_90(ccw_90);
                ccw_90++;
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_modificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_CCW_90ActionPerformed

    private void CCW_180ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CCW_180ActionPerformed
        if (parada==-1){
            
        }
        
        if (parada==-2){
            try {
                PDI_Tarea_1.Girar_CCW_180(ccw_180);
                ccw_180++;
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_modificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_CCW_180ActionPerformed

    private void CCW_270ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CCW_270ActionPerformed
        if (parada==-1){
            
        }
        
        if (parada==-2){
            try {
                PDI_Tarea_1.Girar_CCW_270(ccw_270);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_modificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            ccw_270++;
        }
    }//GEN-LAST:event_CCW_270ActionPerformed
    
    //public void Cargar_panel
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CCW_180;
    private javax.swing.JButton CCW_270;
    private javax.swing.JButton CCW_90;
    private javax.swing.JButton CW_180;
    private javax.swing.JButton CW_270;
    private javax.swing.JButton CW_90;
    private javax.swing.JLayeredPane Cuadro_imagen;
    private javax.swing.JButton button_negativo;
    private javax.swing.JButton button_salir;
    private java.awt.Canvas canvas1;
    private javax.swing.JButton flip_hor;
    private javax.swing.JButton flip_ver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
