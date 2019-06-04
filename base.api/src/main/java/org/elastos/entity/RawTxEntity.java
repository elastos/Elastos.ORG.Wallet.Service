/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.entity;

/**
 * clark
 * <p>
 * 9/4/18
 */
public class RawTxEntity {
    private String method = "sendrawtransaction";
    private String data;
    private ChainType type;
    private String userAgent;
    private String friendChainShortName;

    public String getFriendChainShortName() {
        return friendChainShortName;
    }

    public void setFriendChainShortName(String friendChainShortName) {
        this.friendChainShortName = friendChainShortName;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getMethod() {
        return method;
    }

    public ChainType getType() {
        return type;
    }

    public void setType(ChainType type) {
        this.type = type;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
