package com.puyuntech.flowerToHome.plugin.unionpayPayment;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;








import com.puyuntech.flowerToHome.Setting;
import com.puyuntech.flowerToHome.entity.PaymentLog;
import com.puyuntech.flowerToHome.entity.PluginConfig;
import com.puyuntech.flowerToHome.plugin.PaymentPlugin;
import com.puyuntech.flowerToHome.util.SystemUtils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Component;


/**
 *  Plugin - 银联在线支付
 *
 *   @author shi.changcheng
 */
@Component("unionpayPaymentPlugin")
public class UnionpayPaymentPlugin extends PaymentPlugin {

	/** 货币 */
	private static final String CURRENCY = "156";

	@Override
	public String getName() {
		return "银联在线支付";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getAuthor() {
		return null;
	}

	@Override
	public String getSiteUrl() {
        return null;
	}

	@Override
	public String getInstallUrl() {
        return null;
	}

	@Override
	public String getUninstallUrl() {
        return null;
	}

	@Override
	public String getSettingUrl() {
        return null;
	}

	@Override
	public String getRequestUrl() {
		return "https://gateway.95516.com/gateway/api/frontTransReq.do";
	}

	@Override
	public RequestMethod getRequestMethod() {
		return RequestMethod.post;
	}

	@Override
	public String getRequestCharset() {
		return "UTF-8";
	}

	@Override
	public Map<String, Object> getParameterMap(String sn, String description, HttpServletRequest request) {
		Setting setting = SystemUtils.getSetting();
		PluginConfig pluginConfig = getPluginConfig();
		PaymentLog paymentLog = getPaymentLog(sn);
		Map<String, Object> requestData = new HashMap<String, Object>();


        /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
        requestData.put("version", SDKUtil.version);   			  //版本号，全渠道默认值
        requestData.put("encoding", SDKUtil.encoding_UTF8); 			  //字符集编码，可以使用UTF-8,GBK两种方式
        requestData.put("signMethod", "01");            			  //签名方法，只支持 01：RSA方式证书加密
        requestData.put("txnType", "01");               			  //交易类型 ，01：消费
        requestData.put("txnSubType", "01");            			  //交易子类型， 01：自助消费
        requestData.put("bizType", "000201");           			  //业务类型，B2C网关支付，手机wap支付
        requestData.put("channelType", "07");           			  //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机

        /***商户接入参数***/
//        TODO  merId 先写死
        requestData.put("merId", "898320957220378");    	          			  //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
        requestData.put("accessType", "0");             			  //接入类型，0：直连商户
        requestData.put("orderId", sn );             //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
        requestData.put("txnTime", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") );        //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
        requestData.put("currencyCode", "156");         			  //交易币种（境内商户一般是156 人民币）
        requestData.put("txnAmt", paymentLog.getAmount().multiply(new BigDecimal(100)).setScale(0).toString() );             			      //交易金额，单位分，不要带小数点
        requestData.put("reqReserved", "订单号："+paymentLog.getOrder());        		      //请求方保留域，透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节

        requestData.put("frontUrl",  getNotifyUrl(PaymentPlugin.NotifyMethod.sync) );

        requestData.put("backUrl",  getNotifyUrl(PaymentPlugin.NotifyMethod.async) );


        // 设置签名证书序列号
        String certId = CertUtil.getSignCertId();
        requestData.put(SDKConstants.param_certId,certId);

        Map<String, Object> submitFromData = SDKUtil.signData(requestData,SDKUtil.encoding_UTF8);


		return submitFromData;
	}

	@Override
	public boolean verifyNotify(NotifyMethod notifyMethod, HttpServletRequest request) {
        LogUtil.writeLog("FrontRcvResponse前台接收报文返回开始");

        String encoding = request.getParameter(SDKConstants.param_encoding);

        Map<String, String> respParam = getAllRequestParam(request);

        Map<String, String> valideData = null;

        try {
            request.setCharacterEncoding("ISO-8859-1");
            // 打印请求报文
            System.out.println(new String(respParam.get("reqReserved").getBytes("ISO-8859-1"),"UTF-8"));

            LogUtil.printRequestLog(respParam);

            StringBuffer page = new StringBuffer();
            if (null != respParam && !respParam.isEmpty()) {
                Iterator<Map.Entry<String, String>> it = respParam.entrySet()
                        .iterator();
                valideData = new HashMap<String, String>(respParam.size());
                while (it.hasNext()) {
                    Map.Entry<String, String> e = it.next();
                    String key = (String) e.getKey();
                    String value = (String) e.getValue();
                    value = new String(value.getBytes("ISO-8859-1"), encoding);
                    page.append("<tr><td width=\"30%\" align=\"right\">" + key
                            + "(" + key + ")</td><td>" + value + "</td></tr>");
                    valideData.put(key, value);
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }


        if (!SDKUtil.validate(valideData, encoding)) {
            LogUtil.writeLog("验证签名结果[失败].");
            return false;
        } else {
            LogUtil.writeLog("验证签名结果[成功].");
            System.out.println(valideData.get("orderId")); //其他字段也可用类似方式获取
            return true;
        }
	}

	@Override
	public String getSn(HttpServletRequest request) {
		return request.getParameter("orderId");
	}

	@Override
	public String getNotifyMessage(NotifyMethod notifyMethod, HttpServletRequest request) {
        if (PaymentPlugin.NotifyMethod.async.equals(notifyMethod)) {
            return "success";
        }
        return null;
	}

    /**
     * 获取请求参数中所有的信息
     *
     * @param request
     * @return
     */
    public static Map<String, String> getAllRequestParam(
            final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                // 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                if (res.get(en) == null || "".equals(res.get(en))) {
                    // System.out.println("======为空的字段名===="+en);
                    res.remove(en);
                }
            }
        }
        return res;
    }

}