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
public class Server {
    public static void main(String args[]){
        System.out.println("Servidor de Sockets");
        try{
            
        ServerSocket server=new ServerSocket(4420);
        Socket client=server.accept();
        System.out.println("Se ha recibido una conexion");
            BufferedReader input=new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter output=new PrintWriter(client.getOutputStream(),true);
            String userInput;
            while((userInput=input.readLine())!=null)
            {
                System.out.println("Mensaje Recibido: "+userInput);
                StringBuilder sb=new StringBuilder(userInput);
                output.write(sb.reverse().toString());
                output.write('\n');
                output.flush();
                if("FIN".equalsIgnoreCase(userInput)){
                    break;
                }
            }
            System.out.println("Conecion Finalizada");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
            
}
