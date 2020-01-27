package com.example.nikeurbandictionary.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nikeurbandictionary.model.CustomAdapter
import com.example.nikeurbandictionary.R
import com.example.nikeurbandictionary.model.Network
import com.example.nikeurbandictionary.model.classes.Definition
import com.example.nikeurbandictionary.model.classes.ListDefinitions
import com.example.nikeurbandictionary.presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity(), ViewContract {

    var sort_list: Definition = Definition(emptyList())
    lateinit var presenter: Presenter
    var search: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
        initUI()

        btn_search.setOnClickListener {
            initNetworkCall()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("list", sort_list)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        sort_list = savedInstanceState?.getParcelable<Definition>("list")!!
        if(sort_list != Definition(emptyList())){
            recylerView.adapter = CustomAdapter(sort_list)
        }
    }

    override fun initUI() {
        recylerView.layoutManager = GridLayoutManager(this, 1)
    }

    override fun initPresenter() {
        presenter = Presenter()
        presenter.initView(this)
    }

    override fun passData(dataSet: Definition) {
        recylerView.adapter = CustomAdapter(dataSet)
    }

    override fun getErrorMessage(errorMessage: String) {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.sort, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_thumbs_up -> {
                sortThumbsUp()
                recylerView.adapter = CustomAdapter(sort_list)
                recylerView.adapter?.notifyDataSetChanged()
                return true
            }
            R.id.sort_thumbs_down -> {
                sortThumbsDown()
                recylerView.adapter = CustomAdapter(sort_list)
                recylerView.adapter?.notifyDataSetChanged()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sortThumbsUp() {
        Log.d("ASC", sort_list.toString())
        Collections.sort(sort_list.list, object : Comparator<ListDefinitions> {
            override fun compare(u1: ListDefinitions, u2: ListDefinitions): Int {
                return u2.thumbs_up.compareTo(u1.thumbs_up)
            }
        })
    }

    private fun sortThumbsDown() {
        Log.d("DSC", sort_list.toString())
        Collections.sort(sort_list.list, object : Comparator<ListDefinitions> {
            override fun compare(u1: ListDefinitions, u2: ListDefinitions): Int {
                return u2.thumbs_down.compareTo(u1.thumbs_down)
            }
        })

    }

    private fun initNetworkCall() {

        val network = Network.getInstance()
        val api = network.getApi()

        search = et_search.text.toString()

        progressBar.setVisibility(View.VISIBLE)
        Thread.sleep(1000)

        api.searchWord(search).enqueue(object : Callback<Definition> {
            override fun onFailure(call: Call<Definition>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Fail", Toast.LENGTH_LONG).show()
                Log.d("///////////", t.message)
                progressBar.setVisibility(View.GONE)
            }

            override fun onResponse(
                call: Call<Definition>,
                response: Response<Definition>
            ) {
                progressBar.setVisibility(View.GONE)
                sort_list = response.body()!!
                recylerView.adapter = response.body()?.let { CustomAdapter(it) }
            }
        })

    }
}
