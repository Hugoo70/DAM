package PracticaFinalHJS;

public class MainApp {
    public static void main(String[] args) {
        // Inicializar el administrador de clientes
        ClienteManager clienteManager = new ClienteManager();
        
        // Mostrar todos los clientes cargados
        for (Cliente cliente : clienteManager.getClientes()) {
            System.out.println("Cliente cargado:");
            System.out.println("Número de Cliente: " + cliente.getNumeroCliente());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Dirección: " + cliente.getDireccion());
        }
    }
}
