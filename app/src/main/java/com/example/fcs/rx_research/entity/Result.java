
package com.example.fcs.rx_research.entity;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("contributor")
    @Expose
    private String contributor;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("contributor_note")
    @Expose
    private String contributorNote;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("age_group")
    @Expose
    private String ageGroup;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("isbns")
    @Expose
    private List<Isbn> isbns = new ArrayList<Isbn>();
    @SerializedName("ranks_history")
    @Expose
    private List<RanksHistory> ranksHistory = new ArrayList<RanksHistory>();
    @SerializedName("reviews")
    @Expose
    private List<Review> reviews = new ArrayList<Review>();

    /**
     *
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     *     The contributor
     */
    public String getContributor() {
        return contributor;
    }

    /**
     *
     * @param contributor
     *     The contributor
     */
    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    /**
     *
     * @return
     *     The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     *     The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return
     *     The contributorNote
     */
    public String getContributorNote() {
        return contributorNote;
    }

    /**
     *
     * @param contributorNote
     *     The contributor_note
     */
    public void setContributorNote(String contributorNote) {
        this.contributorNote = contributorNote;
    }

    /**
     *
     * @return
     *     The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     *     The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     *     The ageGroup
     */
    public String getAgeGroup() {
        return ageGroup;
    }

    /**
     *
     * @param ageGroup
     *     The age_group
     */
    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    /**
     *
     * @return
     *     The publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     *
     * @param publisher
     *     The publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     *
     * @return
     *     The isbns
     */
    public List<Isbn> getIsbns() {
        return isbns;
    }

    /**
     *
     * @param isbns
     *     The isbns
     */
    public void setIsbns(List<Isbn> isbns) {
        this.isbns = isbns;
    }

    /**
     *
     * @return
     *     The ranksHistory
     */
    public List<RanksHistory> getRanksHistory() {
        return ranksHistory;
    }

    /**
     *
     * @param ranksHistory
     *     The ranks_history
     */
    public void setRanksHistory(List<RanksHistory> ranksHistory) {
        this.ranksHistory = ranksHistory;
    }

    /**
     *
     * @return
     *     The reviews
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     *
     * @param reviews
     *     The reviews
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
