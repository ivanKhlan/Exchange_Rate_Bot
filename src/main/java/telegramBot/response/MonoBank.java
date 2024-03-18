package telegramBot.response;

/*
{
"currencyCodeA": 840,
"currencyCodeB": 980,
"date": 1552392228,
"rateSell": 27,
"rateBuy": 27.2,
"rateCross": 27.1
}
Коди валют:
USD	840	Долар США
EUR	978	Євро
 */


import lombok.Data;
@Data
public class MonoBank {
    private int currencyCodeA;
    private int currencyCodeB;
    private Integer date;
    private Float rateSell;
    private Float rateBuy;
    private Float rateCross;

}
