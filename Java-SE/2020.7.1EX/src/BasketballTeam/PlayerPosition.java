package BasketballTeam;

public enum PlayerPosition {
    PG("控球后卫"),
    SG("得分后卫"),
    SF("小前锋"),
    C("中锋"),
    PF("大前锋"),

    ;

    private String positionInChinese;

    PlayerPosition(String positionInChinese) {
        this.positionInChinese = positionInChinese;
    }

    public String getPositionInChinese() {
        return positionInChinese;
    }
}
