package com.example.demo.controller;

import io.vertx.core.json.JsonObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://somi.cl:8497")
@RestController
@RequestMapping("/")
public class LoginResource {
    private String urlClaveUnica = "https://accounts.claveunica.gob.cl/";
    // Estas variables contienen los datos de autenticacion del aplicativo con clave
    // unica
    private String clientId = "0d7fbe19b5214363a11f4ed9ee5d6abc"; // Cliente Id entregado por claveunica
    private String clientSecret = "lol"; // Client Secret entregado por claveunica
    private String redirectUri = "https%3A%2F%2Fwww.somi.cl"; // URL a la cual se debe redirigir cuando la
    // autenticacion es exitosa
    private String CSRFToken = "bf30524d59cb4ceaa628c025511e7247"; // Token autogenerado por la institucion
    // https://accounts.claveunica.gob.cl/openid/authorize/?client_id=0d7fbe19b5214363a11f4ed9ee5d6abc&response_type=code&scope=openid%20run%20name&redirect_uri=https%3A%2F%2Fwww.somi.cl&state=123123123

    @GetMapping("/login")
    public void handlerRaiz(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = urlClaveUnica + "openid/authorize" +
                "?client_id=" + clientId +
                "&response_type=code" +
                "&scope=openid%20run%20name" +
                "&redirect_uri=" + redirectUri +
                "&state=" + CSRFToken;

        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write("<script>window.location = \"" + uri + "\"</script>");
    }

    @GetMapping("/")
    public void handlerCallback(HttpServletRequest request, HttpServletResponse response) {
        String[] codex = request.getParameterValues("code");
        String[] statex = request.getParameterValues("state");
        String codigoObtenido = "";
        String stateObtenido = "";

        System.out.println("============= Callback de clave unica =============");
        for (String code : codex) {
            System.out.println("Code: " + code);
            codigoObtenido = code;
        }
        for (String state : statex) {
            System.out.println("State: " + state);
            stateObtenido = state;
        }
        System.out.println("============= Fin Callback de clave unica =============");

        if (CSRFToken.equals(stateObtenido)) {
            String accessToken = "";
            String datos = "";
            try {
                accessToken = obtieneAccessToken(codigoObtenido);
                datos = obtieneDatos(accessToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setContentType("text/html");
            try {
                PrintWriter out = response.getWriter();
                out.println("<h1>Enhorabuena habeis logrado acceder a clave unica</h1><br/><br/>" + datos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.setContentType("text/html");
            try {
                PrintWriter out = response.getWriter();
                out.println("<h1>Token CSRF no corresponde!!!</h1>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String obtieneAccessToken(String code) throws Exception {

        System.out.println("Llamado POST para obtener token...");
        String query = "client_id=" + URLEncoder.encode(clientId, "UTF-8");
        query += "&";
        query += "client_secret=" + URLEncoder.encode(clientSecret, "UTF-8");
        query += "&";
        query += "redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8");
        query += "&";
        query += "grant_type=authorization_code";
        query += "&";
        query += "code=" + URLEncoder.encode(code, "UTF-8");
        query += "&";
        query += "state=" + URLEncoder.encode(CSRFToken, "UTF-8");

        URL myUrl = new URL(urlClaveUnica + "openid/token/");
        HttpsURLConnection con = (HttpsURLConnection) myUrl.openConnection();
        con.setRequestMethod("POST");
        System.out.println("URL Request: " + myUrl);
        System.out.println("Query: " + query);

        con.setRequestProperty("Content-length", String.valueOf(query.length()));
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
        con.setDoOutput(true);
        con.setDoInput(true);

        DataOutputStream output = new DataOutputStream(con.getOutputStream());
        output.write(query.getBytes("UTF-8"));
        output.flush();
        output.close();

        InputStream is;
        System.out.println("ResponseCode: " + con.getResponseCode());
        if (con.getResponseCode() >= 400) {
            is = con.getErrorStream();
        } else {
            is = con.getInputStream();
        }

        BufferedReader input = new BufferedReader(new InputStreamReader(is));
        String inputLine;
        StringBuilder response = new StringBuilder();
        for (inputLine = input.readLine(); inputLine != null; inputLine = input.readLine()) {
            response.append(inputLine);
        }
        input.close();
        System.out.println("" + response.toString());

        JsonObject json = new JsonObject(response.toString());
        return json.getString("access_token");
    }

    public String obtieneDatos(String accesstoken) throws Exception {

        System.out.println("httpsCallGetData...");
        URL myUrl = new URL(urlClaveUnica + "openid/userinfo/");
        HttpsURLConnection con = (HttpsURLConnection) myUrl.openConnection();
        con.setRequestMethod("POST");

        System.out.println("URL Request: " + myUrl);
        System.out.println("accesstoken: " + accesstoken);

        con.setRequestProperty("Authorization", "Bearer " + accesstoken);
        con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
        con.setDoInput(true);

        InputStream is;
        System.out.println("ResponseCode: " + con.getResponseCode());
        if (con.getResponseCode() >= 400) {
            is = con.getErrorStream();
        } else {
            is = con.getInputStream();
        }

        BufferedReader input = new BufferedReader(new InputStreamReader(is));
        String inputLine;
        StringBuilder response = new StringBuilder();
        for (inputLine = input.readLine(); inputLine != null; inputLine = input.readLine()) {
            response.append(inputLine);
        }
        input.close();
        System.out.println(response.toString());

        JsonObject json = new JsonObject(response.toString());
        return json.encodePrettily();
    }
}