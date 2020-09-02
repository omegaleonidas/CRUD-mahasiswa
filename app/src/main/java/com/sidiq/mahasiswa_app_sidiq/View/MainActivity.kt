package com.sidiq.mahasiswa_app_sidiq.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sidiq.mahasiswa_app_sidiq.R
import com.sidiq.mahasiswa_app_sidiq.ViewModel.ViewModelMainActivity
import com.sidiq.mahasiswa_app_sidiq.adapter.dataAdapter
import com.sidiq.mahasiswa_app_sidiq.model.DataItem
import com.sidiq.mahasiswa_app_sidiq.model.ResponseAction
import com.sidiq.mahasiswa_app_sidiq.model.ResponseGetData

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(ViewModelMainActivity::class.java)

        fab.setOnClickListener {
            val intent = Intent(this, ImputActivity::class.java)
            startActivity(intent)
        }
        viewModel.getListData()

        attachobserve()

    }

    private fun attachobserve() {
        //getData
        viewModel.responseData.observe(this, Observer { showData(it) })
        viewModel.isError.observe(this, Observer { showError(it) })

        //hapus
        viewModel.responseDeleteData.observe(this, Observer { deleteData(it) })
        viewModel.isError.observe(this, Observer { showError(it) })
    }


    private fun deleteData(it: ResponseAction?) {
        Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT).show()
        onResume()
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT).show()

    }

    private fun showData(it: ResponseGetData) {

        val adapter = dataAdapter(it.data, object : dataAdapter.OnClickListener {
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
                        viewModel.getDeleteData(item?.idMahasiswa)
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

    override fun onResume() {
        super.onResume()
        viewModel.getListData()
    }

}









