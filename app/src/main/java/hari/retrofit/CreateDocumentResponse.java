package hari.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateDocumentResponse {

    @SerializedName("document_id")
    @Expose
    private String documentId;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;

    /**
     * @return The documentId
     */
    public String getDocumentId() {
        return documentId;
    }

    /**
     * @param documentId The document_id
     */
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    /**
     * @return The result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result The result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return The transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId The transaction_id
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
