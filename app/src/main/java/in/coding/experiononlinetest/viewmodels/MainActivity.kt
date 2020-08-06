package `in`.coding.experiononlinetest.viewmodels

import `in`.coding.experiononlinetest.R
import `in`.coding.experiononlinetest.adapters.ItemsAdapter
import `in`.coding.experiononlinetest.views.MainViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var itemsAdapter: ItemsAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemsAdapter = ItemsAdapter(this)
        itemsView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            itemAnimator = DefaultItemAnimator()
            adapter = itemsAdapter
        }

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        getList()

        swipeRefreshLayout.setOnRefreshListener {
            getList()
        }
    }

    private fun getList() {
        mainViewModel.getList()!!.observe(this, Observer {
            title = it.title
            itemsAdapter.submitList(it.rows)
            swipeRefreshLayout.isRefreshing = false
        })
    }
}