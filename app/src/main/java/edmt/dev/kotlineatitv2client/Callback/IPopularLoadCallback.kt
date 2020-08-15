package edmt.dev.kotlineatitv2client.Callback

import edmt.dev.kotlineatitv2client.Model.PopularCategoryModel

interface IPopularLoadCallback {
    fun onPopularLoadSuccess(popularModelList: List<PopularCategoryModel>)
    fun onPopularLoadFailed(message:String)
}