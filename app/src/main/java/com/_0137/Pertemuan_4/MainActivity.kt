package com._0137.Pertemuan_4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var requestQueue: RequestQueue? = null
    var mLayoutManager: RecyclerView.LayoutManager? = null
    lateinit var mhsAdapter: MahasiswaAdapter
    var mhsList = ArrayList<Mahasiswa>()
    val URL_MHS = "http://192.168.100.218:80/mahasiswa/mahasiswa.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(this)
        }

        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rvMahasiswa.layoutManager = mLayoutManager
        mhsAdapter = MahasiswaAdapter(this, mhsList)
        rvMahasiswa.adapter = mhsAdapter
        rvMahasiswa.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        getMhs()
    }

    fun getMhs() {
        val listener = Response.Listener<JSONObject>{response: JSONObject? ->
            val meta = response?.getJSONObject("meta")
            val code = meta?.getInt("code")
            if (code == 200){
                val dataArray = response.getJSONArray("data")
                for (i in 0 until dataArray.length()){

                    val mahasiswa = Mahasiswa(
                            dataArray.getJSONObject(i).getString("name"),
                            dataArray.getJSONObject(i).getString("address"),
                            dataArray.getJSONObject(i).getString("phone")
                    )
                    mhsList.add(mahasiswa)
                }
                mhsAdapter.notifyDataSetChanged()
            }
        }

        val errorListener = Response.ErrorListener{error: VolleyError? ->
        }

        val request = JsonObjectRequest(URL_MHS,null,listener,errorListener)
        requestQueue?.add(request)
    }
}
