
package com.example.fcs.rx_research.entity.names;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("list_name")
    @Expose
    private String listName;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("list_name_encoded")
    @Expose
    private String listNameEncoded;
    @SerializedName("oldest_published_date")
    @Expose
    private String oldestPublishedDate;
    @SerializedName("newest_published_date")
    @Expose
    private String newestPublishedDate;
    @SerializedName("updated")
    @Expose
    private String updated;

    /**
     * @return The listName
     */
    public String getListName() {
        return listName;
    }

    /**
     * @param listName The list_name
     */
    public void setListName(String listName) {
        this.listName = listName;
    }

    /**
     * @return The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName The display_name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return The listNameEncoded
     */
    public String getListNameEncoded() {
        return listNameEncoded;
    }

    /**
     * @param listNameEncoded The list_name_encoded
     */
    public void setListNameEncoded(String listNameEncoded) {
        this.listNameEncoded = listNameEncoded;
    }

    /**
     * @return The oldestPublishedDate
     */
    public String getOldestPublishedDate() {
        return oldestPublishedDate;
    }

    /**
     * @param oldestPublishedDate The oldest_published_date
     */
    public void setOldestPublishedDate(String oldestPublishedDate) {
        this.oldestPublishedDate = oldestPublishedDate;
    }

    /**
     * @return The newestPublishedDate
     */
    public String getNewestPublishedDate() {
        return newestPublishedDate;
    }

    /**
     * @param newestPublishedDate The newest_published_date
     */
    public void setNewestPublishedDate(String newestPublishedDate) {
        this.newestPublishedDate = newestPublishedDate;
    }

    /**
     * @return The updated
     */
    public String getUpdated() {
        return updated;
    }

    /**
     * @param updated The updated
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Result{" +
                "listName='" + listName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", listNameEncoded='" + listNameEncoded + '\'' +
                ", oldestPublishedDate='" + oldestPublishedDate + '\'' +
                ", newestPublishedDate='" + newestPublishedDate + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
