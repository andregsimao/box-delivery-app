package com.box.delivery.app;

import com.box.delivery.app.config.HibernateUtil;
import com.box.delivery.app.menu.BoxDeliveryMenu;

public class Application {

    public static void main(String[] args) {
        HibernateUtil.init();
        BoxDeliveryMenu menu = new BoxDeliveryMenu();
        menu.run();
    }
}

