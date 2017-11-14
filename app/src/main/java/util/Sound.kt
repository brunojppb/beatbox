package util

/**
 * Created by bruno on 14/11/2017.
 */

data class Sound(val assetPath: String, val soundId: Int) {

    val name: String

    init {
        val components = assetPath.split("/")
        val fileName = components[components.size-1]
        name = fileName.replace(".wav", "")
    }

}