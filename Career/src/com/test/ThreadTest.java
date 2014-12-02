package com.test;

public class ThreadTest
{
	public static void batchRun(String threadname)
	{
	       long start = System.currentTimeMillis();
	       System.out.println(threadname + "_开始时间:" + start);
	        List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
	        String baseUrl = "https://billionhealth.com:443/smart/mobile/";
	        // String baseUrl = "https://192.168.0.80:8443/smart/mobile/";
	        params.add(new BasicNameValuePair("actionType", "Cure2"));
	        params.add(new BasicNameValuePair("actionCode", "getCureTemplatesCache")); // getCureTemplatesCache|| getCureTemplates
	        
	        params.add(new BasicNameValuePair("templateType", "5"));
	        
//	        try
//            {
//                params.add(new BasicNameValuePair("hospitalName", new String("山西儿童医院".getBytes("utf-8"), "iso8859-1")));
//            }
//            catch (UnsupportedEncodingException e)
//            {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }


	        params.add(new BasicNameValuePair("userId", "tom.tao@billionhealth.com"));
	        params.add(new BasicNameValuePair("userPwd", "123456"));
	        test222(baseUrl, params);
	        System.out.println(threadname + "_耗时：" + (System.currentTimeMillis() - start));

	}
}
