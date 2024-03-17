package Response;
/*
{
"StartDate":"18.03.2024","TimeSign":"0000","CurrencyCode":"036","CurrencyCodeL":"AUD","Units":1,"Amount":25.4701

"r030":36,"txt":"Австралійський долар","rate":25.4701,"cc":"AUD","exchangedate":"18.03.2024"
 }
 */
import lombok.Data;

@Data
public class NbuBank {
    private Long r030;
    private String txt;
    private Float rate;
    private String cc;
    private String AUD;
    private String exchangedate;
}
