package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenNegatiivisellaLuvullaEiToimi() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-2);

        assertEquals(0.0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenEnemmanKuinOnVarastossaVieNollaan() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(10);

        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenEnemmanKuinOnVarastossa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(100);

        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uusiVarastoPositiivisellaAlkusaldolla() {
        Varasto uusiVarasto = new Varasto(10, 4);

        assertEquals(6, uusiVarasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void uusiVarastoNegatiivisellaAlkusaldolla() {
        Varasto uusiVarasto = new Varasto(10, -4);

        assertEquals(10, uusiVarasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void uusiVarastoNegatiivisellaTilavuudella() {
        Varasto uusiVarasto = new Varasto(-10);

        assertEquals(0, uusiVarasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void uusiVarastoNegatiivisellaTilavuudellaJaAlkusaldolla() {
        Varasto uusiVarasto = new Varasto(-10, -1);

        assertEquals(0, uusiVarasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void uusiVarastoLiianSuurellaAlkusaldolla() {
        Varasto uusiVarasto = new Varasto(10, 12);

        assertEquals(10, uusiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisataanEnemmanKuinMahtuu() {
        varasto.lisaaVarastoon(18);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisataanNegatiivinenMaara() {
        varasto.lisaaVarastoon(-2);

        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testataanToStringMetodia() {
        varasto.lisaaVarastoon(8);
        String vastaus = "saldo = " + 8.0 + ", vielä tilaa " + 2.0;
        
        assertEquals(vastaus, varasto.toString());
    }
}
