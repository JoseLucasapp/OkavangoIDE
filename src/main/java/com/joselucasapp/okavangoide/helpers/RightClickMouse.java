package com.joselucasapp.okavangoide.helpers;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.fxmisc.richtext.CodeArea;

public class RightClickMouse {
    public static ContextMenu createContextMenu(CodeArea control) {
        MenuItem copy = new MenuItem("Copiar");
        MenuItem paste = new MenuItem("Colar");

        copy.setOnAction(e -> {
            String selected = control.getSelectedText();
            if (!selected.isEmpty()) {
                ClipboardContent content = new ClipboardContent();
                content.putString(selected);
                Clipboard.getSystemClipboard().setContent(content);
            }
        });

        paste.setOnAction(e -> {
            String clipboardText = Clipboard.getSystemClipboard().getString();
            if (clipboardText != null) {
                control.insertText(control.getCaretPosition(), clipboardText);
            }
        });

        return new ContextMenu(copy, paste);
    }
}
