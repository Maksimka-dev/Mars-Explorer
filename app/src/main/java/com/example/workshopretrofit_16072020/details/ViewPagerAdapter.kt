package com.example.workshopretrofit_16072020.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

import com.example.workshopretrofit_16072020.api.MarsPicture

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val arrayList: List<MarsPicture>
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return DetailsFragment.newInstance(arrayList[position])
    }

    override fun getCount(): Int {
        return arrayList.size
    }
}