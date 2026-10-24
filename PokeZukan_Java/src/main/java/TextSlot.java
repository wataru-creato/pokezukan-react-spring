import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TextSlot {
    public String flavor_text;
    public LanguageInfo language;
    public VersionInfo version;

    public TextSlot() {
    }
}
