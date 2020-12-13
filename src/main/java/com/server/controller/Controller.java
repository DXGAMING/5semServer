package com.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    public static void main(String[] args) throws IOException
    {
        final Logger logger = LoggerFactory.getLogger(Controller.class);
        ExecutorService executeIt = Executors.newCachedThreadPool();
        int i = 0;
        try (ServerSocket server = new ServerSocket(Constant.PORT);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            logger.info("Server socket created, port " + Constant.PORT +
                    ", command console reader for listen to server commands");
            while (!server.isClosed()) {
                if (br.ready()) {
                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        logger.info("Main Server initiate exiting...");
                        server.close();
                        break;
                    }
                }
                try {
                    Socket client = server.accept();
                    i++;
                    executeIt.execute(new MonoThreadClientHandler(client, i));
                    logger.info("Connection accepted.\n");
                } catch (IOException e) {
                    logger.error("error while creating connection");
                }
            }
            executeIt.shutdown();
        } catch (IOException e) {
            logger.error("error while working server");
        }
    }
}
