package grupo_go_ra_ri.dam.isi.frsf.codeforces.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by mramos on 01/03/18.
 */

public class RatingChange {
    private Integer contestId;
    private String contestName;
    private Integer rank;
    private Date ratingUpdateTime;
    private Long ratingUpdateTimeSeconds;
    private Integer oldRating;
    private Integer newRating;

    public RatingChange(Integer contestId, String contestName, Integer rank, Integer ratingUpdateTime, Integer oldRating, Integer newRating) {
        this.contestId = contestId;
        this.contestName = contestName;
        this.rank = rank;
        this.ratingUpdateTime = new Date(ratingUpdateTime * 1000);
        this.oldRating = oldRating;
        this.newRating = newRating;
    }

    public RatingChange(JSONObject row) throws JSONException {
        this.contestId = row.getInt("contestId");
        this.contestName = row.getString("contestName");
        this.rank = row.getInt("rank");
        this.ratingUpdateTimeSeconds = row.getLong("ratingUpdateTimeSeconds");
        this.ratingUpdateTime = new Date(row.getLong("ratingUpdateTimeSeconds") * 1000);
        this.oldRating = row.getInt("oldRating");
        this.newRating = row.getInt("newRating");
    }

    public Long getRatingUpdateTimeSeconds() {
        return ratingUpdateTimeSeconds;
    }

    public void setRatingUpdateTimeSeconds(Long ratingUpdateTimeSeconds) {
        this.ratingUpdateTimeSeconds = ratingUpdateTimeSeconds;
    }

    public Date getRatingUpdateTime() {
        return ratingUpdateTime;
    }

    public void setRatingUpdateTime(Date ratingUpdateTime) {
        this.ratingUpdateTime = ratingUpdateTime;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
