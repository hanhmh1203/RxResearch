
package com.example.fcs.rx_research.entity.history;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Isbn {

    @SerializedName("isbn10")
    @Expose
    private String isbn10;
    @SerializedName("isbn13")
    @Expose
    private String isbn13;

    /**
     * 
     * @return
     *     The isbn10
     */
    public String getIsbn10() {
        return isbn10;
    }

    /**
     * 
     * @param isbn10
     *     The isbn10
     */
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    /**
     * 
     * @return
     *     The isbn13
     */
    public String getIsbn13() {
        return isbn13;
    }

    /**
     * 
     * @param isbn13
     *     The isbn13
     */
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

}
