package id.web.saka.report.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Component
public final class Util {

    @Autowired
    private Env env;

    private static Env envStatic;

    @PostConstruct
    private void initStaticEnv() {
        envStatic = env;
    }

    public static String buildGineeSignatureErigo(String brand, HttpMethod httpMethod, String requestUri) {
        Base64.Encoder base64Encoder = Base64.getEncoder();
        String signatureHeaderInfo = httpMethod + "$" + requestUri + "$";
        String signature;
        String secretKey = brand.equals("POLKA")?envStatic.getSecretKeyPolka():envStatic.getSecretKeyErigo();
        String accessKey = brand.equals("POLKA")?envStatic.getAccessKeypolka():envStatic.getAccessKeyErigo();
        try {
            PBEKeySpec keySpec = new PBEKeySpec(secretKey.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey key = keyFactory.generateSecret(keySpec);
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(key);
            signature = base64Encoder.encodeToString(mac.doFinal(signatureHeaderInfo.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException var6) {
            throw new RuntimeException(var6);
        }

        return accessKey+":"+signature;

    }

    public static String buildTokenSignatureSAP() {
        ObjectMapper objectMapper; objectMapper = new ObjectMapper();
        RestTemplate restTemplate; restTemplate = new RestTemplate();
        HttpHeaders headers; headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        JSONObject orderJsonObject = new JSONObject();
        orderJsonObject.put("username", envStatic.getSapApiKeyUsername());
        orderJsonObject.put("password", envStatic.getSapApiKeyPassword());

        HttpEntity<String> request = new HttpEntity<String>(orderJsonObject.toString(), headers);
        String orderResultAsJsonStr = restTemplate.postForObject("http://"+envStatic.getSapApiKeyIp()+"/api/token", request, String.class);

        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(orderResultAsJsonStr);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return jsonNode.get("token").asText();
    }

    public static int getTotalPage(int totalCount, int totalPerPage) {
        int totalPage = totalCount / totalPerPage;
        int totalModulo = totalCount % totalPerPage;

        totalPage = totalModulo > 0 ?  totalPage+1:totalPage;

        return totalPage;
    }

    public static Date addHoursToDate(String date, int hours) {
        Instant actualValue = Instant.parse(date);

        actualValue = actualValue.plus(hours, ChronoUnit.HOURS);

        return Date.from(actualValue);
    }

    public static String arrayJsonNodetoString(JsonNode arrNode) {
        String jsonText = "";

        if (arrNode.isArray()) {
            for (final JsonNode objNode : arrNode) {
                jsonText += objNode + ",";
            }

            jsonText = jsonText.substring(0, jsonText.length() - 1);
        } else {
            jsonText = arrNode.asText();
        }

        return jsonText;
    }

    public static String getNextAlphabetSequence(String current) {
        // Convert the string into a character array for manipulation
        char[] chars = current.toCharArray();

        // Start from the last character and work backwards
        int i = chars.length - 1;

        // Iterate and increment the characters as needed
        while (i >= 0) {
            // If the character is 'Z', reset it to 'A' and move left
            if (chars[i] == 'Z') {
                chars[i] = 'A';
                i--;
            } else {
                // Increment the character by 1
                chars[i]++;
                break;
            }
        }

        // If all characters were 'Z' and now are 'A', we need to add another character at the beginning
        if (i < 0) {
            // The result will have one more character at the start (e.g., "ZZ" -> "AAA")
            return "A" + new String(chars);
        }

        // Return the next sequence
        return new String(chars);
    }

}
