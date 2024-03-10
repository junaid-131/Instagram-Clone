package com.example.instagram_clone.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.instagram_clone.fragments.mypostFragment

class viewpagerAdapter (fm:FragmentManager):FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    val fragmentList = mutableListOf<Fragment>()
    val tittleList = mutableListOf<String>()
    override fun getCount(): Int {
        return fragmentList.size

    }

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tittleList.get(position)
    }
    fun addFragments(fragment:Fragment, tittle:String){
        fragmentList.add(fragment)
        tittleList.add(tittle)
    }
}



