package com.nroad.amcc.Sms;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SmsClient {

    private static final String SMS_URL = "http://gw.api.taobao.com/router/rest";
    private static final String SMS_APPKEY = "23416522";
    private static final String SMS_SECRET = "c36a9eb8d991476f7fb8599523b9da28";

    private static final TaobaoClient client = new DefaultTaobaoClient(SMS_URL, SMS_APPKEY, SMS_SECRET);

    // 模板ID:SMS_75805062
    // 模板内容:${name}同学，根据你的高考情况，我们找到了适合你的专业。速戳https://cc.wenet.com.cn/r/${code}查看。
    public static boolean sendSms(String name, String mobile, String code) {
        boolean result = false;
        try {
            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
            req.setExtend("");
            req.setSmsType("normal");
            req.setSmsFreeSignName("WENET平台");
            req.setRecNum(mobile);
            req.setSmsTemplateCode("SMS_172740567");
            req.setSmsParamString("{name:'" + name + "',code:'" + code + "'}");

            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            System.out.println(rsp.getBody());
            if (rsp.getBody().contains("\"success\":true")) {
                result = true;
            }
        } finally {
            return result;
        }
    }
}