package com.ananda.movieapidb.view.ui.details.person

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.ananda.movieapidb.R
import com.ananda.movieapidb.compose.ViewModelActivity
import com.ananda.movieapidb.databinding.ActivityPersonDetailBinding
import com.ananda.movieapidb.extension.checkIsMaterialVersion
import com.ananda.movieapidb.models.entity.Person
import kotlinx.android.synthetic.main.toolbar_default.*

class PersonDetailActivity : ViewModelActivity() {

    private val viewModel: PersonDetailViewModel by injectViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding<ActivityPersonDetailBinding>(R.layout.activity_person_detail).run {
            lifecycleOwner = this@PersonDetailActivity
            viewModel = this@PersonDetailActivity.viewModel.apply { postPersonId(getPersonFromIntent().id) }
            person = getPersonFromIntent()
        }
        initializeUI()
    }

    private fun initializeUI() {
        toolbar_home.setOnClickListener { onBackPressed() }
        toolbar_title.text = getPersonFromIntent().name
    }

    private fun getPersonFromIntent() = intent.getParcelableExtra(personId) as Person

    companion object {
        const val personId = "person"
        private const val intent_requestCode = 1000

        fun startActivity(context: Context?, person: Person, view: View) {
            if (context is Activity) {
                val intent = Intent(context, PersonDetailActivity::class.java)
                intent.putExtra(personId, person)
                if (checkIsMaterialVersion()) {
                    ViewCompat.getTransitionName(view)?.let {
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context, view, it)
                        context.startActivityForResult(intent, intent_requestCode, options.toBundle())
                    }
                } else {
                    context.startActivity(intent)
                }
            }
        }
    }
}
