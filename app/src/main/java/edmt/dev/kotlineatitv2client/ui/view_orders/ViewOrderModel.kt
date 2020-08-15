package edmt.dev.kotlineatitv2client.ui.view_orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edmt.dev.kotlineatitv2client.Model.Order

class ViewOrderModel : ViewModel() {
    val mutableLiveDataOrderList:MutableLiveData<List<Order>>
    init {
        mutableLiveDataOrderList = MutableLiveData()
    }
    fun setMutableLiveDataOrderList(orderList:List<Order>)
    {
        mutableLiveDataOrderList.value = orderList
    }
}