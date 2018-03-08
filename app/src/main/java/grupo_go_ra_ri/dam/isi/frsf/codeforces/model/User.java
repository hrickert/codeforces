package grupo_go_ra_ri.dam.isi.frsf.codeforces.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class User {
    private String handle; 	                // Codeforces user handle.
    private String email; 	                // Shown only if user allowed to share his contact info.
    private String vkId; 	                // User id for VK social network. Shown only if user allowed to share his contact info.
    private String openId; 	                // Shown only if user allowed to share his contact info.
    private String firstName; 	            // Localized. Can be absent.
    private String lastName; 	            // Localized. Can be absent.
    private String country; 	            // Localized. Can be absent.
    private String city; 	                // Localized. Can be absent.
    private String organization; 	        // Localized. Can be absent.
    private Integer contribution; 	        // User contribution.
    private String rank; 	                // Localized.
    private Integer rating;
    private String maxRank; 	            // Localized.
    private Integer maxRating;
    private Date lastOnlineTimeSeconds; 	// Time, when user was last seen online, in unix format.
    private Date registrationTimeSeconds;// Time, when user was registered, in unix format.
    private Integer friendOfCount; 	        // Amount of users who have this user in friends.
    private String avatar; 	                // User's avatar URL.
    private String titlePhoto; 	            // User's title photo URL.
    private ArrayList<RatingChange> ratingChanges; //Los ratings del usuario

    public User() {}

    public User(String handle, String email, String vkId, String openId, String firstName,
                String lastName, String country, String city, String organization, Integer rating, String rank, String maxRank) {
            this.handle = handle;
            this.email = email;
            this.vkId = vkId;
            this.openId = openId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.country = country;
            this.city = city;
            this.organization = organization;
            this.rank = rank;
            this.maxRank = maxRank;
            this.rating = rating;
    }

    public User(JSONObject obj) throws JSONException {
        this.handle = obj.getString("handle");
        //this.email = obj.getString("email");
        this.rank = obj.getString("rank");
        this.rating = obj.getInt("rating");
        this.firstName = obj.getString("firstName");
        this.lastName = obj.getString("lastName");
        this.country = obj.getString("country");
        this.city = obj.getString("city");
        this.maxRank = obj.getString("maxRank");
        this.rating = obj.getInt("rating");
        this.friendOfCount = obj.getInt("friendOfCount");
        this.lastOnlineTimeSeconds = new Date(obj.getLong("lastOnlineTimeSeconds") * 1000);
        this.registrationTimeSeconds = new Date(obj.getLong("registrationTimeSeconds") * 1000);
        this.avatar = obj.getString("avatar");

    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVkId() {
        return vkId;
    }

    public void setVkId(String vkId) {
        this.vkId = vkId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getContribution() {
        return contribution;
    }

    public void setContribution(Integer contribution) {
        this.contribution = contribution;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getMaxRank() {
        return maxRank;
    }

    public void setMaxRank(String maxRank) {
        this.maxRank = maxRank;
    }

    public Integer getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(Integer maxRating) {
        this.maxRating = maxRating;
    }

    public Date getLastOnlineTimeSeconds() {
        return lastOnlineTimeSeconds;
    }

    public void setLastOnlineTimeSeconds(Date lastOnlineTimeSeconds) {
        this.lastOnlineTimeSeconds = lastOnlineTimeSeconds;
    }

    public Date getRegistrationTimeSeconds() {
        return registrationTimeSeconds;
    }

    public void setRegistrationTimeSeconds(Date registrationTimeSeconds) {
        this.registrationTimeSeconds = registrationTimeSeconds;
    }

    public Integer getFriendOfCount() {
        return friendOfCount;
    }

    public void setFriendOfCount(Integer friendOfCount) {
        this.friendOfCount = friendOfCount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitlePhoto() {
        return titlePhoto;
    }

    public void setTitlePhoto(String titlePhoto) {
        this.titlePhoto = titlePhoto;
    }

    public ArrayList<RatingChange> getRatingChanges() {
        return ratingChanges;
    }

    public void setRatingChanges(ArrayList<RatingChange> ratingChanges) {
        this.ratingChanges = ratingChanges;
    }

    public void setRatingChanges(JSONArray jsonArray) throws JSONException {
        this.ratingChanges = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject row = jsonArray.getJSONObject(i);
            this.ratingChanges.add(new RatingChange(row));
        }
        Collections.sort(this.ratingChanges, new Comparator<RatingChange>() {
            @Override
            public int compare(RatingChange t1, RatingChange t2) {
                return t1.getRatingUpdateTimeSeconds() < t2.getRatingUpdateTimeSeconds()?-1:1;
            }
        });
    }
}
