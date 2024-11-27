
package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import TXT.Usuario;
import TXT.Estadisticas;
import TXT.LecturaEscritura;

public class GestionUsuarios extends JPanel {
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private LecturaEscritura gestorArchivos;

    public GestionUsuarios() {
        setLayout(new BorderLayout());
        gestorArchivos = new LecturaEscritura();
        gestorArchivos.FicheroUsuario("src/TXT/Usuarios.txt");

        // Título
        JLabel lblTitulo = new JLabel("Gestión de Usuarios", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(lblTitulo, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"ID", "Nombre", "Contraseña", "Correo"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaUsuarios = new JTable(modeloTabla);
        cargarDatosUsuarios();
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        add(scrollPane, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar Usuario");
        btnEliminar = new JButton("Eliminar Usuario");
        btnCancelar = new JButton("Cancelar");
        

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        configurarAcciones();
    }

    private void cargarDatosUsuarios() {
        modeloTabla.setRowCount(0); // Limpiar datos previos
        ArrayList<Usuario> usuarios = gestorArchivos.getListaUsuarios();
        for (Usuario usuario : usuarios) {
            modeloTabla.addRow(new Object[]{usuario.getId(), usuario.getName(), usuario.getPass(), usuario.getMail()});
        }
    }

    private void guardarUsuariosEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/TXT/Usuarios.txt"))) {
            ArrayList<Usuario> usuarios = gestorArchivos.getListaUsuarios();
            for (Usuario usuario : usuarios) {
                bw.write(usuario.getId() + ";" + usuario.getName() + ";" + usuario.getPass() + ";" + usuario.getMail() + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarEstadisticasUsuario(String userId) {
        File archivoOriginal = new File("src/TXT/Estadisticas.txt");
        File archivoTemporal = new File("src/TXT/Estadisticas_temp.txt");

        try (
            BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))
        ) {
            String linea;

            // Leer el archivo línea por línea
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                // Comprobar si el ID del usuario coincide
                if (!datos[0].equals(userId)) {
                    // Escribir la línea en el archivo temporal si no coincide
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al procesar las estadísticas.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Reemplazar el archivo original con el temporal
        if (archivoOriginal.delete()) {
            archivoTemporal.renameTo(archivoOriginal);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el archivo de estadísticas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    
    private void guardarEstadisticasEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/TXT/Estadisticas.txt"))) {
            ArrayList<Estadisticas> estadisticas = gestorArchivos.getListaEstadisticas();
            for (Estadisticas estadistica : estadisticas) {
                bw.write(
                    estadistica.getUser() + ";" +
                    estadistica.getDif() + ";" +
                    estadistica.getTiempoTranscurrido() + ";" +
                    estadistica.getPpm() + ";" +
                    estadistica.getErrores() + "\n"
                );
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar estadísticas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    private void configurarAcciones() {
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("Ingrese ID del usuario:");
                String nombre = JOptionPane.showInputDialog("Ingrese nombre del usuario:");
                String pass = JOptionPane.showInputDialog("Ingrese contraseña del usuario:");
                String correo = JOptionPane.showInputDialog("Ingrese correo del usuario:");

                if (id != null && nombre != null && pass != null && correo != null) {
                    Usuario nuevoUsuario = new Usuario(id, nombre, pass, correo);
                    gestorArchivos.getListaUsuarios().add(nuevoUsuario);
                    guardarUsuariosEnArchivo();
                    cargarDatosUsuarios();
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaUsuarios.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener el ID del usuario seleccionado
                    String id = (String) modeloTabla.getValueAt(filaSeleccionada, 0);

                    // Eliminar usuario de la lista
                    gestorArchivos.getListaUsuarios().removeIf(usuario -> usuario.getId().equals(id));

                    // Eliminar estadísticas relacionadas directamente del archivo
                    eliminarEstadisticasUsuario(id);

                    // Guardar cambios en los usuarios
                    guardarUsuariosEnArchivo();

                    // Recargar la tabla
                    cargarDatosUsuarios();

                    // Confirmar eliminación
                    JOptionPane.showMessageDialog(null, "El usuario y sus estadísticas han sido eliminados correctamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un usuario para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });




        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(GestionUsuarios.this);
                AdminPanel adminPanel = new AdminPanel();
                frame.getContentPane().removeAll();
                frame.add(adminPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        


    }
}

