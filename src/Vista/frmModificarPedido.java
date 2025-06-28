package Vista;

import Conexion.conexion;
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

public class frmModificarPedido extends javax.swing.JFrame {

    public frmModificarPedido() {
        initComponents();
        setSize(700, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        datosTabla();
        txtPizza.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtEstadoActual.setEnabled(false);
    }
    private Pedido pedidoActual;
    private String nombre;

    /**
     * Busca el pedido asociado al cliente cuyo ID se ingresa en la interfaz.
     *
     * Funcionamiento paso a paso: 1. Establece la conexión a la base de datos. 2. Crea instancias de los controladores necesarios para pedidos y clientes. 3. Obtiene el ID del cliente desde el campo de texto. 4. Busca el pedido usando el controlador de pedidos. 5. Obtiene el nombre del cliente usando el controlador de clientes. 6. Si se encuentra un pedido: - Muestra los datos del pedido en la interfaz (pizza, precio, estado). - Registra al cliente como observador para notificarle si el estado del pedido cambia. 7. Si no se encuentra el pedido, muestra un mensaje de advertencia. 8. Si ocurre un error durante el proceso, muestra un mensaje de error con la descripción.
     */
    private void buscarPedido() {
        // 1. Establece la conexión a la base de datos.
        conexion conectar = new conexion();
        conectar.establecerConexion();

        // 2. Instancia los controladores de pedidos y clientes.
        pedidosController pedidosController1 = new pedidosController(conectar);
        ClientesController clienteController = new ClientesController(conectar);

        try {
            // 3. Obtiene el ID del cliente desde el campo de texto.
            int idCliente = Integer.parseInt(txtIdCliente.getText().trim());

            // 4. Busca el pedido correspondiente a ese ID de cliente.
            pedidoActual = pedidosController1.buscarPedidoPorId(idCliente);

            // 5. Obtiene el nombre del cliente.
            nombre = clienteController.obtenerNombreClientePorId(idCliente);

            if (pedidoActual != null) {
                // 6. Si se encuentra el pedido, actualiza la interfaz con los datos.
                txtPizza.setText(pedidoActual.getNombrePizza());
                txtPrecio.setText(String.valueOf(pedidoActual.getPrecioTotal()));
                txtEstadoActual.setText(pedidoActual.getEstadoNombre());

                // Registra al cliente como observer para notificaciones de cambios de estado.
                pedidoActual.agregarUsuario(new observer.Usuario(nombre));
            } else {
                // 7. Si no se encuentra el pedido, muestra advertencia.
                JOptionPane.showMessageDialog(this, "No se encontró pedido para ese ID de cliente");
            }

        } catch (Exception ex) {
            // 8. Manejo de errores: muestra el mensaje de excepción.
            JOptionPane.showMessageDialog(this, "Error al buscar pedido: " + ex.getMessage());
        }
    }

    /**
     * Cambia el estado del pedido actual al estado seleccionado por el usuario.
     *
     * Este método realiza las siguientes acciones: 1. Establece una conexión a la base de datos. 2. Verifica si existe un pedido cargado; si no, muestra un mensaje y retorna. 3. Obtiene el nuevo estado seleccionado y el ID del cliente desde la interfaz. 4. Intenta cambiar el estado del pedido utilizando el controlador de pedidos. 5. Actualiza la interfaz con el nuevo estado. Si la transición no es válida, muestra una advertencia. 6. Si la transición fue exitosa y existe una tabla de pedidos, la refresca. 7. Maneja cualquier error mostrando un mensaje al usuario.
     */
    private void cambiarEstadoPedido() {
        // 1. Establece conexión con la base de datos.
        conexion conectar = new conexion();
        conectar.establecerConexion();
        pedidosController pedidosController1 = new pedidosController(conectar);

        // 2. Verifica si hay un pedido cargado.
        if (pedidoActual == null) {
            JOptionPane.showMessageDialog(this, "Primero busque un pedido.");
            return;
        }

        // 3. Obtiene el nuevo estado y el ID del cliente desde la interfaz.
        String nuevoEstado = (String) cmbEstados.getSelectedItem();
        int idCliente = Integer.parseInt(txtIdCliente.getText().trim());

        try {
            // 4. Intenta cambiar el estado del pedido.
            boolean cambio = pedidosController1.cambiarEstadoPedido(pedidoActual, nuevoEstado, idCliente);

            // 5. Actualiza el campo de estado actual en la interfaz.
            txtEstadoActual.setText(pedidoActual.getEstadoNombre());

            if (!cambio) {
                // 6. Si la transición es inválida, muestra advertencia.
                JOptionPane.showMessageDialog(this, "¡Transición de estado inválida! Debes seguir el orden correcto.");
            } else {
                // 7. Si tienes una tabla de pedidos, la refresca.
                datosTabla();
            }
        } catch (Exception ex) {
            // 8. Maneja cualquier error mostrando un mensaje.
            JOptionPane.showMessageDialog(this, "Error al cambiar estado: " + ex.getMessage());
        }
    }

    /**
     * Llena la tabla de la interfaz gráfica con los datos de los pedidos y clientes.
     *
     * Funcionamiento: 1. Crea una nueva conexión a la base de datos. 2. Define un modelo de tabla con las columnas requeridas: Id Cliente, Nombre Cliente, Id Pedido, Pizza, Estado, Precio Total. 3. Asigna este modelo vacío a la tabla (tblDetalle) para limpiar cualquier dato previo. 4. Ejecuta una consulta SQL que une las tablas clientes y pedidos para obtener los datos necesarios. 5. Recorre el resultado y agrega cada fila al modelo de la tabla. 6. Al finalizar, actualiza la tabla con los nuevos datos y cierra la conexión. 7. Si ocurre un error, lo muestra mediante un mensaje emergente.
     */
    public void datosTabla() {
        // 1. Crea la conexión a la base de datos.
        conexion objConexion = new conexion();
        DefaultTableModel nuevoObjModelo = new DefaultTableModel();

        // 2. Agrega las columnas que se mostrarán en la tabla.
        nuevoObjModelo.addColumn("Id Cliente");
        nuevoObjModelo.addColumn("Nombre Cliente");
        nuevoObjModelo.addColumn("Id Pedido");
        nuevoObjModelo.addColumn("Pizza");
        nuevoObjModelo.addColumn("Estado");
        nuevoObjModelo.addColumn("Precio Total");

        // 3. Establece el modelo vacío en la tabla para limpiar datos previos.
        tblDetalle.setModel(nuevoObjModelo);

        String[] datosBD = new String[6];
        try {
            // 4. Realiza la consulta SQL para obtener los datos de clientes y pedidos.
            Statement leerDatos = objConexion.establecerConexion().createStatement();
            String sql = "SELECT c.idCliente, c.nombre AS nombreCliente, p.idPedido, p.nombrePizza, p.estado, p.precioTotal "
                    + "FROM cliente c JOIN pedido p ON c.idCliente = p.idCliente";
            ResultSet resultado = leerDatos.executeQuery(sql);

            // 5. Recorre cada fila del resultado y la agrega al modelo de la tabla.
            while (resultado.next()) {
                datosBD[0] = resultado.getString("idCliente");
                datosBD[1] = resultado.getString("nombreCliente");
                datosBD[2] = resultado.getString("idPedido");
                datosBD[3] = resultado.getString("nombrePizza");
                datosBD[4] = resultado.getString("estado");
                datosBD[5] = resultado.getString("precioTotal");
                nuevoObjModelo.addRow(datosBD.clone()); // Usa clone para evitar sobrescribir filas anteriores
            }
            // 6. Actualiza la tabla con el modelo lleno de datos.
            tblDetalle.setModel(nuevoObjModelo);

            // 7. Cierra la conexión a la base de datos.
            objConexion.cerrarConexion();
        } catch (Exception ex) {
            // 8. Si ocurre un error, muestra un mensaje y traza el error en consola.
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 670, 310));

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 490, 90, 40));

        btnBuscar.setText("buscar pedido");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 110, 40));
        jPanel1.add(txtPizza, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 190, -1));
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 190, -1));
        jPanel1.add(txtEstadoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 190, -1));

        btnCambiar.setText("Cambiar Estado");
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCambiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 130, -1));

        cmbEstados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En preparación", "Entregado" }));
        jPanel1.add(cmbEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 140, -1));

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
            java.util.logging.Logger.getLogger(frmModificarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmModificarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmModificarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmModificarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmModificarPedido().setVisible(true);
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
