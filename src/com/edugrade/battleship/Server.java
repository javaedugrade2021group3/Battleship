package com.edugrade.battleship;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static  void  main(String[] args) throws IOException {
        Socket socket= null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(3030);

        while (true){
            try {
                socket= serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                if(socket!=null){
                    bufferedWriter.write(showOptions("Welcome to group 3 server,"));
                    //här ska man visa game instructions ,start options
                }
                while (true){
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println("Client sent message: " + msgFromClient);
                    //här ska man börja kolla va client skriver och skicka svar
                    bufferedWriter.write("Well received");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    if(msgFromClient.equalsIgnoreCase("quit")){
                        bufferedWriter.write("Bye see u next time");
                        break;
                    }
                }
                socket.close();
                bufferedReader.close();
                bufferedWriter.close();
                inputStreamReader.close();
                outputStreamWriter.close();
            } catch (IOException e){
             e.printStackTrace();
            }

        }


    }
    public static String showOptions(String message){
        return message;
    }
}

