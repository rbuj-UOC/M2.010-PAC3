/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.estadistiques;

import g_compropietarios.fraccio.Fraccio;
import g_compropietarios.percentatges.Percentatge;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class Estadistica {

    private Fraccio fraccioParticipacio;
    private Percentatge percentatgeParticipacio;
    private long numParticipacions;

    public Estadistica() {
        fraccioParticipacio = new Fraccio();
        percentatgeParticipacio = new Percentatge();
    }

    public Estadistica(Fraccio p_fraccio, Percentatge p_percentatge, long p_num) {
        fraccioParticipacio = new Fraccio(p_fraccio);
        percentatgeParticipacio = new Percentatge(p_percentatge);
        numParticipacions = p_num;
    }

    public Estadistica(Estadistica e) {
        fraccioParticipacio = new Fraccio(e.FraccioParticipacio());
        percentatgeParticipacio = new Percentatge(e.PercentatgeParticipacio());
        numParticipacions = e.NumParticipacions();
    }

    public Fraccio FraccioParticipacio(){
        return fraccioParticipacio;
    }

    public Percentatge PercentatgeParticipacio(){
        return percentatgeParticipacio;
    }
    
    public long NumParticipacions(){
        return numParticipacions;
    }
    
    public void add(Estadistica e){
        fraccioParticipacio.add(e.FraccioParticipacio());
        percentatgeParticipacio.add(e.PercentatgeParticipacio());
        numParticipacions = numParticipacions + e.NumParticipacions();
    }

    @Override
    public String toString() {
        return String.format("%-3s", String.valueOf(numParticipacions)) + " " + 
                String.format("%-7s", fraccioParticipacio.toString()) + " " +
                String.format("%7s", percentatgeParticipacio.toString());
    }
}
