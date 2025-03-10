/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ImageConverter.java                                :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/09 01:21:51 by serghini          #+#    #+#             */
/*   Updated: 2025/03/10 18:03:18 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.printer.logic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;
import com.diogonunes.jcdp.color.api.Ansi.Attribute;

public class ImageConverter {
    private static BColor getJCDPColor(String color) {
        switch (color.toUpperCase()) {
            case "RED": return BColor.RED;
            case "GREEN": return BColor.GREEN;
            case "BLUE": return BColor.BLUE;
            case "YELLOW": return BColor.YELLOW;
            case "MAGENTA": return BColor.MAGENTA;
            case "CYAN": return BColor.CYAN;
            case "WHITE": return BColor.WHITE;
            case "BLACK": return BColor.BLACK;
            default: return BColor.NONE;
        }
    }
    
    public static void convertToASCII(BufferedImage image, String white, String black) {
        BColor whiteColor = getJCDPColor(white);
        BColor blackColor = getJCDPColor(black);
        
        ColoredPrinter cp = new ColoredPrinter.Builder(1, false)
            .build();
            
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixelColor = image.getRGB(x, y);
                // * Attributes: Control text styling (bold, underline, etc.)
                // * Foreground Color (FColor): The text color
                // * Background Color (BColor): The background color behind the text
                if (pixelColor == Color.WHITE.getRGB())
                    cp.print(" ", Attribute.NONE, FColor.NONE, whiteColor);
                else
                    cp.print(" ", Attribute.NONE, FColor.NONE, blackColor);
            }
            cp.println("\n", Attribute.NONE, FColor.NONE, BColor.NONE);
        }
    }
}