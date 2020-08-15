package edmt.dev.kotlineatitv2client.Callback

import edmt.dev.kotlineatitv2client.Model.BestDealModel
import edmt.dev.kotlineatitv2client.Model.PopularCategoryModel

interface IBestDealLoadCallback {
    fun onBestDealLoadSuccess(bestDealList:List<BestDealModel>)
    fun onBestDealLoadFailed(message:String)
}