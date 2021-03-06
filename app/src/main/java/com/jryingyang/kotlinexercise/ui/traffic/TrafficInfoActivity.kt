package com.jryingyang.kotlinexercise.ui.traffic

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import com.jryingyang.kotlinexercise.R
import com.jryingyang.kotlinexercise.databinding.ActivityTrafficInfoBinding
import com.jryingyang.kotlinexercise.model.RequestLogin
import com.jryingyang.kotlinexercise.model.ResponseLogin
import com.jryingyang.kotlinexercise.ui.base.BaseAppCompatActivity

class TrafficInfoActivity : BaseAppCompatActivity<ActivityTrafficInfoBinding>() {

    override fun getViewBinding(): ActivityTrafficInfoBinding {
        return ActivityTrafficInfoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.activity_traffic_info)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_traffic, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_timezone -> {
                val action = intent.getParcelableExtra<ResponseLogin>("loginData")?.let {
                    TrafficInfoFragmentDirections.actionTrafficInfoFragmentToSettingFragment(
                        it
                    )
                }
                if (action != null) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(action)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}