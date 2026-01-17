package com.example.pulsepoint.feature.dashboard.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale

/**
 * Custom Sparkline composable using Canvas API to render price/score trend line.
 * Draws a smooth path representing the historical data values.
 */
@Composable
fun Sparkline(
    data: List<Double>,
    modifier: Modifier = Modifier,
    lineColor: Color = Color.Blue,
    strokeWidth: Float = 2f
) {
    if (data.isEmpty() || data.all { it == 0.0 }) {
        return
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        // Find min and max values for normalization
        val minValue = data.minOrNull() ?: 0.0
        val maxValue = data.maxOrNull() ?: 1.0
        val range = if (maxValue > minValue) maxValue - minValue else 1.0

        // Create path with normalized coordinates
        val path = Path()
        val stepX = width / (data.size - 1).coerceAtLeast(1)

        data.forEachIndexed { index, value ->
            val x = index * stepX
            // Normalize y: map value to 0..1, then flip (y=0 is top, y=height is bottom)
            val normalizedY = if (range > 0) {
                ((value - minValue) / range).toFloat()
            } else {
                0.5f // Center if all values are the same
            }
            val y = height - (normalizedY * height)

            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }

        // Draw the path with smooth line
        drawPath(
            path = path,
            color = lineColor,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}
