/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.controller;

import com.alibaba.fastjson.JSON;
import org.elastos.entity.DidInfoEntity;
import org.elastos.entity.HdWalletEntity;
import org.elastos.entity.SignDataEntity;
import org.elastos.entity.TransferParamEntity;
import org.elastos.service.ElaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * clark
 * <p>
 * 9/20/18
 */
@RestController
public class ElaChainController extends BaseController{

    @Autowired
    private ElaService service;

    @RequestMapping(value = "/createWallet",method = RequestMethod.GET)
    @ResponseBody
    public String createWallet(){
        return call(null,null,"createWallet",service);
    }

    @RequestMapping(value = "/cn/mnemonic",method = RequestMethod.GET)
    @ResponseBody
    public String mnemonicCn(){
        return call("chinese",String.class,"mnemonic",service);
    }

    @RequestMapping(value = "/eng/mnemonic",method = RequestMethod.GET)
    @ResponseBody
    public String mnemonicEng(){
        return call("english",String.class,"mnemonic",service);
    }

    @RequestMapping(value = "/hd",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String genHdWallet(@RequestAttribute String reqBody){
        return call(reqBody,HdWalletEntity.class,"genHdWallet",service);
    }

    @RequestMapping(value = "/{friendChainShortName}/currHeight",method = RequestMethod.GET)
    @ResponseBody
    public String getFriendChainCurrentHeight(@PathVariable("friendChainShortName") String friendChainShortName){

        return call(friendChainShortName,String.class,"getFriendChainCurrentHeight",service);
    }

    @RequestMapping(value = "/currHeight",method = RequestMethod.GET)
    @ResponseBody
    public String getCurrentHeight(){

        return call(null,null,"getCurrentHeight",service);
    }

    @RequestMapping(value = "/tx/{txid}",method = RequestMethod.GET)
    @ResponseBody
    public String getTxByTxId(@PathVariable("txid") String txid){

        return call(txid,String.class,"getTxByTxId",service);
    }

    @RequestMapping(value = "/tx",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String getTxsByTxIds(@RequestAttribute String reqBody){

        return call(reqBody,List.class,"getTxsByTxIds",service);
    }

    @RequestMapping(value = "/txs/{height}",method = RequestMethod.GET)
    @ResponseBody
    public String getBlockTxsByHeight(@PathVariable("height") String height){

        return call( height ,String.class,"getBlockTxsByHeight",service);
    }

    @RequestMapping(value = "/block/height/{height}",method = RequestMethod.GET)
    @ResponseBody
    public String getBlockByHeight(@PathVariable("height") String height){

        return call( height ,String.class,"getBlockByHeight",service);
    }

    @RequestMapping(value = "/block/hash/{hash}",method = RequestMethod.GET)
    @ResponseBody
    public String getBlockByHash(@PathVariable("hash") String hash){

        return call( hash ,String.class,"getBlockByHash",service);
    }

    @RequestMapping(value = "/block/transaction/{hash}",method = RequestMethod.GET)
    @ResponseBody
    public String getTransactionByHash(@PathVariable("hash") String hash){

        return call( hash ,String.class,"getTransactionByHash",service);
    }

    @RequestMapping(value = "/balance/{address}",method = RequestMethod.GET)
    @ResponseBody
    public String getBalance(@PathVariable("address") String address){

        return call(address,String.class,"getBalance",service);
    }

    @RequestMapping(value = "/{friendChainShortName}/balance/{address}",method = RequestMethod.GET)
    @ResponseBody
    public String getFriendChainShortNameBalance(@PathVariable("address") String address,@PathVariable("friendChainShortName") String friendChainShortName){

        Map<String,Object> data = new HashMap<>();
        data.put("address",address);
        data.put("shortName",friendChainShortName);
        return call(JSON.toJSONString(data),Map.class,"getFriendChainShortNameBalance",service);
    }

    @RequestMapping(value = "/utxos/{address}",method = RequestMethod.GET)
    @ResponseBody
    public String getUtxos(@PathVariable("address") String address){

        return call( address ,String.class,"getUtxos",service);
    }

    @RequestMapping(value = "/sign",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String sign(@RequestAttribute String reqBody){

        return call(reqBody,SignDataEntity.class,"sign",service);
    }

    @RequestMapping(value = "/verify",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String verify(@RequestAttribute String reqBody){

        return call(reqBody,SignDataEntity.class,"verify",service);
    }

    @RequestMapping(value = "/transfer",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String transfer(@RequestAttribute String reqBody){

        return call(reqBody,TransferParamEntity.class,"transfer",service);
    }

    @RequestMapping(value = "/dpos/vote",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String dposVote(@RequestAttribute String reqBody){

        return call(reqBody,TransferParamEntity.class,"dposVote",service);
    }

    @RequestMapping(value = "/cross/m2d/transfer",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String m2dTransfer(@RequestAttribute String reqBody){

        return call(reqBody,TransferParamEntity.class,"main2DidCrossTransfer",service);
    }

    @RequestMapping(value = "/nodeNotify",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String nodeNotify(@RequestAttribute String reqBody){

        return "{\"SUCC\":200}";
    }
}
