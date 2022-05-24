package org.d3if2103.mitibleday.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if2103.mitibleday.R
import org.d3if2103.mitibleday.databinding.FragmentHitungBinding
import org.d3if2103.mitibleday.model.harga


class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {


        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        binding.btnHitung.setOnClickListener { hitungBmi() }
        viewModel.getHasil().observe(requireActivity() ) {showResult(it) }
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
            Toast.makeText(context, R.string.harga_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val jiwa = binding.totalJiwa.text.toString()
        if (TextUtils.isEmpty(jiwa)) {
            Toast.makeText(context, R.string.jiwa_invalid, Toast.LENGTH_LONG).show()
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
