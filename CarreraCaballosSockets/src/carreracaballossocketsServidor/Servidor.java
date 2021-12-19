package carreracaballossocketsServidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 *
 * @author ewelina
 */
public class Servidor {

    public static void main(String[] args) {
        final int puerto = 3030;

        try {

            ServerSocket socket = new ServerSocket(puerto);

            System.out.println("Servidor listo...");
            while (true) {
                Socket socketCliente = socket.accept();
                atender(socketCliente);
                socketCliente.close();
            }
        } catch (IOException ex) {

        }
    }

    private static void atender(Socket socketCliente) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter pw = new PrintWriter(socketCliente.getOutputStream());

            int length;
            long tiempo = 0;

            length = br.read();
            /*Creamos array de caballos*/
            Caballo[] caballos = new Caballo[length];
            /*Recorremos la longitud del array y le asignamos los caballos*/
            for (int i = 0;
                    i < length;
                    i++) {
                Caballo c = new Caballo("Caballo " + i, tiempo);
                caballos[i] = c;
            }
            Thread[] threads = new Thread[length];
            for (int i = 0;
                    i < length;
                    i++) {
                Thread thread = new Thread(caballos[i]);
                threads[i] = thread;

                thread.start();

            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Arrays.sort(caballos);
            for (Caballo c : caballos) {
                pw.print(c);

            }
        } catch (IOException ex) {

        }

    }
}

