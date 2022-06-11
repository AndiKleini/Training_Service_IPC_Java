package com.meetingplanner.payload;

public class IsMeetingPossibleResponse {
    private boolean isPossible;

    public IsMeetingPossibleResponse(boolean isPossible) {
        this.isPossible = isPossible;
    }

    public boolean isPossible() {
        return isPossible;
    }
}
