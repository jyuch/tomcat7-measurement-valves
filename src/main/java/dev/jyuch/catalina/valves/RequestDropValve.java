package dev.jyuch.catalina.valves;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Random;

public class RequestDropValve extends ValveBase {

    private final Random random;

    private String rate;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public RequestDropValve() {
        super(true);
        random = new Random();
    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        if (random.nextDouble() < Double.parseDouble(rate)) {
            response.setStatus(500);
        } else {
            getNext().invoke(request, response);
        }
    }
}
