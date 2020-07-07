package com.plainid.assignment.dao;

/**
 * Created by Omer Dekel on 04/07/2020.
 * Battle results class.
 */
public class Battle {
    //status of the battle.
    private BattleStatusType status;
    //message description .
    private String message;

    /***
     * Message getter.
     * @return this message.
     */
    public String getMessage() {return message;}

    /**
     * Message setter.
     * @param message .
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * status getter.
     * @return battle's status
     */
    public BattleStatusType getStatus() {
        return status;
    }

    /***
     * status setter .
     * @param status .
     */
    public void setStatus(BattleStatusType status) {
        this.status = status;
    }


}
