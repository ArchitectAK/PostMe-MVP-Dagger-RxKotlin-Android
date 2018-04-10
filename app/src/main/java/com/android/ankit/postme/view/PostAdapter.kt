package com.android.ankit.postme.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.ankit.postme.R
import com.android.ankit.postme.databinding.ItemPostBinding
import com.android.ankit.postme.model.PostModel

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 10/04/2018 (MM/DD/YYYY )
 */
class PostAdapter(private val context: Context) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    /**
     * The list of posts of the adapter
     */
    private var posts: List<PostModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemPostBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_post, parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    /**
     * Updates the list of posts of the adapter
     * @param posts the new list of posts of the adapter
     */
    fun updatePosts(posts: List<PostModel>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Post item
     */
    class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a post into the view
         */
        fun bind(post: PostModel) {
            binding.post = post
            binding.executePendingBindings()
        }
    }
}