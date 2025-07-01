package com.rk.interviewtask.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rk.interviewtask.databinding.ItemPostBinding

class PostAdapter() :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private lateinit var binding: ItemPostBinding
    private var posts = mutableListOf<PostsRes>()
    fun submitList(newPosts: List<PostsRes>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {
        val post = posts[position]
        binding.tvTitle.text = post.title
        binding.tvBody.text = post.body

        holder.itemView.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition == RecyclerView.NO_POSITION) return@setOnClickListener
            val clickedItem = posts[currentPosition]
            posts.removeAt(currentPosition)
            posts.add(0, clickedItem)
            notifyItemMoved(currentPosition, 0)
            notifyItemChanged(0)
            val recyclerView = holder.itemView.parent as? RecyclerView
            recyclerView?.layoutManager?.scrollToPosition(0)
        }
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


}