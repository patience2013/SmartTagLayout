package com.xiong.mylibrary

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import kotlin.random.Random

/**
 * @Author Administrator
 * @Date 2020/7/22 16:06
 */
fun dp2px(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        Resources.getSystem().displayMetrics
    )
}

class ColorTextView(context: Context?, attributeSet: AttributeSet?) :
    androidx.appcompat.widget.AppCompatTextView(context, attributeSet) {

    constructor(context: Context?) : this(context, null)

    private var CORNER_RADIUS = dp2px(10F)
    var X_PADDING = dp2px(6F)

    var Y_PADDING = dp2px(3F)

    var COLORS = arrayOf(
        Color.parseColor("#E91E63"),
        Color.parseColor("#673AB7"),
        Color.parseColor("#3F51B5"),
        Color.parseColor("#2196F3"),
        Color.parseColor("#009688"),
        Color.parseColor("#FF9800"),
        Color.parseColor("#FF5722"),
        Color.parseColor("#795548")
    )

    var TEXT_SIZES = intArrayOf(
        16, 22, 28
    )

    var radom = Random

    var paint: Paint = Paint()


    init {
        setTextColor(Color.WHITE)
        paint.color = COLORS[radom.nextInt(COLORS.size)]
        paint.textSize = TEXT_SIZES[radom.nextInt(TEXT_SIZES.size)].toFloat()
        setPadding(X_PADDING.toInt(), Y_PADDING.toInt(), X_PADDING.toInt(), Y_PADDING.toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRoundRect(0F, 0F, width.toFloat(),
            height.toFloat(), CORNER_RADIUS, CORNER_RADIUS, paint)
        super.onDraw(canvas)
    }
}