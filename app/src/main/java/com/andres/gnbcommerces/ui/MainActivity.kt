package com.andres.gnbcommerces.ui

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.andres.gnbcommerces.R
import com.andres.gnbcommerces.databinding.ActivityMainBinding
import com.andres.gnbcommerces.ui.view.products.ProductsListFragment

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            navigateToFragment(ProductsListFragment.newInstance(), false)
        }

    }

    fun navigateToFragment(fragment: Fragment, addToBackStack: Boolean){
        val ft = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            ft.addToBackStack(fragment.tag)
        }
        ft.replace(R.id.viewRoot, fragment, fragment.tag)
        ft.commit()
    }

    fun hideKeyboard() {
        val imm = getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

}