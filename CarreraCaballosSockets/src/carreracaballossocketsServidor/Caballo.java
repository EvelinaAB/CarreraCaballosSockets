package carreracaballossocketsServidor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Caballo implements Runnable, Comparable<Caballo> {

    Reloj reloj = new Reloj();
    private String nombre;
    private int contador;
    private long tiempoRecorrido;

    public Caballo(String nombre, long tiempoR) {
        this.nombre = nombre;
        this.tiempoRecorrido = tiempoR;
    }

    @Override

    public void run() {

        Correr();

    }

    private synchronized void Correr() {
        reloj.start();
        while (contador < 100) {

            try {
                Thread.sleep((int) (Math.random() * 41 + 10));
            } catch (InterruptedException ex) {
                Logger.getLogger(Caballo.class.getName()).log(Level.SEVERE, null, ex);
            }

            contador++;

        }
        reloj.stop();
        this.tiempoRecorrido = reloj.tiempoTranscurrido();

        System.out.println("Soy " + this.nombre + "  he terminado y he tardado  " + this.tiempoRecorrido + " ms");

    }

    @Override
    public String toString() {

        return nombre + " " + tiempoRecorrido + " ms";
    }

    public String getNombre() {
        return nombre;
    }

    public long getTiempoRecorrido() {
        return tiempoRecorrido;
    }

    @Override
    public int compareTo(Caballo o) {
        if (this.tiempoRecorrido > o.tiempoRecorrido) {
            return 1;
        }
        if (this.tiempoRecorrido < o.tiempoRecorrido) {
            return -1;

        }
        return 0;
    }

}
