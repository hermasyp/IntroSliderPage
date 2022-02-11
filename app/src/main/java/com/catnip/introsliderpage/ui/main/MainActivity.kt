package com.catnip.introsliderpage.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.catnip.introsliderpage.databinding.ActivityMainBinding
import com.catnip.introsliderpage.ui.formfragment.FormFragment
import com.catnip.introsliderpage.ui.sliderfragment.SliderFragment
import com.catnip.introsliderpage.utils.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        initViewPager()
    }

    private fun initViewPager() {
        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragment(
            SliderFragment(
                "This is Title 1",
                "This is desc 1",
                "https://i.kym-cdn.com/entries/icons/original/000/002/819/karp.jpg"
            ),
            "Fragment 1"
        )
        fragmentAdapter.addFragment(
            SliderFragment(
                "This is Title 2",
                "This is desc 2",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/130.png"
            ),
            "Fragment 2"
        )
        fragmentAdapter.addFragment(
            SliderFragment(
                "This is Title 3",
                "This is desc 3",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/143.png"
            ),
            "Fragment 3"
        )
        fragmentAdapter.addFragment(
            FormFragment(),
            "Fragment 4"
        )
        binding.vpIntro.apply {
            adapter = fragmentAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    when {
                        position == 0 -> {
                            binding.tvNext.visibility = View.VISIBLE
                            binding.tvNext.isEnabled = true
                            binding.tvPrevious.visibility = View.INVISIBLE
                            binding.tvPrevious.isEnabled = false
                        }
                        position < fragmentAdapter.itemCount - 1 -> {
                            binding.tvNext.visibility = View.VISIBLE
                            binding.tvNext.isEnabled = true
                            binding.tvPrevious.visibility = View.VISIBLE
                            binding.tvPrevious.isEnabled = true
                        }
                        position == fragmentAdapter.itemCount - 1 -> {
                            binding.tvNext.visibility = View.INVISIBLE
                            binding.tvNext.isEnabled = false
                            binding.tvPrevious.visibility = View.VISIBLE
                            binding.tvPrevious.isEnabled = true
                        }
                    }
                    super.onPageSelected(position)
                }
            })
        }
        binding.dotsIndicator.setViewPager2(binding.vpIntro)
        binding.tvNext.setOnClickListener {
            if (getNextIndex() != -1) {
                binding.vpIntro.setCurrentItem(getNextIndex(), true)
            }
        }
        binding.tvPrevious.setOnClickListener {
            //checking validity
            if (getPreviousIndex() != -1) {
                binding.vpIntro.setCurrentItem(getPreviousIndex(), true)
            }
        }
    }

    //0,1,2,3
    // currentPage = 0
    // previous index =

    private fun getPreviousIndex(): Int {
        val currentPage = binding.vpIntro.currentItem //0
        return if (currentPage - 1 >= 0) { // 0 - 1 = -1 , x >= 0
            currentPage - 1
        } else {
            -1 // unselected index , index always start by 0
        }
    }

    //0,1,2,3 = 4 pages
    // maxPages = 4
    // currentIndex = 3
    // 3 + 1 = 4, x = 4 , x < maxPages ?
    private fun getNextIndex(): Int {
        val maxPages = binding.vpIntro.adapter?.itemCount ?: -1
        val currentIndex = binding.vpIntro.currentItem //
        return if (maxPages != -1) {
            if (currentIndex + 1 < maxPages) { // 4+1 = 5
                currentIndex + 1
            } else {
                -1
            }
        } else {
            -1
        }
    }
}
