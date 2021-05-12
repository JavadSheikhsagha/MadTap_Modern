package ir.rahcode.clickermine

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import ir.rahcode.clickermine.WinnerEnum.*

class MainActivity : AppCompatActivity() {

    val ANIM_DURATION = 100F
    var winner = Continue

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.MODE_NIGHT_NO

        val rel1 = findViewById<RelativeLayout>(R.id.rel1)
        val rel2 = findViewById<RelativeLayout>(R.id.rel2)

        rel1.setOnClickListener {
            clickedAnimate(rel1,rel2)
            checkWinner()
        }

        rel2.setOnClickListener {
            clickedAnimate(rel2,rel1)
            checkWinner()
        }
    }

    fun checkWinner(){

        when(winner){
            Win1 -> Toast.makeText(applicationContext, "1 won", Toast.LENGTH_SHORT).show()
            Win2 -> Toast.makeText(applicationContext, "2 won", Toast.LENGTH_SHORT).show()
            Continue -> {}
        }
    }

    fun clickedAnimate(view1 : View, view2:View){

        val up: LinearLayout.LayoutParams = view1.layoutParams as LinearLayout.LayoutParams
        val down: LinearLayout.LayoutParams = view2.layoutParams as LinearLayout.LayoutParams

        val objectWrapper1 = ViewWeightAnimationWrapper(view1)
        val animator1 = ObjectAnimator
            .ofFloat(objectWrapper1,"weight",objectWrapper1.weight,up.weight + .02F)
        animator1.duration = ANIM_DURATION.toLong()

        val objectWrapper2 = ViewWeightAnimationWrapper(view2)
        val animator2 = ObjectAnimator
            .ofFloat(objectWrapper2,"weight",objectWrapper2.weight,down.weight - .02F)
        animator2.duration = ANIM_DURATION.toLong()

        animator1.start()
        animator2.start()

        if (up.weight > .95F){
            winner = Win1
            val objectWrapper11 = ViewWeightAnimationWrapper(view1)
            val animator11 = ObjectAnimator
                .ofFloat(objectWrapper11,"weight",objectWrapper11.weight,1F)
            animator1.duration = ANIM_DURATION.toLong()
            val objectWrapper22 = ViewWeightAnimationWrapper(view2)
            val animator22 = ObjectAnimator
                .ofFloat(objectWrapper22,"weight",objectWrapper22.weight,0F)
            animator11.start()
            animator22.start()
        }
    }

}