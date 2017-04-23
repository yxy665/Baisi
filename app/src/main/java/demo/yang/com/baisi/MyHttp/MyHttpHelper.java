package demo.yang.com.baisi.MyHttp;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import demo.yang.com.baisi.Base.CustomApplication;
import demo.yang.com.baisi.R;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yxy on 2017/3/16.
 * email:1084625746@qq.com
 */

public class MyHttpHelper {
    private static final String TAG = "MyHttpHelper  调试信息》》》》》";
    //基地址
    public static final String BASE_URL = "http://api.budejie.com/";
    //延时设置5秒
    private static final int DEFAULT_TIMEOUT = 5;
    //    retrofit对象
    private Retrofit retrofit;
    //构造方法私有
    private MyHttpHelper() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new UserAgentInterceptor(System.getProperty("http.agent")));
        httpClientBuilder.addInterceptor(new LogIntercepter());
//        httpClientBuilder.sslSocketFactory(getSslSocketFactory());//配置https证书
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }
    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final MyHttpHelper INSTANCE = new MyHttpHelper();
    }
    //获取单例
    public static MyHttpHelper getInstance(){
        return MyHttpHelper.SingletonHolder.INSTANCE;
    }
    public Retrofit getRetrofit(){
        if (retrofit==null){
            return null;
        }
        return retrofit;
    }

    /**
     * 用于显示http响应结果的拦截器，log日志可以在这里设置打印
     */
    private class LogIntercepter implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.e(TAG, "request:" + request.toString()+request.headers().toString());
            long t1 = System.nanoTime();
            okhttp3.Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();
            Log.e(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.e(TAG, "intercept: body is："+content );//拦截到响应body打印
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }

    /**
     * 拦截器，添加请求头
     */
    public final class UserAgentInterceptor implements Interceptor {
        private static final String USER_AGENT_HEADER_NAME = "User-Agent";
        private  String userAgentHeaderValue;

        public UserAgentInterceptor(String userAgentHeaderValue) {
            if (!TextUtils.isEmpty(userAgentHeaderValue)){
                this.userAgentHeaderValue = userAgentHeaderValue;
            }
        }
        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request originalRequest = chain.request();
            final Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader(USER_AGENT_HEADER_NAME)
                    .addHeader(USER_AGENT_HEADER_NAME, userAgentHeaderValue)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }

    /**
     * https证书配置
     */
    private SSLSocketFactory getSslSocketFactory()
    {
        try {

            CertificateFactory cf=CertificateFactory.getInstance("X.509");
            InputStream caInput = CustomApplication.getContext().getResources().openRawResource(R.raw.ca);
            Certificate ca;

            try {
                ca=cf.generateCertificate(caInput);
            }finally {
                caInput.close();
            }
            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager
            SSLContext s = SSLContext.getInstance("TLSv1", "AndroidOpenSSL");
            s.init(null, tmf.getTrustManagers(), null);

            return s.getSocketFactory();

        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
}
