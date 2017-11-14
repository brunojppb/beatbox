package util

import android.content.Context
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log

/**
 * Created by bruno on 14/11/2017.
 */

class BeatBox(context: Context) {

    private val assets: AssetManager = context.assets
    val sounds: List<Sound>
    val soundPool = SoundPool.Builder().setMaxStreams(util.BeatBox.MAX_SOUNDS).build()


    companion object BeatBox {
        val TAG             = "BeatBox"
        val SOUNDS_FOLDER   = "sample_sounds"
        val MAX_SOUNDS      = 5
    }

    init {
        val soundNames = assets.list(util.BeatBox.SOUNDS_FOLDER)
        Log.i(util.BeatBox.TAG, "Found "+ soundNames.size + " sounds!")
        sounds = soundNames.map { soundName ->
            val soundPath = BeatBox.SOUNDS_FOLDER + "/" + soundName
            Log.i(util.BeatBox.TAG, "Sound: " + soundPath)
            val assetFileDescriptor = assets.openFd(soundPath)
            val soundId = soundPool.load(assetFileDescriptor, 1)
            Sound(soundPath, soundId)
        }
    }

    fun play(sound: Sound): Unit {
        soundPool.play(sound.soundId, 1f, 1f, 1, 0, 1f)
    }

    fun release() = soundPool.release()

}