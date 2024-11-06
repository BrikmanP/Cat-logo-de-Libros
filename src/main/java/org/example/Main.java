package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.GutendexResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static final String BASE_URL = "https://gutendex.com/books/?";

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        JFrame frame = new JFrame("Catálogo de Libros");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JButton titleButton = new JButton("Buscar por Título");
        JButton genreButton = new JButton("Buscar por Género");
        JButton authorButton = new JButton("Buscar por Autor");
        JButton languageButton = new JButton("Buscar por Idioma");
        JButton formatButton = new JButton("Buscar por Formato");
        JButton exitButton = new JButton("Salir");

        panel.add(titleButton);
        panel.add(genreButton);
        panel.add(authorButton);
        panel.add(languageButton);
        panel.add(formatButton);
        panel.add(exitButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel, BorderLayout.WEST);

        titleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog(frame, "Ingrese el título del libro:");
                if (title != null && !title.trim().isEmpty()) {
                    String url = BASE_URL + "search=" + title;
                    String response = getApiResponse(restTemplate, url);
                    resultArea.setText(response);
                }
            }
        });

        genreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String genre = JOptionPane.showInputDialog(frame, "Ingrese el género (tema):");
                if (genre != null && !genre.trim().isEmpty()) {
                    String url = BASE_URL + "subjects=" + genre;
                    String response = getApiResponse(restTemplate, url);
                    resultArea.setText(response);
                }
            }
        });

        authorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String author = JOptionPane.showInputDialog(frame, "Ingrese el nombre del autor:");
                if (author != null && !author.trim().isEmpty()) {
                    String url = BASE_URL + "authors=" + author;
                    String response = getApiResponse(restTemplate, url);
                    resultArea.setText(response);
                }
            }
        });

        languageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String language = JOptionPane.showInputDialog(frame, "Ingrese el idioma (en, es, etc.):");
                if (language != null && !language.trim().isEmpty()) {
                    String url = BASE_URL + "languages=" + language;
                    String response = getApiResponse(restTemplate, url);
                    resultArea.setText(response);
                }
            }
        });

        formatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String format = JOptionPane.showInputDialog(frame, "Ingrese el formato (epub, html):");
                if (format != null && !format.trim().isEmpty()) {
                    String url = BASE_URL + "formats=" + format;
                    String response = getApiResponse(restTemplate, url);
                    resultArea.setText(response);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private static String getApiResponse(RestTemplate restTemplate, String url) {
        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonResponse = objectMapper.readValue(response, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonResponse);

        } catch (Exception e) {
            return "Error al obtener los datos de la API: " + e.getMessage();
        }
    }
}
