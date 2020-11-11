package com.one.second.animate

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Color.BLUE
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.one.kcore.extension.dp

/**
 * @author  diaokaibin@gmail.com on 2020/11/11.
 */

private val provinces = listOf("北京市",
        "天津市",
        "上海市",
        "重庆市",
        "河北省",
        "山西省",
        "辽宁省",
        "吉林省",
        "黑龙江省",
        "江苏省",
        "浙江省",
        "安徽省",
        "福建省",
        "江西省",
        "山东省",
        "河南省",
        "湖北省",
        "湖南省",
        "广东省",
        "海南省",
        "四川省",
        "贵州省",
        "云南省",
        "陕西省",
        "甘肃省",
        "青海省",
        "台湾省",
        "内蒙古自治区",
        "广西壮族自治区",
        "西藏自治区",
        "宁夏回族自治区",
        "新疆维吾尔自治区",
        "香港特别行政区",
        "澳门特别行政区")

class ProvinceView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    var paint = Paint()
    var province = "上海市"
        set(value) {
            field = value
            invalidate()
        }

    init {
        paint.color = BLUE
        paint.textSize = 25.dp
        paint.textAlign = Paint.Align.CENTER
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText(province, width / 2f, height / 2f, paint)

    }
}

class ProvinceTypeEvaluator : TypeEvaluator<String> {
    override fun evaluate(fraction: Float, startValue: String, endValue: String): String {
        val i =  provinces.indexOf(startValue) + (provinces.indexOf(endValue) - provinces.indexOf(startValue))*fraction
        return provinces[i.toInt()]
    }


}