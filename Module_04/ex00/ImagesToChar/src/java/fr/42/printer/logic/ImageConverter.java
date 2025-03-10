/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ImageConverter.java                                :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/09 01:21:51 by serghini          #+#    #+#             */
/*   Updated: 2025/03/09 03:39:32 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.printer.logic;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageConverter {
    public static String convertToASCII(BufferedImage image, char whiteChar, char blackChar) {
        StringBuilder asciiArt = new StringBuilder();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixelColor = image.getRGB(x, y);
                Color color = new Color(pixelColor);

                // Check if the pixel is closer to white or black
                int brightness = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                if (brightness > 128) {
                    asciiArt.append(whiteChar); // White pixel
                } else {
                    asciiArt.append(blackChar); // Black pixel
                }
            }
            asciiArt.append("\n");
        }
        return asciiArt.toString();
    }
}
