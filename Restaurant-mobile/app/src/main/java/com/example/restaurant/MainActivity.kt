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
                Toast.makeText(baseContext, "Pedido feito com Sucesso", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun getMenu()  {
        val itemsMenu = arrayListOf(Menu(id = 1, name = "Lasanha", value = 134.0),
            Menu(id = 2, name = "Frango assado", value = 80.0),
            Menu(id = 3, name = "Feijoada", value = 170.0))
        val adapter = ArrayAdapter<Menu>(this@MainActivity, android.R.layout.simple_list_item_1, itemsMenu.toTypedArray())
        val list: ListView = findViewById(R.id.lista);

        list.setOnItemClickListener { _, _, posicao, _ ->
            orders.add(itemsMenu[posicao])
            checkoutValue?.text = "Total pedido: R$" + getTotalPrice(orders).toString()
        }

        list.adapter  = adapter
        adapter.notifyDataSetChanged()
    }

    private fun getTotalPrice(orders: ArrayList<Menu>) : Double {
        var total = 0.0
        orders.forEach {
            total += it.value
        }
        return total
    }
}