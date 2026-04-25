package com.github.skydoves.colorpicker.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * SaturationSlider allows you to adjust the saturation component of the selected color from color pickers.
 *
 * @param modifier [Modifier] to decorate the internal Canvas.
 * @param controller Allows you to control and interact with color pickers and all relevant subcomponents.
 * @param borderRadius Radius of the border.
 * @param borderSize [Dp] size of the border.
 * @param borderColor [Color] of the border.
 * @param wheelImageBitmap Optional [ImageBitmap] to draw the wheel instead of a default circle.
 * @param wheelRadius Radius of the wheel.
 * @param wheelColor [Color] of the wheel.
 * @param wheelAlpha Alpha value applied to the wheel.
 * @param wheelPaint [Paint] used to draw the wheel.
 * @param initialColor [Color] of the initial state. If null, the slider uses the controller's current color.
 * @param onStart Callback invoked when user interaction with the slider starts.
 * @param onFinish Callback invoked when user interaction with the slider ends.
 */
@Composable
public fun SaturationSlider(
    modifier: Modifier = Modifier,
    controller: ColorPickerController,
    borderRadius: Dp = 6.dp,
    borderSize: Dp = 5.dp,
    borderColor: Color = Color.LightGray,
    wheelImageBitmap: ImageBitmap? = null,
    wheelRadius: Dp = 12.dp,
    wheelColor: Color = Color.White,
    wheelAlpha: Float = 1.0f,
    wheelPaint: Paint = Paint().apply {
        color = wheelColor
        alpha = wheelAlpha
    },
    initialColor: Color? = null,
    onStart: () -> Unit = {},
    onFinish: () -> Unit = {},
) {
    SideEffect {
        controller.isAttachedSaturationSlider = true
    }

    Slider(
        modifier = modifier,
        controller = controller,
        borderRadius = borderRadius,
        borderSize = borderSize,
        borderColor = borderColor,
        wheelImageBitmap = wheelImageBitmap,
        wheelRadius = wheelRadius,
        wheelColor = wheelColor,
        wheelAlpha = wheelAlpha,
        wheelPaint = wheelPaint,
        initialColor = initialColor,
        drawBackground = {},
        getValue = { saturation.value },
        setValue = ColorPickerController::setSaturation,
        onStart = onStart,
        onFinish = onFinish,
        computeInitial = { it.toHSV().second },
        getGradientColors = {
            val h = pureSelectedColor.value.toHSV().first
            val v = controller.brightness.value
            listOf(
                Color.hsv(h, 0f, v),
                Color.hsv(h, 1f, v),
            )
        },
    )
}