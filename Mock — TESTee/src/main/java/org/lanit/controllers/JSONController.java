package org.lanit.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lanit.modelsJson.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.tinylog.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RestController
public class JSONController {

    @PostMapping("/json")
    public Object postResponse (@RequestParam String action, @RequestBody String requestBody) throws IOException {
        long startTime = System.currentTimeMillis();

        UUID uuid = UUID.randomUUID();
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String lastUpdate = dtFormatter.format(dt);

        ResponseJSON responseJSON = new ResponseJSON();

        switch (action) {
            case "add":
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                RequestJSON requestJSON = objectMapper.readValue(requestBody, RequestJSON.class);

                String id = requestJSON.getInfo().getUserID();

                List<TickersItem> tickers = requestJSON.getInfo().getTickers();

                TickersItem tickersItemINADD = new TickersItem();
                tickersItemINADD.setTicker(requestJSON.getAdd().getName());

                List<AlertsItem> alertsItem2 = new ArrayList<>();
                AlertsItem alertsItem1 = new AlertsItem();
                alertsItem1.setPercent(requestJSON.getAdd().getPercent());
                alertsItem1.setTimeframe(requestJSON.getAdd().getTimeFrame());
                alertsItem2.add(alertsItem1);
                tickersItemINADD.setAlerts(alertsItem2);

                int i = 0;
                for (TickersItem nameInTickerItem : tickers) {
                    if (nameInTickerItem.getTicker().equals(requestJSON.getAdd().getName())) {
                        nameInTickerItem.getAlerts().add(alertsItem1);
                        i++;
                    }
                }
                if (i == 0) {
                    tickers.add(tickersItemINADD);
                }

                Info responseinfo = new Info();
                responseinfo.setTickers(tickers);
                responseinfo.setUserID(requestJSON.getInfo().getUserID());

                responseJSON.setInfo(responseinfo);
                responseJSON.setUuid(uuid.toString());
                responseJSON.setLastUpdate(lastUpdate);

                break;
            case "delete":
                ObjectMapper objectMapperDEL = new ObjectMapper();
                objectMapperDEL.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                RequestJSONDEL requestJSONDEL = objectMapperDEL .readValue(requestBody, RequestJSONDEL.class);
                List<TickersItem> tickersDEL = requestJSONDEL.getInfo().getTickers();
                System.out.println("tickersDEL = " + tickersDEL);
                List<Delete> deletech = new ArrayList<>();

                for (TickersItem nameInTickerItem : tickersDEL) {
                    if (nameInTickerItem.getTicker().equals(requestJSONDEL.getDelete().getTickerName())) {
                        nameInTickerItem.getAlerts().remove(requestJSONDEL.getDelete().getAlertIndex());
                        System.out.println("fuck mock");
                    }
                }
                Info responseinfoDEL = new Info();
                responseinfoDEL.setTickers(tickersDEL);
                responseinfoDEL.setUserID(requestJSONDEL.getInfo().getUserID());

                responseJSON.setInfo(responseinfoDEL);
                responseJSON.setUuid(uuid.toString());
                responseJSON.setLastUpdate(lastUpdate);

                break;

            default:
                return ResponseEntity.badRequest().header("content-type", "application/json").body(String.format("{\"message\": \"Передан некорректный action - %s\", \"status\": \"400\"}", action));
        }

        Logger.info(String.format("Заглушка отработала за %s мс. ID клиента - %s. UUID ответа - %s.", System.currentTimeMillis() - startTime, responseJSON.getInfo().getUserID(), responseJSON.getUuid()));

        return ResponseEntity.ok().header("content-type", "application/json").body(responseJSON);
    }
}