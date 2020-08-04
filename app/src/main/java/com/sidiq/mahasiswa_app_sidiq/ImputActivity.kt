package com.sidiq.mahasiswa_app_sidiq

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sidiq.mahasiswa_app_sidiq.model.DataItem
import com.sidiq.mahasiswa_app_sidiq.model.ResponseAction
import com.sidiq.mahasiswa_app_sidiq.presenter.InputView
import com.sidiq.mahasiswa_app_sidiq.presenter.PresenterInput
import com.sidiq.mahasiswa_app_sidiq.presenter.PresenterUpdate
import com.sidiq.mahasiswa_app_sidiq.presenter.UpdateView
import kotlinx.android.synthetic.main.activity_imput.*


class ImputActivity : AppCompatActivity(), InputView,UpdateView {


    private var presenterUpdate: PresenterUpdate? = null
    private var presenter: PresenterInput? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imput)
        val getData = intent.getParcelableExtra<DataItem>("data")

        if (getData != null) {
            etNama.setText(getData.mahasiswaNama)
            etNohp.setText(getData.mahasiswaNobp)
            etAlamat.setText(getData.mahasiswaAlamat)

            btn1.text = "Update"

        }

        when (btn1.text) {
            "Update" -> {
                presenterUpdate = PresenterUpdate(this)
                btn1.setOnClickListener {

                    val id = getData?.idMahasiswa
                    val nama = etNama.text.toString()
                    val nohp = etNohp.text.toString()
                    val alamat = etAlamat.text.toString()

                    presenterUpdate?.update(id, nama, nohp, alamat)

                }


            }
            else -> {

                btn1.setOnClickListener {
                    presenter = PresenterInput(this)
                    btn1.setOnClickListener {
                        //ambil inputan user
                        val nama = etNama.text.toString()
                        val nohp = etNohp.text.toString()
                        val alamat = etAlamat.text.toString()

                        presenter?.inputData(nama, nohp, alamat)
                    }
                }
            }
        }



        btn2.setOnClickListener {
            finish()

        }
    }

    override fun successInput(response: ResponseAction) {
        Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun errorInput(msg: String) {
        showToast("menyimpan gagal")
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun successUpdate(response: ResponseAction) {
        Toast.makeText(applicationContext, "Data berhasil diubah", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun errorUpdate(msg: String) {
        showToast("ubah  gagal")
    }


}
//    private fun inputData(mahasiswa_nama: String?, mahasiswa_nobp: String?, mahasiswa_alamat: String?){
//
//        val input = NetworkModule.service().insertData(mahasiswa_nama ?: "", mahasiswa_nobp ?: "", mahasiswa_alamat ?: "")
//        input.enqueue(object : Callback<ResponseAction> {
//
//            override fun onResponse(
//                call: Call<ResponseAction>,
//                response: Response<ResponseAction>
//            ) {
//
//                Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
//                finish()
//
//            }
//
//            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
//
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
//
//            }
//
//        })
//
//    }

//    private fun updateData(id_mahasiswa: String?, mahasiswa_nama: String?, mahasiswa_nobp: String?, mahasiswa_alamat: String?){
//
//        val input = NetworkModule.service().updateData(id_mahasiswa ?: "", mahasiswa_nama ?: "", mahasiswa_nobp ?: "", mahasiswa_alamat ?: "")
//        input.enqueue(object : Callback<ResponseAction> {
//
//            override fun onResponse(
//                call: Call<ResponseAction>,
//                response: Response<ResponseAction>
//            ) {
//
//                Toast.makeText(applicationContext, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
//                finish()
//
//            }
//
//            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
//
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
//
//            }
//
//        })
//
//    }

