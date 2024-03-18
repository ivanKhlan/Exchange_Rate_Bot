package telegramBot.currency;

public class Currency {
    private String code;
    private boolean selected;

    public Currency(String code, boolean selected) {
        this.code = code;
        this.selected = selected;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
