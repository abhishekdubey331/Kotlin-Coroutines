package com.coroutines.app.repositories

import com.coroutines.app.data.model.Result
import com.coroutines.app.utils.Utils
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Response
import java.io.IOException


open class BaseRepository : KoinComponent {

    private val utils: Utils by inject()

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): T? {

        val result: Result<T> = safeApiResult(call)
        var data: T? = null
        when (result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Logger.d(result.exception)
            }
        }
        return data
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>
    ): Result<T> {
        val response = call.invoke()
        return if (response.isSuccessful) Result.Success(response.body()!!)
        else {
            val error: ResponseBody = response.errorBody()!!
            val message = utils.getErrorMessage(error)
            coroutineScope {
                launch(Dispatchers.Main) {
                    utils.showMessage(message)
                }
            }
            Result.Error(IOException(message))
        }
    }

}
