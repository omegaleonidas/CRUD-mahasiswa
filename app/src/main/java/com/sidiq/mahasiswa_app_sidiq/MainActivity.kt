package com.sidiq.mahasiswa_app_sidiq

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sidiq.mahasiswa_app_sidiq.adapter.dataAdapter
import com.sidiq.mahasiswa_app_sidiq.model.DataItem
import com.sidiq.mahasiswa_app_sidiq.model.ResponseAction
import com.sidiq.mahasiswa_app_sidiq.model.ResponseGetData

import com.sidiq.mahasiswa_app_sidiq.network.NetworkModule
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab.setOnClickListener {
            val intent = Intent(this, ImputActivity::class.java)
            startActivity(intent)
        }


        showData()
    }

    private fun showData() {
        val listMahasiswa = NetworkModule().service().getData()
        listMahasiswa.enqueue(object : retrofit2.Callback<ResponseGetData> {


            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
                Log.e("data onFailure", " " + t.message)
            }

            override fun onResponse(
                call: Call<ResponseGetData>,
                response: Response<ResponseGetData>
            ) {

                if (response.isSuccessful) {
                    Log.e("data Response", " data masuk")
                    val item = response.body()?.data
                    val adapter = dataAdapter(item, object : dataAdapter.OnClickListener {
                        override fun detail(item: DataItem?) {
                            val intent = Intent(applicationContext, ImputActivity::class.java)
                            intent.putExtra("data", item)
                            startActivity(intent)
                        }

                        override fun hapus(item: DataItem?) {
                            AlertDialog.Builder(this@MainActivity).apply {
                                setTitle("Hapus Data")
                                setMessage("Yakin mau hapus data ?")
                                setPositiveButton("Hapus") { dialog, which ->
                                    hapusData(item?.idMahasiswa)
                                    dialog.dismiss()
                                }
                                setNegativeButton("Batal") { dialog, which ->
                                    dialog.dismiss()
                                }
                            }.show()
                        }




                    })
                    list.adapter = adapter
                }

            }


        })
    }

    private fun hapusData(idMahasiswa: String?) {

        val hapus = NetworkModule().service().deleteData(idMahasiswa?: "")
        hapus.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {

                Toast.makeText(applicationContext, "Data Berhasil dihapus", Toast.LENGTH_SHORT)
                    .show()
                showData()
                Log.e("data respon ","data berhasil terhapus")


            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Log.e("data onFailure", " " + t.message)
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }

        })

    }

    override fun onResume() {
        super.onResume()
        showData()
    }

}





