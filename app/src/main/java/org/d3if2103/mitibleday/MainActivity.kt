package org.d3if2103.mitibleday

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.d3if2103.mitibleday.databinding.ActivityMainBinding
import org.d3if2103.mitibleday.model.harga


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHitung.setOnClickListener { hitungBmi() }
        viewModel.getHasil().observe(this ) {showResult(it) }
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

        viewModel.hitungZakat(
            hargaberaszakat.toDouble(),jiwa.toDouble()
        )
    }

    private fun showResult(result:harga?){
        if (result == null) return
        binding.hargaTextView.text = getString(R.string.harga,result)
    }

}
