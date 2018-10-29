package com._0137.Pertemuan_4

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.mahasiswa_item.view.*

class MahasiswaAdapter:RecyclerView.Adapter<MahasiswaAdapter.MahasiswaHolder> {

    var contex:Context
    var mhsList:List<Mahasiswa>

    constructor(context: Context, mhsList: List<Mahasiswa>){
        this.contex = context
        this.mhsList = mhsList
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MahasiswaHolder {
        val View = LayoutInflater.from(parent?.context).inflate(R.layout.mahasiswa_item, parent,false)
        return MahasiswaHolder(View)
    }

    override fun getItemCount(): Int {
        return mhsList.size
    }

    override fun onBindViewHolder(holder: MahasiswaHolder, position: Int) {
        val mhs = mhsList.get(position)
        holder.tvName?.setText(mhs.name)
        holder.tvAddress?.setText(mhs.address)
        holder.tvPhone?.setText(mhs.phone)

    }

    class MahasiswaHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView?.tvName
        val tvAddress = itemView?.tvAddress
        val tvPhone = itemView?.tvPhone
    }
}