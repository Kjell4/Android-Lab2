package com.example.lab2.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.lab2.model.entity.Celebrity

class CelebrityDiffUtil : DiffUtil.ItemCallback<Celebrity>() {
    override fun areItemsTheSame(oldItem: Celebrity, newItem: Celebrity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Celebrity, newItem: Celebrity): Boolean {
        return oldItem == newItem
    }
}