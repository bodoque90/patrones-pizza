/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Conexion.conexion;
import java.sql.Connection;
import java.awt.Color;


/**
 *
 * @author z25lo
 */
public class frmVentanaInicio extends javax.swing.JFrame {
    
    conexion obConexion = new conexion();
    Connection conect = obConexion.establecerConexion();
    
        public void estadoConexion() {
        if (conect != null) {
            lblEstado.setForeground(Color.blue);
            lblEstado.setText("Conectado a " + obConexion.getBd());
        } else {
            lblEstado.setForeground(Color.red);
            lblEstado.setText("No se pudo conectar");

        }
    }

    public frmVentanaInicio() {
        initComponents();
        setSize(700, 600);
        setTitle("Pizzeria Don Titto");
        setLocationRelativeTo(null);
        estadoConexion();
    }
    
    
    




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        lblBienvenida = new javax.swing.JLabel();
        lblBienvenida1 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCerrar.setText("Cerrar Aplicacion");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 140, 50));

        btnVer.setText("Modificar pedido");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });
        jPanel2.add(btnVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 140, 50));

        btnNuevo.setText("Nuevo Pedido");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel2.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 140, 50));

        lblBienvenida.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        lblBienvenida.setText("Pizzeria Don Titto");
        jPanel2.add(lblBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 360, 50));

        lblBienvenida1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        lblBienvenida1.setText("Bienvenido");
        jPanel2.add(lblBienvenida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 230, 50));

        lblEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Network-High-Connection-icon.png"))); // NOI18N
        jPanel2.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        frmModificarPedido detalle = new frmModificarPedido();
        detalle.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        frmNuevoPedido Crud =new frmNuevoPedido();
        Crud.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

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
            java.util.logging.Logger.getLogger(frmVentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVentanaInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnVer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblBienvenida1;
    private javax.swing.JLabel lblEstado;
    // End of variables declaration//GEN-END:variables
}
