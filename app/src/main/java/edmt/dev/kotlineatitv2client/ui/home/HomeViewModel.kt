package edmt.dev.kotlineatitv2client.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edmt.dev.kotlineatitv2client.Callback.IBestDealLoadCallback
import edmt.dev.kotlineatitv2client.Callback.IPopularLoadCallback
import edmt.dev.kotlineatitv2client.Common.Common
import edmt.dev.kotlineatitv2client.Model.BestDealModel
import edmt.dev.kotlineatitv2client.Model.PopularCategoryModel

class HomeViewModel : ViewModel(), IPopularLoadCallback, IBestDealLoadCallback {

    override fun onBestDealLoadSuccess(bestDealList: List<BestDealModel>) {
        bestDealListMutableLiveData!!.value = bestDealList
    }

    override fun onBestDealLoadFailed(message: String) {
        messageError.value = message
    }

    override fun onPopularLoadSuccess(popularModelList: List<PopularCategoryModel>) {
        popularListMutableLiveData!!.value = popularModelList
    }

    override fun onPopularLoadFailed(message: String) {
        messageError.value = message
    }

    private  var popularListMutableLiveData:MutableLiveData<List<PopularCategoryModel>>?=null
    private  var bestDealListMutableLiveData:MutableLiveData<List<BestDealModel>>?=null
    private lateinit var messageError: MutableLiveData<String>
    private  var popularLoadCallbackListener:IPopularLoadCallback
    private var bestDealCallbackListener:IBestDealLoadCallback



    val bestDealList:LiveData<List<BestDealModel>>
        get(){
            if(bestDealListMutableLiveData == null)
            {
                bestDealListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadBestDealList()
            }
            return bestDealListMutableLiveData!!
        }

    private fun loadBestDealList() {
        val tempList = ArrayList<BestDealModel>()
        val BestDealRef = FirebaseDatabase.getInstance().getReference(Common.BEST_DEALS_REF)
        BestDealRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                bestDealCallbackListener.onBestDealLoadFailed((p0.message!!))
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<BestDealModel>(BestDealModel::class.java)
                    tempList.add(model!!)
                }
                bestDealCallbackListener.onBestDealLoadSuccess(tempList)
            }

        })
    }


    val popularList:LiveData<List<PopularCategoryModel>>
        get() {
            if (popularListMutableLiveData == null )
            {
                popularListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadPopularList()
            }
            return popularListMutableLiveData!!
        }

    private fun loadPopularList() {
        val tempList = ArrayList<PopularCategoryModel>()
        val popularRef = FirebaseDatabase.getInstance().getReference(Common.POPULAR_REF)
        popularRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                popularLoadCallbackListener.onPopularLoadFailed((p0.message!!))
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<PopularCategoryModel>(PopularCategoryModel::class.java)
                    tempList.add(model!!)
                }
             popularLoadCallbackListener.onPopularLoadSuccess(tempList)
            }

        })
    }

    init {
        popularLoadCallbackListener = this
        bestDealCallbackListener = this
    }
}