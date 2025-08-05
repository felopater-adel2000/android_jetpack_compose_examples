package com.restart.jetpack_compose_examples.list

/*
abstract class BaseApiDispatcher {

    abstract val path: String

    open val statusCode = 200

    open val bodyMockFile: String? = null
    open val errorMockFile: String? = null

    open val apiDelayMillis = 500L

    open fun canDispatch(request: RecordedRequest): Boolean =
        request.path.orEmpty().contains(path, ignoreCase = true)

    fun dispatch(request: RecordedRequest): MockResponse {
        if (statusCode in 200..299) {
            return mockResponse(fileName = bodyMockFile)
        }

        return mockResponse(fileName = errorMockFile)
    }

    protected fun mockResponse(fileName: String?): MockResponse {
        val body = try {
            readAssetFileToString("mocks/$fileName")
        } catch (th: Throwable) {
            th.printStackTrace()
            null
        }

        return MockResponse()
            .setResponseCode(statusCode)
            .setBodyDelay(apiDelayMillis, TimeUnit.MILLISECONDS)
            .also { response ->
                body?.let {
                    response.setBody(it)
                }
            }

    }
}*/
