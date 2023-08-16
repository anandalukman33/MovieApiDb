package com.ananda.movieapidb.view.viewholder

import android.view.View
import android.widget.Toast
import com.ananda.movieapidb.models.entity.Genre
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_genre.view.*

class GenreListViewHolder constructor(
    val view: View
) : BaseViewHolder(view) {

    private lateinit var genre: Genre

    @Throws(Exception::class)
    override fun bindData(data: Any) {
        if (data is Genre) {
            genre = data
            drawItem()
        }
    }

    private fun drawItem() {
        itemView.run {
            item_genre_title.text = genre.name
//            genre.poster_path?.let {
//                Glide.with(context)
//                    .load(Api.getPosterPath(it))
//                    .listener(
//                        GlidePalette.with(Api.getPosterPath(it))
//                            .use(BitmapPalette.Profile.VIBRANT)
//                            .intoBackground(item_poster_palette)
//                            .crossfade(true))
//                    .into(item_poster_post)
//            }
        }
    }

    override fun onClick(v: View?) = Toast.makeText(view.context, "Di klik ${genre.name}", Toast.LENGTH_LONG).show()//MovieDetailActivity.startActivityModel(context(), genre)

    override fun onLongClick(v: View?) = false
}