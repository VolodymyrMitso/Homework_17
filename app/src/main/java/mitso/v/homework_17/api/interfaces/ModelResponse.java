package mitso.v.homework_17.api.interfaces;

import org.json.JSONException;

import java.text.ParseException;

public interface ModelResponse {

    void configure(Object object) throws JSONException, ParseException;
}
