/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.socketserver1;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrés
 */
public class ServidorHilos extends Thread {

    private static Integer id = 0;
    private final PrintWriter output;
    private final BufferedReader input;
    private final Socket socket;
    private ObjectOutputStream salida;
    private String mensaje;
    private Socket conexion;
    private ObjectInputStream entrada;
    private Socket cliente;

    private void enviar(String mensaje) {
        try {
            salida.writeObject("Servidor>>> " + mensaje);
            salida.flush(); //flush salida a cliente

        } //Fin try
        catch (IOException ioException) {
        } //Fin catch  

    }

    public ServidorHilos(Socket socket) throws IOException {
        if (ServidorHilos.id != null) {
            ServidorHilos.id = ServidorHilos.id++;
        }
        this.socket = socket;
        salida = new ObjectOutputStream(socket.getOutputStream());

        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }
    //enviar objeto a cliente 

    @Override
    public void run() {
        try {
            entrada = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {

        }
        do { //procesa los mensajes enviados dsd el servidor
            try {//leer el mensaje y mostrarlo 
                mensaje = (String) entrada.readObject(); //leer nuevo mensaje
                System.out.println(mensaje + "sms");
                //salida.flush(); 
                this.enviar("lo logre");
            } //fin try
            catch (SocketException ex) {
            } catch (EOFException eofException) {
                System.out.println("Fin de la conexion");
                break;
            } //fin catch
            catch (IOException ex) {
            } catch (ClassNotFoundException classNotFoundException) {
                System.out.println("Objeto desconocido");
            } //fin catch               

        } while (!mensaje.equals("Servidor>>> TERMINATE")); //Ejecuta hasta que el server escriba TERMINATE

        try {
            entrada.close(); //cierra input Stream
            cliente.close(); //cieraa Socket
        } //Fin try
        catch (IOException ioException) {
            ioException.printStackTrace();
        } //fin catch

        System.out.println("Fin de la conexion");
        System.exit(0);
    }
    /*        public void run() {
     try {
     salida = new ObjectOutputStream(conexion.getOutputStream());
     salida.flush(); 
     } catch (SocketException | NullPointerException ex) {
     } catch (IOException ioException) {
     }
     }*/

}
