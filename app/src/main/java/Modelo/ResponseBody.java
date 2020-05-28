package Modelo;

import com.google.gson.annotations.SerializedName;

public class ResponseBody
{
    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;
    @SerializedName("response")
    private Object response;
    @SerializedName("success")
    private Boolean success;

    public String getCode() {
        return code;
    }

public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
