import java.io.*;

public class Cliente implements Serializable {

    private String NIF;
    private String nombre;
    private String telefono;
    private String direccion;

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Cliente(String NIF, String nombre, String telefono, String direccion) {
        this.NIF = NIF;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    /*public void EscribirCliente(String DNI, String nombre, String telefono, String direccion) throws IOException {
        FileOutputStream fichero = new FileOutputStream("/home/administrador/Escritorio/ClientesBanco/cliente.info.txt");
        ObjectOutputStream objeto = new ObjectOutputStream(fichero);
        objeto.writeObject();

    }
    public void ListarClientes() throws IOException, ClassNotFoundException {
        FileInputStream fichero = new FileInputStream("/home/administrador/Escritorio/ClientesBanco/cliente.info.txt");
        ObjectInputStream objeto = new ObjectInputStream((fichero));
        Object lector = objeto.readObject();
        while (lector != null)
            System.out.println(lector);

        objeto.close();
    }*/

    public String toString() {
        return "NIF: " + this.NIF + " \n" +
                "nombre: " + this.nombre + " \n" +
                "telefono: " + this.telefono + " \n" +
                "direccion: " + this.direccion + " \n";
    }
}