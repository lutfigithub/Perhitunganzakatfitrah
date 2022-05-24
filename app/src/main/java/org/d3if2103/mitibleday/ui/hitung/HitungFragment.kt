package org.d3if2103.mitibleday.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if2103.mitibleday.R
import org.d3if2103.mitibleday.databinding.FragmentHitungBinding
import org.d3if2103.mitibleday.db.ZakatDb
import org.d3if2103.mitibleday.model.harga
import org.d3if2103.mitibleday.ui.HitungViewModel


class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.tentang_aplikasi_zakat, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_hitungFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private val viewModel: HitungViewModel by lazy {
        val db = ZakatDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }
    private fun shareData(){
        val message = getString(R.string.Bagikan_template,binding.hargaTextView.text)

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) !=null) {
            startActivity(shareIntent)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {


        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        binding.btnHitung.setOnClickListener { hitungBmi() }
        binding.bagikanButton.setOnClickListener { shareData() }
        binding.tuntunanButton.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_hitungFragment_to_tuntunanFragment
            )
        }
        viewModel.getHasil().observe(requireActivity() ) {showResult(it) }
        viewModel.data.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            Log.d("HitungFragment", "Data tersimpan. ID = ${it.id}")
        }
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

    private fun showResult(result: harga?){
        if (result == null) return
        binding.hargaTextView.text = getString(R.string.harga,result.harga)
        binding.bagikanButton.visibility = View.VISIBLE
    }

}
