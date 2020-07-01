package com.mp.tifosifutsal

import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val fragmenPesan = PesanFragment()
        val fragmentSetting = SettingFragment()
        val fragmentHome = DashboardFragment()

        setFragment(fragmentHome)

        iv_menu1.setOnClickListener {
            setFragment(fragmentHome)

            changeIcon(iv_menu1, R.drawable.ic_home_aktif)
            changeIcon(iv_menu2, R.drawable.ic_pesan)
            changeIcon(iv_menu3, R.drawable.ic_user)
        }

        iv_menu2.setOnClickListener {
            setFragment(fragmenPesan)

            changeIcon(iv_menu1, R.drawable.ic_home)
            changeIcon(iv_menu2, R.drawable.ic_pesan_aktif)
            changeIcon(iv_menu3, R.drawable.ic_user)
        }

        iv_menu3.setOnClickListener {
            setFragment(fragmentSetting)

            changeIcon(iv_menu1, R.drawable.ic_home)
            changeIcon(iv_menu2, R.drawable.ic_pesan)
            changeIcon(iv_menu3, R.drawable.ic_user_aktif)
        }
    }

    protected fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int){
        imageView.setImageResource(int)
    }

}
