package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class);

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                try (Socket socket = server.accept()) {
                    OutputStream out = socket.getOutputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String str = in.readLine();
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String paramValue = str.split(" ")[1]
                            .replace("/?msg=", "");
                    if ("Hello".equals(paramValue)) {
                        out.write("Hello".getBytes());
                    } else if ("Exit".equals(paramValue)) {
                        out.write("Bye".getBytes());
                        server.close();
                    } else {
                        out.write("What".getBytes());
                    }
                    while (str != null && !str.isEmpty()) {
                        System.out.println(str);
                        str = in.readLine();
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Ошибка при работе ServerSocket", e);
        }
    }
}
