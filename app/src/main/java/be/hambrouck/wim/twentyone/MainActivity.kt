package be.hambrouck.wim.twentyone

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val spel = Spel()

    private var spelerBeurt = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateFields()

        btn_hit_me.setOnClickListener {
            spel.geefKaartAanSpeler()
            updateTotalen()
            if(spel.totaalSpeler == 21)
            {
                txt_kaarten_speler.text = getString(R.string.gewonnen)
            } else if (spel.totaalSpeler > 21)
            {
                txt_kaarten_speler.text = getString(R.string.verloren)
                btn_hit_me.isEnabled = false
            } else {
                updateKaartenrij()
            }
        }
    }

    private fun updateFields()
    {
        updateTotalen()
        updateKaartenrij()
    }

    private fun updateKaartenrij() {
        var i = 0
        for (kaart in spel.kaartenPc) {
            if (spelerBeurt && i == 0) {
                txt_kaarten_pc.text = "(X)"
                i++
            } else {
                txt_kaarten_pc.text = getString(R.string.kaartenrij, txt_kaarten_pc.text, kaart.naam)
            }
        }

        i = 0
        for (kaart in spel.kaartenSpeler) {
            if (i == 0) {
                txt_kaarten_speler.text = kaart.naam
                i++
            } else {
                txt_kaarten_speler.text = getString(R.string.kaartenrij, txt_kaarten_speler.text, kaart.naam)
            }
        }
    }

    private fun updateTotalen() {
        txt_totaal_speler.text = getString(R.string.totaal, spel.totaalSpeler)

        if (spelerBeurt) {
            txt_totaal_pc.text = ""

        } else {
            txt_totaal_pc.text = getString(R.string.totaal, spel.totaalPc)
        }
    }


}
