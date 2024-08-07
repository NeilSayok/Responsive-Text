package `in`.bluelabs.responsive_text.m3

import android.util.Log
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import `in`.bluelabs.responsive_text.constants.TEXT_SCALE_REDUCTION_INTERVAL


/**
 * High level element that displays text and provides semantics / accessibility information.
 *
 * The default [style] uses the [LocalTextStyle] provided by the [MaterialTheme] / components. If
 * you are setting your own style, you may want to consider first retrieving [LocalTextStyle],
 * and using [TextStyle.copy] to keep any theme defined attributes, only modifying the specific
 * attributes you want to override.
 *
 * For ease of use, commonly used parameters from [TextStyle] are also present here. The order of
 * precedence is as follows:
 * - If a parameter is explicitly set here (i.e, it is _not_ `null` or [TextUnit.Unspecified]),
 * then this parameter will always be used.
 * - If a parameter is _not_ set, (`null` or [TextUnit.Unspecified]), then the corresponding value
 * from [style] will be used instead.
 *
 * Additionally, for [color], if [color] is not set, and [style] does not have a color, then
 * [LocalContentColor] will be used.
 *
 * @param text the text to be displayed
 * @param modifier the [Modifier] to be applied to this layout node
 * @param color [Color] to apply to the text. If [Color.Unspecified], and [style] has no color set,
 * this will be [LocalContentColor].



 * @param overflow how visual overflow should be handled.
 * @param softWrap whether the text should break at soft line breaks. If false, the glyphs in the
 * text will be positioned as if there was unlimited horizontal space. If [softWrap] is false,
 * [overflow] and TextAlign may have unexpected effects.
 * @param maxLines An optional maximum number of lines for the text to span, wrapping if
 * necessary. If the text exceeds the given number of lines, it will be truncated according to
 * [overflow] and [softWrap]. It is required that 1 <= [minLines] <= [maxLines].
 * @param minLines The minimum height in terms of minimum number of visible lines. It is required
 * that 1 <= [minLines] <= [maxLines].

 * @param style style configuration for the text such as color, font, line height etc.
 */

@Composable
fun ResponsiveText(
    text: String,
    modifier: Modifier = Modifier,
    overflow: TextOverflow,
    style: TextStyle,
    targetTextSizeHeight: TextUnit = style.fontSize,
    textAlign: TextAlign? = null,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
) {
    var textSize by remember { mutableStateOf(targetTextSizeHeight) }

    Text(
        modifier = modifier,
        text = text,
        color = style.color,
        textAlign = textAlign,
        fontSize = textSize,
        fontFamily = style.fontFamily,
        fontStyle = style.fontStyle,
        fontWeight = style.fontWeight,
        lineHeight = style.lineHeight,
        letterSpacing = style.letterSpacing,
        textDecoration = style.textDecoration,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        overflow = overflow,
        onTextLayout = { textLayoutResult ->
            try {
                val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex) || textLayoutResult.didOverflowWidth) {
                    textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                }
            } catch (e: Exception) {
                Log.e("responsivetexterror", e.message.toString())
            }
        },
    )
}

@Composable
fun ResponsiveText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    overflow: TextOverflow,
    style: TextStyle,
    targetTextSizeHeight: TextUnit = style.fontSize,
    textAlign: TextAlign? = null,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
) {
    var textSize by remember { mutableStateOf(targetTextSizeHeight) }

    Text(
        modifier = modifier,
        text = text,
        color = style.color,
        textAlign = textAlign,
        fontSize = textSize,
        fontFamily = style.fontFamily,
        fontStyle = style.fontStyle,
        fontWeight = style.fontWeight,
        lineHeight = style.lineHeight,
        letterSpacing = style.letterSpacing,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        overflow = overflow,
        onTextLayout = { textLayoutResult ->
            try {
                val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
                if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex) || textLayoutResult.didOverflowWidth) {
                    textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                }
            } catch (e: Exception) {
                Log.e("responsivetexterror", e.message.toString())
            }
        },
    )
}


