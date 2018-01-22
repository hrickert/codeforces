package grupo_go_ra_ri.dam.isi.frsf.codeforces.model;


public class Contest {
    private Integer id;
    private String name;
    private enum  type { CF, IOI, ICPC}     //Scoring system used for the contest.
    private enum phase {BEFORE, CODING, PENDING_SYSTEM_TEST, SYSTEM_TEST, FINISHED}
    private boolean frozen;                 //If true, then the ranklist for the contest is frozen and shows only submissions, created before freeze.
    private Integer durationSeconds;        //Duration of the contest in seconds.
    private Integer startTimeSeconds;       //Can be absent. Contest start time in unix format.
    private Integer relativeTimeSeconds;    //Can be absent. Number of seconds, passed after the start of the contest. Can be negative.
    private String preparedBy;              //Can be absent. Handle of the user, how created the contest.
    private String websiteUrl;              //Can be absent. URL for contest-related website.
    private String description;             //Localized. Can be absent.
    private Integer difficulty;             //Can be absent. From 1 to 5. Larger number means more difficult problems.
    private String kind;                    /*Localized. Can be absent. Human-readable type of the contest from t
                                             he following categories: Official ACM-ICPC Contest, Official School Contest,
                                             Opencup Contest, School/University/City/Region Championship, Training Camp Contest,
                                             Official International Personal Contest, Training Contest.*/
    private String icpcRegion;              //Localized. Can be absent. Name of the ICPC Region for official ACM-ICPC contests.
    private String country;                 //Localized. Can be absent.
    private String city;                    //Localized. Can be absent.
    private String season;                  //Can be absent.


    public Contest(){}

    public Contest(Integer id, String name, boolean frozen, Integer durationSeconds,
                   Integer startTimeSeconds, Integer relativeTimeSeconds, String preparedBy,
                   String websiteUrl, String description, Integer difficulty, String kind,
                   String icpcRegion, String country, String city, String season) {
        this.id = id;
        this.name = name;
        this.frozen = frozen;
        this.durationSeconds = durationSeconds;
        this.startTimeSeconds = startTimeSeconds;
        this.relativeTimeSeconds = relativeTimeSeconds;
        this.preparedBy = preparedBy;
        this.websiteUrl = websiteUrl;
        this.description = description;
        this.difficulty = difficulty;
        this.kind = kind;
        this.icpcRegion = icpcRegion;
        this.country = country;
        this.city = city;
        this.season = season;
    }

    public Integer getId() {return id;}

    public String getName() {return name;}

    public boolean isFrozen() {return frozen;}

    public Integer getDurationSeconds() {return durationSeconds;}

    public Integer getStartTimeSeconds() {return startTimeSeconds;}

    public Integer getRelativeTimeSeconds() {return relativeTimeSeconds;}

    public String getPreparedBy() {return preparedBy;}

    public String getWebsiteUrl() {return websiteUrl;}

    public String getDescription() {return description;}

    public Integer getDifficulty() {return difficulty;}

    public String getKind() {return kind;}

    public String getIcpcRegion() {return icpcRegion;}

    public String getCountry() {return country;}

    public String getCity() {return city;}

    public String getSeason() {return season;}

    public void setId(Integer id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setFrozen(boolean frozen) {this.frozen = frozen;}

    public void setDurationSeconds(Integer durationSeconds) {this.durationSeconds = durationSeconds;}

    public void setStartTimeSeconds(Integer startTimeSeconds) {this.startTimeSeconds = startTimeSeconds;}

    public void setRelativeTimeSeconds(Integer relativeTimeSeconds) {this.relativeTimeSeconds = relativeTimeSeconds;}

    public void setPreparedBy(String preparedBy) {this.preparedBy = preparedBy;}

    public void setWebsiteUrl(String websiteUrl) {this.websiteUrl = websiteUrl;}

    public void setDescription(String description) {this.description = description;}

    public void setDifficulty(Integer difficulty) {this.difficulty = difficulty;}

    public void setKind(String kind) {this.kind = kind;}

    public void setIcpcRegion(String icpcRegion) {this.icpcRegion = icpcRegion;}

    public void setCountry(String country) {this.country = country;}

    public void setCity(String city) {this.city = city;}

    public void setSeason(String season) {this.season = season;}
}
