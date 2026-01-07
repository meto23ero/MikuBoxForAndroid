package com.neko.expandable.layout

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.material.card.MaterialCardView
import io.nekohasekai.sagernet.R 

class ExpandableLayout(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs), View.OnClickListener {
    private lateinit var arrowIcon: ImageView
    private lateinit var cardExpandable: MaterialCardView
    private lateinit var expandableContent: ExpandableView

    override fun onFinishInflate() {
        super.onFinishInflate()
        expandableContent = findViewById(R.id.expandable_view)
        arrowIcon = findViewById(R.id.arrow_button)
        cardExpandable = findViewById(R.id.card_expandable)
        
        [span_7](start_span)// Hem karta hem oka tıklama özelliği[span_7](end_span)
        cardExpandable.setOnClickListener(this)
        arrowIcon.setOnClickListener(this)
        
        initializeLogic()
    }

    override fun onClick(view: View) {
        toggleExpansion()
    }

    private fun toggleExpansion() {
        // Her tıklamada yönün dikey olduğundan emin oluyoruz (eski kodun kararlılığı için)
        expandableContent.setOrientation(ExpandableView.VERTICAL)
        
        val isExpanding = !expandableContent.isExpanded
        
        if (isExpanding) {
            expandableContent.expand()
            [span_8](start_span)// Ok ikonunu aşağı döndür (90 derece)[span_8](end_span)
            arrowIcon.animate().setDuration(300L).rotation(90.0f).start()
        } else {
            expandableContent.collapse()
            [span_9](start_span)// Ok ikonunu yana döndür (0 derece)[span_9](end_span)
            arrowIcon.animate().setDuration(300L).rotation(0.0f).start()
        }
    }

    private fun initializeLogic() {
        [span_10](start_span)// Ripple efekti ve başlangıç durumu[span_10](end_span)
        arrowIcon.background = RippleDrawable(ColorStateList(arrayOf(intArrayOf()), intArrayOf(-0x8a8a8b)), null, null)
        arrowIcon.isClickable = true
        
        [span_11](start_span)// Başlangıçta açıksa oku 90 derece yap[span_11](end_span)
        if (expandableContent.isExpanded) {
            arrowIcon.rotation = 90.0f
        } else {
            arrowIcon.rotation = 0.0f
        }
    }
}
