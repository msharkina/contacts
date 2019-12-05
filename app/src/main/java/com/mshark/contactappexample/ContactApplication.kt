package com.mshark.contactappexample

import android.app.Application
import com.mshark.contactappexample.di.DependencyInjector

/**
 * @author msharkina
 * @since 4/12/19.
 */
class ContactApplication : Application() {

    fun getDependencyInjector() : DependencyInjector {

        return DependencyInjector
    }
}