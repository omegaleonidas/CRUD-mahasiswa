package com.sidiq.mahasiswa_app_sidiq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sidiq.mahasiswa_app_sidiq.model.DataItem
import com.sidiq.mahasiswa_app_sidiq.model.ResponseAction
import com.sidiq.mahasiswa_app_sidiq.network.NetworkModule
import kotlinx.android.synthetic.main.activity_imput.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imput)
        val getData = intent.getParcelableExtra<DataItem>("data")

        if (getData != null){
            etNama.setText(getData.mahasiswaNama)
            etNohp.setText(getData.mahasiswaNobp)
            etAlamat.setText(getData.mahasiswaAlamat)

            btn1.text = "Update"

        }

        when (btn1.text) {
            "Update" -> {

                btn1.setOnClickListener {
                    updateData(getData?.idMahasiswa, etNama.text.toString(), etNohp.text.toString(), etAlamat.text.toString() )
                }

            } else -> {

            btn1.setOnClickListener {
                inputData(etNama.text.toString(), etNohp.text.toString(), etAlamat.text.toString())

            }
        }
        }



        btn2.setOnClickListener {
            finish()
        }

    }


    private fun inputData(mahasiswa_nama: String?, mahasiswa_nobp: String?, mahasiswa_alamat: String?){

        val input = NetworkModule.service().insertData(mahasiswa_nama ?: "", mahasiswa_nobp ?: "", mahasiswa_alamat ?: "")
        input.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {

                Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                finish()

            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }

        })

    }

    private fun updateData(id_mahasiswa: String?, mahasiswa_nama: String?, mahasiswa_nobp: String?, mahasiswa_alamat: String?){

        val input = NetworkModule.service().updateData(id_mahasiswa ?: "", mahasiswa_nama ?: "", mahasiswa_nobp ?: "", mahasiswa_alamat ?: "")
        input.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {

                Toast.makeText(applicationContext, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
                finish()

            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }

        })

    }
}