package com.edugrade.battleship;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() {
    }
    public void startServer() throws IOException {
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

                while (true){
                    //här tar server emot  meddelande från client
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println("Client sent message: " + msgFromClient);
                    //här svarar serven till client
                    //jag tror det är här som ska implimenteras den classen eller methoden för logiken
                    bufferedWriter.write("Well received ! skicka din shoot");
                    bufferedWriter.newLine();
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

            }

        }



    }
}

