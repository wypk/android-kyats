package wyp.kyats.component.util;

import android.annotation.SuppressLint;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Wai Yan on 3/14/19.
 */
public class JSoupUtil {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
    private static final int TIME_OUT = 5 * 60 * 1000;

    public static Document getData(String url) {

        Document pageDocument = null;

        //Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @SuppressLint("TrustAllX509TrustManager")
            public void checkClientTrusted(java.security.cert.X509Certificate[] certs,
                    String authType) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            public void checkServerTrusted(java.security.cert.X509Certificate[] certs,
                    String authType) {
            }
        }};

        //Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            try {
                pageDocument = Jsoup.connect(url)
                        .userAgent(USER_AGENT)
                        .maxBodySize(0) //Unlimited
                        .timeout(TIME_OUT)
                        .ignoreHttpErrors(true)
                        .ignoreContentType(true)
                        .followRedirects(true)
                        .get();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return pageDocument;
    }
}
