package pl.chmielewski.LeavePlanner.Authentication.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

    public Cookie generateCookie(String name, String value, int exp) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(exp);
        cookie.setHttpOnly(false);
        return cookie;
    }

    public void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = generateCookie(name, value, maxAge);
        response.addCookie(cookie);
    }

    public Cookie createExpiredCookie(String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        return cookie;
    }

    public void removeCookie(HttpServletResponse response, String name) {
        Cookie cookie = createExpiredCookie(name);
        response.addCookie(cookie);
    }
}
