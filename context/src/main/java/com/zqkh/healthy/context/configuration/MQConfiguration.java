package com.zqkh.healthy.context.configuration;

import com.jovezhao.nest.amq.AMQChannelProvider;
import com.jovezhao.nest.amq.AMQProviderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @date 2017/12/4 0004 15:30
 */
@Configuration
public class MQConfiguration {

    @Autowired
    private CloudConfigProperties cloudConfigProperties;

    @Bean
    public AMQProviderConfig getAMQProvider() {
        AMQProviderConfig providerConfig = new AMQProviderConfig();
        providerConfig.setSecretId(cloudConfigProperties.getAmq().getSecretId());
        providerConfig.setSecretKey(cloudConfigProperties.getAmq().getSecretKey());
        providerConfig.setEndpoint(cloudConfigProperties.getAmq().getEndpoint());
        return providerConfig;
    }

    @Bean
    public AMQChannelProvider getAMQChannelProvider(AMQProviderConfig providerConfig) {
        AMQChannelProvider channelProvider = new AMQChannelProvider(providerConfig);
        return channelProvider;
    }





}
