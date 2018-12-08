package com.rohyme.aro7ezai.presentation.ui.screens.newAddress

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.ToastUtils
import com.rohyme.aro7ezai.R
import kotlinx.android.synthetic.main.new_address_fragment.*

class AddNewAddressFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.new_address_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doneBt.setOnClickListener {
            when {
                place_from.text.isEmpty() -> {
                    ToastUtils.showShort("من فضلك ادخل بداية العنوان")

                }

                place_to.text.isEmpty() -> {
                    ToastUtils.showShort("من فضلك ادخل مكان الوصول")

                }
                phoneNumber.text.isEmpty() -> {
                    ToastUtils.showShort("من فضلك ادخل رقم الهاتف الخاص بك")
                }
                else -> Handler().postDelayed({
                    ToastUtils.showShort("تم ارسال استفسارك بنجاح ، وسيتم التواصل معك قريبآ")
                    place_from.text.clear()
                    place_to.text.clear()
                    phoneNumber.text.clear()
                }, 500)
            }
        }
    }
}