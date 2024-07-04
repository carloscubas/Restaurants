package com.example.restaurant

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var list: ListView? = null
    private val orders: ArrayList<Menu> = ArrayList()
    private var checkoutValue: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        val myToolbar: Toolbar = findViewById(R.id.toolbar)
        myToolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(myToolbar)

        checkoutValue = findViewById(R.id.checkout_price)

        getMenu()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.checkout -> {
                val retrofitClient = NetworkUtils
                    .getRetrofitInstance("http://192.168.5.138:8095/")
                val endpoint = retrofitClient.create(Endpoint::class.java)
                val callback = endpoint.makeOrder(makeOrder(orders))
                callback.enqueue(object : Callback<Int> {
                    override fun onResponse(call: Call<Int>, response: Response<Int>) {
                        Toast.makeText(baseContext, "Sucesso", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
                return false
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun getMenu()  {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("http://192.168.5.138:8095/")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getItems()

        callback.enqueue(object : Callback<List<Menu>> {
            override fun onFailure(call: Call<List<Menu>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Menu>>, response: Response<List<Menu>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val itemsMenu = mutableListOf<Menu>()
                        for (menu in it) {
                            itemsMenu.add(menu)
                        }

                        val adapter = ArrayAdapter<Menu>(this@MainActivity, android.R.layout.simple_list_item_1, itemsMenu.toTypedArray())
                        val list: ListView = findViewById(R.id.lista);

                        list.setOnItemLongClickListener { _, _, posicao, _ ->
                            Toast.makeText(baseContext, posicao.toString(), Toast.LENGTH_SHORT).show()
                            orders.add(itemsMenu[posicao])
                            checkoutValue?.text = "R$" + getTotalPrice(orders).toString()
                            false
                        }
                        list.adapter  = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    private fun getTotalPrice(orders: ArrayList<Menu>) : Double {
        var total = 0.0
        orders.forEach {
            total += it.value
        }
        return total
    }

    private fun makeOrder(orders: ArrayList<Menu>) : ArrayList<Long> {
        val idOrders: ArrayList<Long> = ArrayList()
        orders.forEach {
            idOrders.add(it.id.toLong())
        }
        return idOrders
    }
}