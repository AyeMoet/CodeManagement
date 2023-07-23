package com.am.codemanagement.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.am.codemanagement.R
import com.am.codemanagement.adapter.SearchListAdapter
import com.am.codemanagement.data.models.MyModelImpl
import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.am.codemanagement.delegates.MovieViewHolderDelegate
import com.am.codemanagement.fragment.PagerFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,MovieViewHolderDelegate{
    private lateinit var mAdapter: SearchListAdapter
    private var movieList = mutableListOf<UpcommingMovieVO>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpTab()
        listener()
        setUpAdapter()
        getOfflineData()

    }

    private fun setUpAdapter() {
        mAdapter = SearchListAdapter(this,this)
        rv_search.layoutManager = LinearLayoutManager(this)
        rv_search.adapter = mAdapter
    }

    private fun getOfflineData() {
        movieList = MyModelImpl.mMovieDatabase?.movieDao()?.getAllData()?.toMutableList() ?: mutableListOf()

        Log.d("test_main","count ${movieList.count()}")
    }

    private fun setUpTab() {
        tab.addTab(tab.newTab().setText("Movies" ))
        tab.addTab(tab.newTab().setText("Events"))
        tab.addTab(tab.newTab().setText("Plays"))
        tab.addTab(tab.newTab().setText("Sports"))
        tab.addTab(tab.newTab().setText("Activities"))
        loadFragment(PagerFragment.newFragment())
    }

    private fun listener() {
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                loadFragment(PagerFragment.newFragment())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        et_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (et_search.text.isNotEmpty()) {
                        rv_search.visibility = View.VISIBLE
                        tab.visibility = View.GONE
                        frame_1.visibility = View.GONE
                        searchMovie(et_search.text.toString())
                    }
                    return true
                }

                return false
            }

        })

        iv_close.setOnClickListener {
            et_search.setText("")
            rv_search.visibility = View.GONE
            tab.visibility = View.VISIBLE
            frame_1.visibility = View.VISIBLE
        }
    }

    private fun searchMovie(searchItem: String) {
        //movieList = mutableListOf()
        var list = MyModelImpl.mMovieDatabase?.movieDao()?.getMovieByTitle(searchItem)

        /*list?.let {
            it.map {
                Log.d("test_data","data in dblist $list")
                movieList.addAll(it)
            }
        }*/
        Log.d("test_data","data in db movie $list")
        if (list != null) {
            mAdapter.setData(list.toMutableList())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        this.supportFragmentManager.beginTransaction().replace(R.id.frame_1, fragment).commit()
    }

    override fun onTapMovie(vo: UpcommingMovieVO, from: String) {
        startActivity(MovieDetailActivity.newIntent(this,vo,from))
    }
}