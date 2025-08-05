package com.restart.jetpack_compose_examples.list

import org.junit.rules.ExternalResource

class MockWebServerRule : ExternalResource() {

    /*private lateinit var okHttp3IdlingResource: OkHttp3IdlingResource

    private val mockWebServer: MockWebServer = MockWebServer()

    override fun before() {
        val localhostCertificate =
            HeldCertificate.decode(readAssetFileToString("instrumentation_cert.pem"))

        val serverCertificates = HandshakeCertificates.Builder()
            .heldCertificate(localhostCertificate)
            .build()

        val clientCertificates = HandshakeCertificates.Builder()
            .addTrustedCertificate(localhostCertificate.certificate)
            .build()

        mockWebServer.useHttps(
            serverCertificates.sslSocketFactory(),
            tunnelProxy = false
        )

        val okHttpClient = OkHttpClient.Builder()
            .sslSocketFactory(
                clientCertificates.sslSocketFactory(),
                clientCertificates.trustManager
            )
            .build()

        RetrofitFactory.maxabClient = okHttpClient

        okHttp3IdlingResource = OkHttp3IdlingResource.create("okhttp", okHttpClient)
        IdlingRegistry.getInstance().register(okHttp3IdlingResource)

        setMockWebServerDispatcher()

        mockWebServer.start(8080)
    }

    private fun setMockWebServerDispatcher() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                MockApiRegistry.apiDispatchers.forEach {
                    if (it.canDispatch(request)) {
                        return it.dispatch(request)
                    }
                }
                return MockResponse().setResponseCode(404)
                    .setBody("Not Found")
            }
        }
    }

    override fun after() {
        IdlingRegistry.getInstance().unregister(okHttp3IdlingResource)
        mockWebServer.shutdown()
    }*/
}