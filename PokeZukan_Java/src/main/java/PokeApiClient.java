import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.net.URL;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// APIを取得するメインの動き,最終的に出力する場所
public class PokeApiClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));


        try {
            HttpClient client = HttpClient.newHttpClient();


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + (num)))
                    .GET()
                    .build();

            HttpRequest requestJaNa = HttpRequest.newBuilder()
                    .uri(URI.create("https://pokeapi.co/api/v2/pokemon-species/" + (num)))
                    .GET()
                    .build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            HttpResponse<String> responseJaNa = client.send(requestJaNa, HttpResponse.BodyHandlers.ofString());


            ObjectMapper mapper = new ObjectMapper();
            Pokemon pokemon = mapper.readValue(response.body(), Pokemon.class);
            Species pokemonJaNa = mapper.readValue(responseJaNa.body(), Species.class);


            System.out.println("ステータスコード: " + response.statusCode());
            System.out.println("図鑑No." + pokemon.getId());

            //URLから世代の数を取得（url内にidの値として存在している）
            String url=pokemonJaNa.generation.url;
            String[] parts=url.split("/");
            String generationNumber = parts[parts.length - 1];

            //世代番号から登場世代の表示
            String series = switch (generationNumber) {
                case "1" -> "赤/緑";
                case "2" -> "金/銀";
                case "3" -> "ルビー/サファイヤ";
                case "4" -> "ダイヤモンド/パール";
                case "5" -> "ブラック/ホワイト";
                case "6" -> "X/Y";
                case "7" -> "サン/ムーン";
                case "8" -> "剣（ソード）/盾（シールド）";
                case "9" -> "スカーレット/ヴァイオレット";
                default -> "その他";
            };

            System.out.println("第"+generationNumber+"世代ポケモン"+"("+series+")");


            //日本語名を取得する（日本語名のAPIはspeciesのほう）
            String jaName = "";
            for (NameEntry entry : pokemonJaNa.names) {
                if (entry.language.name.equals("ja") || entry.language.name.equals("ja-Hrkt")) {
                    jaName = entry.name;
                    break;
                }
            }
            System.out.println("ポケモン名：" + jaName);

            //日本語でのタイプの表示、pokeAPIの英語から辞書の日本語へ変換
            //APIを2回たたくよりも辞書で英語名と一致したら日本語で返すようにした

            java.util.Map<String, String> typeDictionary = new java.util.HashMap<>();
            typeDictionary.put("normal", "ノーマル");
            typeDictionary.put("fire", "ほのお");
            typeDictionary.put("water", "みず");
            typeDictionary.put("grass", "くさ");
            typeDictionary.put("electric", "でんき");
            typeDictionary.put("ice", "こおり");
            typeDictionary.put("fighting", "かくとう");
            typeDictionary.put("poison", "どく");
            typeDictionary.put("ground", "じめん");
            typeDictionary.put("flying", "ひこう");
            typeDictionary.put("psychic", "エスパー");
            typeDictionary.put("bug", "むし");
            typeDictionary.put("rock", "いわ");
            typeDictionary.put("ghost", "ゴースト");
            typeDictionary.put("dragon", "ドラゴン");
            typeDictionary.put("dark", "あく");
            typeDictionary.put("steel", "はがね");
            typeDictionary.put("fairy", "フェアリー");

            String type1En = pokemon.getTypes().get(0).type.name;
            String type1Ja = typeDictionary.getOrDefault(type1En, type1En);

            //2つのタイプを持っているなら2タイプを、それ以外なら1タイプを表示する
            if (pokemon.getTypes().size() >= 2) {
                String type2En = pokemon.getTypes().get(1).type.name;
                String type2Ja = typeDictionary.getOrDefault(type2En, type2En);
                System.out.println("ポケモンタイプ: " + type1Ja + "," + type2Ja);
            } else {
                System.out.println("ポケモンタイプ: " + type1Ja);
            }

            PokemonStats pokemonStats = new PokemonStats(pokemon.stats);
            System.out.println("ポケモンステータス: " + pokemonStats.printAllStats());

            System.out.println("高さ：" + pokemon.getHeight() / 10.0 + "m");
            System.out.println("重さ：" + pokemon.getWeight() / 10.0 + "kg");


            //説明テキストを表示する

            String scarletText="";
            String violetText = "";
            String latestFlavorText="";


            if (pokemonJaNa.flavorTextEntries != null) {
                for (TextSlot textEntry : pokemonJaNa.flavorTextEntries) {

                    boolean isJapanese = textEntry.language != null &&
                            (textEntry.language.name.equals("ja") || textEntry.language.name.equals("ja-Hrkt"));



                    if (isJapanese && textEntry.version != null) {
                        // スカーレットのとき
                        if (textEntry.version.name.equals("scarlet")) {
                            scarletText = textEntry.flavor_text;

                        }
                        // バイオレットのとき
                        if (textEntry.version.name.equals("violet")) {
                            violetText = textEntry.flavor_text;
                        }
                            latestFlavorText = textEntry.flavor_text;

                    }

                    if (scarletText.isEmpty()) {
                        scarletText = "情報なし";
                    }

                    if (violetText.isEmpty()) {
                        violetText = "情報なし";
                    }

                    if (latestFlavorText.isEmpty()) {
                        latestFlavorText = "情報なし";
                    }


                }
            }

            scarletText = scarletText.replace("\n", " ").replace("\f", " ");
            violetText = violetText.replace("\n", " ").replace("\f", " ");
            latestFlavorText = latestFlavorText.replace("\n", " ").replace("\f", " ");

            System.out.println("【スカーレット説明文】：" + scarletText);
            System.out.println("【バイオレット説明文】：" + violetText);
            System.out.println("【テキスト説明】：" + latestFlavorText);


        } catch (IOException |
                 InterruptedException e) {
            System.out.println("エラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }

    }
}