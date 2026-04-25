package com.github.skydoves.colorpicker.compose

/**
 * Source of a color change event from [ColorPickerController].
 * Allows consumers to react differently based on interaction type.
 * Part of [ColorEnvelope], it occurs in: [Slider] [ColorPicker]
 */
public enum class ColorChangeSource {

    /** Discrete tap gesture → final selection. */
    Tap,

    /** Drag gesture → continuous updates (e.g., live preview). */
    Drag,

    /** Programmatic update. No user interaction. */
    Programmatic
}
