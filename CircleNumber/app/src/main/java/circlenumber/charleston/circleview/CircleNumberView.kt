package circlenumber.charleston.circleview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View


class CircleNumberView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val drawable = ShapeDrawable(OvalShape())
    private var number = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint(canvas)
    }

    public fun setNumber(number: Int) {
        this.number = number
    }


    private fun paint(canvas: Canvas) {
        paintCircle(canvas)
        paintText(canvas)
    }

    private fun paintCircle(canvas: Canvas) {
        val paintCircle = Paint()
        paintCircle.color = Color.BLACK
        paintCircle.style = Paint.Style.FILL

        val radius = Math.min(canvas.width, canvas.height / 2)

        canvas.drawCircle(
                centerX(canvas),
                centerY(canvas),
                radius.toFloat(),
                paintCircle
        )
    }

    private fun paintText(canvas: Canvas) {
        val paintText = Paint()
        paintText.style = Paint.Style.FILL
        paintText.color = Color.WHITE
        paintText.textSize = spToPx(24f)

        val centerTextX = centerX(canvas) - (paintText.measureText(number.toString()) / 2)
        val centerTextY = centerY(canvas) - ((paintText.descent() + paintText.ascent()) / 2)

        canvas.drawText(number.toString(),
                centerTextX,
                centerTextY,
                paintText
        )
    }

    private fun centerX(canvas: Canvas) = (canvas.width / 2).toFloat()
    private fun centerY(canvas: Canvas) = (canvas.height / 2).toFloat()

    private fun spToPx(sp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics)
    }
}