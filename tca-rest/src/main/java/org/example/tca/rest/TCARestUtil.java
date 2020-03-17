package org.example.tca.rest;

import org.example.tca.response.Level;
import org.example.tca.response.Status;
import org.example.tca.response.TCAResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TCARestUtil {

    public static Response printPassResponse(String message) {
        return Response
                .ok()
                .entity(new TCAResponse(Status.Pass, message))
                .build();
    }

    public static Response printFailResponse(String message, Exception e) {
        String level = e.getClass().getSimpleName();
        TCAResponse response = new TCAResponse(Status.Fail, message + ". " + e.getMessage(), Level.from(level));
        return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(response)
                .build();
    }

    public static String printPath(String name, String family) {
        return "model(" + name + ", " + family + ")"; // mode;
    }

    public static String printPath(String name, String family, String objectType, String tcaLabel) {
        return printPath(name, family) + "threshold(" + objectType + ", " + tcaLabel + ")"; // threshold
    }

    public static String printPath(String name, String family, String objectType, String tcaLabel, long ruleId) {
        return printPath(name, family, objectType, tcaLabel) + "rule(" + ruleId + ")"; // rule
    }

    public static String printPath(String name, String family, String objectType, String tcaLabel, long ruleId, long alarmId) {
        return printPath(name, family, objectType, tcaLabel, ruleId) + "alarm(" + alarmId + ")"; // alarm
    }

    public static String printPath(String name, String family, String objectType, String tcaLabel, long ruleId, String attributeName) {
        return printPath(name, family, objectType, tcaLabel, ruleId) + "condition(" + attributeName + ")"; // condition
    }

}
