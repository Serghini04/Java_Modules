/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Main.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 17:40:07 by serghini          #+#    #+#             */
/*   Updated: 2025/03/10 02:11:27 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.printer.app;

import fr._42.printer.logic.BMPReader;
import fr._42.printer.logic.ImageConverter;
import java.awt.image.BufferedImage;

public class Main {
	public static void main(String[] args) {
		if (args.length != 3 || args[0].length != 1 || args[1].length != 1) {
			System.err.println("Usage: java -cp target src.javafr._42.printer.app.Main <white-char> <black-char> <image-path>");
			System.exit(1);
		}

		char whiteChar = args[0].charAt(0);
		char blackChar = args[1].charAt(0);
		String imagePath = args[2];

		try {
			BufferedImage image = BMPReader.readBMP(imagePath);
			String asciiImage = ImageConverter.convertToASCII(image, whiteChar, blackChar);
			System.out.println(asciiImage);
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
