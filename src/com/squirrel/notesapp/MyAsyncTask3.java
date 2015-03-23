package com.squirrel.notesapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class MyAsyncTask3 extends AsyncTask<Void, Void, String> {

    public interface OnWorkDone {
        public void handleResponse(String resp);
    }

    private availablecoursesFragment listener;

    public void setListener(availablecoursesFragment l) {
        this.listener = l;
    }

    @Override
    protected String doInBackground(Void... params) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://notesapp.org/student/follow");

        List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
        pairs.add(new BasicNameValuePair("studentId", LoginObject.ID));
        pairs.add(new BasicNameValuePair("courseId", CourseObject.CourseId));
        String result = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(pairs));

            HttpResponse response = client.execute(post);
            result = EntityUtils.toString(response.getEntity());

        } catch (UnsupportedEncodingException e) {
            //Should never be thrown
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (listener != null) {
            listener.handleResp(result);
        }
    }
}
