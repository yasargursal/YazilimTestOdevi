import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class OdevTesti {

    // ADIM 1: GET İsteği (Veri Okuma)
    @Test
    public void veriGetirmeTesti() {
        System.out.println("=== GET Testi Başlıyor ===");

        // Bu adres bize hazır bir "yapılacak iş" (todo) verisi verir
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/1")
                .then()
                .statusCode(200) // Başarılı mı?
                .body("title", notNullValue()) // Başlık gelmiş mi?
                .body("completed", equalTo(false)) // Tamamlanmadı olarak mı işaretli?
                .time(lessThan(2000L)) // 2 saniyeden hızlı mı?
                .log().body(); // Gelen cevabı ekrana yaz
    }

    // ADIM 2: POST İsteği (Veri Gönderme)
    @Test
    public void veriGondermeTesti() {
        System.out.println("=== POST Testi Başlıyor ===");

        // Göndereceğimiz örnek veri
        String yeniVeri = "{\n" +
                "  \"title\": \"Odevimi yapiyorum\",\n" +
                "  \"body\": \"Yazilim test muhendisligi cok zevkli\",\n" +
                "  \"userId\": 1\n" +
                "}";

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(yeniVeri)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201) // 201: Oluşturuldu (Created) kodu
                .body("title", equalTo("Odevimi yapiyorum")) // Gönderdiğimiz başlık geri geldi mi?
                .log().body(); // Cevabı görelim
    }
}

//Yorum satırı ekledim