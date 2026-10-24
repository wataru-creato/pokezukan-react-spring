import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
//ステータスをまとめる場所
//StatSlotから受け取ったものをわかりやすい入れ物にいれる
@JsonIgnoreProperties(ignoreUnknown = true)
class PokemonStats {
    public int hp;
    public int attack;
    public int defense;

    @JsonProperty("special-attack")
    public int specialAttack;
    @JsonProperty("special-defense")
    public int specialDefense;
    public int speed;

    public PokemonStats(ArrayList<StatSlot> stats) {
        for (int x = 0; x < stats.size(); x++) {
            switch (stats.get(x).stat.name) {
                case "hp":
                    this.hp = stats.get(x).base_stat;
                    break;
                case "attack":
                    this.attack = stats.get(x).base_stat;
                    break;
                case "defense":
                    this.defense = stats.get(x).base_stat;
                    break;
                case "special-attack":
                    this.specialAttack = stats.get(x).base_stat;
                    break;
                case "special-defense":
                    this.specialDefense = stats.get(x).base_stat;
                    break;
                case "speed":
                    this.speed = stats.get(x).base_stat;
                    break;

            }
        }
    }

    public PokemonStats() {
    }

    public String printAllStats() {
        return "H:" + hp + " A:" + attack + " B:" + defense + " C:" + specialAttack + " D:" + specialDefense + " S:" + speed;
    }
}
