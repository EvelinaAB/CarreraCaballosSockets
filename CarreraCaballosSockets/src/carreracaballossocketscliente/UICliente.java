package carreracaballossocketscliente;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author ewelina
 */
public class UICliente {

    public static void main(String[] args) throws InterruptedException {

        final int puertoServidor = 3030;
        final String servidor = "localhost";

        /*Creamos ventana*/
        JFrame ventana = new JFrame("Ejemplo de manejo de eventos");
        ventana.setLayout(new FlowLayout());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*Creamos etiqueta+botón*/
        final JLabel etiqueta = new JLabel("¡¡Empezar carrera!!");
        JButton botón = new JButton("¡Pulsa INTRO!");
        ventana.add(etiqueta);
        ventana.add(botón);

        /*Propiedades de la ventana*/
        ventana.setSize(
                300, 100);
        ventana.setVisible(
                true);
        ventana.setAlwaysOnTop(true);
        ventana.setLocationRelativeTo(null);
        /*Agregamos un KeyListener al botón para capturar la tecla intro*/
        botón.addKeyListener(
                new KeyListener() {
            @Override
            public void keyTyped(KeyEvent d
            ) {

            }

            @Override
            public void keyPressed(KeyEvent d
            ) {
                if (d.getKeyCode() == KeyEvent.VK_ENTER) {

                    ventana.setVisible(false);
                    /*Creamos la misma cantidad de hilos que de caballos*/
                    try {
                        Socket socket = new Socket(servidor, puertoServidor);
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter pw = new PrintWriter(socket.getOutputStream());
                        int entradaTeclado = 0;
                        Scanner entradaEscaner = new Scanner(System.in); //Creación de un objeto Scanner
                        System.out.println("Introduzca el número de caballos:");
                        entradaTeclado = entradaEscaner.nextInt();
                        //envío la entrada al servidor
                        pw.print(entradaTeclado);
                        pw.flush();
                        
                        String[] c=new String[entradaTeclado];
                        for(int i=0;i<c.length;i++){
                            c[i]=br.readLine();
                        }
                        System.out.println(c);
                        
                        
                        //recibe los datos de los caballos
                        System.out.println(br.readLine());
                        
                        

                    } catch (IOException ex) {

                    }

                    System.out.println("\n");
                    System.out.println("Lista de calificaciones y tiempos:");

                    System.exit(0);

                }
            }

            @Override
            public void keyReleased(KeyEvent e
            ) {

            }

        }
        );

    }
}
