package mitso.v.homework_17.api;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

import mitso.v.homework_17.api.interfaces.ConnectCallback;
import mitso.v.homework_17.api.interfaces.ModelResponse;

public class Connect {

    public static final int PARSER_GSON = 8;
    public static final int PARSER_JSON = 9;
    private static final String LOG_TAG = Connect.class.getName();

    private static Connect _instance;
    private AsyncHttpClient client;
    private int mParser = PARSER_JSON;

    private Connect (){}

    public static Connect getInstance() {

        if (_instance == null) {
            _instance = new Connect();
            _instance.client = new AsyncHttpClient();
        }

        return _instance;
    }

    public void getRequestWithParam(String url, RequestParams requestParams,final ModelResponse modelResponse , final ConnectCallback callback) {

        Log.d(LOG_TAG, ApiConstants.URL_SERVER + url);

        client.get(ApiConstants.URL_SERVER+url,requestParams,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        parseData(response, modelResponse, callback);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        parseData(response, modelResponse, callback);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        callback.onFailure(throwable,errorResponse.toString());
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        callback.onFailure(throwable, errorResponse.toString());
                    }

                }

        );
    }

    private void parseData(Object jsonObject, ModelResponse modelObject, ConnectCallback callback) {
        if (null != modelObject) {
            try {

                modelObject.configure(jsonObject);
                callback.onSuccess(modelObject);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }



    public int getParser() {
        return mParser;
    }


    public void getRequest(String url,final ModelResponse modelResponse , final ConnectCallback callback) {
        getRequestWithParam(url, null, modelResponse, callback);
    }
}