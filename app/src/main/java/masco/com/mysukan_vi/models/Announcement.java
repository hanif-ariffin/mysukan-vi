package masco.com.mysukan_vi.models;

import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Announcement class is used in announcement.
 * It contain the id of the announcement, the subject (header), the message (text of the announcement) and the time the announcement was pushed to the database.
 * Announcement only supports plain text messages.
 * Created by Haziq on 2017-10-02.
 */

public class Announcement implements Comparable<Announcement> {

    String id, subject, message;
    Long time;

    public Announcement() {
    }

    public Announcement(String id, String subject, String message, Long time) {
        this.id = id;
        this.subject = subject;
        this.message = message;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public int compareTo(@NonNull Announcement announcement) {
        if (time < announcement.time) {
            return 1;
        } else if (time == announcement.time) {
            return 0;
        } else {
            return -1;
        }
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();

        // Announcement data
        map.put("id", id);
        map.put("subject", subject);
        map.put("message", message);
        map.put("time", time);

        return map;
    }
}