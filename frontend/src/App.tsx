import { useState } from "react";
import Sidebar from "./components/sidebar";
import Editor from "./components/editor";

function App() {
    const [code, setCode] = useState("// Selecione um arquivo");

    const openFile = async (fileName: string) => {
        const { ReadFile } = await import("../wailsjs/go/main/App");
        const content = await ReadFile(fileName);
        setCode(content);
    };

    return (
        <div style={{ display: "flex", height: "100vh", backgroundColor: "#282A36", alignItems: "start", justifyContent: "start" }}>
            <Sidebar onFileClick={openFile} />
            <div style={{ flex: 1, backgroundColor: "#282A36" }}>
                <Editor code={code} />
            </div>
        </div>
    );
}

export default App;
