package com.aboelela924.android.physiatry.model.dataModeling;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class User {
    @Exclude
    private String mId;
    private String email;
    private String firstName;
    private String lastName;
    private int type;
    private String profileImageUrl;

    public User(){

    }

    public User(String email) {
        this.email = email;
    }

    @Exclude
    public String getId() {
        return mId;
    }

    @Exclude
    public void setId(String id) {
        mId = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("firstName", firstName);
        result.put("lastName", lastName);
        result.put("type", type);

        return result;
    }

    @Exclude
    public static User fromMap(HashMap data) {
        User user = new User();
        user.setEmail(String.valueOf(data.get("email")));
        user.setFirstName(String.valueOf(data.get("firstName")));
        user.setLastName(String.valueOf(data.get("lastName")));
        user.setType(Integer.valueOf(String.valueOf(data.get("type"))));

        return user;
    }
}
