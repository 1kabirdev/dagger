package com.dagger.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dagger.databinding.ItemListPostBinding
import com.dagger.models.Post

class AdapterPost : RecyclerView.Adapter<AdapterPost.ViewHolder>() {

    private var posts: ArrayList<Post> = arrayListOf()

    fun addPost(post: List<Post>) {
        posts.addAll(post)
        notifyItemInserted(post.size - 1)
    }

    inner class ViewHolder(private val binding: ItemListPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindViewPost(post: Post) = with(binding) {
            textViewTitle.text = post.title
            textViewBody.text = post.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemListPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindViewPost(posts[position])


    override fun getItemCount(): Int = posts.size
}