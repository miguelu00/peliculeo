/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Miguelañez-PC
 */
public class ControladorBBDD_mysql {
    private static Connection conx;
    private final static String TIPODB = "mysql", NOMBREBBDD = "bdpeliculas";
    private final static String USUARIO = "root";
    private final static String PASSWD = "4321";
    
    private ControladorBBDD_mysql() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conx = (Connection) DriverManager.getConnection("jdbc:" + TIPODB + "://localhost:3307/" + NOMBREBBDD, USUARIO, PASSWD);
        } catch (ClassNotFoundException notFoundClassErr) {
            System.err.println("NO SE ENCONTRÓ LA CLASE:");
            notFoundClassErr.printStackTrace();
        } catch (CommunicationsException comsErr) {
            System.err.println("ERROR. ¡La base de datos no está iniciada!");
            JOptionPane.showMessageDialog(new JPanel(), "ERROR. ¡No se ha puesto en marcha el servicio de Base de Datos!");
            return;
        } catch (SQLException sqlExc) {
            System.err.println("Error SQL:");
            sqlExc.printStackTrace();
        }
    }
    
    public static Connection getConexion() {
        if (conx == null) {
            new ControladorBBDD_mysql();
            return conx;
        }
        return conx;
    }
    
    /**
     * Recoge, mediante sintaxis de MYSQL, el último ID introducido en la tabla que indiquemos
     * @param nombreTabla La tabla dónde buscar el ID (debe tener uno ó mas campos AUTO-INCREMENT)
     * @return El último ID introducido; -1 si ha ocurrido un error ó no se ha iniciado la conexión.
     */
    public static int getUltimoID(String nombreTabla) {
        conx = getConexion();
        if (conx == null) {
            return -1;
        }
        try {
            Statement st = conx.createStatement();
            ResultSet res = st.executeQuery("SELECT LAST_INSERT_ID() FROM " + nombreTabla + " ORDER BY 1");
            
            return res.getInt(1);
        } catch (SQLException sql) {
            System.err.println("ERROR SQL: " + sql.getMessage());
            sql.printStackTrace();
            return -1;
        }
    }
    
    /**
     * Hacer una consulta SELECT * FROM ... con MYSQL en la tabla que elijamos y los campos que escojamos.
     * @param nombreTabla El nombre de la tabla sobre la que hacer la consulta
     * @param columnas Nombre de las columnas que queremos recuperar de la tabla especificada (separadas por coma)
     * @return Un <b>ResultSet</b> conteniendo los datos de las Filas encontradas con SELECT; NULL si no existen registros/ha fallado la consulta [SQLException]
     */
    public static ResultSet hacerSELECT(String nombreTabla, String columnas) {
        conx = ControladorBBDD_mysql.getConexion();
        if (conx == null) {
            return null;
        }
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sql = "";
        try {
            if (!"*".equals(columnas)) {
                sql = "SELECT " + columnas + " FROM " + nombreTabla;
            } else {
                sql = "SELECT * FROM " + nombreTabla + " ORDER BY 1";
            }
            pst = conx.prepareStatement(sql);
            rs = pst.executeQuery();
            
            
        } catch (SQLException sqlErr) {
            System.err.println("ERROR SQL: " + sqlErr.getMessage());
            //listaDatos = null;
        }
        return rs;
    }
    
    /**
     * Hacer una consulta SELECT * FROM ... con MYSQL en la tabla que elijamos y los campos que escojamos.
     * @param nombreTabla nombre de la tabla sobre la que queremos hacer la consulta SELECT.
     * @param campos Las columnas/campos que queremos obtener de la consulta.
     * @param WHEREdatos Sólo permitirá una especificación WHERE; Array de String con formato new String[]{NOMBRE_COLUMNA/CONDICIÓN, VALOR_A_COMPARAR}.
     * @return Un <b>ResultSet</b> con los datos de la Columna; NULL si no existen registros/ha fallado la consulta [SQLException].
     */
    public static ResultSet hacerSELECTWHERE(String nombreTabla, String campos, String[] WHEREdatos) {
        conx = ControladorBBDD_mysql.getConexion();
        
        ResultSet rs = null;
        try {
            if (!"*".equals(campos) && WHEREdatos.length > 1) {
                rs = conx.createStatement()
                        .executeQuery("SELECT " + campos + " FROM " + nombreTabla + " WHERE " + WHEREdatos[0] + "=" + WHEREdatos[1] + " ORDER BY 1");
            } else if ("*".equals(campos) && WHEREdatos.length > 1) {
                rs = conx.createStatement()
                    .executeQuery("SELECT * FROM " + nombreTabla + " WHERE " + WHEREdatos[0] + "=" + WHEREdatos[1] + " ORDER BY 1");
            } else {
                rs = null;
            }
        } catch (SQLException sqlErr) {
            System.err.println("ERROR SQL: " + sqlErr.getMessage());
            //listaDatos = null;
        }
        
        return rs;
    }
    
    /**
     * INSERT INTO en MySQL, devuelve false si falla ó ha ocurrido una excepción SQLException.
     * @param nombreTabla Nombre de la tabla sobre la que insertar los datos.
     * @param datos Los datos a insertar (separados por coma y Strings con comillas simples)
     * @return TRUE si ha podido insertar los datos en BBDD; FALSE en caso contrario
     */
    public static boolean hacerINSERT(String nombreTabla, String datos) {
        conx = ControladorBBDD_mysql.getConexion();
        if (conx == null) {
            return false;
        }
        
        try {
            if (conx.createStatement()
                .executeUpdate("INSERT INTO " + nombreTabla + " VALUES(" + datos + ")") > 0) {
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException exc) {
            System.err.println("Excepción SQL: " + exc.getMessage());
            return false;
        }
    }
    
    /**
     * Encapsular los datos de un String (separados por coma) en comillas simples.
     * @param datos Los datos que queremos insertar, en órden y 
     * @return El string que pasamos, apto para hacer INSERT en MySQL. Esto es, con comillas simples ['NOMBRE', 'DNI']
     */
    public static String formatearDatosParaINSERT(String datos) {
        conx = ControladorBBDD_mysql.getConexion();
        if (conx == null) {
            return null;
        }
        
        String datoFormateado = "";
        //Si no existe la coma en la cadena, devolvemos la cadena envuelta en comillas simples
        if (datos.indexOf(',') == -1) {
            return "'" + datos + "'";
        }
        
        for (int i=0;i!=datos.length();) {
            //Primera ocurrencia de ","
            i = datos.charAt(datos.indexOf(','));
            if (i == -1) {
                i = datos.length();
            }
            //Evitar que las subcadenas "null" se envuelvan en comillas simples; en el resto de datos que no impliquen comillas (p.Ej: INT, DOUBLE ...), MySQL elimina las comillas simples automáticamente
            if ("null".equals(datos.substring(0, i-1))) {
                datoFormateado += "null, ";
            } else {
                datoFormateado += "'" + datos.substring(0, i-1) + "', ";
            }
            //Vamos eliminando de la cadena original, palabra por palabra separada por coma (sacando subcadena, a partir de la posición después de la coma [i+1])
            if (i == -1) {
                if ("null".equals(datos)) {
                    datoFormateado += "null";
                }
                break;
            } else {
                datos = datos.substring(i+1, datos.length());
            }
        }
        //ya no hay más comas, sacamos el último string
        if ("null".equals(datos)) {
            datoFormateado += "null";
        } else {
            datoFormateado += "'" + datos + "'";
        }
        
        return datoFormateado;
    }
    
    /**
     * Órden DELETE, se borrarán los datos correspondientes en la tabla que elijamos,
     * <br>
     * bajo la columna que elijamos (campoWHERE).
     * <br>
     * Sólo permite una especificación WHERE!
     * @param nombreTabla La tabla sobre la que hacer DELETE FROM
     * @param campoWHERE Especificación para WHERE
     * @param dato Lo que buscamos de dicho WHERE [DNI=3  P.Ejemplo]
     * @return TRUE si ha podido realizar el DELETE FROM; FALSE si no ha podido
     */
    public static boolean hacerDELETE(String nombreTabla, String campoWHERE, String dato) {
        conx = ControladorBBDD_mysql.getConexion();
        if (conx == null) {
            return false;
        }
        ResultSet rs;
        
        try {
            PreparedStatement pst = conx.prepareStatement("DELETE FROM " + nombreTabla + " WHERE " + campoWHERE + "=?");
            pst.setString(1, dato);
            
            pst.executeUpdate();
        } catch (SQLException exc) {
            System.err.println("ERROR SQL: " + exc.getMessage());
            return false;
        }
        return true;
    }
    
    public static boolean hacerDELETEID(String nombreTabla, String nombreColumnaID, int ID) {
        conx = ControladorBBDD_mysql.getConexion();
        if (conx == null) {
            return false;
        }
        ResultSet rs;
        
        try {
            PreparedStatement pst = conx.prepareStatement("DELETE FROM " + nombreTabla + " WHERE " + nombreColumnaID + "=?");
            pst.setInt(1, ID);
            
            //Retornar si se han modificado o no un total de 0 o más Filas
            return pst.executeUpdate() == 0;
        } catch (SQLException exc) {
            System.err.println("ERROR SQL: " + exc.getMessage());
            return false;
        }
    }
    
    /**
     * Órden UPDATE, se actualizarán las columnas que indiquemos en datoSET. Diseñado para una sola especificación WHERE
     * <br>
     * Ejemplo de uso datoSET => ["patas", 4, "ojos", 2] será lo mismo que escribir "SET PATAS=4, OJOS=2"
     * <br>
     * Ejemplo campoWHERE => ["ID", 4] será "WHERE ID LIKE '4'"
     * @param nombreTabla Tabla en la que realizar el UPDATE
     * @param datoSET Array con primera posición LA COLUMNA A SETTEAR, y segunda posición EL DATO [COLUMNA, DATOSET]
     * @param campoWHERE Array para delimitar los campos a los que hacer UPDATE
     * @return TRUE si logra actualizar los campos de la tabla especificada; FALSE si no lo consigue u ocurrió un error
     */
    public static boolean hacerUPDATE(String nombreTabla, String[] datoSET, String[] campoWHERE) {
        conx = ControladorBBDD_mysql.getConexion();
        if (conx == null) {
            return false;
        }
        ResultSet rs;
        PreparedStatement pst;
        if (datoSET.length < 3) {
            
            try {
                pst = conx.prepareStatement("UPDATE " + nombreTabla + " SET " + datoSET[0] + "=" + datoSET[1] + " WHERE " + campoWHERE[0] + "=" + campoWHERE[1]);
            
                pst.execute();
            } catch (SQLException sqlErr) {
                System.err.println("ERROR SQL: " + sqlErr.getMessage());
                return false;
            }
        } else {
            
            String cadenaSET = "SET ";
            for (int i=0; i<datoSET.length; i+=2) {
                cadenaSET += datoSET[i] + "='" + datoSET[i+1] + "', ";
            }
            //Eliminar coma del final
            cadenaSET = cadenaSET.substring(0, cadenaSET.length()-2);
            try {
                pst = conx.prepareStatement(
                    "UPDATE " + nombreTabla + " " + cadenaSET + " WHERE " + campoWHERE[0] + " LIKE ?"
                );
                
                pst.setString(1, campoWHERE[1]);
            
                pst.executeUpdate();
            } catch (SQLException sqlErr) {
                System.err.println("ERROR SQL: " + sqlErr.getMessage());
                return false;
            }
        }
        return true;
    }
    
    /**
     * Órden UPDATE, se actualizarán las columnas que indiquemos en datoSET. Diseñado para una sola especificación WHERE
     * 
     * Ejemplo de uso datoSET => ["patas", 4, "ojos", 2] será lo mismo que escribir "SET PATAS=4, OJOS=2"
     * @param nombreTabla Tabla en la que realizar el UPDATE
     * @param cadenaSET String(cadena) con formato => "COLUMN1='dato1', COLUMN2='dato2'" ...
     * @param campoWHERE Array para delimitar los campos a los que hacer UPDATE
     * @return TRUE si logra actualizar los campos de la tabla especificada; FALSE si no lo consigue u ocurrió un error
     */
    public static boolean hacerUPDATECadenaSET(String nombreTabla, String cadenaSET, String[] campoWHERE) {
        conx = ControladorBBDD_mysql.getConexion();
        if (conx == null) {
            return false;
        }
        ResultSet rs;
        PreparedStatement pst;
        try {
            pst = conx.prepareStatement("UPDATE " + nombreTabla + " SET " + cadenaSET + " WHERE " + campoWHERE[0] + " LIKE " + campoWHERE[1]);
            
            //Retornar si se han modificado o no un total de 0 o más Filas
            return (pst.executeUpdate() > 0);
        } catch (SQLException sqlErr) {
            System.err.println("ERROR SQL: " + sqlErr.getMessage());
            return false;
        }
    }
}
