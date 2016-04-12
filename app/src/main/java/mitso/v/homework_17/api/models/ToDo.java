package mitso.v.homework_17.api.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;

import mitso.v.homework_17.api.ApiConstants;
import mitso.v.homework_17.api.Connect;
import mitso.v.homework_17.api.interfaces.GsonModel;
import mitso.v.homework_17.api.interfaces.ModelResponse;

public class Todo implements ModelResponse, Serializable, GsonModel {

    private int userId;
    private int id;
    private String title;
    private boolean completed;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();
        switch (parser) {
            case Connect.PARSER_JSON:
                JSONObject jsonObject = (JSONObject) object;

                if (jsonObject.has(ApiConstants.TODO_USER_ID_KEY) && !jsonObject.isNull(ApiConstants.TODO_USER_ID_KEY))
                    userId = jsonObject.getInt(ApiConstants.TODO_USER_ID_KEY);

                if (jsonObject.has(ApiConstants.TODO_ID_KEY) && !jsonObject.isNull(ApiConstants.TODO_ID_KEY))
                    id = jsonObject.getInt(ApiConstants.TODO_ID_KEY);

                if (jsonObject.has(ApiConstants.TODO_TITLE_KEY) && !jsonObject.isNull(ApiConstants.TODO_TITLE_KEY))
                    title = jsonObject.getString(ApiConstants.TODO_TITLE_KEY);

                if (jsonObject.has(ApiConstants.TODO_COMPLETED_KEY) && !jsonObject.isNull(ApiConstants.TODO_COMPLETED_KEY))
                    completed = jsonObject.getBoolean(ApiConstants.TODO_COMPLETED_KEY);
        }
    }

    @Override
    public void updateByClass(Object object) {
        Todo todo = (Todo) object;

        this.userId = todo.userId;
        this.id = todo.id;
        this.title = todo.title;
        this.completed = todo.completed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return  "----- TODO INFO -----\n" +
                "----- userId = " + userId + "\n" +
                "----- id = " + id + "\n" +
                "----- title = " + title + "\n" +
                "----- completed = " + completed;
    }
}
