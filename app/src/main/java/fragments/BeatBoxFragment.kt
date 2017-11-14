package fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import br.com.bpaulino.beatbox.R
import kotlinx.android.synthetic.main.list_item_sound.view.*
import util.BeatBox
import util.Sound

class BeatBoxFragment: Fragment() {

    lateinit var beatBox: BeatBox

    companion object {
        fun newInstance(): BeatBoxFragment = BeatBoxFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beatBox = BeatBox(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_beat_box, container, false)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.fragment_beat_box_recycler_view)
        recyclerView?.layoutManager = GridLayoutManager(activity, 3)
        recyclerView?.adapter = SoundAdapter(activity, beatBox.sounds)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }

    inner class SoundHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var sound: Sound
        val button: Button = itemView.list_item_sound_button

        fun bind(sound: Sound): Unit {
            this.sound = sound
            button.setText(sound.name)
            button.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            beatBox.play(sound)
        }

    }

    inner class SoundAdapter(val context: Context, val sounds: List<Sound>): RecyclerView.Adapter<SoundHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SoundHolder {
            val inflater = LayoutInflater.from(context)
            return SoundHolder(inflater.inflate(R.layout.list_item_sound, parent, false))
        }

        override fun onBindViewHolder(holder: SoundHolder?, position: Int) {
            holder?.let {
                val sound = sounds.get(position)
                it.bind(sound)
            }
        }

        override fun getItemCount(): Int = sounds.size

    }
}

