<p align="center">
    <img alt="logo" src="./src/main/resources/images/logo.png" width="150">
</p>

# OKavangoIDE
### The official Zumbra IDE

<blockquote style="font-size: 16px; font-style: italic;">
  ⚙️ <em>ZUMBRA is a simple, fun, and expressive programming language made for learning, experimenting, and enjoying the beauty of code. OKavangoIDE or, for close friends, <strong>OKIDE</strong> was created to help you enjoy coding with Zumbra, featuring syntax highlighting, file management, and a smooth development experience.</em>
</blockquote>

## 🚀 Features
<ul style="font-size: 16px; line-height: 1.6;">
  <li>🎨 Real-time syntax highlighting for Zumbra</li>
  <li>📂 File explorer with in-editor editing</li>
  <li>💾 Save and manage your Zumbra scripts easily</li>
  <li>🧠 Simple, minimal UI for distraction-free coding</li>
  <li>☕ Powered by Java</li>
</ul>

<p style="font-size: 18px; font-weight: 500; margin-top: 20px;">💡 Tip:</p>
<p style="font-size: 16px;">Press <code>Ctrl + S</code> to quickly save your file, or right-click a folder to create a new Zumbra file!</p>

<p style="font-size: 18px; font-weight: 500; margin-top: 20px;">🎨 The current interface:</p>
<p align="center">
    <img alt="print" src="./src/main/resources/images/print1.png" width="500">
</p>

## 🚀 How to run?
<p style="font-size: 22px; font-weight: 600;">🐧 Running on Linux</p>

<p style="font-size: 16px; line-height: 1.6;">
  You can run <strong>OKavangoIDE</strong> on most Linux distributions. These instructions were tested on <strong>Arch Linux</strong>, but the process is similar for others such as Ubuntu, Debian, or Fedora.
</p>

<p style="font-size: 18px; font-weight: 500;">📦 Requirements</p>
<ul style="font-size: 16px; line-height: 1.6;">
  <li>Java Development Kit (JDK) 17 or later</li>
  <li>Apache Maven</li>
</ul>

<p style="font-size: 18px; font-weight: 500;">🛠️ Installation Steps</p>
<ol style="font-size: 16px; line-height: 1.8;">
  <li>
    <strong>Install dependencies</strong><br>
    <em>On Arch Linux:</em><br>
    <code>sudo pacman -Syu jdk-openjdk maven</code><br>
    <em>On Ubuntu/Debian:</em><br>
    <code>sudo apt update && sudo apt install openjdk-17-jdk maven</code>
  </li>
  <li>
    <strong>Clone the repository</strong><br>
    <code>git clone https://github.com/JoseLucasapp/OkavangoIDE.git</code><br>
    <code>cd OkavangoIDE</code>
  </li>
  <li>
    <strong>Ensure you're using a JDK (not a JRE)</strong><br>
    <code>java -version</code><br>
    <code>javac -version</code><br>
    <em>On Arch, if needed:</em><br>
    <code>sudo archlinux-java set java-21-openjdk</code>
  </li>
  <li>
    <strong>Build the project</strong><br>
    <code>mvn clean install</code>
  </li>
  <li>
    <strong>Run the application</strong><br>
    <code>mvn exec:java -Dexec.mainClass="com.joselucasapp.okavangoide.Main"</code>
  </li>
</ol>

<br />

<p style="font-size: 22px; font-weight: 600;">🪟 Running on Windows</p>

<p style="font-size: 16px; line-height: 1.6;">
  <strong>OKavangoIDE</strong> also runs smoothly on Windows. Just follow the steps below to get everything set up.
</p>

<p style="font-size: 18px; font-weight: 500;">📦 Requirements</p>
<ul style="font-size: 16px; line-height: 1.6;">
  <li>Java Development Kit (JDK) 17 or later (JDK 21 recommended)</li>
  <li>Apache Maven</li>
  <li>Git</li>
</ul>

<p style="font-size: 18px; font-weight: 500;">🛠️ Installation Steps</p>
<ol style="font-size: 16px; line-height: 1.8;">
  <li>
    <strong>Install the JDK</strong><br>
    Download the JDK from Oracle or use a distribution like OpenJDK via <a href="https://adoptium.net/">https://adoptium.net/</a>.<br>
    After installation, set the <code>JAVA_HOME</code> environment variable and add the JDK <code>bin</code> folder to your system <code>Path</code>.
  </li>
  <li>
    <strong>Install Apache Maven</strong><br>
    Download it from <a href="https://maven.apache.org/download.cgi">https://maven.apache.org/download.cgi</a><br>
    Extract the files and add the Maven <code>bin</code> folder to your <code>Path</code> environment variable.
  </li>
  <li>
    <strong>Verify Java and Maven are working</strong><br>
    Open Command Prompt or PowerShell and run:<br>
    <code>java -version</code><br>
    <code>mvn -v</code>
  </li>
  <li>
    <strong>Clone the repository</strong><br>
    <code>git clone https://github.com/JoseLucasapp/OkavangoIDE.git</code><br>
    <code>cd OkavangoIDE</code>
  </li>
  <li>
    <strong>Build the project</strong><br>
    <code>mvn clean install</code>
  </li>
  <li>
    <strong>Run the application</strong><br>
    <code>mvn exec:java -Dexec.mainClass="com.joselucasapp.okavangoide.Main"</code>
  </li>
</ol>

<p style="font-size: 16px;">Tip: You can use Windows Terminal or Git Bash for a better CLI experience with Git and Maven.</p>

## 🤝 Contributing
<p style="font-size: 16px;">We welcome contributions! Please read our <a href="./CONTRIBUTING.md">Contributing Guide</a> and <a href="./CODE_OF_CONDUCT.md">Code of Conduct</a> before submitting pull requests.</p>

<p style="font-size: 12px; color: #888888; text-align: center; margin-top: 40px;">
  Built with ❤️ • project in progress 🚧
</p>

