/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.controller;

import com.alibaba.fastjson.JSON;
import org.elastos.entity.HdTxEntity;
import org.elastos.entity.RawTxEntity;
import org.elastos.service.ElaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 *
 * clark
 *
 * 8/31/18
 *
 * hard wallet related service
 */
@RestController
public class ElaOfflineController extends BaseController{
    @Autowired
    private ElaService elaService;

    /**
     * request  : {
     *     "inputs"  : ["xxx","yyy"],
     *     "outputs" : [{
     *         "addr":"zzz",
     *         "amt" :200
     *     },{
     *         "addr":"www",
     *         "amt":240
     *     }]
     * }
     *
     * response : {"result":{
     *              "Transactions":[
     *                 {
     *                     "UTXOInputs":[
     *                         {
     *                             "txid":"61c22a83bb96d958f473148fa64f3b2be02653c66ede506e83b82e522200d446",
     *                             "index":0,
     *                             "address":"xxx"
     *                         },
     *                         {
     *                             "txid":"a91b63ba6ffdb13379451895c51abd25c54678bc89268db6e6c3dcbb7bb07062",
     *                             "index":0,
     *                             "address":"yyy"
     *                         }
     *                     ],
     *                     "Outputs":[
     *                         {
     *                             "address":"zzz",
     *                             "amount":200
     *                         },
     *                         {
     *                             "address":"wwww",
     *                             "amount":240
     *                         }
     *                     ]
     *                     "Fee":100
     *                 }
     *             ]
     *         },"status":200}
     * @param reqBody
     * @return
     */
    @RequestMapping(value = "/createTx",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String genTxData(@RequestAttribute String reqBody ){
        return call(reqBody,HdTxEntity.class,"genHdTx",elaService);
    }

    @RequestMapping(value = "/{friendChainShortName}/createTx",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String genFriendChainTxData(@RequestAttribute String reqBody ,@PathVariable("friendChainShortName") String friendChainShortName){
        Map<String,Object> data = (Map<String, Object>) JSON.parse(reqBody);
        data.put("friendChainShortName",friendChainShortName);
        String ret = call(JSON.toJSONString(data),HdTxEntity.class,"genFriendChainHdTx",elaService);
        return ret;
    }

    /**
     * request  : {
     *     "inputs"  : ["xxx","yyy"],
     *     "outputs" : [{
     *         "addr":"zzz",
     *         "amt" :200
     *     },{
     *         "addr":"www",
     *         "amt":240
     *     }]
     * }
     *
     * response : {"result":{
     *              "Transactions":[
     *                 {
     *                     "UTXOInputs":[
     *                         {
     *                             "txid":"61c22a83bb96d958f473148fa64f3b2be02653c66ede506e83b82e522200d446",
     *                             "index":0,
     *                             "address":"xxx"
     *                         },
     *                         {
     *                             "txid":"a91b63ba6ffdb13379451895c51abd25c54678bc89268db6e6c3dcbb7bb07062",
     *                             "index":0,
     *                             "address":"yyy"
     *                         }
     *                     ],
     *                     "Outputs":[
     *                         {
     *                             "address":"zzz",
     *                             "amount":200
     *                         },
     *                         {
     *                             "address":"wwww",
     *                             "amount":240
     *                         }
     *                     ]
     *                     "Fee":100
     *                 }
     *             ]
     *         },"status":200}
     * @param reqBody
     * @return
     */
    @RequestMapping(value = "/createVoteTx",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String genVoteTxData(@RequestAttribute String reqBody){
        return call(reqBody, HdTxEntity.class,"genVoteHdTx",elaService);
    }

    /**
     * request : {
     *              "data":"0800012245627855313854334D3975666E726B5259374E4C7436734B79636B4457345641734100805CD70500000000010012383539333939363733363635343039303730014EDE26A8A2AFD83891D243D05354E62236BFC07EEB731553F23887A4604BF0E301000000000001B037DB964A231458D2D6FFD5EA18944C4F90E63D547C5D3B9874DF66A4EAD0A3C09EE60500000000000000004B5929CBD09401EB2CE4134CB5EE117A01152C387E0000000001414039407BAA6830E5227C85D49942E7AA15083F51EB32F0CF02E434FF0FDACA9C61F71CF9CA6A4B810EE433813A99BE78D5E7E23C11B2B302113444017277C1A2372321021421976FDBE518CA4E8B91A37F1831EE31E7B4BA62A32DFE2F6562EFD57806ADAC"
     *           }
     * @param reqBody
     * @return
     */
    @RequestMapping(value = "/sendRawTx",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String sendRawTx(@RequestAttribute String reqBody){
        return call(reqBody, RawTxEntity.class,"sendRawTx",elaService);
    }

    @RequestMapping(value = "/{friendChainShortName}/sendRawTx",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String sendFriendChainRawTx(@RequestAttribute String reqBody,@PathVariable("friendChainShortName") String friendChainShortName){
        Map<String,Object> data = (Map<String, Object>) JSON.parse(reqBody);
        data.put("friendChainShortName",friendChainShortName);
        return call(JSON.toJSONString(data), RawTxEntity.class,"sendFriendChainRawTx",elaService);
    }

    /**
     * request  : {
     *     "inputs"  : ["xxx","yyy"],
     *     "outputs" : [{
     *         "addr":"zzz",
     *         "amt" :200
     *     },{
     *         "addr":"www",
     *         "amt":240
     *     }]
     * }
     *
     * response :
     * {
     *     "result": {
     *         "Transactions": [
     *             {
     *                 "UTXOInputs": [
     *                     {
     *                         "address": "EbxU18T3M9ufnrkRY7NLt6sKyckDW4VAsA",
     *                         "txid": "76943c5a9999c2eac3dbfbbf543154c7687606fedce54e462af36f7dbc7f6245",
     *                         "index": 1
     *                     }
     *                 ],
     *                 "Fee": 10000,
     *                 "Outputs": [
     *                     {
     *                         "address":"XKUh4GLhFJiqAMTF6HyWQrV9pK9HcGUdfJ",
     *                         "amount":100000
     *                     },
     *                     {
     *                       "address":"EbxU18T3M9ufnrkRY7NLt6sKyckDW4VAsA",
     *                       "amount":34329900
     *                     }
     *                 ],
     *                 "CrossChainAsset":[
     *                    {
     *                        "address":"EQSpUzE4XYJhBSx5j7Tf2cteaKdFdixfVB",
     *                        "amount":90000
     *                    }
     *                 ]
     *             }
     *         ]
     *     },
     *     "status": 200
     * }
     * @param reqBody
     * @return
     */
    @RequestMapping(value = "/createCrossTx",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String genCrossTxData(@RequestAttribute String reqBody ){
        return call(reqBody,HdTxEntity.class,"genCrossHdTx",elaService);
    }

}
