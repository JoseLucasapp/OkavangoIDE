import { useEffect, useState } from "react";
import { ListFiles } from "../../../wailsjs/go/main/App";

interface FileInfo {
  name: string;
  isDir: boolean;
}

const Sidebar = ({ onFileClick }: { onFileClick: (name: string) => void }) => {
  const [files, setFiles] = useState<FileInfo[]>([]);

  useEffect(() => {
    ListFiles("./").then(setFiles);
  }, []);

  return (
    <div style={{ width: "15vw", backgroundColor: "#21222C", color: "#F8F8F2", display: "flex", flexDirection: "column", alignItems: "start", justifyContent: "start" }}>
      <ul style={{ listStyle: "none", padding: 0, display: "flex", flexDirection: "column", alignItems: "start", width: "100%", justifyContent: "start" }}>
        {files.map((file) => (
          <li
            key={file.name}
            style={{ cursor: "pointer", margin: "3px 0", width: "100%", textAlign: "start" }}
            onClick={() => onFileClick(file.name)}
          >
            {file.isDir ? `📁 ${file.name}` : `📄 ${file.name}`}
          </li>
        ))}
      </ul>
    </div>
  );
};


export default Sidebar;