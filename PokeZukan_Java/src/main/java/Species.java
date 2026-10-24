import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.ArrayList;

//ポケモンの名前のリストを持っている
@JsonIgnoreProperties(ignoreUnknown = true)
class Species {
    public ArrayList<NameEntry> names;

    @JsonProperty("flavor_text_entries")
    public ArrayList<TextSlot> flavorTextEntries;

    public Generation generation;

    public Species() {
    }
}