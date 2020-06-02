/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.elastos.api.Basic;
import org.elastos.ela.Ela;
import org.elastos.ela.Util;
import org.elastos.elaweb.ElaController;
import org.elastos.entity.ReturnMsgEntity;
import org.elastos.entity.SignDataEntity;
import org.elastos.service.ElaService;
import org.elastos.util.HttpKit;
import org.elastos.util.JsonUtil;
import org.elastos.util.ela.ElaKit;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.Data;
import java.util.Map;

/**
 * clark
 * <p>
 * 9/20/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestElaService {

    @Autowired
    private ElaService elaService ;

    @Test
    public void test(){
        String result = "{\"Desc\":\"Success\",\"Error\":0,\"Result\":{\"Hash\":\"41c6a2b46bc0c839c13cda0b42bbecacaf6051bd9ee25e6e72220f6e6dc3cf00\",\"Height\":10,\"Transactions\":[\"4f8365ce16ad5c82fee6689ab8a569d907b1d2e25424efdb75a9a23e7b704d25\"]}}";
        ReturnMsgEntity.ELAReturnMsg retMsg = JsonUtil.jsonStr2Entity(result,ReturnMsgEntity.ELAReturnMsg.class);

        System.out.println(JSON.toJSONString(new ReturnMsgEntity().setResult(retMsg.getResult()).setStatus(111l)));

    }

    @Test
    public void test01(){

        String str = "{\"Hash\":\"41c6a2b46bc0c839c13cda0b42bbecacaf6051bd9ee25e6e72220f6e6dc3cf00\",\"Height\":10,\"Transactions\":[\"4f8365ce16ad5c82fee6689ab8a569d907b1d2e25424efdb75a9a23e7b704d25\"]}";

        Map<String,String> result = (Map<String,String>)JSON.parse(str);

        System.out.println(JSON.toJSONString(result));


    }

    @Test
    public void test02() throws Exception{
        JSONObject param = new JSONObject();
        String privKey = Ela.getPrivateKey();
        String did = Ela.getIdentityIDFromPrivate(privKey);
        System.out.println(did);
    }

    @Test
    public void test_sign()throws Exception{
        String msg = "我们都是中国人";
        String privateKey = "0D5D7566CA36BC05CFF8E3287C43977DCBB492990EA1822643656D85B3CB0226";
        SignDataEntity entity = new SignDataEntity();
        entity.setMsg(msg); entity.setPrivateKey(privateKey);
        System.out.println(elaService.sign(entity));
    }

    @Test
    public void test_verify(){
        String data = "{\"result\":{\"msg\":\"E68891E4BBACE983BDE698AFE4B8ADE59BBDE4BABA\",\"pub\":\"02C3F59F337814C6715BBE684EC525B9A3CFCE55D9DEEC53E1EDDB0B352DBB4A54\",\"sig\":\"543EC5E2DC93308A5D4D76B38584D2F807E57389F4E563BA04571F64759766DDC0C0A547748A41A20DBF71FA7DF177D203EADEC0956FB45AC286895837369CAB\"},\"status\":200}";
        SignDataEntity entity = new SignDataEntity();
        entity.setPub("0257b0a7a0b536d9cdb8ba748accd560dbc1b9e2fb77a7983329f2d0563f7fa144");
        entity.setMsg("7b225472616e73616374696f6e73223a5b7b22466565223a3130302c224f757470757473223a5b7b2261646472657373223a22454e3841397848554e434a39584574615646576138787372786577483838664d5566222c22616d6f756e74223a343730307d2c7b2261646472657373223a2245525a59436d63643132637441666469544d65754c72536448644e587a5950316b67222c22616d6f756e74223a323030303030303030307d2c7b2261646472657373223a2245525a59436d63643132637441666469544d65754c72536448644e587a5950316b67222c22616d6f756e74223a32303030303031303030307d2c7b2261646472657373223a224559483639725241664451324852613335626d59526836556f415a3875336e375a4a222c22616d6f756e74223a313035333838333230333934367d5d2c225554584f496e70757473223a5b7b2261646472657373223a224559483639725241664451324852613335626d59526836556f415a3875336e375a4a222c22696e646578223a302c2274786964223a2236373532616132346234303663306138306631343633393838313461646639636333643561653031383037346534663364646533363365323764386263633166227d2c7b2261646472657373223a224559483639725241664451324852613335626d59526836556f415a3875336e375a4a222c22696e646578223a312c2274786964223a2239343630626634363062366238363939636231633136373732323935656265383862313037306361663932616561626539336662346139373939643235356164227d5d7d5d7d");
        entity.setSig("2a0ed9fbb93aede771b76c881284ae3e1e6d7523199f52580d3d037b38b52f7b590c307391ad76c3706c15acbd5b442a699c270f503f44c0c901511bedc4f7d5");
        System.out.println(elaService.verify(entity));
    }

    @Test
    public void testMemo() throws Exception{
        String data = "617366e6b58be8af95";
        System.out.println(new String(DatatypeConverter.parseHexBinary(data),"utf-8"));
    }

    @Test
    public void testCodeToAddr(){
        String hexStr = "21036f9a2f47ce0ee6472c97eeec96407c70db6a3727e5427c45468693a034b21e92ac";
        byte[] program = DatatypeConverter.parseHexBinary(hexStr);
        System.out.println(Util.ToAddress(Util.ToCodeHash(program,1)));
    }

    @Test
    public void testCal(){
    }

    @Test
    public void testWallets(){
        String privKey = "93045a06a0cee2e28eb4715501644486b710485612800ab3e9dc5c143e3fc49e";
        String publicKey = Ela.getPublicFromPrivate(privKey);
        String addresss = Ela.getAddressFromPrivate(privKey);
        String did = Ela.getIdentityIDFromPrivate(privKey);
        System.out.println(addresss + "  " + did + " " + publicKey);
    }

    @Test
    public void testCheckAddr(){
        System.out.println(ElaKit.checkAddress("EHLhCEbwViWBPwh1VhpECzYEA7jQHZ4zLv"));
    }


}
