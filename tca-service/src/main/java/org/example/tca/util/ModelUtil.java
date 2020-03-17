package org.example.tca.util;

import org.example.tca.api.Model;
import org.example.tca.vo.ModelVO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ModelUtil {

    private static final String DATE_TIME_PATTERN = "dd-MMM-yyyy HH:mm";

    public static Model parseModelVOToEntity(ModelVO modelVO) throws Exception {
        if (modelVO == null) {
            throw new Exception("Invalid model info");
        }

        if (modelVO.getName() == null || modelVO.getName().isEmpty()
                || modelVO.getFamily() == null || modelVO.getFamily().isEmpty()
                || modelVO.getVersion() == null || modelVO.getVersion().isEmpty()) {
            throw new Exception("Model name, family and version are mandatory");
        }

        return new Model(modelVO.getName(), modelVO.getFamily(), modelVO.getVersion(), modelVO.getDescription(),
                modelVO.getBuild(), parseTime(modelVO.getDate()), modelVO.getAuthor(), new Date());
    }

    public static Date parseTime(String time) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(DATE_TIME_PATTERN);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.parse(time);
    }

    public static String parseTime(Date date) {
        DateFormat formatter = new SimpleDateFormat(DATE_TIME_PATTERN);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }

}
