/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   HtmlProcessor.java                                 :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/10 22:09:01 by meserghi          #+#    #+#             */
/*   Updated: 2025/04/13 05:26:15 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.processor;

import fr._42.annotations.HtmlForm;
import fr._42.annotations.HtmlInput;
import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes("fr._42.annotations.HtmlForm")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class HtmlProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            
            for (Element element : annotatedElements) {
                HtmlForm formAnnotation = element.getAnnotation(HtmlForm.class);
                try {
                    FileObject fileObject = processingEnv.getFiler().createResource(
                            StandardLocation.CLASS_OUTPUT,
                            "",
                            formAnnotation.fileName()
                    );
                    
                    try (Writer writer = fileObject.openWriter()) {
                        writer.write(generateForm(formAnnotation, element));
                    }
                } catch (IOException e) {
                    processingEnv.getMessager().printMessage(
                            Diagnostic.Kind.ERROR,
                            "Failed to create file: " + e.getMessage()
                    );
                }
            }
        }
        return true;
    }

    private String generateForm(HtmlForm form, Element element) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("<form action=\"%s\" method=\"%s\">\n", 
                form.action(), form.method()));
        
        for (Element enclosed : element.getEnclosedElements()) {
            HtmlInput input = enclosed.getAnnotation(HtmlInput.class);
            if (input == null) continue;
            
            builder.append(String.format(
                    "  <input type=\"%s\" name=\"%s\" placeholder=\"%s\">\n",
                    input.type(), input.name(), input.placeholder()
            ));
        }
        
        builder.append("  <input type=\"submit\" value=\"Send\">\n");
        builder.append("</form>");
        return builder.toString();
    }
}