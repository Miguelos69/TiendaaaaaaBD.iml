package ean.tienda;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;



public class PrincipalJava {
    private static Object Producto;



    public static void main(String[] args) throws SQLException, IOException {
        Scanner teclado = new Scanner(System.in);

        //Conectarse al drive

        JdbcPooledConnectionSource con = new JdbcPooledConnectionSource("jdbc:sqlite:TiendaBD");

        //Creamos un DAO, un representante de la tabla del programa

        Dao<Producto, Integer> tablaProductos =
                DaoManager.createDao(con, Producto.class);

        int opcion = 0;
        int codigo;
        String nombre;
        String materiales;
        int cantidad;
        double precio;
        double costo_de_produccion;
        double tiempo_de_produccion;
        do {
        	System.out.println(":::::::::::Bienvenido a Tejidos Bomani:::::::::");
            System.out.println("0.Salir");
            System.out.println("1. Crear un producto");
            System.out.println("2. Mostrar todos los productos");
            System.out.println("3. Comprar productos");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Agregar existencias al producto");
            System.out.println("6. Mostrar producto con mayor precio");
            System.out.println("7. Mostrar producto menos demorado");
            System.out.println("8. Mostrar producto mas costoso de fabricar");
            System.out.print("Escoja su opcion => ");
            
            opcion = teclado.nextInt();

            switch (opcion) {

                //CREAR PRODUCTO

                case 1:
                    System.out.print("Código: ");
                    codigo = teclado.nextInt();
                    teclado.nextLine();
                    System.out.print("Nombre: ");
                    nombre = teclado.nextLine();
                    System.out.print("Materiales: ");
                    materiales = teclado.nextLine();
                    System.out.print("Cantidad: ");
                    cantidad = teclado.nextInt();
                    System.out.print("Precio: ");
                    precio = teclado.nextDouble();
                    System.out.print("Costo de producción: ");
                    costo_de_produccion = teclado.nextDouble();
                    System.out.print("Tiempo de producción en horas: ");
                    tiempo_de_produccion = teclado.nextDouble();
                    Producto p = new Producto(codigo, nombre, materiales, cantidad, precio, costo_de_produccion, tiempo_de_produccion);
                    tablaProductos.create(p);
                    break;

                //MOSTRAR TODOS LOS PRODUCTOS

                case 2:
                    for (Producto prod : tablaProductos){
                        System.out.println(prod.getCodigo() + " - " + prod.getNombre() + ", con precio de venta de: " + prod.getPrecio() + ", con costo de produccion de: "+ prod.getCosto_de_produccion() + ", y un tiempo de fabricacion de: " + prod.getTimepo_de_produccion() + ", y su codigo respectivo de: " + prod.getCodigo());
                    }
                    break;

                //COMPRAR PRODUCTOS

                case 3:
                    System.out.print("Código del producto que va a comprar:" );
                    codigo =  teclado.nextInt();
                    Producto pr = tablaProductos.queryForId(codigo);
                    if (pr != null){
                        System.out.print("Â¿Cuantos va a comprar? ");
                        cantidad = teclado.nextInt();
                        pr.setCantidad(pr.getCantidad() + cantidad);
                        System.out.println("Ahora hay " +  pr.getCantidad()  + "del producto!");
                        tablaProductos.update(pr);
                    }
                else{
                    System.out.println("El producto con ese código no existe!!");
                    }
                break;

                // ELIMINAR PRODUCTO

                case 4:
                    System.out.print("Codigo del producto que desea eliminar: ");
                    codigo = teclado.nextInt();
                    tablaProductos.deleteById(codigo);
                    System.out.println("El producto ha sido eliminado con exito! ");
                    break;

                //AGREGAR EXISTENCIAS

                case 5:
                    System.out.print("Código del producto que va a agregar existencias:" );
                    codigo =  teclado.nextInt();
                    pr = tablaProductos.queryForId(codigo);
                    if (pr != null) {
                        System.out.print("¿Cuantos va a agregar? ");
                        cantidad = teclado.nextInt();
                        pr.setCantidad(pr.getCantidad() + cantidad);
                        System.out.println("Ahora hay "  +  pr.getCantidad() +  "del producto!");
                        tablaProductos.update(pr);
                    }
                    else{
                        System.out.println("El producto con ese código no existe!!");
                    }
                    break;

                //MOSTAR PRODUCTO MAS CARO

                case 6:
                	
                	double ProdMasCostoso = 0;
                	String NombreProdMasCostoso = "";
                	for (Producto prod : tablaProductos){
                        if(prod.getPrecio() > ProdMasCostoso) {
                        	ProdMasCostoso = prod.getPrecio();
                        	NombreProdMasCostoso = prod.getNombre();
                        }   	
                    }
                	System.out.println("El producto mas caro es: "  + NombreProdMasCostoso + ", con un precio de: " + ProdMasCostoso );
                    break;
                	

                case 7:
                	
                	double ProdMenosDemorado = 1000000000;
                	String NombreProdMenosDemorado = "";
                	for (Producto prod : tablaProductos){
                        if(prod.getTimepo_de_produccion() < ProdMenosDemorado) {
                        	ProdMenosDemorado = prod.getTimepo_de_produccion();
                        	NombreProdMenosDemorado = prod.getNombre();
                        }   	
                    }
                	System.out.println("El producto menos demorado de fabricar es: "  + NombreProdMenosDemorado + ", con un tiempo de fabricación de: " + ProdMenosDemorado + "horas" );
                    break;
                    
                
                case 8:
                	
                	double ProdMayorCostoProduccion = 0;
                	String NombreProdMayorCostoProduccion = "";
                	for (Producto prod : tablaProductos){
                        if(prod.getCosto_de_produccion() > ProdMayorCostoProduccion) {
                        	ProdMayorCostoProduccion = prod.getCosto_de_produccion();
                        	NombreProdMayorCostoProduccion = prod.getNombre();
                        }   	
                    }
                	System.out.println("El producto con mayor costo de producción es: "  + NombreProdMayorCostoProduccion + ", con un costo de: " + ProdMayorCostoProduccion );
                    break;


            }
        } while (opcion != 0);

        //Cerrar la conexion con la base de datos

        con.close();
    }
}

