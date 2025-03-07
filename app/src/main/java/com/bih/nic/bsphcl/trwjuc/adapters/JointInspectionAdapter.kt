package com.bih.nic.bsphcl.trwjuc.adapters
import com.google.android.material.snackbar.Snackbar;
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bih.nic.bsphcl.trwjuc.data.JointInspectionReport
import com.bih.nic.bsphcl.trwjuc.databinding.JointInspectionItemBinding
import com.bih.nic.bsphcl.trwjuc.ui.ApplyForNewUC
import com.bih.nic.bsphcl.trwjuc.ui.JointInspectionActivity
import com.bih.nic.bsphcl.trwjuc.ui.MainActivity


/**
 *Created by Chandan Singh on 2/25/2025.
 */
class JointInspectionAdapter(var mContext: Context, var jointInspectionData: List<JointInspectionReport>)
    : RecyclerView.Adapter<JointInspectionAdapter.CardViewHolder>() {

    inner class CardViewHolder( var view:  JointInspectionItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = JointInspectionItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return jointInspectionData.size

    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val jointInspectionData = jointInspectionData.get(position)
        val view = holder.view

       /* view.imageViewProduct.setImageResource(
            mContext.resources.getIdentifier(product.image, "drawable", mContext.packageName)
        )*/
        view.butEdit.setOnClickListener {
            Snackbar.make(it, "Update ${jointInspectionData.uId}", Snackbar.LENGTH_SHORT).show()
            val intent = Intent(mContext, ApplyForNewUC::class.java)
            intent.putExtra("trwNo",jointInspectionData.uId)
            mContext.startActivity(intent)
        }
        view.butUpload.setOnClickListener {
            Snackbar.make(it, "Upload ${jointInspectionData.uId}", Snackbar.LENGTH_SHORT).show()
        }
        view.textViewDescription.text = jointInspectionData.uId
        view.textViewUnit1.text = jointInspectionData.make
        view.textViewUnit2.text = "${jointInspectionData.capacity} TL"

        /*if(product.isDoping == true){
            view.cardViewProduct.setCardBackgroundColor(Color.parseColor("#E6FBD4"))
            view.textViewDescription.setTypeface(null, Typeface.BOLD)
        }*/
    }
}