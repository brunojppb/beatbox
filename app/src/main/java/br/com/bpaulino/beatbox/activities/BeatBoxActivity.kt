package br.com.bpaulino.beatbox.activities

import android.support.v4.app.Fragment
import fragments.BeatBoxFragment

class BeatBoxActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = BeatBoxFragment.newInstance()

}
