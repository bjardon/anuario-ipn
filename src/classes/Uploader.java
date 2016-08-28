package classes;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import java.io.File;
import java.net.URI;

/**
 * Created by root on 13/05/15.
 */
public class Uploader {

    public static void uploadFile(String source, String uid) {
        try {
            HttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());

            HttpPost httppost = new HttpPost(URI.create("http://localhost/projects/yearbook/upload.php"));

            File file = new File(source);

            MultipartEntity multipartEntity = new MultipartEntity();
            ContentBody contentFile = new FileBody(file);
            ContentBody userID = new StringBody(uid);
            multipartEntity.addPart("userfile", contentFile);
            multipartEntity.addPart("userID", userID);
            httppost.setEntity(multipartEntity);

            HttpResponse response = httpclient.execute(httppost);

            HttpEntity httpEntity = response.getEntity();
            String status = response.getStatusLine().toString();

            new Dialog().informationMessage("Resultado", "Respuesta del servidor:", status);

            if (status.contains("200") || status.contains("OK")) {
                new Dialog().errorMessage(Dialog.SuccessType.UPLOAD_SUCCESSFUL);
            } else {
                new Dialog().errorMessage(Dialog.ErrorType.UPLOAD_UNSUCCESSFUL);
            }

            if (httpEntity != null) {
                httpEntity.consumeContent();
            }

            httpclient.getConnectionManager().shutdown();
        } catch (Exception ex) {
            new Dialog().errorMessage(Dialog.ErrorType.UPLOAD_UNSUCCESSFUL);
        }
    }

    public static Boolean checkFile(String id) {
        Boolean r = false;
        try {
            HttpParams params = new BasicHttpParams();
            HttpClient httpclient = new DefaultHttpClient(params);

            HttpPost httpPost = new HttpPost(URI.create("http://bjardon.com/upload/img/" + id + ".jpg"));


        } catch (Exception e) {

        }
        return r;
    }

}
