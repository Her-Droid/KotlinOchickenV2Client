package edmt.dev.kotlineatitv2client.Callback

import edmt.dev.kotlineatitv2client.Model.Order

interface ILoadTimeFromFirebaseCallback {
    fun onLoadTimeSuccess(order: Order, estimatedTimeMs: Long )
    fun onLoadTimeFailed(message:String)
}