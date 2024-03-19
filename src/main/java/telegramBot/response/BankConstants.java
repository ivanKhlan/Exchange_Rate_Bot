package telegramBot.response;
import lombok.experimental.UtilityClass;
@UtilityClass
public class BankConstants {
    static final String MONO_API = "https://api.monobank.ua/bank/currency";
    static final String NBU_API = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    static final String PRIVAT_API = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";
    static final int USD_CODE = 840;
    static final int EUR_CODE = 978;
    static final String PATTERN_DECIMAL_FORMAT_1 = "#0.0";
    static final String PATTERN_DECIMAL_FORMAT_2 = "#0.00";
    static final String PATTERN_DECIMAL_FORMAT_3 = "#0.000";
    static final String PATTERN_DECIMAL_FORMAT_4 = "#0.0000";
}
