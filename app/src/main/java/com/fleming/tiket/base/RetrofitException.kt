package com.fleming.tiket.base

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class RetrofitException internal constructor(message: String?,
											 val url: String,
											 val response: Response<*>?,
											 val kind: Kind, exception: Throwable?,
											 val retrofit: Retrofit?) : RuntimeException(message, exception) {
	/** Identifies the event kind which triggered a [RetrofitException].  */
	enum class Kind {
		/** An [IOException] occurred while communicating to the server.  */
		NETWORK,
		/** A non-200 HTTP status code was received from the server.  */
		HTTP,
		/**
		 * An internal error occurred while attempting to execute a request. It is best practice to
		 * re-throw this exception so your application crashes.
		 */
		UNEXPECTED
	}

	/** The request URL which produced the error.  */
	/** Response object containing status code, headers, body, etc.  */
	/** The event kind which triggered this error.  */
	/** The Retrofit this request was executed on  */

	/**
	 * HTTP response body converted to specified `type`. `null` if there is no
	 * response.
	 *
	 * @throws IOException if unable to convert the body to the specified `type`.
	 */
	@Throws(IOException::class)
	fun <T> getErrorBodyAs(type: Class<T>?): T? {
		retrofit?.let {
			response?.errorBody()?.let { errorBody ->
				val converter: Converter<ResponseBody?, T> = retrofit.responseBodyConverter(type, arrayOfNulls(0))
				return converter.convert(errorBody)
			}
		}
		return null
	}

	companion object {
		@JvmStatic
		fun httpError(url: String, response: Response<*>, retrofit: Retrofit?): RetrofitException {
			val message = response.code().toString() + " " + response.message()
			return RetrofitException(message, url, response, Kind.HTTP, null, retrofit)
		}

		@JvmStatic
		fun networkError(exception: IOException): RetrofitException {
			return RetrofitException(exception.message, "", null, Kind.NETWORK, exception, null)
		}

		@JvmStatic
		fun unexpectedError(exception: Throwable): RetrofitException {
			return RetrofitException(exception.message, "", null, Kind.UNEXPECTED, exception, null)
		}
	}

}