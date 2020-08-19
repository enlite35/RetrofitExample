package ru.vologda.retrofitexample;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
// сам веб сервачок
// при старте указываем порт
public class MyServer extends NanoHTTPD {

    public MyServer(int port)  {
        super(port);
        try {
            start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
// этот метод определяет что мы возвращаем
    // из сессии мы можем взять все параметры которые нам передали в запросе
    // uri - это путь
    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        Gson gson = new Gson();
        if(uri.equals("/login")) {
            Map<String,String> parms = session.getParms();
            if(parms.get("user")==null) {
                return newFixedLengthResponse("enter user");
            } else {
                return newFixedLengthResponse(gson.toJson("Hello "+parms.get("user")+" pass is "+parms.get("password")));
            }
        }

        return newFixedLengthResponse(uri);
    }
}
