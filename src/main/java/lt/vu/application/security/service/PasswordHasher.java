package lt.vu.application.security.service;

import org.apache.commons.codec.digest.DigestUtils;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PasswordHasher {

    public String hash(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
