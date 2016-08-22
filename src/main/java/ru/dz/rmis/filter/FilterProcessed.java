package ru.dz.rmis.filter;

import java.awt.*;

public class FilterProcessed implements Filter {


    @Override
    public Image apply(Image img) {
        //вписываемый текст
        String text = "Обработано";
        Graphics graphics = img.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
        graphics.drawString(text, 10, 25);
        return img;
    }
}
