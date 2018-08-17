package com.andy.database.coreelasticsearch

import io.searchbox.client.JestClient
import io.searchbox.client.JestClientFactory
import io.searchbox.client.config.HttpClientConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class JestClientUtil {

    @Autowired
    private lateinit var esClientUtil: EsClientConfiguration

    private var jestClient: JestClient? = null

    /**
     * describe: 进行创建 JestClient 操作 ElasticSearch 的读写相关
     * author 候帅
     * date 2018/8/17 下午11:22
     */
    fun createJestClient(): JestClient {
        if (jestClient == null){
            synchronized(this){
                if (jestClient == null){
                    val jestClientFactory = JestClientFactory()

                    val httpClientConfig = HttpClientConfig
                            .Builder(esClientUtil.url)
                            .defaultCredentials(esClientUtil.username, esClientUtil.password)
                            .multiThreaded(true)
                            .connTimeout(esClientUtil.connectionTimeout)
                            .readTimeout(esClientUtil.readTimeout)
                            .build()

                    jestClientFactory.setHttpClientConfig(httpClientConfig)

                    jestClient = jestClientFactory.`object`
                }
            }
        }
        return jestClient!!
    }

}