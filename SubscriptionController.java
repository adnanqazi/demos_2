package com.aq.mcorpdemo.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import static com.aq.mcorpdemo.WebUtil.truncate;

@Controller
public class SubscriptionController {
    private static final Logger LOG = Logger.getAnonymousLogger();

    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    public String subscribe(HttpServletRequest request) {

        String orderDetails = getOrderDetails(request);
        String truncatedOrderDetails = truncate(orderDetails, 250);
        log(truncatedOrderDetails);
        return "orderDetail";

    }

    private void log(String message) {
        LOG.info(message);
    }

    private String getOrderDetails(HttpServletRequest request) {
        int times = ThreadLocalRandom.current().nextInt(250, 255);
        return StringUtils.repeat("A", times);
    }
}
