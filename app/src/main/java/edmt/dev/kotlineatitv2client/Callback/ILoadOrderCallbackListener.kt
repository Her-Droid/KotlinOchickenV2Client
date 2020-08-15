package edmt.dev.kotlineatitv2client.Callback

import edmt.dev.kotlineatitv2client.Model.Order

interface ILoadOrderCallbackListener {
    fun onLoadOrderSuccess(orderList:List<Order>)
    fun onLoadOrderFailed(message:String)
}