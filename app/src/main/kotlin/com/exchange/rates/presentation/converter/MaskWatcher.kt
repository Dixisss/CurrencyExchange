package com.exchange.rates.presentation.converter

import android.text.Editable
import android.text.Selection
import android.text.TextWatcher

class MaskTextWatcher(
    private val mask: String
) : TextWatcher {

    private var selfChange = false

    override fun afterTextChanged(s: Editable) {
        if (selfChange) return
        selfChange = true
        format(s)
        selfChange = false
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    fun format(text: Editable) {
        text.apply {
            // reset input filters
            val editableFilters = filters
            filters = emptyArray()

            val formatted = StringBuilder()
            val list = toMutableList()

            // apply mask
            mask.forEach { m ->
                if (list.isEmpty()) return@forEach
                var c = list[0]
                if ('#' == m) {
                    if (!c.isLetterOrDigit()) {
                        // find next letter or digit
                        val iterator = list.iterator()
                        while (iterator.hasNext()) {
                            c = iterator.next()
                            if (c.isLetterOrDigit()) break
                            iterator.remove()
                        }
                    }
                    if (list.isEmpty()) return@forEach
                    formatted.append(c)
                    list.removeAt(0)
                } else {
                    formatted.append(m)
                    if (m == c) {
                        list.removeAt(0)
                    }
                }
            }
            val previousLength = length
            val currentLength = formatted.length
            replace(0, previousLength, formatted, 0, currentLength)

            // set correct cursor position when editing
            if (currentLength < previousLength) {
                Selection.setSelection(text, currentLength)
            }

            // restore input filters
            filters = editableFilters
        }
    }
}