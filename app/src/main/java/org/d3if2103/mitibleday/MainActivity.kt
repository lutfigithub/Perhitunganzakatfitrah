package org.d3if2103.mitibleday

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.d3if2103.mitibleday.databinding.ActivityMainBinding


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
        val hasil = hargaberaszakat.toDouble()*2.5*jiwa.toDouble()
        val hargaTotal = hasil.toInt().toString()
        binding.hargaTextView.text = getString(R.string.harga,hargaTotal)

    }

}
