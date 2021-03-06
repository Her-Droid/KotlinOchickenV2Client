package edmt.dev.kotlineatitv2client.ui.view_orders

import android.app.AlertDialog
import android.os.Bundle
import android.renderscript.Sampler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.Unbinder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dmax.dialog.SpotsDialog
import edmt.dev.kotlineatitv2client.Adapter.MyOrderAdapter
import edmt.dev.kotlineatitv2client.Callback.ILoadOrderCallbackListener
import edmt.dev.kotlineatitv2client.Common.Common
import edmt.dev.kotlineatitv2client.Model.Order
import edmt.dev.kotlineatitv2client.R
import java.util.*
import kotlin.collections.ArrayList

class ViewOrderFragment : Fragment(), ILoadOrderCallbackListener {

    private var viewOrderModel : ViewOrderModel?=null

    private var unbinder: Unbinder?=null

    internal lateinit var dialog:AlertDialog

    internal lateinit var recycler_order:RecyclerView

    internal lateinit var listener:ILoadOrderCallbackListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewOrderModel = ViewModelProviders.of(this).get(ViewOrderModel::class.java!!)
        val root = inflater.inflate(R.layout.fragment_view_order,container,false)
        initViews(root)
        loadOrderFromFirebase()

        viewOrderModel!!.mutableLiveDataOrderList.observe(this, Observer{
            Collections.reverse(it!!)
            val adapter = MyOrderAdapter(context!!,it!!)
            recycler_order!!.adapter = adapter

        })
        return root
    }

    private fun loadOrderFromFirebase() {
        dialog.show()
        val orderList = ArrayList<Order>()

        FirebaseDatabase.getInstance().getReference(Common.ORDER_REF)
            .orderByChild("userId")
            .equalTo(Common.currentUser!!.uid!!)
            .limitToLast(100)
            .addListenerForSingleValueEvent(object:ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    listener.onLoadOrderFailed(p0.message!!)
                }

                override fun onDataChange(p0: DataSnapshot) {
                    for (orderSnapshot in p0.children)
                    {
                        val order = orderSnapshot.getValue(Order::class.java)
                        order!!.orderNumber = orderSnapshot.key
                        orderList.add(order!!)
                    }
                    listener.onLoadOrderSuccess(orderList)
                }

            })
    }

    private fun initViews(root: View?) {

        listener = this

        dialog = SpotsDialog.Builder().setContext(context!!).setCancelable(false).build()

        recycler_order = root!!.findViewById(R.id.recycler_order) as RecyclerView
        recycler_order.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context!!)
        recycler_order.layoutManager = layoutManager
        recycler_order.addItemDecoration(DividerItemDecoration(context!!,layoutManager.orientation))
    }

    override fun onLoadOrderSuccess(orderList: List<Order>) {
        // We need implement it
        dialog.dismiss()
        viewOrderModel!!.setMutableLiveDataOrderList(orderList)
    }

    override fun onLoadOrderFailed(message: String) {
        dialog.dismiss()
        Toast.makeText(context!!,message,Toast.LENGTH_SHORT).show()
    }
}