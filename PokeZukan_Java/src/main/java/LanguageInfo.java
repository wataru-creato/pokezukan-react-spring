import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//どの言語なのかを持ってくる
@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguageInfo {
    public String name;

    public LanguageInfo() {
    }
}
