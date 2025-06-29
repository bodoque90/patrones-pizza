/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Conexion.conexion;
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
public class frmNuevoPedido extends javax.swing.JFrame {

    /**
     * Creates new form frmCrud
     */
    public frmNuevoPedido() {
        initComponents();
        setSize(700, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        setName("Pizzeria Don Titto de Crem");
    }

    /**
     * Crea una nueva pizza personalizada y la guarda como un pedido en la base de datos.
     *
     * Funcionamiento: 1. Establece la conexión a la base de datos. 2. Crea el objeto base de pizza según la selección del usuario (napolitana o pepperoni). 3. Aplica los ingredientes extra seleccionados (queso, BBQ, pepperoni) usando el patrón Decorator. 4. Obtiene el nombre del cliente desde el campo de texto. 5. Si el nombre está vacío, muestra un mensaje y no continúa. 6. Si hay nombre: a. Crea un objeto Usuario y un Pedido con la descripción y precio de la pizza. b. Registra al usuario como observer del pedido. c. Usa los controladores para guardar el cliente y el pedido en la base de datos. d. Si todo sale bien, muestra los detalles del pedido. Si no, muestra mensajes de error apropiados.
     */
    private void crearNuevaPizza() {
        // 1. Establece la conexión a la base de datos.
        conexion conectar = new conexion();
        conectar.establecerConexion();

        Usuario usuario = null;
        IPizza base;

        // 2. Crea el objeto base de pizza según la selección.
        if (cmbPizza.getSelectedItem().equals("napolitana")) {
            base = new pizzaNapolitana();
        } else {
            base = new pizzaPepperoni();
        }

        // 3. Aplica ingredientes extra usando el patrón Decorator.
        if (chkExtraQueso.isSelected()) {
            base = new extraQueso(base);
        }
        if (chkExtraBbq.isSelected()) {
            base = new extraBarbeque(base);
        }
        if (chkExtraPepp.isSelected()) {
            base = new extraPeperoni(base);
        }

        // 4. Obtiene el nombre del cliente.
        String nombre = txtNombreUser.getText();

        // 5. Verifica si el nombre está vacío.
        if (nombre.length() == 0) {
            JOptionPane.showMessageDialog(null, "debe ingresar un nombre para la orden");
        } else {
            // 6a. Crea el usuario y el pedido.
            usuario = new Usuario(nombre);
            Pedido pedido = new Pedido(base.getDescripcion(), base.getPrecio());
            pedido.agregarUsuario(usuario); // Registra al usuario como observer
            String tipoEstado = pedido.getEstadoNombre();

            // 6c. Usa controladores para guardar en la base de datos.
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
                    // 6d. Muestra mensaje de éxito con el detalle del pedido.
                    String mensaje = "Producto agregado exitosamente\n"
                            + "-----------------------------------\n"
                            + "Cliente: " + nombre + "\n"
                            + "Tipo de Pizza: " + base.getDescripcion() + "\n"
                            + "Precio Total: $" + base.getPrecio() + "\n"
                            + "Estado: " + tipoEstado;
                    JOptionPane.showMessageDialog(null, mensaje);
                } else {
                    // Si hubo error al guardar el pedido.
                    JOptionPane.showMessageDialog(null, "No se pudo guardar el pedido en la base de datos.");
                }
            } else {
                // Si hubo error al guardar el cliente.
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

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
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
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        cmbPizza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "napolitana", "pepperoni", " " }));
        jPanel1.add(cmbPizza, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 180, -1));

        jLabel3.setText("Desea Agregar extras");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, -1, -1));

        chkExtraQueso.setText("Queso Extra");
        jPanel1.add(chkExtraQueso, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, -1, -1));

        chkExtraBbq.setText("Barbeque Extra");
        jPanel1.add(chkExtraBbq, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 120, -1));

        chkExtraPepp.setText("Pepperoni Extra");
        jPanel1.add(chkExtraPepp, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 120, -1));

        btnCrear.setText("Agregar");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 110, 40));

        btnVolver.setText("<html><p>Volver</p></html>");
        btnVolver.setActionCommand("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 110, 40));

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
            java.util.logging.Logger.getLogger(frmNuevoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmNuevoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmNuevoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmNuevoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmNuevoPedido().setVisible(true);
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
