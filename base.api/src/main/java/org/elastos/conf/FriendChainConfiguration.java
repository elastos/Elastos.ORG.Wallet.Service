/**
 * Copyright (c) 2017-2019 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * clark
 * <p>
 * 6/3/19
 */
@Component
@ConfigurationProperties("friendchain")
public class FriendChainConfiguration {

    private final Map<String, Object> config = new HashMap<>();

    public Map<String, Object> getConfig() {
        return config;
    }
}
