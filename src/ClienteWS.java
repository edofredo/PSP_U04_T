/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.text.Normalizer;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import tarea6.*;

/**
 *
 * @author Cristian
 */
public class ClienteWS {

    private String entradaUsuario = "";
    private Scanner sc = new Scanner(System.in, "ISO-8859-1");
    private List<Zona> zonas = null;
    private List<Pais> paises = null;
    private List<Moneda> monedas = null;

    private static WebServiceT6_Service ws = null;
    private static WebServiceT6 s = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ws = new WebServiceT6_Service();
        s = ws.getWebServiceT6Port();
        ClienteWS cws = new ClienteWS();
        try {
            cws.zonas();
            cws.paises();
            cws.monedas();
            cws.buclePrograma();

            //cws.exitoConexion();    
            //cws.imprimeZonas();
            //cws.imprimePaises();
            //cws.imprimeMonedas();
            //cws.imprimeInfoPais("Argentina");
            //cws.imprimePaisesZonas();
            //cws.imprimePaisesMonedas();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void buclePrograma() {
        String opciones = "Introduzca una de las siguientes opciones: \n"
                + "1: Imprimir todas las zonas.\n"
                + "2: Imprimir todos los paises.\n"
                + "3: Imprimir todas las monedas.\n"
                + "4: Imprimir listado de países por zona.\n"
                + "5: Imprimir listado de paises por moneda.\n"
                + "6: Imprimir información sobre un país.\n"
                + "0: Salir del programa";
        int entradaUsuario = -1;
        while (entradaUsuario != 0) {
            System.out.println(opciones);
            entradaUsuario = Integer.parseInt(sc.nextLine());

            switch (entradaUsuario) {
                case 1: {
                    imprimeZonas();
                    break;
                }
                case 2: {
                    imprimePaises();
                    break;
                }
                default: {

                }

            }
        }
    }

    private void zonas() {
        this.zonas = s.getZonas();
    }

    private void paises() {
        this.paises = s.getPaises();
    }

    private void monedas() {
        this.monedas = s.getMonedas();
    }

    private void imprimeZonas() {
        for (Zona i : zonas) {
            System.out.println(i.getNombre());
        }
    }

    private void imprimePaises() {
        for (Pais i : paises) {
            System.out.println(i.getNombre());
        }
    }

    private void imprimeMonedas() {
        for (Moneda i : monedas) {
            System.out.println(i.getNombre());
        }
    }

    private void imprimeInfoPais(String nom) {
        String nombre = nom;
        for (Pais i : paises) {
            if (i.getNombre().equalsIgnoreCase(nombre)) {
                String monedaPais = i.getCodigoDivisa();
                for (Moneda m : s.getMonedas()) {
                    if (i.getCodigoDivisa().equalsIgnoreCase(m.getCodigo())) {
                        monedaPais = m.getNombre();
                        break;
                    }
                }
                String areaPais = s.getZonaById(i.getIdArea()).getNombre();

                System.out.println("Nombre: " + i.getNombre() + "\n"
                        + "Area: " + areaPais + "\n"
                        + "Moneda: " + monedaPais + "\n"
                        + "Código de bandera: " + i.getCodigoBandera() + "\n"
                        + "Código NIC: " + i.getCodigoNic());
            }
        }
    }

    private void imprimePaisesZonas() {
        entradaUsuario = sc.nextLine();
        for (Zona i : zonas) {
            if (i.getNombre().equalsIgnoreCase(entradaUsuario)) {
                paises = s.getPaisesZona(i.getId());
                for (Pais p : paises) {
                    System.out.println(p.getNombre());
                }
            }

        }
    }

    private void imprimePaisesMonedas() {

        entradaUsuario = sc.nextLine();
        for (Moneda i : monedas) {
            if (entradaUsuario.equalsIgnoreCase(i.getNombre())) {
                paises = s.getPaisesMoneda(i.getCodigo());
                for (Pais p : paises) {
                    System.out.println(p.getNombre());
                }
            }

        }
    }

    private void exitoConexion() {
        System.out.println("********************************************************\n"
                + "\n"
                + "* PSP - Tarea Individual 4 - Web Services      *\n"
                + "\n"
                + "********************************************************\n"
                + "\n"
                + "* Cristian Ade Medina                   *\n"
                + "\n"
                + "********************************************************\n"
                + "\n"
                + "* 54212045X                                           *\n"
                + "\n"
                + "******************************************************** ");
    }

}
