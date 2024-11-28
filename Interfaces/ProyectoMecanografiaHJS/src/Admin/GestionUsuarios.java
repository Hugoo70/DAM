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
    // Declaración de componentes de la interfaz
    private JTable tablaUsuarios;  // Tabla que mostrará los usuarios
    private DefaultTableModel modeloTabla;  // Modelo para gestionar las filas de la tabla
    private JButton btnAgregar;  // Botón para agregar un usuario
    private JButton btnEliminar;  // Botón para eliminar un usuario
    private JButton btnCancelar;  // Botón para cancelar la acción y regresar
    private LecturaEscritura gestorArchivos;  // Gestor para leer y escribir archivos de usuarios y estadísticas

    // Constructor de la clase
    public GestionUsuarios() {
        setLayout(new BorderLayout());  // Usamos un BorderLayout para organizar el contenido
        gestorArchivos = new LecturaEscritura();  // Inicializamos el gestor de archivos
        gestorArchivos.FicheroUsuario("src/TXT/Usuarios.txt");  // Leemos los datos de los usuarios

        // Título del panel
        JLabel lblTitulo = new JLabel("Gestión de Usuarios", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));  // Configuramos la fuente del título
        add(lblTitulo, BorderLayout.NORTH);  // Añadimos el título al borde norte del panel

        // Definición de las columnas de la tabla
        String[] columnas = {"ID", "Nombre", "Contraseña", "Correo"};
        modeloTabla = new DefaultTableModel(columnas, 0);  // Inicializamos el modelo de la tabla
        tablaUsuarios = new JTable(modeloTabla);  // Creamos la tabla con el modelo de datos
        cargarDatosUsuarios();  // Cargamos los datos de los usuarios en la tabla
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);  // Añadimos un scroll a la tabla
        add(scrollPane, BorderLayout.CENTER);  // Añadimos la tabla al centro del panel

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar Usuario");  // Botón para agregar un usuario
        btnEliminar = new JButton("Eliminar Usuario");  // Botón para eliminar un usuario
        btnCancelar = new JButton("Cancelar");  // Botón para cancelar la operación

        // Añadimos los botones al panel
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);  // Añadimos el panel de botones al borde sur del panel

        // Configuramos las acciones de los botones
        configurarAcciones();
    }

    // Método para cargar los datos de los usuarios en la tabla
    private void cargarDatosUsuarios() {
        modeloTabla.setRowCount(0);  // Limpiar la tabla antes de agregar los nuevos datos
        ArrayList<Usuario> usuarios = gestorArchivos.getListaUsuarios();  // Obtener la lista de usuarios
        // Añadir cada usuario como una nueva fila en la tabla
        for (Usuario usuario : usuarios) {
            modeloTabla.addRow(new Object[]{usuario.getId(), usuario.getName(), usuario.getPass(), usuario.getMail()});
        }
    }

    // Método para guardar los usuarios en el archivo
    private void guardarUsuariosEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/TXT/Usuarios.txt"))) {
            ArrayList<Usuario> usuarios = gestorArchivos.getListaUsuarios();  // Obtener los usuarios
            // Escribir cada usuario en el archivo
            for (Usuario usuario : usuarios) {
                bw.write(usuario.getId() + ";" + usuario.getName() + ";" + usuario.getPass() + ";" + usuario.getMail() + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Método para eliminar las estadísticas de un usuario
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

                // Si el ID no coincide, escribir la línea en el archivo temporal
                if (!datos[0].equals(userId)) {
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al procesar las estadísticas.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Reemplazar el archivo original con el archivo temporal
        if (archivoOriginal.delete()) {
            archivoTemporal.renameTo(archivoOriginal);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el archivo de estadísticas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para guardar las estadísticas en el archivo
    private void guardarEstadisticasEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/TXT/Estadisticas.txt"))) {
            ArrayList<Estadisticas> estadisticas = gestorArchivos.getListaEstadisticas();
            // Escribir las estadísticas en el archivo
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

    // Método para validar la contraseña según los criterios
    private String validarContraseña(String pass) {
        if (pass.length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres.";
        }

        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;

        // Verificar que la contraseña tenga mayúsculas, minúsculas y números
        for (char c : pass.toCharArray()) {
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            }
            if (Character.isLowerCase(c)) {
                tieneMinuscula = true;
            }
            if (Character.isDigit(c)) {
                tieneNumero = true;
            }
        }

        // Comprobaciones para cada requisito
        if (!tieneMayuscula) {
            return "La contraseña debe contener al menos una letra mayúscula.";
        }
        if (!tieneMinuscula) {
            return "La contraseña debe contener al menos una letra minúscula.";
        }
        if (!tieneNumero) {
            return "La contraseña debe contener al menos un número.";
        }

        return "VALIDA";
    }

    // Configuración de las acciones para los botones
    private void configurarAcciones() {
        // Acción para el botón "Agregar Usuario"
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si ya hay 5 usuarios
                if (gestorArchivos.getListaUsuarios().size() >= 5) {
                    JOptionPane.showMessageDialog(null, 
                        "No se pueden agregar más usuarios. El máximo permitido es 5.", 
                        "Límite alcanzado", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Pedir al usuario que ingrese los datos
                String id = JOptionPane.showInputDialog("Ingrese ID del usuario:");
                String nombre = JOptionPane.showInputDialog("Ingrese nombre del usuario:");
                String pass = JOptionPane.showInputDialog("Ingrese contraseña del usuario:");
                String correo = JOptionPane.showInputDialog("Ingrese correo del usuario:");

                if (id != null && nombre != null && pass != null && correo != null) {
                    // Validar la contraseña
                    String validacion = validarContraseña(pass);
                    if (!validacion.equals("VALIDA")) {
                        JOptionPane.showMessageDialog(null, 
                            "Error en la contraseña: " + validacion, 
                            "Contraseña Inválida", 
                            JOptionPane.WARNING_MESSAGE);
                        return; // Si la contraseña no es válida, no agregar el usuario
                    }

                    // Crear el nuevo usuario y agregarlo a la lista
                    Usuario nuevoUsuario = new Usuario(id, nombre, pass, correo);
                    gestorArchivos.getListaUsuarios().add(nuevoUsuario);
                    guardarUsuariosEnArchivo();
                    cargarDatosUsuarios();
                    JOptionPane.showMessageDialog(null, 
                        "Usuario agregado exitosamente.", 
                        "Éxito", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Acción para el botón "Eliminar Usuario"
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Comprobar si quedan 3 o menos usuarios
                if (gestorArchivos.getListaUsuarios().size() <= 3) {
                    JOptionPane.showMessageDialog(null, 
                        "No se pueden eliminar más usuarios. Debe haber al menos 3 usuarios registrados.", 
                        "Límite alcanzado", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

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
                    JOptionPane.showMessageDialog(null, 
                        "El usuario y sus estadísticas han sido eliminados correctamente.", 
                        "Confirmación", 
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Seleccione un usuario para eliminar.", 
                        "Error", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Acción para el botón "Cancelar"
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Regresar al panel de administración
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
