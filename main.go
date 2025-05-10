package main

import (
	"embed"
	"io/ioutil"
	"log"

	"github.com/wailsapp/wails/v2"
	"github.com/wailsapp/wails/v2/pkg/options"
	"github.com/wailsapp/wails/v2/pkg/options/assetserver"
)

//go:embed all:frontend/dist
var assets embed.FS

func main() {
	// Create an instance of the app structure
	app := NewApp()

	// Create application with options
	err := wails.Run(&options.App{
		Title:  "OkavangoIDE",
		Width:  1024,
		Height: 768,
		AssetServer: &assetserver.Options{
			Assets: assets,
		},
		BackgroundColour: &options.RGBA{R: 27, G: 38, B: 54, A: 1},
		OnStartup:        app.startup,
		Bind: []interface{}{
			app,
		},
	})

	if err != nil {
		println("Error:", err.Error())
	}
}

type FileInfo struct {
	Name  string `json:"name"`
	IsDir bool   `json:"isDir"`
}

func (a *App) ListFiles(path string) []FileInfo {
	files, err := ioutil.ReadDir(path)
	if err != nil {
		log.Println("Erro ao listar arquivos:", err)
		return nil
	}

	var result []FileInfo
	for _, f := range files {
		result = append(result, FileInfo{
			Name:  f.Name(),
			IsDir: f.IsDir(),
		})
	}

	return result
}

func (a *App) ReadFile(path string) string {
	data, err := ioutil.ReadFile(path)
	if err != nil {
		log.Println("Erro ao ler arquivo:", err)
		return ""
	}
	return string(data)
}
