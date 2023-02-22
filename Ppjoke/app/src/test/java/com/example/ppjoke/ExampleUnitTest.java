package com.example.ppjoke;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.libnetwork.ApiResponse;
import com.example.libnetwork.GetRequest;
import com.example.libnetwork.JsonCallback;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void TestHttp() {
        GetRequest<JSONObject> request = new GetRequest<JSONObject>("http://www.baidu.com");
//        request.execute();

        request.execute(new JsonCallback<JSONObject>() {
            @Override
            public void onSuccess(ApiResponse <JSONObject>response) {
                super.onSuccess(response);
            }

            @Override
            public void onError(ApiResponse<JSONObject> response) {
                super.onError(response);
            }

            @Override
            public void onCacheSuccess(ApiResponse<JSONObject> response) {
                super.onCacheSuccess(response);
            }
        });
    }

}