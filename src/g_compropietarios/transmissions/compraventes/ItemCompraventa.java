/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.transmissions.compraventes;

import g_compropietarios.propietaris.LlistaPropietaris;
import g_compropietarios.transmissions.finques.Finca;
import g_compropietarios.transmissions.finques.LlistaFinques;
import java.util.ListIterator;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class ItemCompraventa extends LlistaFinques {

    private float preu;
    private String modePagament;

    /**
     * La constructora.
     */
    public ItemCompraventa() {
        super();
    }

    /**
     * La constructora.
     */
    public ItemCompraventa(float p_preu, String p_modePagament) {
        super();
        setPreu(p_preu);
        setModePagament(p_modePagament);
    }

    /**
     * La constructora.
     */
    public ItemCompraventa(ItemCompraventa itemCompraventa) {
        super((LlistaFinques) itemCompraventa);
        setPreu(itemCompraventa.Preu());
        setModePagament(itemCompraventa.ModePagament());
    }

    /**
     * La constructora.
     */
    public ItemCompraventa(String str) {
        super();
        int start = 1;
        int end = str.indexOf(';');
        setPreu(Float.parseFloat(str.substring(start, end).trim()));
        start = end + 1;
        end = str.length();
        setModePagament(str.substring(start, end).trim());
    }

    /**
     * Mètode Getter.
     * @return Preu de la compraventa.
     */
    public float Preu() {
        return preu;
    }

    /**
     * Mètode Getter.
     * @return Mode de pagament de la compraventa.
     */
    public String ModePagament() {
        return modePagament;
    }

    /**
     * Mètode Setter. Estableix el preu de la compraventa.
     * @param p_preu Preu de la compraventa.
     */
    public final void setPreu(float p_preu) {
        preu = p_preu;
    }

    /**
     * Mètode Setter. Estableix el mode de pagament de la compraventa.
     * @param p_modePagament Mode de pagament de la compraventa.
     */
    public final void setModePagament(String p_modePagament) {
        modePagament = p_modePagament;
    }

    public String informe(long idAnticPropietari, String strDataTransmissio,
            LlistaPropietaris llistaPropietaris, String strTipus) {
        String resposta = "";
        ListIterator<Finca> it = listIterator();
        while (it.hasNext()) {
            Finca finca = it.next();
            resposta += finca.informe(finca.Id(), idAnticPropietari,
                    strDataTransmissio, llistaPropietaris, strTipus,
                    this.toString());
        }
        return resposta;
    }

    @Override
    public String toString() {
        return "Precio: " + java.text.NumberFormat.getCurrencyInstance().format(preu) + " (" + modePagament + ")";
    }
}
