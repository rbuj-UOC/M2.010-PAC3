/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios;

import g_compropietarios.propietaris.LlistaPropietaris;
import g_compropietarios.propietaris.Propietari;
import g_compropietarios.propietats.LlistaPropietats;
import g_compropietarios.propietats.Propietat;
import g_compropietarios.transmissions.LlistaTransmissions;
import g_compropietarios.transmissions.compraventes.Compraventa;
import g_compropietarios.transmissions.donacions.Donacio;
import g_compropietarios.transmissions.herencies.Herencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class Compropietarios {

    protected static LlistaPropietaris llistaPropietaris;
    protected static LlistaPropietaris llistaNousPropietaris;
    protected static LlistaPropietats llistaPropietats;
    protected static LlistaTransmissions llistaTransmissions;
    protected static int any;

    /**
     * Programa principal
     * @param args arguments de la línia de comandes
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            usage();
            java.lang.System.exit(1);
        }
        try {
            any = Integer.parseInt(args[0]);
            llegirDades();
            verificarDadesParticipacions();
            generarFitxerPropietaris();
            generarLlistatParticipacions();
            generarInformeTransmissions();
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Mostra la correcta utilització de la crida al programa
     */
    private static void usage() {
        System.out.println("USAGE: java g_compropietaris [any]");
    }

    /**
     * Llegeix les dades les fitxers i carrega les dades en memòria.
     * @see LlistaPropietaris
     * @see llistaPropietats
     */
    private static void llegirDades() {
        System.out.println("Procesando año " + String.valueOf(any + 1) + "...");
        llistaPropietaris = new LlistaPropietaris();
        llistaPropietats = new LlistaPropietats();
        llegirFitxerPropietats();
        System.out.println("Registros leídos:");
        System.out.println("propietarios " + String.valueOf(any) + ": " + String.valueOf(llistaPropietaris.size()));
        System.out.println("propiedades : " + String.valueOf(llistaPropietats.size()));
        llistaNousPropietaris = new LlistaPropietaris();
        llistaTransmissions = new LlistaTransmissions();
        llegirFitxerTransmissions();
        System.out.println("propietarios nuevos : " + String.valueOf(llistaNousPropietaris.size()));
        System.out.println("transmisiones : " + String.valueOf(llistaTransmissions.size()));
    }

    /**
     * Llegeix el fitxer de propietats i carrega les dades en memòria.
     * @see LlistaPropietaris
     * @see llistaPropietats
     */
    private static void llegirFitxerPropietats() {
        try {
            FileInputStream fstream = new FileInputStream(String.valueOf(any) + "-propietarios.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLinia;
            boolean estat_propietaris = false;
            boolean estat_propietats = false;

            while ((strLinia = br.readLine()) != null) {
                // ometre línies buides
                if (strLinia.length() > 0) {
                    // ometre els comentaris
                    if (strLinia.charAt(0) != '.') {
                        if (strLinia.charAt(0) == '#') {
                            estat_propietaris = (strLinia.compareTo("#propietarios") == 0);
                            estat_propietats = (strLinia.compareTo("#propiedades") == 0);
                            if ((estat_propietaris == false) && (estat_propietats == false)) {
                                System.out.println("Error en el format del fitxer");
                            }
                        } else {
                            if (estat_propietaris == true) {
                                llistaPropietaris.add(new Propietari(strLinia));
                            } else {
                                if (estat_propietats == true) {
                                    llistaPropietats.add(new Propietat(strLinia));
                                } else {
                                    System.out.println("Error en el format del fitxer");
                                }
                            }
                        }
                    }
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Llegeix el fitxer de transmissions i carrega les dades en memòria.
     * @see LlistaPropietaris
     */
    private static void llegirFitxerTransmissions() {
        try {
            FileInputStream fstream = new FileInputStream(String.valueOf(any) + "-transmisiones.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLinia;
            boolean estat_propietaris = false;
            boolean estat_transmissions = false;
            int idxTransmissio = 0;
            int idxItem = 0;

            while ((strLinia = br.readLine()) != null) {
                // ometre línies buides
                if (strLinia.length() > 0) {
                    // ometre els comentaris
                    if (strLinia.charAt(0) != '.') {
                        if (strLinia.charAt(0) == '#') {
                            estat_propietaris = (strLinia.compareTo("#nuevos propietarios") == 0);
                            estat_transmissions = (strLinia.compareTo("#transmisiones") == 0);
                            if ((estat_propietaris == false) && (estat_transmissions == false)) {
                                System.out.println("Error en el format del fitxer");
                            }
                        } else {
                            if (estat_propietaris == true) {
                                llistaNousPropietaris.add(new Propietari(strLinia));
                            } else {
                                if (estat_transmissions == true) {
                                    switch (strLinia.charAt(0)) {
                                        case '>':
                                            String tipus = strLinia.substring(strLinia.lastIndexOf(';') + 1, strLinia.length()).trim();
                                            if (tipus.compareTo("herencia") == 0) {
                                                idxTransmissio = llistaTransmissions.add(new Herencia(strLinia));
                                            } else {
                                                if (tipus.compareTo("donaciones") == 0) {
                                                    idxTransmissio = llistaTransmissions.add(new Donacio(strLinia));
                                                } else {
                                                    if (tipus.compareTo("compraventa") == 0) {
                                                        idxTransmissio = llistaTransmissions.add(new Compraventa(strLinia));
                                                    } else {
                                                        System.out.println("Error: Format de l'arxiu de transmissions incorrecte");
                                                    }
                                                }
                                            }
                                            break;
                                        case '+':
                                            idxItem = llistaTransmissions.addItem(idxTransmissio, strLinia);
                                            break;
                                        default:
                                            llistaTransmissions.addItem(idxTransmissio, idxItem, strLinia);
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Comprova que la suma de les participacions sigui igual a 1.
     */
    private static void verificarDadesParticipacions() {
        llistaPropietats.verifica();
    }

    /**
     * Genera el fitxer de propietaris de l'any en curs (any)-transmissiones.txt
     */
    private static void generarFitxerPropietaris() {
        // Fusionar les llistes de propietaris
        llistaPropietaris.append(llistaNousPropietaris);
        // Salvaguarda le la llista amb tots els propietaris
        llistaNousPropietaris.assign(llistaPropietaris);
        // Aplicar les transmissions a les propietats 
        llistaPropietats.apply(llistaTransmissions);
        // Actualitzar la llista de propietaris eliminant els que no tenen cap
        // participació en cap propietat
        llistaPropietaris.update(llistaPropietats);
        // Escriure el fitxer
        try {
            FileWriter fstream = new FileWriter(String.valueOf(any + 1) + "-propietarios.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("#propietarios\n");
            out.write(llistaPropietaris.toString());
            out.write("\n#propiedades\n");
            out.write(llistaPropietats.toString());
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Compropietarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Genera el llistat de propietaris amb la seva participació global en la
     * comunitat (any)-participaciones.txt
     */
    private static void generarLlistatParticipacions() {
        llistaPropietaris.llistatParticipacions(llistaPropietats, any);
    }

    /**
     * Genera l'informe de transmissions ordenades per finca i data al fitxer
     * (any)-informe.txt
     */
    private static void generarInformeTransmissions() {
        llistaTransmissions.informe(llistaNousPropietaris, any);
    }
}
