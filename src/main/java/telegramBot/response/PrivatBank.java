package telegramBot.response;

/*
{"ccy":"EUR","base_ccy":"UAH","buy":"41.65000","sale":"42.65000"}
 */
import lombok.Data;

@Data
public class PrivatBank {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;

}
