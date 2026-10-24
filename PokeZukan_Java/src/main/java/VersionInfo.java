import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VersionInfo {
    public String name; // 例: "scarlet", "violet", "sword", "shield" など
    public VersionInfo() {}
}