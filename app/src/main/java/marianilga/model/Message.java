package marianilga.model;

import java.util.Date;

/**
 * Created by mnilg_000 on 6/29/2015.
 */
public class Message {

    String textMessage;
    Contact fromId;
    Date dateMessage;

    public String getTextMessage() {
        return textMessage;
    }

    public Contact getFromId() {
        return fromId;
    }

    public Date getDate() {
        return dateMessage;
    }

    public void setTextMessage(String mTextMessage) {
        this.textMessage = mTextMessage;
    }

    public void setFromId(Contact mFromId) {
        this.fromId = mFromId;
    }

    public void setDate(Date mDateMessage) {
        this.dateMessage = mDateMessage;
    }
}
