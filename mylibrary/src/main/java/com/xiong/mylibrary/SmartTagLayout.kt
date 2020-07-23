package com.xiong.mylibrary

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * @Author Administrator
 * @Date 2020/7/22 16:05
 */
class SmartTagLayout<T> : ViewGroup, SmartTagAdapter.OnDataChangeListener {
    lateinit var mSmartTagAdapter: SmartTagAdapter<T>
    private var xPadding = dp2px(10F)
    private var yPadding = dp2px(10F)

    var childBounds = mutableListOf<Rect>()

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        var typedArray = context.obtainStyledAttributes(attrs, R.styleable.SmartTagLayout)
        xPadding = typedArray.getDimension(R.styleable.SmartTagLayout_xPadding, 10F)
        xPadding = typedArray.getDimension(R.styleable.SmartTagLayout_yPadding, 10F)
        typedArray.recycle()
    }

    fun setAdapter(adapter: SmartTagAdapter<T>) {
        mSmartTagAdapter = adapter
        mSmartTagAdapter?.setOnDataChageListener(this)
        changeAdapter()
    }

    private fun changeAdapter() {
        removeAllViews()
        val adapter= mSmartTagAdapter
        for (i in 0 until adapter!!.getCount()){
            var childView = adapter.getView(this, i, adapter.getItem(i))
            addView(childView)
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = 0
        var lineWidthUsed = 0
        var lineMaxHeight = 0
//当前父view测量的宽度
        //当前父view测量的宽度
        val widthSpec = MeasureSpec.getSize(widthMeasureSpec)
        val widthSpecMode = MeasureSpec.getSize(widthMeasureSpec)

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            if (widthSpecMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.measuredWidth > widthSpec) {
                heightUsed += lineMaxHeight + yPadding.toInt()
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
                lineWidthUsed = 0
                lineMaxHeight = 0
            }
            var bound: Rect
            if (childBounds.size <= i) {
                bound = Rect()
                childBounds.add(bound)
            } else {
                bound = childBounds[i]
            }
            bound[lineWidthUsed, heightUsed, lineWidthUsed + child.measuredWidth] =
                heightUsed + child.measuredHeight
            lineWidthUsed += child.measuredWidth + xPadding.toInt()
            widthUsed = Math.max(lineWidthUsed, widthUsed)
            lineMaxHeight = Math.max(lineMaxHeight, child.measuredHeight)
        }
        val width = widthUsed
        val height = heightUsed + lineMaxHeight
        setMeasuredDimension(width, height)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.layout(
                childBounds[i].left,
                childBounds[i].top,
                childBounds[i].right,
                childBounds[i].bottom
            )
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
    override fun onDataChange() {
        changeAdapter()
    }

}