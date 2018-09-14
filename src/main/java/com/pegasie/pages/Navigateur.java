package com.pegasie.pages;

import com.pegasie.base.TestBase;

public class Navigateur extends TestBase {
    public void saisirUrl(String url) {
        driver.get(url);
    }
}
