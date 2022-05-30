package com.example.myappfood

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myappfood.databinding.ListMenuItemBinding

class FoodItemAdapter(
    private val modelMenu: MutableList<ModelMenu>
): RecyclerView.Adapter<FoodItemAdapter.FoodViewHolder>() {
    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ListMenuItemBinding.bind(itemView)
        fun bind(modelMenu: ModelMenu) = with(binding){
            itemName.text = modelMenu.itemName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FoodViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.list_menu_item, parent, false)
            )

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(modelMenu[position])
    }

    override fun getItemCount() = modelMenu.size

}