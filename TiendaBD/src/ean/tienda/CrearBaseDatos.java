package ean.tienda;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;

public class CrearBaseDatos {
    public static void main(String[] args) throws SQLException, IOException {

        //Conectarse al drive

        JdbcPooledConnectionSource con = new JdbcPooledConnectionSource( "jdbc:sqlite:TiendaBD");

        //Creamos la tabla en base de datos

        TableUtils.createTableIfNotExists(con, Producto.class);
        System.out.print("La tabla del producto ha sido creada con exito");

        // Cerramos la concexion con la base de datos

        con.close();
    }
}


