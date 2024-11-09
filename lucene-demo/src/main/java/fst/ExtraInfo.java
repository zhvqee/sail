package fst;


public class ExtraInfo {
    private String desc;
    private int value;

    public static ExtraInfo of(String desc, int value) {
        return new ExtraInfo(desc, value);
    }

    private ExtraInfo(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
