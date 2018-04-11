package com.android.ankit.postme.network

import com.android.ankit.postme.model.PostModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 05/04/2018 (MM/DD/YYYY )
 */
interface PostAPI {
    /**
     * Get the list of the pots from the API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<PostModel>>
}