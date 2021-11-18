package com.edugrade.battleship;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public Client() {
    }

    public void startClient(){
        Socket socket= null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;


        try {
            socket = new Socket("localhost",3030);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            System.out.println("Write some thing to Start conversation with server! :");
            Scanner scanner = new Scanner(System.in);

            while (true){
                String msgTosend = scanner.nextLine();
                bufferedWriter.write(msgTosend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                String textServerWelcome = null;
                System.out.println("Grupp 3 server : "+ bufferedReader.readLine() );
                if(msgTosend.equalsIgnoreCase("quit")){
                    break;
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }finally {
           try {
               if(socket!=null){
                   socket.close();
               }
               if(bufferedReader!=null){
                   bufferedReader.close();
               }
               if(bufferedWriter!=null){
                   bufferedWriter.close();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
        }
    }

}
