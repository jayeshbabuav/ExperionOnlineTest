package `in`.coding.experiononlinetest.adapters

import `in`.coding.experiononlinetest.R
import `in`.coding.experiononlinetest.models.ItemModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items_layout.view.*
import java.lang.Exception

class ItemsAdapter(private val context: Context): ListAdapter<ItemModel, ItemsAdapter.ItemsViewHolder>(diffUtilCallback) {

    companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<ItemModel>() {
            override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder =
        ItemsViewHolder(LayoutInflater.from(context).inflate(R.layout.items_layout, parent, false))

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.titleText.text = getItem(holder.adapterPosition).title
        holder.descriptionText.text = getItem(holder.adapterPosition).description

        if (getItem(holder.adapterPosition).imageHref != null) {
            Picasso.get()
                .load(getItem(holder.adapterPosition).imageHref)
                .fit()
                .into(holder.imageView, object: Callback{
                    override fun onSuccess() {
                    }

                    override fun onError(e: Exception?) {
                        holder.imageView.visibility = View.GONE
                    }
                })
        }
        else
            holder.imageView.visibility = View.GONE
    }

    class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.titleText
        val descriptionText: TextView = itemView.descriptionText;
        val imageView: ImageView = itemView.imageView
    }
}