/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Exceptions.ClienteYaExisteException;
import Exceptions.ErrorInsercionException;
import Exceptions.ErrorListadoClientesException;
import clienteapi.ClientesAPIUtils;
import gestionPeliculas.dto.Cliente;
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
        
        //Agregar tanto a la BBDD, como al List<Cliente>, si se ha conseguido agregar por petición POST
        String resultado = ClientesAPIUtils.sendPostRequest("", cliente.toJSONString());
        
        if (!CFG_APP.EN_PRODUCCION) {
            System.out.println(resultado);
        }
        
        boolean hecho = !resultado.toUpperCase().startsWith("FALL");
        if (!hecho) {
            return;
        }
        listaClientes.add(cliente);
    }
    
    public static boolean eliminarCliente(Cliente cli) {
        String resultado = ClientesAPIUtils.sendDeleteRequest(cli.getDNI());
        if (!CFG_APP.EN_PRODUCCION) {
            System.out.println(resultado);
        }
        
        if (resultado.toUpperCase().startsWith("FALL")) {
            return false;
        }
        
        if (listaClientes.contains(cli)) {
            return listaClientes.remove(cli);
        }
        return false;
    }
    
    public static Cliente eliminarClienteByNIF(String DNI) {
        Cliente aux = null;
        boolean hecho = false;
        
        String resultado = ClientesAPIUtils.sendDeleteRequest(DNI);
        if (!CFG_APP.EN_PRODUCCION) {
            System.out.println(resultado);
        }
        if (resultado.toUpperCase().startsWith("FALL")) {
            return null;
        }
        
        for (Cliente c: listaClientes) {
            if (c.getDNI().equals(DNI)) {
                aux = c;
                hecho = true;
            }
        }
        if (hecho) {
            listaClientes.remove(aux);
            return aux;
        }
        return null;
    }
    
    public static boolean editarCliente(Cliente clienteCambiado) throws ErrorInsercionException {
        Cliente edit = ControladorClientes.getClienteByNIF(clienteCambiado.getDNI());
        
        boolean hecho = false;
        //Comprobar que el Cliente no es nulo
        if (edit == null) {
            throw new ErrorInsercionException("ERROR AL ACTUALIZAR EL CLIENTE!");
        }
        
        //Editar sólo si los campos del cliente a Editar son distintos actualmente
        if (!clienteCambiado.toString().equals(edit.toString())) {
            String resultado = ClientesAPIUtils.sendPutRequest(clienteCambiado.getDNI(), clienteCambiado.toJSONString());
            if (!CFG_APP.EN_PRODUCCION) {
                System.out.println(resultado);
            }
            
            hecho = !resultado.toUpperCase().startsWith("FALL");
            if (hecho) {
                listaClientes.remove(edit);
                listaClientes.add(clienteCambiado);
            }
        } else {
            return false;
        }
        
        return hecho;
    }
    
    /**
     * Actualizará el ArrayList de Cliente's actual a partir de los datos introducidos en la tabla de Clientes
     * Así, se mantendrá bien reflejado siempre el orden en ambos listados
     * @param modeloT El DefaultTableModel (swing) proveniente de la tabla en GUI.
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
     * Evaluará si la lista de Clientes de este controlador contiene algún cliente con el NIF pasado por parámetro.
     * Realiza la comprobación directamente a la API.
     * @param NIFusuario El DNI/NIF del Usuario a encontrar, en STRING.
     * @return TRUE si existe una entrada con el NIF especificado, FALSE si no.
     */
    public static boolean existeCliente(String NIFusuario) {
        String resultado = ClientesAPIUtils.sendGetRequest(NIFusuario);
        
        if (resultado.toUpperCase().startsWith("FALL")) {
            return false;
        }
        
        return true;
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
        String resultado = ClientesAPIUtils.sendGetRequest("");
        if (!CFG_APP.EN_PRODUCCION) {
            System.out.println(resultado);
        }
        
        if (resultado.toUpperCase().startsWith("FALL")) {
            return false;
        }
        
        //Limpiamos el ArrayList de Cliente's
        listaClientes = new ArrayList<Cliente>();
        
        //Volcamos los valores de la API al ArrayList recién creado
        listaClientes = Cliente.getArrayClientesFromJSON(resultado);
        
        if (listaClientes == null) {
            // En caso de no conseguir llenar el listado, usar uno vacío
            listaClientes = new ArrayList<Cliente>();
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
