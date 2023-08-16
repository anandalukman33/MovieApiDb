package com.ananda.movieapidb.view.ui.details.tv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.ananda.movieapidb.R
import com.ananda.movieapidb.compose.ViewModelActivity
import com.ananda.movieapidb.databinding.ActivityTvDetailBinding
import com.ananda.movieapidb.extension.applyToolbarMargin
import com.ananda.movieapidb.extension.simpleToolbarWithHome
import com.ananda.movieapidb.models.entity.Tv
import com.ananda.movieapidb.view.adapter.ReviewListAdapter
import com.ananda.movieapidb.view.adapter.VideoListAdapter
import com.skydoves.whatif.whatIfNotNull
import kotlinx.android.synthetic.main.activity_tv_detail.*

class TvDetailActivity : ViewModelActivity() {

    private val viewModel: TvDetailViewModel by injectViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding<ActivityTvDetailBinding>(R.layout.activity_tv_detail).run {
            lifecycleOwner = this@TvDetailActivity
            viewModel = this@TvDetailActivity.viewModel.apply { postTvId(getTvFromIntent().id) }
            tv = getTvFromIntent()
            videoAdapter = VideoListAdapter()
            reviewAdapter = ReviewListAdapter()
        }
        initializeUI()
    }

    private fun initializeUI() {
        applyToolbarMargin(tv_detail_toolbar)
        simpleToolbarWithHome(tv_detail_toolbar, getTvFromIntent().name)
    }

    private fun getTvFromIntent() = intent.getParcelableExtra(tvId) as Tv

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) onBackPressed()
        return false
    }

    companion object {
        private const val tvId = "tv"
        fun startActivityModel(context: Context?, tv: Tv) {
            context.whatIfNotNull {
                val intent = Intent(it, TvDetailActivity::class.java).apply { putExtra(tvId, tv) }
                it.startActivity(intent)
            }
        }
    }
}