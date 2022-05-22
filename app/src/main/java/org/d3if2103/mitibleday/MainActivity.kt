package org.d3if2103.mitibleday

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.d3if2103.mitibleday.databinding.ActivityMainBinding
import org.d3if2103.mitibleday.model.harga


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHitung.setOnClickListener { hitungBmi() }
        binding.btnReset.setOnClickListener { resetHarga() }


    }
    private fun resetHarga(){
        binding.hargaTextView.text = ""
        binding.hargaBeras.setText("")
        binding.totalJiwa.setText("")

    }


    private fun hitungBmi() {
        val hargaberaszakat = binding.hargaBeras.text.toString()
        if (TextUtils.isEmpty(hargaberaszakat)) {
            Toast.makeText(this, R.string.harga_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val jiwa = binding.totalJiwa.text.toString()
        if (TextUtils.isEmpty(jiwa)) {
            Toast.makeText(this, R.string.jiwa_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val result = hitungZakat(
            hargaberaszakat.toDouble(),jiwa.toDouble()
        )
        showResult(result)

    }

    private fun hitungZakat(hargaBeras:Double,jiwa:Double):harga{
        val hasil = hargaBeras*2.5*jiwa
        return harga(hasil)
    }
    private fun showResult(result: harga){
        binding.hargaTextView.text = getString(R.string.harga,result)
    }

}
