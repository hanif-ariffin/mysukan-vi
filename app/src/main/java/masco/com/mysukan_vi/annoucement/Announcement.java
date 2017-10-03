package masco.com.mysukan_vi.annoucement;

import android.support.annotation.NonNull;

/**
 * Created by Haziq on 2017-10-02.
 */

public class Announcement implements Comparable<Announcement>{

    public Announcement(){}

    public Announcement(String subject, String message, Long time){
        this.subject = subject;
        this.message = message;
        this.time = time;
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

    String subject, message;
    Long time;

    @Override
    public int compareTo(@NonNull Announcement announcement) {
        if(time < announcement.time){
            return 1;
        } else if (time == announcement.time){
            return 0;
        } else {
            return -1;
        }
    }
}