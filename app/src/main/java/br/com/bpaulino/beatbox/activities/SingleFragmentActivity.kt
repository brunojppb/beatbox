package br.com.bpaulino.beatbox.activities

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.bpaulino.beatbox.R

/**
 * Created by bruno on 14/11/2017.
 */

abstract class SingleFragmentActivity: AppCompatActivity() {

    abstract fun createFragment(): Fragment


    @LayoutRes
    fun getLayoutResId(): Int = R.layout.activity_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        val maybeFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if(maybeFragment == null) {
            val fragment = createFragment()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }

    }

}