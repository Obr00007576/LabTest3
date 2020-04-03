import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class WepApiTest {
    String key = "14E2F206F0BF02A0F2C7B4A4CAA38D07";

    @Test//Get the all of heroes in Dota2.
    public void test1() {
        var expectedMap = new HashMap<String, Object>();
        expectedMap.put("name","npc_dota_hero_antimage");
        expectedMap.put("id",1);
        var url = "https://api.steampowered.com/IEconDOTA2_205790/GetHeroes/v1";
        RestAssured.given().queryParam("key", key)
                .when()
                .get(url)
                .then()
                .body("result.heroes", Matchers.hasItem(expectedMap))
                .body("result.status", Matchers.is(200))
                .body("result.count", Matchers.is(115));
    }
    @Test
    public void test2()
    {
        var appid = 570;
        var url = "https://api.steampowered.com/ISteamUserStats/GetNumberOfCurrentPlayers/v1/";
        RestAssured.given().queryParam("key",key)
                .queryParam("appid",appid)
                .when()
                .get(url)
                .then()
                .body("response.player_count", Matchers.allOf(Matchers.greaterThan(0),Matchers.lessThan(100000000)))
                .body("response.result", Matchers.is(1));
    }
}