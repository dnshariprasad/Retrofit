package hari.retrofit;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kvanadev5 on 09/01/16.
 */
public class Document {
    private String docId;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("gender")
    private String gender;
    @Expose
    @SerializedName("geo")
    private geo geo;

    public Document.geo getGeo() {
        return geo;
    }

    public void setGeo(Document.geo geo) {
        this.geo = geo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public static Document toPojo(String json) {
        return new Gson().fromJson(json, Document.class);
    }

    public String toJson() {
        return new Gson().toJson(this, Document.class);
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    class geo {
        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

        @Expose
        @SerializedName("lat")
        private Double lat;

        @Expose
        @SerializedName("lon")
        private Double lon;


    }
}
