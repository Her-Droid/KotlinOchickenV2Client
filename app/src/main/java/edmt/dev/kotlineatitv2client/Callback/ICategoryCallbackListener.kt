package edmt.dev.kotlineatitv2client.Callback

import edmt.dev.kotlineatitv2client.Model.CategoryModel

interface ICategoryCallbackListener {
    fun onCategoryLoadSuccess(CategoriesList: List<CategoryModel>)
    fun onCategoryLoadFailed(message:String)
}