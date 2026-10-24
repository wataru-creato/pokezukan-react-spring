import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//言語と名前のセットを持つ
@JsonIgnoreProperties(ignoreUnknown = true)
public class NameEntry {
    public String name;
    public LanguageInfo language;

    public NameEntry() {
    }
}