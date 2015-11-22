/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.socketserver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Andrés
 */
public class ServerMulti {
    public static void main(String args[]){
        System.out.println("Servidor de Sockets");
        try{
            
        ServerSocket server=new ServerSocket(4420);
     while(true){
        Socket client=server.accept();
        new ServidorHilos(client).start();
        System.out.println("Se ha recibido una conexion");
      
     }
            
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
