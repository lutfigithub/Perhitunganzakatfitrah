package org.d3if2103.mitibleday.ui.histori

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if2103.mitibleday.R
import org.d3if2103.mitibleday.databinding.ItemHistoriBinding
import org.d3if2103.mitibleday.db.ZakatEntity
import org.d3if2103.mitibleday.model.hitungZakat
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter(private val viewModel: HistoriViewModel,private val context: Context):
    ListAdapter<ZakatEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<ZakatEntity>() {
                override fun areItemsTheSame(
                    oldData: ZakatEntity, newData: ZakatEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: ZakatEntity, newData: ZakatEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding,viewModel,context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class ViewHolder(
        private val binding: ItemHistoriBinding,
        private val viewModel: HistoriViewModel,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))



        fun bind(item: ZakatEntity) = with(binding) {
            val hasilZakat = item.hitungZakat()

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            dataTextView.text = root.context.getString(R.string.berapa_jiwa, item.jiwa.toString())
            hargaPerKilo.text = root.context.getString(R.string.harga_per_kilo, item.hargaBeras.toString())
            hasilTextView.text = root.context.getString(R.string.hasil_zakat_kamu, hasilZakat.harga.toString())

            hapusButton.setOnClickListener {
                MaterialAlertDialogBuilder(context)
                    .setMessage(R.string.konfirmasi_hapus_histori)
                    .setPositiveButton(R.string.hapus) { _, _ ->
                        viewModel.hapusDataSatuPerSatu(item)
                    }
                    .setNegativeButton(R.string.batal) { dialog, _ ->
                        dialog.cancel()
                    }
                    .show()
            }

        }
    }
}
