package mitso.v.homework_17.api.response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

import mitso.v.homework_17.api.Connect;
import mitso.v.homework_17.api.interfaces.ModelResponse;
import mitso.v.homework_17.api.models.User;

public class UserListResponse  implements ModelResponse {
    private ArrayList<User> users;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        JSONArray results = (JSONArray) object;
        switch (parser) {
            case Connect.PARSER_JSON:
                users = new ArrayList<>();
                for (int i = 0; i < results.length(); i++) {
                    User user = new User();
                    user.configure(results.getJSONObject(i));
                    users.add(user);
                }
                break;


            case Connect.PARSER_GSON:
                Gson gson = new Gson();
                users = gson.fromJson(results.toString(), new TypeToken<ArrayList<User>>(){}.getType());
                break;
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
