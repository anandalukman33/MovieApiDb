package com.ananda.movieapidb.view.viewholder

import android.view.View
import com.ananda.movieapidb.api.Api
import com.ananda.movieapidb.models.entity.Movie
import com.ananda.movieapidb.view.ui.details.movie.MovieDetailActivity
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_poster.view.*

class MovieListViewHolder constructor(
    val view: View
) : BaseViewHolder(view) {

    private lateinit var movie: Movie

    @Throws(Exception::class)
    override fun bindData(data: Any) {
        if (data is Movie) {
            movie = data
            drawItem()
        }
    }

    private fun drawItem() {
        itemView.run {
            item_poster_title.text = movie.title
            movie.poster_path?.let {
                Glide.with(context)
                    .load(Api.getPosterPath(it))
                    .listener(
                        GlidePalette.with(Api.getPosterPath(it))
                        .use(BitmapPalette.Profile.VIBRANT)
                        .intoBackground(item_poster_palette)
                        .crossfade(true))
                    .into(item_poster_post)
            }
        }
    }

    override fun onClick(v: View?) = MovieDetailActivity.startActivityModel(context(), movie)

    override fun onLongClick(v: View?) = false
}