package com.example.fmli_app;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Simple {
    public static String getDateCurrentTimeZone(long timestamp) {
        try {
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getDefault();
            calendar.setTimeInMillis(timestamp * 1000);
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date currenTimeZone = calendar.getTime();
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
        }
        return "";
    }

    public static String Nid(long id) {
        return "N" + Encode(id);
    }

    public static String Cid(long id) {
        return "C" + Encode(id);
    }

    public static String Encode(long id) {
        String[] _alph = "0123456789abcdefgjhklmonpqrstyuvwxyz".split("");
        String new_id = "";

        while (id != 0) {
            new_id = _alph[(int) id % _alph.length] + new_id;
            id /= _alph.length;
        }

        return new_id;
    }
}
