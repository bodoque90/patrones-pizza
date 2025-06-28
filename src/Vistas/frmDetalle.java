package Vistas;

import ConexionBs.conexion;
import Controller.ClientesController;
import Controller.pedidosController;
import com.sun.jdi.connect.spi.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import observer.Usuario;
import state.IEstadoPizza;
import state.Pedido;

public class frmDetalle extends javax.swing.JFrame {

    public frmDetalle() {
        initComponents();
        setSize(750, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        datosTabla();
        txtPizza.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtEstadoActual.setEnabled(false);
    }
    private Pedido pedidoActual;
    private String nombre;

    private void buscarPedido() {
        conexion conectar = new conexion();
        conectar.establecerConexion();
        pedidosController pedidosController1 = new pedidosController(conectar);
        ClientesController clienteController = new ClientesController(conectar);
        try {
            int idCliente = Integer.parseInt(txtIdCliente.getText().trim());
            pedidoActual = pedidosController1.buscarPedidoPorId(idCliente);
            nombre = clienteController.obtenerNombreClientePorId(idCliente);

            if (pedidoActual != null) {
                txtPizza.setText(pedidoActual.getNombrePizza());
                txtPrecio.setText(String.valueOf(pedidoActual.getPrecioTotal()));
                txtEstadoActual.setText(pedidoActual.getEstadoNombre());
                pedidoActual.agregarUsuario(new observer.Usuario(nombre));
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró pedido para ese ID de cliente");
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar pedido: " + ex.getMessage());
        }
    }

    private void cambiarEstadoPedido() {
        conexion conectar = new conexion();
        conectar.establecerConexion();
        pedidosController pedidosController1 = new pedidosController(conectar);
        if (pedidoActual == null) {
            JOptionPane.showMessageDialog(this, "Primero busque un pedido.");
            return;
        }
        
        String nuevoEstado = (String) cmbEstados.getSelectedItem();
        int idCliente = Integer.parseInt(txtIdCliente.getText().trim());
        try {
            boolean cambio = pedidosController1.cambiarEstadoPedido(pedidoActual, nuevoEstado, idCliente);
            txtEstadoActual.setText(pedidoActual.getEstadoNombre());
            if (!cambio) {
                JOptionPane.showMessageDialog(this, "¡Transición de estado inválida! Debes seguir el orden correcto.");
            } else {
                // Si tienes una tabla, refresca aquí
                
                datosTabla();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cambiar estado: " + ex.getMessage());
        }
    }

    public void datosTabla() {
        conexion objConexion = new conexion();
        DefaultTableModel nuevoObjModelo = new DefaultTableModel();

        // Agrega las columnas que quieras mostrar
        nuevoObjModelo.addColumn("Id Cliente");
        nuevoObjModelo.addColumn("Nombre Cliente");
        nuevoObjModelo.addColumn("Id Pedido");
        nuevoObjModelo.addColumn("Pizza");
        nuevoObjModelo.addColumn("Estado");
        nuevoObjModelo.addColumn("Precio Total");

        tblDetalle.setModel(nuevoObjModelo);

        String[] datosBD = new String[6];
        try {
            Statement leerDatos = objConexion.establecerConexion().createStatement();
            String sql = "SELECT c.idCliente, c.nombre AS nombreCliente, p.idPedido, p.nombrePizza, p.estado, p.precioTotal "
                    + "FROM clientes c JOIN pedidos p ON c.idCliente = p.idCliente";
            ResultSet resultado = leerDatos.executeQuery(sql);

            while (resultado.next()) {
                datosBD[0] = resultado.getString("idCliente");
                datosBD[1] = resultado.getString("nombreCliente");
                datosBD[2] = resultado.getString("idPedido");
                datosBD[3] = resultado.getString("nombrePizza");
                datosBD[4] = resultado.getString("estado");
                datosBD[5] = resultado.getString("precioTotal");
                nuevoObjModelo.addRow(datosBD.clone()); // Usa clone para evitar sobrescribir filas
            }
            tblDetalle.setModel(nuevoObjModelo);

            objConexion.cerrarConexion();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener los datos: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtIdCliente = new javax.swing.JTextField();
        lblPedido = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        btnVolver = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtPizza = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtEstadoActual = new javax.swing.JTextField();
        btnCambiar = new javax.swing.JButton();
        cmbEstados = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 70, -1));

        lblPedido.setBackground(new java.awt.Color(0, 0, 0));
        lblPedido.setForeground(new java.awt.Color(0, 0, 0));
        lblPedido.setText("ID del Cliente");
        jPanel1.add(lblPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblDetalle);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 610, 440));

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 630, -1, -1));

        btnBuscar.setText("buscar pedido");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 110, 40));
        jPanel1.add(txtPizza, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 190, -1));
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 190, -1));
        jPanel1.add(txtEstadoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 190, -1));

        btnCambiar.setText("Cambiar Estado");
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCambiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 130, -1));

        cmbEstados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En preparación", "Entregado" }));
        jPanel1.add(cmbEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 140, -1));

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

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        frmVentanaInicio inicio = new frmVentanaInicio();
        inicio.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarPedido();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarActionPerformed
        cambiarEstadoPedido();
    }//GEN-LAST:event_btnCambiarActionPerformed

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
            java.util.logging.Logger.getLogger(frmDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDetalle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCambiar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbEstados;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPedido;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTextField txtEstadoActual;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtPizza;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
