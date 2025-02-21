package com.bih.nic.bsphcl.trwjuc.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bih.nic.bsphcl.trwjuc.data.MaterialUtilized
import com.bih.nic.bsphcl.trwjuc.databinding.MaterialItemBinding


/**
 *Created by Chandan Singh on 2/21/2025.
 */
class MaterialAdapter(var mContext: Context, var productList: List<MaterialUtilized>)
    : RecyclerView.Adapter<MaterialAdapter.CardViewHolder>() {

    inner class CardViewHolder( var view: MaterialItemBinding ) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = MaterialItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size

    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val product = productList.get(position)
        val view = holder.view

       /* view.imageViewProduct.setImageResource(
            mContext.resources.getIdentifier(product.image, "drawable", mContext.packageName)
        )*/
        view.cardViewProduct.setOnClickListener {
            //Snackbar.make(it, "${product.description} başlıklı ürün seçildi", Snackbar.LENGTH_SHORT).show()
        }
        view.textViewDescription.text = product.matId
        view.textViewUnit1.text = product.unit1
        view.textViewUnit2.text = "${product.unit2} TL"

        /*if(product.isDoping == true){
            view.cardViewProduct.setCardBackgroundColor(Color.parseColor("#E6FBD4"))
            view.textViewDescription.setTypeface(null, Typeface.BOLD)
        }*/
    }
}