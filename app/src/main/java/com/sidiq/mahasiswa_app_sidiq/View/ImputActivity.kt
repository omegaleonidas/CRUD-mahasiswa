package com.sidiq.mahasiswa_app_sidiq.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.sidiq.mahasiswa_app_sidiq.R
import com.sidiq.mahasiswa_app_sidiq.ViewModel.ViewModelInputActivity
import com.sidiq.mahasiswa_app_sidiq.model.DataItem
import kotlinx.android.synthetic.main.activity_imput.*


class ImputActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelInputActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imput)
        viewModel = ViewModelProviders.of(this).get(ViewModelInputActivity::class.java)
        val getData = intent.getParcelableExtra<DataItem>("data")
        if (getData != null) {
            etNama.setText(getData.mahasiswaNama)
            etNohp.setText(getData.mahasiswaNobp)
            etAlamat.setText(getData.mahasiswaAlamat)

            btn1.text = "Update"

        }
        when (btn1.text) {
            "Update" -> {
                //untuk update
                btn1.setOnClickListener {

                    val id = getData?.idMahasiswa.toString()
                    val nama = etNama.text.toString()
                    val nobp = etNohp.text.toString()
                    val alamat = etAlamat.text.toString()

                    viewModel.getListDataUpdate(id, nama, nobp, alamat)
                    // memberikan notifikasi berupa toast
                    Toast.makeText(applicationContext, "data telah Diubah", Toast.LENGTH_SHORT)
                        .show()
                    //setelah di simpan akan pindah ke avtivity selanjutnya
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

            }
            else -> {

                btn1.setOnClickListener {

                    btn1.setOnClickListener {

                        //ambil inputan user
                        val nama = etNama.text.toString()
                        val nobp = etNohp.text.toString()
                        val alamat = etAlamat.text.toString()

                        viewModel.getListDataInsert(nama, nobp, alamat)
                        // memberikan notifikasi berupa toast
                        Toast.makeText(
                            applicationContext,
                            "data telah disimpan",
                            Toast.LENGTH_SHORT
                        ).show()
                        //setelah di simpan akan pindah ke avtivity selanjutnya
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }

        btn2.setOnClickListener {
            finish()

        }
    }


}

