package id.web.saka.report.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Env {

    @Value("${ginee.secret.key.erigo}")
    private String secretKeyErigo;

    @Value("${ginee.access.key.erigo}")
    private String accessKeyErigo;

    @Value("${ginee.secret.key.polka}")
    private String secretKeyPolka;

    @Value("${ginee.access.key.polka}")
    private String accessKeypolka;

    @Value("${erigo.secret.key}")
    private String erigoWebhookSecretKey;

    @Value("${polka.secret.key}")
    private String polkaWebhookSecretKey;

    @Value("${sap.api.key.username}")
    private String sapApiKeyUsername;

    @Value("${sap.api.key.password}")
    private String sapApiKeyPassword;

    @Value("${sap.api.key.ip}")
    private String sapApiKeyIp;

    public String getSecretKeyErigo() {
        return secretKeyErigo;
    }

    public void setSecretKeyErigo(String secretKeyErigo) {
        this.secretKeyErigo = secretKeyErigo;
    }

    public String getAccessKeyErigo() {
        return accessKeyErigo;
    }

    public void setAccessKeyErigo(String accessKeyErigo) {
        this.accessKeyErigo = accessKeyErigo;
    }

    public String getSecretKeyPolka() {
        return secretKeyPolka;
    }

    public void setSecretKeyPolka(String secretKeyPolka) {
        this.secretKeyPolka = secretKeyPolka;
    }

    public String getAccessKeypolka() {
        return accessKeypolka;
    }

    public void setAccessKeypolka(String accessKeypolka) {
        this.accessKeypolka = accessKeypolka;
    }

    public String getErigoWebhookSecretKey() {
        return erigoWebhookSecretKey;
    }

    public void setErigoWebhookSecretKey(String erigoWebhookSecretKey) {
        this.erigoWebhookSecretKey = erigoWebhookSecretKey;
    }

    public String getPolkaWebhookSecretKey() {
        return polkaWebhookSecretKey;
    }

    public void setPolkaWebhookSecretKey(String polkaWebhookSecretKey) {
        this.polkaWebhookSecretKey = polkaWebhookSecretKey;
    }

    public String getSapApiKeyUsername() {
        return sapApiKeyUsername;
    }

    public void setSapApiKeyUsername(String sapApiKeyUsername) {
        this.sapApiKeyUsername = sapApiKeyUsername;
    }

    public String getSapApiKeyPassword() {
        return sapApiKeyPassword;
    }

    public void setSapApiKeyPassword(String sapApiKeyPassword) {
        this.sapApiKeyPassword = sapApiKeyPassword;
    }

    public String getSapApiKeyIp() {
        return sapApiKeyIp;
    }

    public void setSapApiKeyIp(String sapApiKeyIp) {
        this.sapApiKeyIp = sapApiKeyIp;
    }
}
