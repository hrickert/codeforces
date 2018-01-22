package grupo_go_ra_ri.dam.isi.frsf.codeforces.model;


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
    private Integer lastOnlineTimeSeconds; 	// Time, when user was last seen online, in unix format.
    private Integer registrationTimeSeconds;// Time, when user was registered, in unix format.
    private Integer friendOfCount; 	        // Amount of users who have this user in friends.
    private String avatar; 	                // User's avatar URL.
    private String titlePhoto; 	            // User's title photo URL.

    public User() {}

    public User(String handle) {
        this.handle = handle;
        this.email = null;
        this.vkId = null;
        this.openId = null;
        this.firstName = null;
        this.lastName = null;
        this.country = null;
        this.city = null;
        this.organization = null;
        this.contribution = null;
        this.rank = null;
        this.rating = null;
        this.maxRank = null;
        this.maxRating = null;
        this.lastOnlineTimeSeconds = null;
        this.registrationTimeSeconds = null;
        this.friendOfCount = null;
        this.avatar = null;
        this.titlePhoto = null;
    }

    public User(String handle, String email, String vkId, String openId, String firstName,
        String lastName, String country, String city, String organization, Integer contribution,
        String rank, Integer rating, String maxRank, Integer maxRating, Integer lastOnlineTimeSeconds,
        Integer registrationTimeSeconds, Integer friendOfCount, String avatar, String titlePhoto) {
            this.handle = handle;
            this.email = email;
            this.vkId = vkId;
            this.openId = openId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.country = country;
            this.city = city;
            this.organization = organization;
            this.contribution = contribution;
            this.rank = rank;
            this.rating = rating;
            this.maxRank = maxRank;
            this.maxRating = maxRating;
            this.lastOnlineTimeSeconds = lastOnlineTimeSeconds;
            this.registrationTimeSeconds = registrationTimeSeconds;
            this.friendOfCount = friendOfCount;
            this.avatar = avatar;
            this.titlePhoto = titlePhoto;
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

    public Integer getLastOnlineTimeSeconds() {
        return lastOnlineTimeSeconds;
    }

    public void setLastOnlineTimeSeconds(Integer lastOnlineTimeSeconds) {
        this.lastOnlineTimeSeconds = lastOnlineTimeSeconds;
    }

    public Integer getRegistrationTimeSeconds() {
        return registrationTimeSeconds;
    }

    public void setRegistrationTimeSeconds(Integer registrationTimeSeconds) {
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
}
