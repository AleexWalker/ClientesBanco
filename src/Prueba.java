import java.io.*;
import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner teclado = new Scanner(System.in);
        boolean validez = true;

        FileOutputStream ficheroEscritura = new FileOutputStream("C:\\Users\\ursaa\\IdeaProjects\\ClientesBanco/cliente.info.txt");
        ObjectOutputStream objetoEscritura = new ObjectOutputStream(ficheroEscritura);

        while (validez){
            System.out.println("MENU CLIENTES BANCO FEDERAL");
            System.out.println("1. Añadir un cliente. \n" +
                    "2. Listar Cliente. \n" +
                    "3. Buscar Cliente. \n" +
                    "4. Borrar Cliente. \n" +
                    "5. Eliminar fichero 'clientes'. \n" +
                    "6. Salir de la aplicación. \n");
            System.out.print("Seleccione una opción de las anteriores: ");
            String entrada = teclado.next();

            if (entrada.equals("1")) {

                FileInputStream ficheroLectura = new FileInputStream("C:\\Users\\ursaa\\IdeaProjects\\ClientesBanco\\cliente.info.txt");
                ObjectInputStream objetoLectura = new ObjectInputStream((ficheroLectura));
                boolean comprobacionNIF = false;
                String NIF = null;

                System.out.println("-----------INTRODUZCA DATOS DEL CLIENTE-----------");

                try {

                    do {

                        System.out.print("Introduzca el NIF del cliente: ");
                        NIF = teclado.next();
                        Cliente auxiliar = (Cliente) objetoLectura.readObject();
                        while (auxiliar != null && comprobacionNIF){

                            if (auxiliar.getNIF().equals(NIF)) {
                                System.out.println("¡ESTE NIF YA ESTÁ SIENDO UTILIZADO, ESCOJA OTRO!");

                                do {
                                    auxiliar = (Cliente) objetoLectura.readObject();
                                    System.out.print("Introduzca el NIF del cliente: ");
                                    NIF = teclado.next();
                                } while (auxiliar.getNIF().equals(NIF));

                                comprobacionNIF = false;
                            } else
                                comprobacionNIF = true;
                            auxiliar = (Cliente) objetoLectura.readObject();

                        }

                    } while (comprobacionNIF);

                }catch (EOFException e){
                    teclado.nextLine();
                }catch (IOException e){

                }

                System.out.print("Introduzca el nombre del cliente: ");
                String nombre = teclado.next();
                System.out.print("Introduzca el número de teléfono del cliente: ");
                String telefono = teclado.next();
                System.out.print("Introduzca la dirección del cliente: ");
                String direccion = teclado.next();

                Cliente nombreCliente = new Cliente( NIF , nombre , telefono , direccion );

                objetoEscritura.writeObject(nombreCliente);
                objetoLectura.close();                          //Usando objetoEscritura.close() da el error siguiente: at java.base/java.io.FileOutputStream.writeBytes(Native Method)
                validez = true;
            }

            if (entrada.equals("2")) {

                FileInputStream ficheroLectura = new FileInputStream("C:\\Users\\ursaa\\IdeaProjects\\ClientesBanco\\cliente.info.txt");
                ObjectInputStream objetoLectura = new ObjectInputStream((ficheroLectura));

                Cliente auxiliar = (Cliente) objetoLectura.readObject();
                try {
                    while (auxiliar != null) {
                        System.out.println(auxiliar);
                        auxiliar = (Cliente) objetoLectura.readObject();
                    }
                } catch (EOFException e) {
                    System.out.println("Introduzca almenos a un cliente");
                    validez = true;
                } catch (IOException e){
                    System.out.println("Error de lectura del listado de clientes");
                }

                validez = true;
            }

            if (entrada.equals("3")) {

                FileInputStream ficheroLectura = new FileInputStream("C:\\Users\\ursaa\\IdeaProjects\\ClientesBanco\\cliente.info.txt");
                ObjectInputStream objetoLectura = new ObjectInputStream((ficheroLectura));
                boolean encontrado = false;
                try {

                    String busquedaNIF;
                    do {
                        Cliente auxiliar = (Cliente) objetoLectura.readObject();

                        System.out.print("Introduzca el NIF del cliente a buscar: ");
                        busquedaNIF = teclado.next();

                        while (auxiliar != null) {

                            if (busquedaNIF.equals(auxiliar.getNIF())) {
                                System.out.println(auxiliar);
                                encontrado = true;
                            }
                            auxiliar = (Cliente) objetoLectura.readObject();

                        }

                    } while (!encontrado);

                } catch (IOException e) {

                }

                if (!encontrado){
                    System.out.println("\nNo se ha encontrado el NIF de este usuario, no existe o la lista no está complementada.\n");
                }
                validez = true;
            }

            if (entrada.equals("4")) {

                FileInputStream ficheroLectura = new FileInputStream("C:\\Users\\ursaa\\IdeaProjects\\ClientesBanco\\cliente.info.txt");
                ObjectInputStream objetoLectura = new ObjectInputStream((ficheroLectura));

                File ficheroSalida = new File("C:\\Users\\ursaa\\IdeaProjects\\ClientesBanco\\cliente.info.txt");

                boolean encontrado = false;

                try {

                    String busquedaNIF;
                    do {
                        Cliente auxiliar = (Cliente) objetoLectura.readObject();

                        System.out.print("Introduzca el NIF del cliente a eliminar: ");
                        busquedaNIF = teclado.next();

                        while (auxiliar != null) {

                            if (busquedaNIF.equals(auxiliar.getNIF())) {
                                System.out.println(auxiliar);
                                ficheroSalida.delete();
                                encontrado = true;
                            }
                            auxiliar = (Cliente) objetoLectura.readObject();

                        }

                        if (!encontrado){
                            System.out.println("No se ha encontrado el NIF de este usuario o no existe");
                        }

                    } while (!encontrado);
                } catch (IOException e) {

                }
                validez = true;
            }


            if (entrada.equals("5")) {

                File borrarFichero = new File("C:\\Users\\ursaa\\IdeaProjects\\ClientesBanco\\cliente.info.txt");

                if (borrarFichero.exists()) {
                    System.out.print("¿Realmente desea eliminar el fichero? Escriba SI para suprimir finalmente el archivo: ");
                    String decision = teclado.next();
                    decision = decision.toUpperCase();

                    if (decision.equals("SI") || decision.equals("si")) {
                        ficheroEscritura.close();

                        if (borrarFichero.exists())
                            System.out.println("El fichero de clientes HA SIDO ELIMINADO con éxito.");
                        else
                            System.out.println("Por desgracia NO SE HA PODIDO eliminar el fichero de clientes debido a un error.");

                    } else
                        System.out.println("El fichero de clientes NO HA SIDO borrado finalmente");

                } else
                    System.out.println("El archivo de clientes NO EXISTE o no está creado todavía");
            }

            if (entrada.equals("6")) {
                System.out.println("¡Gracias por utilizar el asistente de clientes! \n" +
                        "¡Que tenga un buen día!");
                validez = false;
            }
        }
    }
}