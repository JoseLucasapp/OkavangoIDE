import { useEffect, useRef } from "react";
import * as monaco from "monaco-editor";
import './style.css';

interface EditorProps {
    code: string;
}

const Editor = ({ code }: EditorProps) => {
    const editorRef = useRef<HTMLDivElement>(null);
    const monacoRef = useRef<monaco.editor.IStandaloneCodeEditor>();

    useEffect(() => {
        if (editorRef.current && !monacoRef.current) {
            monacoRef.current = monaco.editor.create(editorRef.current, {
                value: code,
                language: "javascript",
                theme: "dracula",
                fontSize: 16,
                automaticLayout: true,
            });
        }
    }, []);

    // Atualiza o conteúdo se o `code` mudar
    useEffect(() => {
        if (monacoRef.current && monacoRef.current.getValue() !== code) {
            monacoRef.current.setValue(code);
        }
    }, [code]);

    return (
        <div
            ref={editorRef}
            className="editor-main"
        />
    );
};

export default Editor;
