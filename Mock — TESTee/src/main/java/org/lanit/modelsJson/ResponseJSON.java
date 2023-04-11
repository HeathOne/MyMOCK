package org.lanit.modelsJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseJSON {

    @JsonProperty("info")
    private Info info;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("lastUpdate")
    private String lastUpdate;

    public ResponseJSON(Info info, String uuid, String lastUpdate) {
        this.info = info;
        this.uuid = uuid;
        this.lastUpdate = lastUpdate;
    }

    public ResponseJSON() {
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}