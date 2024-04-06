package com.example.lab2.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.R
import com.example.lab2.databinding.ItemCelebrityBinding
import com.example.lab2.model.entity.Celebrity

class CelebrityAdapter : ListAdapter<Celebrity, CelebrityAdapter.ViewHolder>(CelebrityDiffUtil()) {

    companion object {
        private const val CELEBRITY_ADAPTER_TAG = "CelebrityAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(CELEBRITY_ADAPTER_TAG, "onCreateViewHolder")

        return ViewHolder(
            ItemCelebrityBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(CELEBRITY_ADAPTER_TAG, "onBindViewHolder: $position")
        holder.bind(getItem(position))
    }

    private var originalList: List<Celebrity> = listOf()

    fun setOriginalList(list: List<Celebrity>) {
        originalList = list
    }

    fun filter(query: String) {
        val filteredList = originalList.filter { celebrity ->
            celebrity.name.contains(query, ignoreCase = true)
        }
        submitList(filteredList)
    }

    inner class ViewHolder(
        private val binding: ItemCelebrityBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(celebrity: Celebrity){

            with(binding) {
                celebrityName.text = celebrity.name
                celebrityNationality.text = binding.root.context.getString(R.string.nationality, celebrity.nationality)
                celebrityHeight.text = binding.root.context.getString(R.string.height, celebrity.height.toString())
                celebrityBirthday.text = binding.root.context.getString(R.string.birthday, celebrity.birthday)
                celebrityOccupation.text = binding.root.context.getString(R.string.occupation, celebrity.occupation.toString())
            }
        }
    }
}
