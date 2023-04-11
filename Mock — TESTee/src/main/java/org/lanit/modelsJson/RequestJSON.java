package org.lanit.modelsJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestJSON{

    @JsonProperty("info")
    private Info info;
    @JsonProperty("add")
    private Add add;
    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("lastUpdate")
    private String lastUpdate;


    public void setAdd(Add add){
        this.add = add;
    }

    public Add getAdd(){
        return add;
    }

    public void setLastUpdate(String lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdate(){
        return lastUpdate;
    }

    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    public String getUuid(){
        return uuid;
    }

    public void setInfo(Info info){
        this.info = info;
    }

    public  Info getInfo(){
        return info;
    }

    public RequestJSON(Info info, Add add, String uuid, String lastUpdate) {
        this.info = info;
        this.add = add;
        this.uuid = uuid;
        this.lastUpdate = lastUpdate;
    }

    public RequestJSON() {
    }

    @Override
    public String toString() {
        return "RequestJSON{" +
                "info=" + info +
                ", add=" + add +
                ", uuid='" + uuid + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}