/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Main.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 17:40:07 by serghini          #+#    #+#             */
/*   Updated: 2025/03/10 17:58:32 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.printer.app;

import fr._42.printer.logic.BMPReader;
import fr._42.printer.logic.ImageConverter;
import java.awt.image.BufferedImage;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static class Args {
        @Parameter
        private List<String> parameters = new ArrayList<>();
        
        public String getWhite() throws Exception {
            for (String arg : parameters) {
                if (arg.startsWith("--white=") && !arg.split("=")[1].isEmpty())
                    return arg.split("=")[1];
            }
            throw new Exception("invalid arguments: --white parameter required");
        }
        
        public String getBlack() throws Exception {
            for (String arg : parameters) {
                if (arg.startsWith("--black=") && !arg.split("=")[1].isEmpty())
                    return arg.split("=")[1];
            }
            throw new Exception("invalid arguments: --black parameter required");
        }
    }
    
    public static void main(String[] args) {
        try {
            Args arguments = new Args();
            JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);
            BufferedImage image = BMPReader.readBMP("target/resources/it.bmp");
            ImageConverter.convertToASCII(image, arguments.getWhite(), arguments.getBlack());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}