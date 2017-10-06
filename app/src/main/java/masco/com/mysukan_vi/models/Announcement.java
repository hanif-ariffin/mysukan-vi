package masco.com.mysukan_vi.models;

import android.support.annotation.NonNull;

/**
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
}