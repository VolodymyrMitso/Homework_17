package mitso.v.homework_17.api.models.user;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;

import mitso.v.homework_17.api.ApiConstants;
import mitso.v.homework_17.api.Connect;
import mitso.v.homework_17.api.interfaces.GsonModel;
import mitso.v.homework_17.api.interfaces.ModelResponse;

public class User implements ModelResponse, Serializable, GsonModel {

    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();
        switch (parser) {
            case Connect.PARSER_JSON:
                JSONObject jsonObject = (JSONObject) object;

                if (jsonObject.has(ApiConstants.USER_ID_KEY) && !jsonObject.isNull(ApiConstants.USER_ID_KEY))
                    id = jsonObject.getInt(ApiConstants.USER_ID_KEY);

                if (jsonObject.has(ApiConstants.USER_NAME_KEY) && !jsonObject.isNull(ApiConstants.USER_NAME_KEY))
                    name = jsonObject.getString(ApiConstants.USER_NAME_KEY);

                if (jsonObject.has(ApiConstants.USER_USERNAME_KEY) && !jsonObject.isNull(ApiConstants.USER_USERNAME_KEY))
                    username = jsonObject.getString(ApiConstants.USER_USERNAME_KEY);

                if (jsonObject.has(ApiConstants.USER_EMAIL_KEY) && !jsonObject.isNull(ApiConstants.USER_EMAIL_KEY))
                    email = jsonObject.getString(ApiConstants.USER_EMAIL_KEY);

                if (jsonObject.has(ApiConstants.USER_ADDRESS_KEY) && !jsonObject.isNull(ApiConstants.USER_ADDRESS_KEY)) {
                    JSONObject jsonNestedObjectAddress = jsonObject.getJSONObject(ApiConstants.USER_ADDRESS_KEY);
                    address = new Address();

                    if (jsonNestedObjectAddress.has(ApiConstants.ADDRESS_STREET_KEY) && !jsonNestedObjectAddress.isNull(ApiConstants.ADDRESS_STREET_KEY))
                        address.setStreet(jsonNestedObjectAddress.getString(ApiConstants.ADDRESS_STREET_KEY));

                    if (jsonNestedObjectAddress.has(ApiConstants.ADDRESS_SUITE_KEY) && !jsonNestedObjectAddress.isNull(ApiConstants.ADDRESS_SUITE_KEY))
                        address.setSuite(jsonNestedObjectAddress.getString(ApiConstants.ADDRESS_SUITE_KEY));

                    if (jsonNestedObjectAddress.has(ApiConstants.ADDRESS_CITY_KEY) && !jsonNestedObjectAddress.isNull(ApiConstants.ADDRESS_CITY_KEY))
                        address.setCity(jsonNestedObjectAddress.getString(ApiConstants.ADDRESS_CITY_KEY));

                    if (jsonNestedObjectAddress.has(ApiConstants.ADDRESS_ZIPCODE_KEY) && !jsonNestedObjectAddress.isNull(ApiConstants.ADDRESS_ZIPCODE_KEY))
                        address.setZipcode(jsonNestedObjectAddress.getString(ApiConstants.ADDRESS_ZIPCODE_KEY));

                    if (jsonNestedObjectAddress.has(ApiConstants.ADDRESS_GEO_KEY) && !jsonNestedObjectAddress.isNull(ApiConstants.ADDRESS_GEO_KEY)) {
                        JSONObject jsonNestedObjectGeo = jsonNestedObjectAddress.getJSONObject(ApiConstants.ADDRESS_GEO_KEY);
                        Geo geo = new Geo();

                        if (jsonNestedObjectGeo.has(ApiConstants.GEO_LAT_KEY) && !jsonNestedObjectGeo.isNull(ApiConstants.GEO_LAT_KEY))
                            geo.setLat(jsonNestedObjectGeo.getDouble(ApiConstants.GEO_LAT_KEY));

                        if (jsonNestedObjectGeo.has(ApiConstants.GEO_LNG_KEY) && !jsonNestedObjectGeo.isNull(ApiConstants.GEO_LNG_KEY))
                            geo.setLng(jsonNestedObjectGeo.getDouble(ApiConstants.GEO_LNG_KEY));

                        address.setGeo(geo);
                    }
                }

                if (jsonObject.has(ApiConstants.USER_PHONE_KEY) && !jsonObject.isNull(ApiConstants.USER_PHONE_KEY))
                    phone = jsonObject.getString(ApiConstants.USER_PHONE_KEY);

                if (jsonObject.has(ApiConstants.USER_WEBSITE_KEY) && !jsonObject.isNull(ApiConstants.USER_WEBSITE_KEY))
                    website = jsonObject.getString(ApiConstants.USER_WEBSITE_KEY);

                if (jsonObject.has(ApiConstants.USER_COMPANY_KEY) && !jsonObject.isNull(ApiConstants.USER_COMPANY_KEY)) {
                    JSONObject jsonNestedObject = jsonObject.getJSONObject(ApiConstants.USER_COMPANY_KEY);
                    company = new Company();

                    if (jsonNestedObject.has(ApiConstants.COMPANY_NAME_KEY) && !jsonNestedObject.isNull(ApiConstants.COMPANY_NAME_KEY))
                        company.setName(jsonNestedObject.getString(ApiConstants.COMPANY_NAME_KEY));

                    if (jsonNestedObject.has(ApiConstants.COMPANY_CATCH_PHRASE_KEY) && !jsonNestedObject.isNull(ApiConstants.COMPANY_CATCH_PHRASE_KEY))
                        company.setCatchPhrase(jsonNestedObject.getString(ApiConstants.COMPANY_CATCH_PHRASE_KEY));

                    if (jsonNestedObject.has(ApiConstants.COMPANY_BS_KEY) && !jsonNestedObject.isNull(ApiConstants.COMPANY_BS_KEY))
                        company.setBs(jsonNestedObject.getString(ApiConstants.COMPANY_BS_KEY));
                }

                break;
        }
    }

    @Override
    public void updateByClass(Object object) {
        User user = (User) object;

        this.id = user.id;
        this.name = user.name;
        this.username = user.username;
        this.email = user.email;
        this.address = user.address;
        this.phone = user.phone;
        this.website = user.website;
        this.company = user.company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return  "----- USER INFO -----\n" +
                "----- id = " + id + "\n" +
                "----- name = " + name + "\n" +
                "----- username = " + username + "\n" +
                "----- email = " + email + "\n" +
                "----- address = " + address + "\n" +
                "----- phone = " + phone + "\n" +
                "----- website = " + website + "\n" +
                "----- company = " + company;
    }
}
