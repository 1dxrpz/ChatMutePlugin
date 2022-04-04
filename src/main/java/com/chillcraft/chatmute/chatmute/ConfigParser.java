package com.chillcraft.chatmute.chatmute;

public class ConfigParser {
    MuteDataModel model;
    public ConfigParser CreateParserProps(MuteDataModel data){
        this.model = data;
        return this;
    }
    public long AmountUnix() {
        String result = "";
        long unix = 0;
        long multiplier = 1;
        for (int i = 0; i < this.model.amount.length(); i++){
            if (Character.isLetter(this.model.amount.charAt(i))){
                multiplier = 1;
                if (this.model.amount.charAt(i) == 's')
                    multiplier = 1;
                if (this.model.amount.charAt(i) == 'm')
                    multiplier = 60;
                if (this.model.amount.charAt(i) == 'h')
                    multiplier = 60 * 60;
                if (this.model.amount.charAt(i) == 'd')
                    multiplier = 60 * 60 * 24;
                unix += Integer.parseInt(result) * multiplier;
                result = "";

            } else {
                result += this.model.amount.charAt(i);
            }
        }
        return unix;
    }
    public String AmountOffset(long offset) {
        String seconds = offset % 60 == 0 ? "" : offset % 60 + "s ";
        offset /= 60;
        String minutes = offset % 60 == 0 ? "" : offset % 60 + "m ";
        offset /= 60;
        String hours = offset % 60 == 0 ? "" : offset % 60 + "h ";
        offset /= 24;
        String days = offset % 24 == 0 ? "" : offset % 24 + "d ";
        return String.format(days + hours + minutes + seconds);
    }
    public String Parse(String v) {
        return v.replace("{player}", model.player)
                .replace("{executor}", model.executor)
                .replace("{amount}", model.amount)
                .replace("{expires}", model.expirationDate - model.currentDate < 0 ? "0" : AmountOffset(model.expirationDate - model.currentDate))
                .replace("{reason}", model.reason);
    }
}
