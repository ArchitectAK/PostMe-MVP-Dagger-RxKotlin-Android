package com.android.ankit.postme.base

import com.android.ankit.postme.injection.component.PresenterInjector

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 05/04/2018 (MM/DD/YYYY )
 */
abstract class BasePresenter<out V : BaseView>(protected val view: V) {
    private val injector: PresenterInjector = DaggerPresenterInjector
            .builder()
            .baseView(view)
            .contextModule(ContextModule)
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependenciesss
     */
    private fun inject() {
        when (this) {
            is PostPresenter -> injector.inject(this)
        }
    }
}