package com.android.ankit.postme.presenter


import android.util.Log
import android.view.View
import com.android.ankit.postme.R
import com.android.ankit.postme.base.BasePresenter
import com.android.ankit.postme.model.PostModel
import com.android.ankit.postme.network.PostAPI
import com.android.ankit.postme.utils.TAG
import com.android.ankit.postme.view.PostView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 05/04/2018 (MM/DD/YYYY )
 */
class PostPresenter(postView: PostView) : BasePresenter<PostView>(postView) {
    @Inject
    lateinit var postApi: PostAPI

    private var subscription: Disposable? = null

    override fun onViewCreated() {
        loadPosts()
    }

    /**
     * Loads the posts from the API and presents them in the view when retrieved, or showss error if
     * any.
     */
    private fun loadPosts() {
        view.showLoading()
        subscription = postApi
                .getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { postList ->
                            view.updatePosts(postList)
                            Log.d("List", postList.toString())
                        },
                        {
                            view.showError(it.localizedMessage)
                            Log.e("Error", it.localizedMessage)
                        }
                )
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}