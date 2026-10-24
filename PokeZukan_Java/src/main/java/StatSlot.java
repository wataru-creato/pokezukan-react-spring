import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ステータス情報に関する親のクラス、StatInfoが子供である
// PokeAPIから送られてきたJsonデータを受け取る場所
@JsonIgnoreProperties(ignoreUnknown = true)
class StatSlot {
    public int base_stat; // 数値
    public StatInfo stat; // 名前が入っているオブジェクト

    public StatSlot() {
    }
}