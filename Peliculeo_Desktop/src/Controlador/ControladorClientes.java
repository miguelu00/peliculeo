/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Exceptions.ClienteYaExisteException;
import Exceptions.ErrorInsercionException;
import Exceptions.ErrorListadoClientesException;
import gestionPeliculas.dto.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguelañez-PC
 * 
 * CLASE CONTROLADOR CON ARRAYLIST DE Objs. CLIENTE
 */
public class ControladorClientes {
    private static List<Cliente> listaClientes = new ArrayList<>();
    
    public static void nuevoCliente(Cliente cliente) throws ClienteYaExisteException {
        if (existeCliente(cliente.getDNI())) {
            throw new ClienteYaExisteException();
        }
        
        //Agregar tanto a la BBDD, como al List<Cliente>
        boolean hecho = ControladorBBDD_mysql.hacerINSERT("clientes",
                "'" + cliente.getDNI() + "',"
                        + "'" + cliente.getNombre() + "',"
                        + "'" + cliente.getApellidos() + "',"
                        + "'" + cliente.getFechAlta() + "',"
                        + "'" + cliente.getProvincia() + "'");
        
        listaClientes.add(cliente);
    }
    
    public static boolean eliminarCliente(Cliente cli) {
        if (listaClientes.contains(cli)) {
            return listaClientes.remove(cli);
        }
        return false;
    }
    
    public static Cliente eliminarClienteByNIF(String DNI) {
        Cliente aux = null;
        boolean hecho = false;
        for (Cliente c: listaClientes) {
            if (c.getDNI().equals(DNI)) {
                aux = c;
                hecho = true;
            }
        }
        if (hecho) {
            listaClientes.remove(aux);
            hecho = ControladorBBDD_mysql.hacerDELETE("clientes", "NIF", DNI);
            if (hecho) {
                return aux;
            }
        }
        return null;
    }
    
    public static boolean editarCliente(Cliente clienteCambiado) throws ErrorInsercionException {
        Cliente edit = ControladorClientes.getClienteByNIF(clienteCambiado.getDNI());
        //Comprobar que el Cliente no es nulo
        if (edit == null) {
            throw new ErrorInsercionException("ERROR AL ACTUALIZAR EL CLIENTE!");
        }
        //Editar sólo si los campos del cliente a Editar son distintos actualmente
        if (!clienteCambiado.toString().equals(edit.toString())) {
            listaClientes.remove(edit);
            listaClientes.add(clienteCambiado);
        } else {
            return false;
        }
        
        //Hacer update en BBDD
        boolean hecho = ControladorBBDD_mysql.hacerUPDATE("clientes", new String[]{"nombre", clienteCambiado.getNombre(), "apellidos", 
            clienteCambiado.getApellidos(), "fecha_Alta", clienteCambiado.getFechAlta(), "provincia", clienteCambiado.getProvincia()},
                new String[]{"NIF", clienteCambiado.getDNI()});
        
        return hecho;
    }
    
    /**
     * Actualizará el ArrayList de Cliente's actual a partir de los datos introducidos en la tabla de Clientes
     * @param modeloT 
     */
    public static void actualizarListaDesdeTablaGUI(DefaultTableModel modeloT) {
        int numColumnas = modeloT.getColumnCount();
        int numFilas = modeloT.getRowCount();
        ArrayList[] listadoClientes = new ArrayList[numFilas];
        ArrayList<String> datosCliente = new ArrayList<>();
        
        for (int i = 0; i<numFilas; i++) {
            for (int j = 0; j<numColumnas; j++) {
                datosCliente.add(String.valueOf(modeloT.getValueAt(i, j)));
            }
            listadoClientes[i] = datosCliente;
        }
       
        //Crear la lista de nuevo (new ArrayList la vaciará)
        listaClientes = new ArrayList<Cliente>();
        
        //Rellenar la lista recien vaciada con los datos de los Clientes que se han ido recogiendo desde la jTable.
        for (ArrayList al : listadoClientes) {
            Cliente c = new Cliente(); //Cliente vacío
            c.setArray(al);
            listaClientes.add(c);
        }
    }
    
    /**
     * Evaluará si la lista de Clientes de este controlador contiene alguna película con el NOMBRE y AÑO (ANIO) pasados.
     * Realiza la comprobación directamente en la BASE DE DATOS.
     * @param NIFusuario El DNI/NIF del Usuario a encontrar, en STRING.
     * @return TRUE si existe una entrada con el NIF especificado, FALSE si no.
     */
    public static boolean existeCliente(String NIFusuario) {
        ResultSet res = ControladorBBDD_mysql.hacerSELECT("clientes", "*");
        
        try {
            while (res.next()) {
                if (Objects.equals(res.getString("NIF"), NIFusuario)) {
                    return true;
                }
            }
        } catch (SQLException sqlErr) {
            System.err.println("ERROR SQL => " + sqlErr.getMessage());
        }
        
        
        return false;
    }
    
    
    public static String getNombreCliente(int indiceLista) {
        return listaClientes.get(indiceLista).getNombre();
    }
    
    public static String getApellidosCliente(int indiceLista) {
        return listaClientes.get(indiceLista).getApellidos();
    }
    
    public static String getDNICliente(int indiceLista) {
        return listaClientes.get(indiceLista).getDNI();
    }
    
    /**
     * 
     * @param NIF
     * @return Cliente|NULL
     */
    public static Cliente getClienteByNIF(String NIF) {
        Cliente encontrado = null;
        for (Cliente c: listaClientes) {
            if (Objects.equals(c.getDNI(), NIF)) {
                encontrado = c;
                break;
            }
        }
        
        return encontrado;
    }
    
    /**
     * Actualizará la lista de CLIENTES desde la Base de Datos.
     * @sideeffect 
     * @return TRUE si ha podido llenar el listado de CLIENTES desde la BBDD.<br>
     * FALSE en caso contrario.
     */
    private static boolean llenarListadoBBDD() {
        ResultSet rs = ControladorBBDD_mysql.hacerSELECT("clientes", "*");
        
        //Limpiamos el ArrayList de Cliente's
        listaClientes = new ArrayList<Cliente>();
        Cliente aux = null;
        
        try {
            while (rs.next()) {
                aux = new Cliente();
                
                aux.setDNI(rs.getString("NIF"));
                aux.setNombre(rs.getString("nombre"));
                aux.setApellidos(rs.getString("apellidos"));
                aux.setFechAlta(rs.getString("fecha_Alta"));
                aux.setProvincia(rs.getString("provincia"));
                
                listaClientes.add(aux);
            }
        } catch (SQLException sqlErr) {
            System.err.println("ERROR SQL! -> " + sqlErr.getMessage());
            return false;
        }
        
        return true;
        
    }
    
    public static List<Cliente> getListaCliente() throws ErrorListadoClientesException {
        if (!llenarListadoBBDD()) {
            throw new ErrorListadoClientesException();
        }
        return listaClientes;
    }
}
