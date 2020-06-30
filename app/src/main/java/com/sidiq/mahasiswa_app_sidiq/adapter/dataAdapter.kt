package com.sidiq.mahasiswa_app_sidiq.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sidiq.mahasiswa_app_sidiq.R
import com.sidiq.mahasiswa_app_sidiq.model.DataItem
import kotlinx.android.synthetic.main.list_item.view.*

class dataAdapter(val data: List<com.sidiq.mahasiswa_app_sidiq.model.DataItem?>?,val itemClick: OnClickListener) : RecyclerView.Adapter<dataAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dataAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nama = view.textNama
        val nohp = view.textNobp
        val alamat = view.textAlamat
        val btnHapus = view.btnHapus
    }

    override fun getItemCount(): Int = data?.size ?: 0


    override fun onBindViewHolder(holder: dataAdapter.ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.nama.text = item?.mahasiswaNama
        holder.nohp.text = item?.mahasiswaNobp
        holder.alamat.text = item?.mahasiswaAlamat


        holder.btnHapus.setOnClickListener {
            itemClick.hapus(item)
        }
        holder.view.setOnClickListener {
            itemClick.detail(item)
        }



    }

    interface OnClickListener {
        fun detail(item: DataItem?)
        fun hapus(item: com.sidiq.mahasiswa_app_sidiq.model.DataItem?)
    }

}




