/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import ConexionBs.conexion;
import Controller.ClientesController;
import Controller.pedidosController;
import decorator.IPizza;
import decorator.extraBarbeque;
import decorator.extraPeperoni;
import decorator.extraQueso;
import decorator.pizzaNapolitana;
import decorator.pizzaPepperoni;
import javax.swing.JOptionPane;
import observer.Usuario;
import state.Pedido;

/**
 *
 * @author Hogar
 */
public class frmCrud extends javax.swing.JFrame {

    /**
     * Creates new form frmCrud
     */
    public frmCrud() {
        initComponents();
        setSize(750, 750);
        setResizable(false);

        setName("Pizzeria Don Titto de Crem");
    }

    private void crearNuevaPizza() {
        conexion conectar = new conexion();
        conectar.establecerConexion();
        
        Usuario usuario = null;
        IPizza base;
        if (cmbPizza.getSelectedItem().equals("napolitana")) {
            base = new pizzaNapolitana();
        } else {
            base = new pizzaPepperoni();
        }
        if (chkExtraQueso.isSelected()) {
            base = new extraQueso(base);
        }
        if (chkExtraBbq.isSelected()) {
            base = new extraBarbeque(base);
        }
        if (chkExtraPepp.isSelected()) {
            base = new extraPeperoni(base);
        }
        String nombre = txtNombreUser.getText();
        if (nombre.length() == 0) {
            JOptionPane.showMessageDialog(null, "debe ingresar un nombre para la orden");
        } else {
            usuario = new Usuario(nombre);
            Pedido pedido = new Pedido(base.getDescripcion(), base.getPrecio());
            pedido.agregarUsuario(usuario);
            String tipoEstado = pedido.getEstadoNombre();
            
            // GUARDAR EN BASE DE DATOS USANDO CONTROLLERS
        ClientesController clientesController = new ClientesController(conectar);
        int idCliente = clientesController.guardarClienteYObtenerId(nombre);

        if (idCliente != -1) {
            pedidosController pedidosController = new pedidosController(conectar);
            boolean pedidoGuardado = pedidosController.guardarPedido(
                idCliente,
                base.getDescripcion(),
                tipoEstado,
                base.getPrecio()
            );
            if (pedidoGuardado) {
                String mensaje = "Producto agregado exitosamente\n"
                        + "-----------------------------------\n"
                        + "Cliente: " + nombre + "\n"
                        + "Tipo de Pizza: " + base.getDescripcion() + "\n"
                        + "Precio Total: $" + base.getPrecio() + "\n"
                        + "Estado: " + tipoEstado;
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar el pedido en la base de datos.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el cliente en la base de datos.");
        }
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbPizza = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        chkExtraQueso = new javax.swing.JCheckBox();
        chkExtraBbq = new javax.swing.JCheckBox();
        chkExtraPepp = new javax.swing.JCheckBox();
        btnCrear = new javax.swing.JToggleButton();
        btnVolver = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre del Cliente");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 59, 150, -1));

        txtNombreUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreUserActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombreUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 180, -1));

        jLabel2.setText("Seleccione la pizza");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, -1, -1));

        cmbPizza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "napolitana", "pepperoni", " " }));
        jPanel1.add(cmbPizza, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 180, -1));

        jLabel3.setText("Desea Agregar extras");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, -1, -1));

        chkExtraQueso.setText("Queso Extra");
        jPanel1.add(chkExtraQueso, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, -1, -1));

        chkExtraBbq.setText("Barbeque Extra");
        jPanel1.add(chkExtraBbq, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, 120, -1));

        chkExtraPepp.setText("Pepperoni Extra");
        jPanel1.add(chkExtraPepp, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, 120, -1));

        btnCrear.setText("Agregar");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 110, 40));

        btnVolver.setText("<html><p>Volver</p></html>");
        btnVolver.setActionCommand("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 204, 110, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreUserActionPerformed

    }//GEN-LAST:event_txtNombreUserActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        crearNuevaPizza();

    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        frmVentanaInicio Inicio = new frmVentanaInicio();
        Inicio.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

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
            java.util.logging.Logger.getLogger(frmCrud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCrud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCrud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCrud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCrud().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnCrear;
    private javax.swing.JToggleButton btnVolver;
    private javax.swing.JCheckBox chkExtraBbq;
    private javax.swing.JCheckBox chkExtraPepp;
    private javax.swing.JCheckBox chkExtraQueso;
    private javax.swing.JComboBox<String> cmbPizza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNombreUser;
    // End of variables declaration//GEN-END:variables
}
