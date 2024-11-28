package TXT;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LecturaEscritura {
    // Listas para almacenar usuarios, textos y estadísticas
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<String> textos;
    private ArrayList<Estadisticas> estadisticas;

    // Constructor para inicializar las listas
    public LecturaEscritura() {
        this.listaUsuarios = new ArrayList<>();
        this.textos = new ArrayList<>();
        this.estadisticas = new ArrayList<>();
    }

    // Métodos para obtener las listas de usuarios, textos y estadísticas
    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public ArrayList<String> getListaTexto() {
        return textos;
    }

    public ArrayList<Estadisticas> getListaEstadisticas() {
        return estadisticas;
    }

    // Método para leer el archivo de usuarios y cargar los datos
    public void FicheroUsuario(String ruta) {
        listaUsuarios = new ArrayList<>();  // Limpiar lista de usuarios antes de cargar

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");  // Separar los datos por ";"

                // Validar que la línea tiene el número correcto de campos
                if (datos.length != 4) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Línea mal formada en el archivo Usuarios.txt:\n" + linea,
                            "Error en archivo de usuarios", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);  // Cerrar el programa
                }

                // Validar que la contraseña no supere los 6 caracteres
                if (datos[2].length() > 6) {
                    JOptionPane.showMessageDialog(null,
                            "Error: La contraseña del usuario '" + datos[1] + "' supera los 6 caracteres.",
                            "Error en archivo de usuarios", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);  // Cerrar el programa
                }

                // Validar que no haya nombres duplicados
                for (Usuario u : listaUsuarios) {
                    if (u.getName().equalsIgnoreCase(datos[1])) {
                        JOptionPane.showMessageDialog(null,
                                "Error: El nombre de usuario '" + datos[1] + "' está duplicado.",
                                "Error en archivo de usuarios", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);  // Cerrar el programa
                    }
                }

                // Validar el formato del correo electrónico
                String regexCorreo = "^[a-zA-Z0-9.@]+$";
                if (!datos[3].matches(regexCorreo)) {
                    JOptionPane.showMessageDialog(null,
                            "Error: El correo del usuario '" + datos[1] + "' contiene caracteres no permitidos:\n" + datos[3],
                            "Error en archivo de usuarios", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);  // Cerrar el programa
                }

                // Crear un nuevo usuario y agregarlo a la lista
                Usuario usuario = new Usuario(datos[0], datos[1], datos[2], datos[3]);
                listaUsuarios.add(usuario);
            }

            // Validar que el número de usuarios esté entre 3 y 5
            if (listaUsuarios.size() < 3 || listaUsuarios.size() > 5) {
                JOptionPane.showMessageDialog(null,
                        "Error: El archivo de usuarios debe contener entre 3 y 5 usuarios.\nUsuarios encontrados: " + listaUsuarios.size(),
                        "Error en archivo de usuarios", JOptionPane.ERROR_MESSAGE);
                System.exit(0);  // Cerrar el programa
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Error crítico: El archivo Usuarios.txt no se encontró en la ruta especificada.",
                    "Archivo no encontrado", JOptionPane.ERROR_MESSAGE);
            System.exit(0);  // Cerrar el programa
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al leer el archivo Usuarios.txt:\n" + e.getMessage(),
                    "Error en archivo de usuarios", JOptionPane.ERROR_MESSAGE);
            System.exit(0);  // Cerrar el programa
        }
    }

    // Método para leer el archivo de textos y cargar los datos
    public void FicheroTexto(String fichero) {
        textos = new ArrayList<>();  // Limpiar lista de textos antes de cargar

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Advertencia: Se encontró una línea vacía en el archivo textos.txt.",
                            "Advertencia en textos", JOptionPane.WARNING_MESSAGE);
                }
                textos.add(linea);  // Añadir cada línea al ArrayList de textos
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: El archivo textos.txt no se encontró.",
                    "Error en archivo de textos", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al leer el archivo textos.txt:\n" + e.getMessage(),
                    "Error en archivo", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // Comprobación final de la lista de textos
        if (textos.size() < 2) {  // Se espera que haya al menos 2 textos (para diferentes dificultades)
            JOptionPane.showMessageDialog(null,
                    "Error: El archivo textos.txt debe contener al menos un texto para cada dificultad.",
                    "Error en archivo de textos", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    // Método para leer el archivo de estadísticas y cargar los datos
    public void FicheroEstadistica(String fichero) {
        estadisticas = new ArrayList<>();  // Limpiar lista de estadísticas antes de cargar

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");  // Separar los datos por ";"

                // Validar que la línea tiene el número correcto de campos
                if (datos.length != 5) {
                    JOptionPane.showMessageDialog(null,
                            "Error crítico: Línea mal formada en el archivo Estadisticas.txt:\n" + linea,
                            "Error en archivo de estadísticas", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);  // Cerrar el programa
                }

                try {
                    // Crear y agregar una nueva estadística a la lista
                    Estadisticas estadistica = new Estadisticas(
                            datos[0], // ID del usuario
                            Integer.parseInt(datos[1]), // Dificultad
                            Integer.parseInt(datos[2]), // Tiempo transcurrido
                            Integer.parseInt(datos[3]), // PPM
                            Integer.parseInt(datos[4])  // Errores cometidos
                    );
                    estadisticas.add(estadistica);  // Añadir estadística a la lista
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Error crítico: Formato numérico inválido en la línea:\n" + linea,
                            "Error de formato", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);  // Cerrar el programa
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Error crítico: El archivo Estadisticas.txt no se encontró en la ruta especificada.",
                    "Archivo no encontrado", JOptionPane.ERROR_MESSAGE);
            System.exit(0);  // Cerrar el programa
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error crítico al leer el archivo Estadisticas.txt:\n" + e.getMessage(),
                    "Error en archivo", JOptionPane.ERROR_MESSAGE);
            System.exit(0);  // Cerrar el programa
        }
    }

    // Método para guardar una nueva estadística en el archivo
    public void guardarEstadistica(String fichero, Estadisticas nuevaEstadistica) {
        try (FileWriter fw = new FileWriter(fichero, true);  // Abrir archivo en modo append (agregar al final)
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            // Escribir la nueva estadística en formato CSV
            out.println(
                    nuevaEstadistica.getUser() + ";" +
                            nuevaEstadistica.getDif() + ";" +
                            nuevaEstadistica.getTiempoTranscurrido() + ";" +
                            nuevaEstadistica.getPpm() + ";" +
                            nuevaEstadistica.getErrores() + ";"
            );

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al guardar las estadísticas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
