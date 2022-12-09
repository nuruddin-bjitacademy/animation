package com.graphicless.animation

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.graphicless.animation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var target: View = binding.targetImage
        binding.viewSwitch.setOnClickListener{
            if(binding.viewSwitch.isChecked){
                Toast.makeText(this, "ImageView", Toast.LENGTH_SHORT).show()
                target = binding.targetImage
            }else{

                Toast.makeText(this, "TextView", Toast.LENGTH_SHORT).show()
                target = binding.targetTextview
            }
        }

        binding.reset.setOnClickListener{
            target.translationX = 0f
        }

        binding.btnRotate.setOnClickListener {
            if (binding.mySwitch.isChecked) {
                rotateAnimation(target)
            } else {
                rotator(target)
            }
        }

        binding.btnBounce.setOnClickListener {
            if (binding.mySwitch.isChecked) {
                bounceAnimation(target)
            } else {
                bounce(target)
            }
        }

        binding.btnFadeIn.setOnClickListener {
            if (binding.mySwitch.isChecked) {
                fadeInWithXML(target)
            } else {
                fadeIn(target)
            }
        }

        binding.btnFadeOut.setOnClickListener {
            if (binding.mySwitch.isChecked) {
                fadeOutWithXML(target)
            } else {
                fadeOut(target)
            }
        }

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        binding.btnSlideIn.setOnClickListener {
            if (binding.mySwitch.isChecked) {
                slideInAnimation(target)
            } else {
                slideIn(target, width.toFloat())
            }
        }

        binding.btnSlideOut.setOnClickListener {
            if (binding.mySwitch.isChecked) {
                slideOutAnimation(target)
            } else {
                slideOut(target, width.toFloat())
            }
        }

        binding.btnZoomIn.setOnClickListener {
            if (binding.mySwitch.isChecked) {
                zoomInAnimation(target)
            } else {
                zoomIn(target)
            }
        }

        binding.btnZoomOut.setOnClickListener {
            if (binding.mySwitch.isChecked) {
                zoomOutAnimation(target)
            } else {
                zoomOut(target)
            }
        }


    }

    // Animation functions
    private fun rotator(target: View) {
        val animator = ObjectAnimator.ofFloat(target, View.ROTATION, 0f, 360f)
        animator.repeatCount = 1
        animator.duration = 1000
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }

    private fun bounce(target: View) {
        val start = 1f
        val end = .5f
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, start, end)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, start, end)
        val animator = ObjectAnimator.ofPropertyValuesHolder(target, scaleX, scaleY)

        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.duration = 500
        animator.start()
        Handler().postDelayed(Runnable {
            val scaleX2 = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, .75f)
            val scaleY2 = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, .75f)
            val animator2 = ObjectAnimator.ofPropertyValuesHolder(target, scaleX2, scaleY2)

            animator2.repeatCount = 1
            animator2.repeatMode = ObjectAnimator.REVERSE
            animator2.duration = 500
            animator2.start()
        }, 500)
    }

    private fun fadeOut(target: View) {
        val animator = ObjectAnimator.ofFloat(target, View.ALPHA, 1f, 0f)
        animator.duration = 1000
        animator.start()
    }

    private fun fadeIn(target: View) {
        val animator = ObjectAnimator.ofFloat(target, View.ALPHA, 0f, 1f)
        animator.duration = 1000
        animator.start()
    }

    private fun fadeInWithXML(target: View) {
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        target.startAnimation(fadeInAnimation)
    }

    private fun fadeOutWithXML(target: View) {
        val fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        target.startAnimation(fadeOutAnimation)
    }

    private fun bounceAnimation(target: View) {
        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        target.startAnimation(bounceAnimation)
    }

    private fun rotateAnimation(target: View) {
        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        target.startAnimation(bounceAnimation)
    }

    private fun slideInAnimation(target: View) {
        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in)
        target.startAnimation(bounceAnimation)
    }

    private fun slideOutAnimation(target: View) {
        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_out)
        target.startAnimation(bounceAnimation)
    }

    private fun slideIn(target: View, w: Float){
        val animator = ObjectAnimator.ofFloat(target, View.X, 0f, w/2)
        animator.duration = 1000
        animator.start()
    }
    private fun slideOut(target: View, w: Float){
        val animator = ObjectAnimator.ofFloat(target, View.X, w/2, w)
        animator.duration = 1000
        animator.start()
    }

    private fun zoomInAnimation(target: View) {
        val zoomInAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        target.startAnimation(zoomInAnimation)
    }

    private fun zoomOutAnimation(target: View) {
        val zoomOutAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
        target.startAnimation(zoomOutAnimation)
    }

    private fun zoomIn(target: View) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f,3f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f,3f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(target, scaleX, scaleY)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }
    private fun zoomOut(target: View) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f,.2f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f,.2f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(target, scaleX, scaleY)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }
}