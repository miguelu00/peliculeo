/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Exceptions.NoHayClientesException;
import gestionPeliculas.dto.Cliente;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Miguelañez-PC
 * 
 * ESTA CLASE ACTÚA COMO CONTROLADOR, PERO MEDIANTE FICHEROS .DAT
 */
public class LogicaAppClientes {
    
    //Fichero que actuará como BBDD externa
    private final static String FICHEROBBDD = "bd/CLIENTES.dat";
    private static File ficheroDB = new File(FICHEROBBDD);
    private final static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

    
    public static boolean agregarCliente(Cliente nuevoC) {
        
        //Lista de clientes, recogida del fichero BBDD
        ArrayList<Cliente> listaClientesBBDD = LogicaAppClientes.leerClientes();
        File ficheroBD = new File(FICHEROBBDD);
        long espacioAnteriorFichero = ficheroBD.getTotalSpace();
        FileOutputStream fos = null;
        ObjectOutputStream oostream = null;
        
        try {
            if (!ficheroBD.exists()) {
                ficheroBD.createNewFile();
            }
            //Escritura en el fichero que actuará como BBDD
            fos = new FileOutputStream(ficheroBD);
            oostream = new ObjectOutputStream(fos);
            
            if (!listaClientes.contains(nuevoC)) {
                listaClientes.add(nuevoC);
            }
        } catch (FileNotFoundException notFoundErr) {
            System.err.println("ERROR Fichero no encontrado! Se falló al crear el fichero");
        } catch (IOException ioe) {
            System.err.println("ERROR Al escribir en el fichero");
        } finally {
            try {
                oostream.close();
                fos.close();
            } catch (IOException ioExc) {
                System.err.println("Fallo al CERRAR STREAMS (ObjectOutput)");
            }
        }
            return (ficheroBD.getTotalSpace() > espacioAnteriorFichero);
    }
    
    public static boolean borrarCliente(Cliente oldCliente) throws NoHayClientesException {
        //Aonseguir todos los clientes
        ArrayList<Cliente> clientesEnBBDD = LogicaAppClientes.leerClientes();
        
        //Comprobar si hemos leido bien
        if (clientesEnBBDD.isEmpty()) {
            throw new NoHayClientesException();
        }
        //Si existe en la lista (y por consiguiente, en la BBDD) borrar el Usuario
        if (clientesEnBBDD.contains(oldCliente)) {
            clientesEnBBDD.remove(oldCliente);
        }
        
        return true;
    }
    
    public static ArrayList<Cliente> leerClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        ResultSet rs = ControladorBBDD_mysql.hacerSELECT("clientes", "*");
        return clientes;
    }
    
    public static String[][] leerClientesToArray() {
        File ficheroBD = new File(FICHEROBBDD);
        FileInputStream fis = null;
        ObjectInputStream oistream = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try {
            if (!ficheroBD.exists()) {
                ficheroBD.createNewFile();
            }
            //Lectura en el fichero que actuará como BBDD
            fis = new FileInputStream(ficheroBD);
            oistream = new ObjectInputStream(fis);
            
            while (true) {
                clientes.add((Cliente) oistream.readObject()); //Como Cliente es SERIALIZABLE, se puede agregar/transformar como objeto
            }
        } catch (FileNotFoundException notFoundErr) {
            System.err.println("ERROR Fichero no encontrado! Se falló al crear el fichero");
        } catch (EOFException finFichero) {
            //Se llega al fin del fichero, y sale por aquí
        } catch (IOException ioe) {
            System.err.println("ERROR Al escribir en el fichero");
        } catch (ClassNotFoundException notFoundClaseErr) {
            System.err.println("ERROR NO SE HA IMPORTADO LA CLASE COMPARADA");
        } finally {
            try {
                oistream.close();
                fis.close();
            } catch (IOException ioExc) {
                System.err.println("Fallo al CERRAR STREAMS (ObjectOutput)");
            }
        }
        
        String[][] arrClientes = new String[5][5];
        String[] cabecera = {"DNI", "Nombre", "Apellidos", "Fecha de alta", "Provincia"};
        int i = 0;
        for (Cliente c: clientes) {
            String[] client = c.toArrayString();
            for (int j=0;j<5;j++) {
                arrClientes[i][j] = client[j];
            }
            i++;
        }
        return arrClientes;
    }
    
    public static boolean actualizarFicheroBBDD() {
        boolean todoBien = true;
        
        return todoBien;
    }
}
