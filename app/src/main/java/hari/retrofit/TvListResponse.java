package hari.retrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvListResponse {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public class Data {
        @SerializedName("full_document")
        @Expose
        private Boolean fullDocument;
        @SerializedName("items")
        @Expose
        private List<Item> items = new ArrayList<Item>();
        @SerializedName("page")
        @Expose
        private Integer page;
        @SerializedName("per_page")
        @Expose
        private Integer perPage;
        @SerializedName("total")
        @Expose
        private Integer total;

        public Boolean getFullDocument() {
            return fullDocument;
        }

        public void setFullDocument(Boolean fullDocument) {
            this.fullDocument = fullDocument;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }
    }

    public class Item {
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("document")
        @Expose
        private String document;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("modified_at")
        @Expose
        private String modifiedAt;
        @SerializedName("schema_id")
        @Expose
        private String schemaId;
        @SerializedName("vault_id")
        @Expose
        private String vaultId;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDocument() {
            return document;
        }

        public void setDocument(String document) {
            this.document = document;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getModifiedAt() {
            return modifiedAt;
        }

        public void setModifiedAt(String modifiedAt) {
            this.modifiedAt = modifiedAt;
        }

        public String getSchemaId() {
            return schemaId;
        }

        public void setSchemaId(String schemaId) {
            this.schemaId = schemaId;
        }

        public String getVaultId() {
            return vaultId;
        }

        public void setVaultId(String vaultId) {
            this.vaultId = vaultId;
        }
    }
}
