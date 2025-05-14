package com.joselucasapp.okavangoide;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.*;
import java.util.function.IntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Editor {

    private static final Map<String, String> KEYWORDS = Map.of(
            "var", "keyword-system",
            "fct", "keyword-fct",
            "if", "keyword-system",
            "else", "keyword-system"
    );
    private static final Pattern PATTERN = Pattern.compile("\\b(" + String.join("|", KEYWORDS.keySet()) + ")\\b");

    public CodeArea start() {
        CodeArea editor = new CodeArea();

        editor.getStyleClass().add("code-area");
        editor.setEditable(true);
        editor.setWrapText(true);

        editor.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/highlight.css")).toExternalForm());

        IntFunction<Node> numberFactory = line -> {
            Label label = new Label(String.valueOf(line + 1));
            label.setStyle("-fx-text-fill: #6272a4; -fx-font-family: Fira code; -fx-font-size: 16px;");
            label.setMinWidth(35);
            label.setAlignment(Pos.CENTER_RIGHT);
            label.setPadding(new Insets(0, 10, 0, 0));
            return label;
        };

        editor.setParagraphGraphicFactory(numberFactory);

        editor.setStyleSpans(0, computeHighlighting(editor.getText()));
        editor.plainTextChanges().subscribe(change ->
                editor.setStyleSpans(0, computeHighlighting(editor.getText()))
        );

        return editor;
    }

    private StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        while (matcher.find()) {
            String word = matcher.group();
            spansBuilder.add(Collections.singleton("default-text"), matcher.start() - lastEnd);
            spansBuilder.add(Collections.singleton(KEYWORDS.get(word)), matcher.end() - matcher.start());
            lastEnd = matcher.end();
        }

        spansBuilder.add(Collections.singleton("default-text"), text.length() - lastEnd);
        return spansBuilder.create();
    }
}
