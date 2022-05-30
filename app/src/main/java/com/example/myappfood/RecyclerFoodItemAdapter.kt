package com.example.myappfood

import android.content.Context
import android.view.*
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class RecyclerFoodItemAdapter(
    var context: Context,
    private var itemList: ArrayList<ModelMenu>,
    private val loadDefaultImage: Int,
    val listener: OnItemClickListener

) :
    RecyclerView.Adapter<RecyclerFoodItemAdapter.ItemListViewHolder>(), Filterable{

    private var fullItemList = ArrayList<ModelMenu>(itemList)

    interface OnItemClickListener {
        fun onItemClick(item: ModelMenu)
        fun onPlusBtnClick(item: ModelMenu)
        fun onMinusBtnClick(item: ModelMenu)
    }

    class ItemListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImageIV: ImageView = itemView.findViewById(R.id.item_image)
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemPriceTV: TextView = itemView.findViewById(R.id.item_price)
        val itemStarsTV: TextView = itemView.findViewById(R.id.item_stars)
        val itemQuantityTV: TextView = itemView.findViewById(R.id.item_quantity_tv)
        val itemQuantityIncreaseIV: ImageView = itemView.findViewById(R.id.increase_item_quantity_iv)
        val itemQuantityDecreaseIV: ImageView =
            itemView.findViewById(R.id.decrease_item_quantity_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_menu_item, parent, false)
        fullItemList = ArrayList<ModelMenu>(itemList)
        return ItemListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val currentItem = itemList[position]
        if (loadDefaultImage == 1) holder.itemImageIV.setImageResource(R.drawable.fast_food)


        holder.itemName.text = currentItem.itemName
        holder.itemPriceTV.text = "$${currentItem.itemPrice}"
        holder.itemStarsTV.text = currentItem.itemStars.toString()
        holder.itemQuantityTV.text = currentItem.quantity.toString()

        holder.itemQuantityIncreaseIV.setOnClickListener {
            val n = currentItem.quantity
            holder.itemQuantityTV.text = (n+1).toString()

            listener.onPlusBtnClick(currentItem)

        }

        holder.itemQuantityDecreaseIV.setOnClickListener {
            val n = currentItem.quantity
            if (n == 0) return@setOnClickListener
            holder.itemQuantityTV.text = (n-1).toString()

            listener.onMinusBtnClick(currentItem)
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int = itemList.size

    fun filterList(filteredList: ArrayList<ModelMenu>) {
        itemList = filteredList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return searchFilter;
    }

    private val searchFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = ArrayList<ModelMenu>()
            if (constraint!!.isEmpty()) {
                filteredList.addAll(fullItemList)
            } else {
                val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()

                for (item in fullItemList) {
                    if (item.itemName.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            itemList.clear()
            itemList.addAll(results!!.values as ArrayList<ModelMenu>)
            notifyDataSetChanged()
        }

    }
}