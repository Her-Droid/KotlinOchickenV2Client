package edmt.dev.kotlineatitv2client.Remote


import edmt.dev.kotlineatitv2client.Model.BraintreeToken
import edmt.dev.kotlineatitv2client.Model.BraintreeTransaction
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*
import java.util.*

interface ICloudFunctions {
    @GET("token")
    fun getToken(@HeaderMap headers:Map<String,String>): Observable<BraintreeToken>

    @POST
    @FormUrlEncoded
    fun submitPayment(
        @HeaderMap headers:Map<String,String>,
        @Field("amount") amount:Double,
                      @Field("payment_method_nonce") nonce:String): Observable<BraintreeTransaction>
}