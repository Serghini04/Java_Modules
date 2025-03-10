/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   BMPReader.java                                     :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/09 01:12:37 by serghini          #+#    #+#             */
/*   Updated: 2025/03/09 03:32:35 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BMPReader {
    public static BufferedImage readBMP(String filePath) throws IOException {
        File file = new File(filePath);
        return ImageIO.read(file);
    }
}
